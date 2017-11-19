import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class InBalcony extends Task {

	public InBalcony(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.balcony.contains(script.myPlayer().getPosition()) && !(script.getConfigs().get(144) == 20) && !(script.getConfigs().get(144) == 60);
	}

	@Override
	public int execute() throws Exception {
		script.log("in the inBalcony class");
		NPC juliet = script.getNpcs().closest("Juliet");
		Position failSafe1 = new Position(3160, 3425, 1);

		if (juliet != null && script.getMap().canReach(juliet)) {
			

				script.getWalking().walk(failSafe1);
			
			if (!script.getDialogues().inDialogue()) {
				if (juliet.interact("Talk-to")) {
					Sleep.sleepUntil(() -> script.getDialogues().inDialogue(), 5000);
				}
			}
			if (script.getDialogues().isPendingContinuation()) {
				script.getDialogues().clickContinue();
			}

			if (script.getDialogues().isPendingOption()) {
				script.getDialogues().selectOption("Yes I've met him.");
				script.getDialogues().selectOption("Certainly, I'll do so straight away.");
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
