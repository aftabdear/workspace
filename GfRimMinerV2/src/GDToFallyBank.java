import java.util.Arrays;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

public class GDToFallyBank extends Task {

	public GDToFallyBank(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getQuests().getQuestPoints() == 7 && Areas.zoomedOutGDArea.contains(script.myPlayer()); 
	}

	@Override
	public int execute() throws Exception {
		RS2Object largeDoor = script.getObjects().closest("Large door");

		
		
		if (script.getInventory().contains("Gold bar")){
			script.getInventory().dropAll();
		}
		
		else if (largeDoor != null && largeDoor.hasAction("Open")) { 
			
				if (largeDoor.interact("Open")) {
					Sleep.sleepUntil(() -> largeDoor.hasAction("Close"), 5000);
				}
			} 
			
			else if (script.getWalking().walkPath(Arrays.asList(Areas.pathGDToFallyBank))) {
				Sleep.sleepUntil(() -> Banks.FALADOR_EAST.contains(script.myPlayer()), 5000); 
			}
		return 200;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
