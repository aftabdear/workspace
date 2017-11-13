import java.util.Arrays;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;

public class Config2 extends Task {

	public Config2(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getConfigs().get(144) == 30;
	}

	@Override
	public int execute() throws Exception {
		NPC father = script.getNpcs().closest("Father Lawrence");

		if (Areas.romeoArea.contains(script.myPlayer().getPosition())) {
			script.getWalking().walkPath(Arrays.asList(Areas.pathRToF));
		}

		if (Areas.church.contains(script.myPlayer().getPosition())) {
			if (father != null && father.isVisible()) {
				if (!script.getDialogues().inDialogue()) {
					if (father.interact("Talk-to")) {
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
