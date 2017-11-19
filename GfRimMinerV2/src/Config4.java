import java.util.Arrays;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class Config4 extends Task {

	public Config4(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getConfigs().get(144) == 50;
	}

	@Override
	public int execute() throws Exception {
		script.log("in config4 class - walking to juliet");
		NPC potionMan = script.getNpcs().closest("Apothecary");
		
		RS2Object doorOutsideClosed = script.getObjects().closest(Areas.d, 11775);
		RS2Object doorOutsideOpen = script.getObjects().closest(Areas.d, 11774);
		

		
		
		if  (Areas.potionHouse.contains(script.myPlayer().getPosition())){
			if (potionMan != null && potionMan.isVisible()) {
				if (!script.getDialogues().inDialogue() && !script.getInventory().contains("Cadava Potion")) {
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
				
				if (script.getInventory().contains("Cadava Potion")){
					if (doorOutsideClosed != null) {
						script.log("door is visible");
						if (doorOutsideClosed.hasAction("Open"))
							if (doorOutsideClosed.interact("Open")) {
								script.log("Opening door");
								Sleep.sleepUntil(() -> doorOutsideOpen != null, 5000);
							}
					}
					if (doorOutsideOpen != null) {
						script.getWalking().walkPath(Arrays.asList(Areas.pathAToJ));
					}
				}
				
			}
			
			
		}
		
		if (script.getDialogues().isPendingContinuation()) {
			script.getDialogues().clickContinue();
		}

		return 100;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
