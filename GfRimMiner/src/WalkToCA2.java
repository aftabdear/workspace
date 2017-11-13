
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;

public class WalkToCA2 extends Task {

	public WalkToCA2(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return (script.getInventory().contains("Iron ore")) &&

				!script.getInventory().getItem("Iron ore").isNote()
				&& (Areas.lumbridgeStairsFloor1.contains(script.myPlayer().getPosition()) || Areas.lumbridgeStairsFloor1North.contains(script.myPlayer().getPosition()));
	}

	@Override
	public int execute() throws Exception {
		script.log("reached CA class 2");

		Entity stairs = script.getObjects().closest("Staircase");

		if (stairs != null && stairs.isVisible()){
			if (stairs.interact("Climb-down")){
				Sleep.sleepUntil(() -> Areas.lumbridgeStairsFloor0.contains(script.myPlayer().getPosition()) || Areas.lumbridgeStairsFloor0North.contains(script.myPlayer().getPosition()) , 5000);
				}
			}

		return 2000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
