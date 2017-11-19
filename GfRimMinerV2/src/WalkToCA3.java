
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class WalkToCA3 extends Task {

	public WalkToCA3(Script script) {
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
				&& Areas.zoomedOutLumbridgeArea.contains(script.myPlayer().getPosition());
		
	}

	@Override
	public int execute() throws Exception {
		script.log("reached CA class 3");

		if (!script.myPlayer().getPosition().equals(Areas.CA)) {
			script.log("walking to correct position");

			WalkingEvent wEvent = new WalkingEvent(Areas.CA);
			wEvent.setMinDistanceThreshold(0);
			script.execute(wEvent);
		}

		return 2000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
