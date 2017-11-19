import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class DQQuest extends Task {

	public DQQuest(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getQuests().getQuestPoints() == 6
				&& Areas.DQAreaZoomedOut.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
		script.log("in DQQuest class");
		Entity door = script.getObjects().closest(1535);
		Entity NPC = script.getNpcs().closest("Doric");
		Position failSafe1 = new Position(2952, 3450, 0);

		if (door != null && door.isVisible()) {
			if (script.getCamera().toEntity(door)) {
				if (door.hasAction("Open")) {
					door.interact("Open");
				}
			}
		}

			else {
				script.getWalking().walk(failSafe1);
					if (NPC != null && NPC.isVisible()) {
						script.log("NPC VISIBLE");
						if (!script.getDialogues().inDialogue()) {
							if (NPC.interact("Talk-to")) {
								Sleep.sleepUntil(() -> script.getDialogues().inDialogue(), 5000);
							}
						}

						if (script.getDialogues().isPendingContinuation()) {
							script.getDialogues().clickContinue();
						}

						if (script.getDialogues().isPendingOption()) {
							script.getDialogues().selectOption("I wanted to use your");
							script.getDialogues().selectOption("Yes, I will get you");
							script.getDialogues().selectOption("Certainly, I'll");
						}
					}

				}
			
		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
