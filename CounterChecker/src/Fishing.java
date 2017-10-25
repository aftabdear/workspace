import java.util.Arrays;
import java.util.Random;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

public class Fishing extends Task {

	public Fishing(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getSkills().getDynamic(Skill.FISHING) <= 19 && script.getQuests().getQuestPoints() == 7;
	}

	@Override
	public int execute() throws Exception {
		NPC fishingSpot = script.getNpcs().closest("Fishing Spot");
		RS2Widget a = script.getWidgets().get(261, 1, 7);
		RS2Widget b = script.getWidgets().get(261, 66);

		if (Areas.lumbridgeFishingSpot.contains(script.myPlayer().getPosition())) {

			if (script.myPlayer().getAnimation() != -1) {
				script.log("fishing");
			}

			if (script.myPlayer().getAnimation() == -1 && script.getInventory().isFull()) {
				script.log("Depositing all the fishes");
				if (script.getInventory().dropAllExcept("Small fishing net", "Fly fishing rod", "Lobster pot", "Feather", "Coins")) {
					Sleep.sleepUntil(() -> script.getInventory().onlyContains("Small fishing net", "Fly fishing rod", "Lobster pot", "Feather", "Coins"),
							5000);
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

				if (script.getInventory().isFull()) {
					script.getInventory().dropAll("Small fishing net", "Fly fishing rod", "Lobster pot", "Feather", "Coins");
				}

				script.log("not animating");

				if (fishingSpot != null && fishingSpot.isVisible() && script.getMap().canReach(fishingSpot)) {
					script.log("fishing spot is visible");
					if (fishingSpot.interact("Net")) {
						script.log("interacting with fishing spot");
						Sleep.sleepUntil(() -> script.myPlayer().getAnimation() != -1, 5000);

					}
				}
				
				if (fishingSpot != null && fishingSpot.isVisible() && !script.getMap().canReach(fishingSpot)) {
					script.log("cant reach fishing spot - walking to a more located fishing area");
					script.getWalking().walk(Areas.lumbridgeFishingSpotFailSafe);
				}
				
			}
		}

		if (Areas.lumbridgeTeleSpot.contains(script.myPlayer().getPosition())) {
			script.getWalking().walkPath(Arrays.asList(Areas.pathLumbyTelespotToFishingSpot1));
		}

		return 5000;

	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
