import java.util.Arrays;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class Config3 extends Task {

	public Config3(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getConfigs().get(144) == 40;
	}

	@Override
	public int execute() throws Exception {
		script.log("in config3 class - walking to juliet");
		NPC potionMan = script.getNpcs().closest("Apothecary");
		
		RS2Object doorOutsideClosed = script.getObjects().closest(Areas.d, 11775);
		RS2Object doorOutsideOpen = script.getObjects().closest(Areas.d, 11774);
		

		if (Areas.church.contains(script.myPlayer().getPosition())){
			script.getWalking().walkPath(Arrays.asList(Areas.pathAToF));
		}
		
		if (Areas.outsidePotionHouse.contains(script.myPlayer().getPosition())){
			if (doorOutsideClosed != null) {
				script.log("door is visible");
				if (doorOutsideClosed.hasAction("Open"))
					if (doorOutsideClosed.interact("Open")) {
						script.log("Opening door");
						Sleep.sleepUntil(() -> doorOutsideOpen != null, 5000);
					}
			}
			if (doorOutsideOpen != null) {
				script.getWalking().walk(Areas.potionHouse);
			}
		}
		
		if  (Areas.potionHouse.contains(script.myPlayer().getPosition())){
			if (potionMan != null && potionMan.isVisible()) {
				if (!script.getDialogues().inDialogue()) {
					if (potionMan.interact("Talk-to")) {
						Sleep.sleepUntil(() -> script.getDialogues().inDialogue(), 5000);
					}
				}
				if (script.getDialogues().isPendingContinuation()) {
					script.getDialogues().clickContinue();
				}
				
				if (script.getDialogues().isPendingOption()) {
					script.getDialogues().selectOption("Talk about something else.");
					script.getDialogues().selectOption("Talk about Romeo & Juliet.");
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
