import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class WalkToBankToUnote extends Task {

	public WalkToBankToUnote(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		
		return script.getInventory().contains("Clay") && script.getInventory().getItem("Clay").isNote()
				|| Areas.lumbridgeBank.contains(script.myPlayer().getPosition())
						&& script.getQuests().getQuestPoints() == 0; 
	}

	@Override
	public int execute() throws Exception {
		script.log("inside the WalkToBankToUnote class");

		Entity ladder = script.getObjects().closest("Ladder");
		Entity door = script.getObjects().closest("Door");
		Entity stairs = script.getObjects().closest("Staircase");

		if (Areas.lumbridgeTowerFloor1.contains(script.myPlayer().getPosition())) {
			if (ladder != null && ladder.isVisible()) {
				if (ladder.interact("Climb-down")) {
					Sleep.sleepUntil(() -> Areas.lumbridgeTowerFloor0.contains(script.myPlayer().getPosition()), 5000);
				}
			}
		}

		if (Areas.lumbridgeTowerFloor0.contains(script.myPlayer().getPosition())) {
			if (door != null && door.isVisible()) {
				if (door.hasAction("Open")) {
					door.interact("Open");

				}

				else {

					List<Position> pathToStairFloor0 = new ArrayList<>();

					pathToStairFloor0.add(new Position(3225, 3223, 0));
					pathToStairFloor0.add(new Position(3222, 3219, 0));
					pathToStairFloor0.add(new Position(3217, 3218, 0));
					pathToStairFloor0.add(new Position(3214, 3218, 0));
					pathToStairFloor0.add(new Position(3213, 3210, 0));
					pathToStairFloor0.add(new Position(3208, 3210, 0));
					pathToStairFloor0.add(new Position(3205, 3208, 0));

					if (script.getWalking().walkPath(pathToStairFloor0)) {
						Sleep.sleepUntil(() -> Areas.lumbridgeStairsFloor0.contains(script.myPlayer().getPosition()),
								5000);
					}
				}
			}
		}

		if (Areas.lumbridgeStairsFloor0.contains(script.myPlayer().getPosition())
				|| Areas.lumbridgeStairsFloor0North.contains(script.myPlayer().getPosition())) {
			if (stairs.interact("Climb-up")) {
				Sleep.sleepUntil(() -> Areas.lumbridgeStairsFloor1.contains(script.myPlayer().getPosition()), 5000);
			}
		}

		if (Areas.lumbridgeStairsFloor1.contains(script.myPlayer().getPosition())
				|| Areas.lumbridgeStairsFloor1North.contains(script.myPlayer().getPosition())) {
			if (stairs.interact("Climb-up")) {
				Sleep.sleepUntil(() -> Areas.lumbridgeStairsFloor2.contains(script.myPlayer().getPosition()), 5000);
			}
		}

		if (Areas.lumbridgeStairsFloor2.contains(script.myPlayer().getPosition())
				|| Areas.lumbridgeStairsFloor2North.contains(script.myPlayer().getPosition())) {
			List<Position> pathToBank = new ArrayList<>();
			pathToBank.add(new Position(3206, 3210, 2));
			pathToBank.add(new Position(3206, 3214, 2));
			pathToBank.add(new Position(3206, 3218, 2));
			pathToBank.add(new Position(3208, 3219, 2));

			if (script.getWalking().walkPath(pathToBank)) {
				Sleep.sleepUntil(() -> Areas.lumbridgeBank.contains(script.myPlayer().getPosition()), 5000);
			}
		}

		if (Areas.lumbridgeBank.contains(script.myPlayer().getPosition())) {
			script.log("we've reached the bank");

			if (!script.getBank().isOpen()) {
				script.getBank().open();
				new ConditionalSleep(2500, 3000) {
					@Override
					public boolean condition() {
						return script.getBank().isOpen();
					}
				}.sleep();
			} else {
				if (script.getBank().isOpen()) {

					if (script.getInventory().contains("Steel Pickaxe")) {
						if (script.getBank().depositAll()) {
							Sleep.sleepUntil(() -> script.getInventory().isEmpty(), 5000);
						}
					}

					else if (!script.getInventory().contains("Steel Pickaxe")) {
						if (script.getBank().withdraw("Egg", 1)) {
							Sleep.sleepUntil(() -> script.getInventory().contains("Egg")
									&& script.getInventory().getItem("Egg").getAmount() == 1, 5000);
						} else if (script.getBank().withdraw("Clay", 6)) {
							Sleep.sleepUntil(() -> script.getInventory().contains("Clay")
									&& script.getInventory().getItem("Clay").getAmount() == 6, 5000);
						} else if (script.getBank().withdraw("Copper ore", 4)) {
							Sleep.sleepUntil(() -> script.getInventory().contains("Copper ore")
									&& script.getInventory().getItem("Copper ore").getAmount() == 4, 5000);
						} else if (script.getBank().withdraw("Iron ore", 2)) {
							Sleep.sleepUntil(() -> script.getInventory().contains("Iron ore")
									&& script.getInventory().getItem("Iron ore").getAmount() == 2, 5000);
						} else if (script.getBank().withdraw("Bucket of milk", 2)) {
							Sleep.sleepUntil(() -> script.getInventory().contains("Bucket of milk")
									&& script.getInventory().getItem("Bucket of milk").getAmount() == 1, 5000);
						} else if (script.getBank().withdraw("Pot of flour", 1)) {
							Sleep.sleepUntil(() -> script.getInventory().contains("Pot of flour")
									&& script.getInventory().getItem("Pot of flour").getAmount() == 1, 5000);
						}

					}

				}

			}

		}

		return 500;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}

// if (script.getBank().withdraw("Goblin mail", 3)) {
// if (script.getBank().withdraw("Orange dye", 1)) {
// if (script.getBank().withdraw("Clay", 6)) {
// if (script.getBank().withdraw("Egg", 1)) {
// if (script.getBank().withdraw("Copper ore", 4)) {
// if (script.getBank().withdraw("Iron ore", 2)) {
// if (script.getBank().withdraw("Blue dye", 1)) {
// if (script.getBank().withdraw("Pot of flour", 1)) {
// if (script.getBank().withdraw("Bucket of milk", 1)) {
// script.getBank().close();
//
// }
// }
//
// }
// }
// }
//
// }
// }
//
// }
// }
