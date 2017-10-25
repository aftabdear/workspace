import org.osbot.rs07.api.Configs;
import org.osbot.rs07.api.Players;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class AttackingOgres extends Task {

	public AttackingOgres(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.cannonOgreaArea.contains(script.myPlayer().getPosition()) &&
				script.getEquipment().contains(StaticStrings.dart) &&
				(script.getInventory().contains("Ranging Potion(4)") || script.getInventory().contains("Ranging Potion(3)") || script.getInventory().contains("Ranging Potion(2)") || script.getInventory().contains("Ranging Potion(1)"))&&
				script.getInventory().contains("Cannonball") &&
				script.getInventory().contains("Ardougne teleport") &&
				script.getInventory().contains("Varrock teleport");
	}

	@Override
	public int execute() throws Exception {
		RS2Object cannon = script.getObjects().closest(6);
		RS2Object brokenCannon = script.getObjects().closest(14916);
		Item cannonBase = script.getInventory().getItem("Cannon base");
		Item potion = script.getInventory().getItem("Ranging potion(4)", "Ranging potion(3)", "Ranging potion(2)",
				"Ranging potion(1)");
		NPC ogre = script.getNpcs().closest(npc -> npc.getName().equals("Ogre") && npc.isAttackable());

		Position cannonPosition = new Position(2528, 3371, 0);

		if (!cannonPosition.equals(script.myPlayer().getPosition())) {
			WalkingEvent wEvent = new WalkingEvent(cannonPosition);
			wEvent.setMinDistanceThreshold(0);
			script.execute(wEvent);
		}

		if (cannonPosition.equals(script.myPlayer().getPosition())) {
			script.log("in the cannon position");

			if (script.getPlayers().getAll().size() > 1 && script.getInventory().contains("Cannon Base")) {
				script.log("hopping");
				script.getWorlds().hopToP2PWorld();
			}

			else {
				if (Main.beenCrashed) {
					 if (script.getWorlds().hopToP2PWorld()) {
					 Main.beenCrashed = false;
					 }
					
					 }
				
				script.log("only me in the area");
				if (cannonBase != null) {
					if (cannonBase.interact("Set-up")) {
						Sleep.sleepUntil(() -> cannon != null, 5000);
					}
				}

				if (script.getInventory().contains("Cannon Base")) {
					script.log("setting up cannon");
					if (cannonBase.interact("Set-up")) {
						Sleep.sleepUntil(() -> cannon != null, 5000);
					}

				}

				if (brokenCannon != null) {
					brokenCannon.interact("Repair");
				}

				if (cannon != null && cannon.isVisible()) {
					script.log("cannon is visible");
					if (cannon.getX() == 2527 && cannon.getY() == 3370) {

						if (script.getConfigs().get(1) == 0) {
							script.log("time to load the cannon");
							if (cannon.interact("Fire")) {
								Sleep.sleepUntil(() -> script.getConfigs().get(1) == 1048576, 5000);
							}
						}
						if (script.getConfigs().get(1) == 1048576) {
							script.log("we're firing at the ogres");
							int boostedRangeLevel = script.getSkills().getDynamic(Skill.RANGED);
							int rangeLevel = script.getSkills().getStatic(Skill.RANGED);

							if (boostedRangeLevel < (rangeLevel * 1.04)) {
								script.log("time to drink potion");
								if (potion != null) {
									potion.interact("Drink");
								}
							}

							if (script.myPlayer().getInteracting() == null) {
								script.log("Attacking Ogres");
								if (ogre != null && ogre.interact("Attack")) {

									new ConditionalSleep(20000) {
										@Override
										public boolean condition() {
											return script.getCombat().isFighting()
													|| script.myPlayer().getInteracting() != null;
										}
									}.sleep();
								}

							}
						}
					}
				}
			}
		}

		

		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
