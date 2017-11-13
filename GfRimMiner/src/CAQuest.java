import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;

public class CAQuest extends Task {

	public CAQuest(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return (script.getInventory().contains("Iron ore")) &&

				!script.getInventory().getItem("Iron ore").isNote()
				&& Areas.CA.contains(script.myPlayer().getPosition())
				&& script.getQuests().getQuestPoints() == 0;
	}

	@Override
	public int execute() throws Exception {
		script.log("in CA Quest class");

	
		Entity cook = (Entity) script.getNpcs().closest("Cook");
		

		if (cook != null && cook.isVisible()) {
			script.log("NPC VISIBLE");
			if (!script.getDialogues().inDialogue()) {
				if (cook.interact("Talk-to")) {
					Sleep.sleepUntil(() -> script.getDialogues().inDialogue(), 5000);
				}
			}

		if (script.getDialogues().isPendingContinuation()) {
			script.getDialogues().clickContinue();
		}

		if (script.getDialogues().isPendingOption()) {
			script.getDialogues().selectOption("What's wrong?");
			script.getDialogues().selectOption("I'm always happy to help");
			script.getDialogues().selectOption("Actually, I know");
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
