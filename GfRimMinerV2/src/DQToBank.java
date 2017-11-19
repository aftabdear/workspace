
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

public class DQToBank extends Task {

	public DQToBank(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getQuests().getQuestPoints() == 7
				&& Areas.DQAreaZoomedOut.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
		script.log("in the dq to bank class");
		
		RS2Object door = script.getObjects().closest("Door");
		
		if (script.getWidgets().isVisible(277,15)){
			script.log("closing the pop up");
			script.getWidgets().interact(277, 15, "Close");
		}
		
		else if (door != null && door.isVisible()) {
			if (door.hasAction("Open")) {
				door.interact("Open");
			}
		
		
			else{	
			    
				script.getWalking().walkPath(Arrays.asList(Areas.pathDQToBank));
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
