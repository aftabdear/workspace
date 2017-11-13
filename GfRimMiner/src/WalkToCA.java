
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;

public class WalkToCA extends Task {

	public WalkToCA(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return (script.getInventory().contains("Egg") && !script.getInventory().getItem("Egg").isNote()
				&& script.getInventory().contains("Pot of flour")
				&& !script.getInventory().getItem("Pot of flour").isNote() && script.getInventory().contains("Clay")
				&& !script.getInventory().getItem("Clay").isNote() && script.getInventory().contains("Bucket of milk")
				&& !script.getInventory().getItem("Bucket of milk").isNote()

				&& script.getInventory().contains("Copper ore") && !script.getInventory().getItem("Copper ore").isNote()
				&& script.getInventory().contains("Iron ore") && !script.getInventory().getItem("Iron ore").isNote())

				&& (Areas.lumbridgeBank.contains(script.myPlayer().getPosition())
						|| Areas.lumbridgeStairsFloor2North.contains(script.myPlayer().getPosition())
						|| Areas.lumbridgeStairsFloor2.contains(script.myPlayer().getPosition()));
	}

	@Override
	public int execute() throws Exception {
		script.log("reached CA class 1");

		Entity stairs = script.getObjects().closest("Staircase");
		Entity stairsFloor1 = script.getObjects().closest(16673);
		Entity stairsFloor1North = script.getObjects().closest(16672);

		if (script.getWalking().getWalking().walk(Areas.lumbridgeStairsFloor2North)) {
			Sleep.sleepUntil(() -> Areas.lumbridgeStairsFloor2North.contains(script.myPlayer().getPosition()), 5000);
		}

		if (stairs != null && stairs.isVisible()) {
			if (stairs.interact("Climb-down")) {
				Sleep.sleepUntil(() -> Areas.lumbridgeStairsFloor1.contains(script.myPlayer().getPosition())
						|| Areas.lumbridgeStairsFloor1North.contains(script.myPlayer().getPosition()), 5000);
			}
		}

		/*
		 * if
		 * (Areas.lumbridgeStairsFloor1.contains(script.myPlayer().getPosition()
		 * ) || Areas.lumbridgeStairsFloor1North.contains(script.myPlayer().
		 * getPosition())) {
		 * 
		 * if ((stairsFloor1 != null && stairsFloor1.isVisible()) ||
		 * (stairsFloor1North != null && stairsFloor1North.isVisible())) {
		 * script.log("stairs are visible"); if
		 * (stairsFloor1.interact("Climb-down") ||
		 * stairsFloor1North.interact("Climb-down")) { Sleep.sleepUntil( () ->
		 * Areas.lumbridgeStairsFloor0.contains(script.myPlayer().getPosition())
		 * || Areas.lumbridgeStairsFloor0North.contains(script.myPlayer().
		 * getPosition()), 5000); } } }
		 * 
		 * if
		 * (Areas.lumbridgeStairsFloor0.contains(script.myPlayer().getPosition()
		 * ) || Areas.lumbridgeStairsFloor0North.contains(script.myPlayer().
		 * getPosition())) { if (script.getWalking().walk(Areas.CA)) {
		 * Sleep.sleepUntil(() ->
		 * Areas.CA.contains(script.myPlayer().getPosition()), 5000); } }
		 */

		return 2000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
