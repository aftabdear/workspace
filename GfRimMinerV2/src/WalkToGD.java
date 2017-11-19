
import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;

public class WalkToGD extends Task {

	public WalkToGD(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getQuests().getQuestPoints() == 2
				&& Areas.DQAreaZoomedOut.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
		script.log("inside the WalkToGDQuest Class");
		Entity door = script.getObjects().closest(1535);

		if (script.getWidgets().isVisible(277,15)){
			script.log("closing the pop up");
			script.getWidgets().interact(277, 15, "Close");
		}
		
		else if (door != null && door.isVisible()) {
			if (door.hasAction("Open")) {
				door.interact("Open");
			}
		}
		
			else{
				List<Position> walkToGD = new ArrayList<>();

			    walkToGD.add(new Position(2948, 3450, 0));
			    walkToGD.add(new Position(2948, 3455, 0));
			    walkToGD.add(new Position(2951, 3460, 0));
			    walkToGD.add(new Position(2952, 3466, 0));
			    walkToGD.add(new Position(2954, 3473, 0));
			    walkToGD.add(new Position(2955, 3481, 0));
			    walkToGD.add(new Position(2955, 3488, 0));
			    walkToGD.add(new Position(2957, 3496, 0));
			    walkToGD.add(new Position(2956, 3506, 0));
			    walkToGD.add(new Position(2956, 3508, 0));
			    
				script.getWalking().walkPath(walkToGD);
			}
				
		

		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
