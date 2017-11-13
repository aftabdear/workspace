import java.util.Arrays;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

public class FreshAcc1 extends Task {

	public FreshAcc1(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.coolDownArea.contains(script.myPlayer().getPosition())
				|| Areas.outsideLumbridgeTowerStairs.contains(script.myPlayer().getPosition()) && !script.getInventory().contains("Clay");
	}

	// && !script.getInventory().contains("Clay") && !script.getInventory().getItem("Clay").isNote()
	
	// its clashing with the other class

	@Override
	public int execute() throws Exception {
		RS2Object door = script.getObjects().closest(Areas.outsideLumbridgeTowerStairs, "Door");
		RS2Object ladder = script.getObjects().closest(Areas.outsideLumbridgeTowerStairs, "Ladder");

		if (script.getSettings().areRoofsEnabled() == false){
			script.getKeyboard().typeString("::toggleroofs");
		}
		
		if (script.getInventory().contains(StaticStrings.tutorialIslandItems)) {
			script.getInventory().dropAll();

		}

		if (Areas.coolDownArea.contains(script.myPlayer().getPosition())) {
			script.log("cooldownarea if");
			if (script.getWalking().walkPath(Arrays.asList(Areas.pathToSecretRoom))) {
				Sleep.sleepUntil(() -> Areas.outsideLumbridgeTowerStairs.contains(script.myPlayer().getPosition()),
						5000);
			}
		}

		if (Areas.outsideLumbridgeTowerStairs.contains(script.myPlayer().getPosition())) {
			script.log("Test.");
			if (door != null) {
				if (door.hasAction("Close")) {
					script.log("You kidding me?");
					if (ladder != null) {
						if (ladder.interact("Climb-up")) {
							Sleep.sleepUntil(() -> Areas.lumbridgeTowerFloor1.contains(script.myPlayer().getPosition()),
									5000);
						}
					}
				} else if (door.interact("Open")) {
					Sleep.sleepUntil(() -> door.hasAction("Close"), 5000);
				}
			}
		}
		return 200;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
