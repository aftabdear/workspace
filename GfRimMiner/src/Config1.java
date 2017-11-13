import java.util.Arrays;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class Config1 extends Task {

	public Config1(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getConfigs().get(144) == 20;
	}

	@Override
	public int execute() throws Exception {
		script.log("in config1 class - walking to juliet");
		NPC romeo = script.getNpcs().closest("Romeo");
		RS2Object doorAClosed = script.getObjects().closest(Areas.b, 11773);
		RS2Object doorAOpen = script.getObjects().closest(Areas.b, 11772);
		RS2Object doorBClosed = script.getObjects().closest(Areas.c, 11773);
		RS2Object doorBOpen = script.getObjects().closest(Areas.c, 11772);
		RS2Object doorOutsideClosed = script.getObjects().closest(Areas.a, 11773);
		RS2Object doorOutsideOpen = script.getObjects().closest(Areas.a, 11772);
		RS2Object stairs = script.getObjects().closest(11799);

		if (Areas.balcony.contains(script.myPlayer().getPosition())) {
			script.log("walking out of balcony");
			if (doorBClosed != null) {
				script.log("door is visible");
				if (doorBClosed.hasAction("Open"))
					if (doorBClosed.interact("Open")) {
						script.log("Opening door");
						Sleep.sleepUntil(() -> doorBOpen != null, 5000);
					}
			}
			if (doorBOpen != null) {
				script.log("door B is open");
				script.getWalking().walk(Areas.secondFloorOfJulietsHouseRoom2);

			}
		}
		
		
		if (Areas.secondFloorOfJulietsHouseRoom2.contains(script.myPlayer().getPosition())){
			script.log("in room 2");
			if (doorAClosed != null) {
				script.log("door is visible");
				if (doorAClosed.hasAction("Open"))
					if (doorAClosed.interact("Open")) {
						script.log("Opening door");
						Sleep.sleepUntil(() -> doorAOpen != null, 5000);
					}
			}
			if (doorAOpen != null){
				script.getWalking().walk(Areas.secondFloorOfJulietsHouseRoom1);
			}
		}
		
		if (Areas.secondFloorOfJulietsHouseRoom1.contains(script.myPlayer().getPosition())){
			script.log("in room 1");
			if (stairs != null && stairs.isVisible()) {
				if (stairs.interact("Climb-down")) {
					Sleep.sleepUntil(() -> Areas.firstFloorOfJulietsHouse.contains(script.myPlayer().getPosition()),
							5000);
				}
			}
		}
		
		if (Areas.firstFloorOfJulietsHouse.contains(script.myPlayer().getPosition())){
			script.log("on the first floor");
			if (doorOutsideClosed != null) {
				script.log("door is visible");
				if (doorOutsideClosed.hasAction("Open"))
					if (doorOutsideClosed.interact("Open")) {
						script.log("Opening door");
						Sleep.sleepUntil(() -> doorOutsideOpen != null, 5000);
					}
			}
			if (doorOutsideOpen != null) {
				script.getWalking().walk(Areas.areaOutsideJulietsHouse);
			}
		}
		
		if (Areas.areaOutsideJulietsHouse.contains(script.myPlayer().getPosition())){
			script.log("outside the house - walking to romeo");
			script.getWalking().walkPath(Arrays.asList(Areas.pathJToR));
		}
		
		if (Areas.romeoArea.contains(script.myPlayer().getPosition())){
			if (romeo != null && romeo.isVisible()){
				if (!script.getDialogues().inDialogue()) {
					if (romeo.interact("Talk-to")) {
						Sleep.sleepUntil(() -> script.getDialogues().inDialogue(), 5000);
					}
				}
				if (script.getDialogues().isPendingContinuation()) {
					script.getDialogues().clickContinue();
				}

				
			}
		}

		return 100;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
