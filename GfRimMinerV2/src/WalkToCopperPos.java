import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class WalkToCopperPos extends Task {

	public WalkToCopperPos(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getInventory().onlyContains(Banking.pickaxes) && !script.getTrade().isCurrentlyTrading();
	}

	@Override
	public int execute() throws Exception {
		script.log("int the walktocopper class");
		Position p = new Position(3006, 3352, 0);
		
		script.getWalking().walk(p);
		/*if (Areas.miningPos1 != script.myPlayer().getPosition()) { //<- It's a useless check, but we'll keep it for now, then might remove it later
			WalkingEvent walkEvent = new WalkingEvent(Areas.miningPos1);
			walkEvent.setMinDistanceThreshold(0); 
			walkEvent.execute();
		}*/

		return 500;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
