import org.osbot.rs07.api.Configs;
import org.osbot.rs07.api.Players;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class AttackingOgres extends Task {

	public AttackingOgres(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.cannonOgreaArea.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
		RS2Object cannon = script.getObjects().closest(6);
		RS2Object brokenCannon = script.getObjects().closest(14916);
		Item cannonBase = script.getInventory().getItem("Cannon base");

		Position cannonPosition = new Position(2528, 3371, 0);

		if (!cannonPosition.equals(script.myPlayer().getPosition())) {
			WalkingEvent wEvent = new WalkingEvent(cannonPosition);
			wEvent.setMinDistanceThreshold(0);
			script.execute(wEvent);
		}

		if (cannonPosition.equals(script.myPlayer().getPosition())) {
			script.log("in the cannon position");

			/*
			 * java.util.List<Player> players = script.getPlayers().getAll();
			 * java.util.List<Player> players1 = script.getPlayers().filter(p ->
			 * !p.equals(script.myPlayer())); //not my player
			 * 
			 * for (Player pa : players) { if
			 * (Areas.cannonOgreaArea.contains(pa) &&
			 * !pa.getName().equals(script.myPlayer().getName()) &&
			 * script.getInventory().contains("Cannon Base")) { //Weird why this
			 * is true. It's true, then goes false sec
			 * 
			 * script.log("players in area hopping"); if
			 * (script.getWorlds().hopToP2PWorld()){ Sleep.sleepUntil(()->
			 * Areas.cannonOgreaArea.contains(pa) &&
			 * pa.getName().equals(script.myPlayer().getName()) &&
			 * script.getInventory().contains("Cannon Base"), 10000); } } }
			 */

			// else {
			// if (script.getInventory().contains("Cannon Base")) { //u sure
			// this wont conflict with the above cuz they both can be true why
			// script.log("setting up cannon"); //try now, I have to go eat
			// dinner as well.I'll be back in 30 min or so np
			// if (cannonBase.interact("Set-up")) {
			// Sleep.sleepUntil(() -> cannon != null, 5000);
			// }
			//
			// }
			// }

			if (script.getPlayers().getAll().size() > 1 && script.getInventory().contains("Cannon Base")) {
				script.log("hopping");
				script.getWorlds().hopToP2PWorld();
			}

			else {
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
							
							if (boostedRangeLevel < (rangeLevel * 1.04)){
								script.log("time to drink potion");
							}
							
							
						}
					}
				}
			}
		}

		// if (Main.beenCrashed) {
		// if (script.getWorlds().hopToP2PWorld()) {
		// Main.beenCrashed = false;
		// }
		//
		// }

		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
