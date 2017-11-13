
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class RimmingtonMine extends Task {

	public RimmingtonMine(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.rimmingtonMiningArea.contains(script.myPlayer().getPosition()) && !script.getInventory().isFull();
	}

	@Override
	public int execute() throws Exception {
		script.log(" in the RimmingtonMine class");

		

		RS2Object ore1 = script.getObjects().closest("Rocks");

		if (!(Main.world == script.worlds.getCurrentWorld())) {
			script.log("not in correct world");
			script.getWorlds().hop(Main.world);
				
			
		}

		if (Main.world == script.worlds.getCurrentWorld()) {
			script.log("in the correct world");

			if (script.getSkills().getDynamic(Skill.MINING) >= Main.lowLevelMining1 && script.getSkills().getDynamic(Skill.MINING) <= Main.lowLevelMining2) { 
				script.log("mining from level " + Main.lowLevelMining1 + "-" + Main.lowLevelMining2);

				if (!script.myPlayer().getPosition().equals(Areas.miningPos1)) {
					script.log("walking to correct position");

					WalkingEvent wEvent = new WalkingEvent(Areas.miningPos1);
					wEvent.setMinDistanceThreshold(0);
					script.execute(wEvent);
				}

				if (script.myPlayer().getPosition().equals(Areas.miningPos1)) {
					script.log("in the correct position");
					if (ore1 != null && ore1.isVisible() && ore1.getX() == Main.oreID_1_x
							&& ore1.getY() == Main.oreID_1_y) {
						if (ore1.interact("Mine")) {
							Sleep.sleepUntil(() -> !ore1.exists(), 15000); 
						}
					}
				}

			}
			if (script.getSkills().getDynamic(Skill.MINING) >= Main.highLevelMining1 && script.getSkills().getDynamic(Skill.MINING) <= Main.highLevelMining2) { 
				script.log("mining from level " + Main.highLevelMining1 + "-" + Main.highLevelMining2);

				if (!script.myPlayer().getPosition().equals(Areas.miningPos2)) {
					script.log("walking to correct position 2");

					WalkingEvent wEvent = new WalkingEvent(Areas.miningPos2);
					wEvent.setMinDistanceThreshold(0);
					script.execute(wEvent);
				}

				if (script.myPlayer().getPosition().equals(Areas.miningPos2)) {
					script.log("in the correct position 2");
					if (ore1 != null && ore1.isVisible() && ore1.getX() == Main.oreID_2_x
							&& ore1.getY() == Main.oreID_2_y) {
						if (ore1.interact("Mine")) {
							Sleep.sleepUntil(() -> !ore1.exists(), 15000); 
						}
					}
				}

			}
		}
		
		
		
	

		return 100;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
