import java.util.Arrays;
import java.util.Random;

import org.osbot.rs07.api.Settings;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

public class Fishing3 extends Task {

	//to change back to normal theme window -> preferences-> general -> appearances
	
	public Fishing3(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getSkills().getDynamic(Skill.FISHING) <= 40 && script.getQuests().getQuestPoints() == 7;
	}

	@Override
	public int execute() throws Exception {
		NPC fishingSpot = script.getNpcs().closest("Fishing Spot");
		RS2Widget a = script.getWidgets().get(261, 1, 7);
		RS2Widget b = script.getWidgets().get(261, 66);

		if (Areas.lumbridgeFishingSpot.contains(script.myPlayer().getPosition())){
			script.getWalking().walkPath(Arrays.asList(Areas.pathFishingSpot1ToFishingSpot2));
		}
		
		if (Areas.lumbridgeFishingSpot2.contains(script.myPlayer().getPosition())) {

			if (script.myPlayer().getAnimation() != -1) {
				script.log("fishing - salmon/trout");
				
			}
			
			if (script.myPlayer().getAnimation() == -1 && script.getInventory().isFull()){
				script.log("Dropping all the fishes");
				if (script.getInventory().dropAllExcept("Small fishing net", "Fly fishing rod", "Lobster pot", "Feather", "Coins")){
					Sleep.sleepUntil(()-> script.getInventory().onlyContains("Small fishing net", "Fly fishing rod", "Lobster pot", "Feather", "Coins"), 5000);
				}
				
				
				
			}
			
			if (script.myPlayer().getAnimation() == -1) {
				
				if (script.getSettings().isShiftDropActive() == false){
					if (script.getTabs().open(Tab.SETTINGS)){
						if (a != null && a.isVisible()){
							if (a.interact("Controls")){
								if (b != null && b.isVisible()){
									b.interact("Toggle Shift Click Drop");
								}
							}
						}
					}
				}
				
				if (script.getInventory().isFull()){
					script.getInventory().dropAllExcept("Small fishing net", "Fly fishing rod", "Lobster pot", "Feather", "Coins");
				}
				script.log("not animating");
				if (fishingSpot != null && fishingSpot.isVisible()) {
					
					script.log("fishing spot is visible");
					if (fishingSpot.interact("Lure")) {
						script.log("interacting with fishing spot");
						Sleep.sleepUntil(() -> script.myPlayer().getAnimation() != -1, 5000);
					}
				}
			}
		}
		
		


		return 5000;

	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
