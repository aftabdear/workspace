import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.script.Script;

public class WaitingForItems extends Task {

	public WaitingForItems(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getQuests().getQuestPoints() == 0 && script.getInventory().isEmpty()
				&& Areas.secretArea.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
		script.log("in the WaitingForItem class");
		Player lastRequest = script.getTrade().getLastRequestingPlayer();

		if (script.getDialogues().inDialogue()) {
			if (script.dialogues.clickContinue()){
				Sleep.sleepUntil(()-> script.getDialogues().inDialogue(), 5000);
			}

		}
		
		if (!(Main.worldToTrade == script.worlds.getCurrentWorld())) {
			script.log("not in correct world");
			script.getWorlds().hop(Main.worldToTrade);

		}

		if (Main.worldToTrade == script.worlds.getCurrentWorld()) {
			script.log("in the correct world");

			if (Main.spammer) {
				if (script.getKeyboard().typeString("hello", true)) {
					Sleep.sleepUntil(() -> script.getTrade().isCurrentlyTrading(), 5000);
				}
			}

			if (!script.getTrade().isCurrentlyTrading()) {
				for (Player p : script.getPlayers().getAll()) {

					if (p != null && p.isVisible() && p.getName().contains(Main.Slave)) {
						if (lastRequest != null && lastRequest.interact("Trade with")) {
							script.log("We are trading our slave");
							Sleep.sleepUntil(() -> script.getTrade().isCurrentlyTrading(), 5000);
						}
					}
				}
			}

			else if (script.getTrade().isFirstInterfaceOpen()) {
				if (script.getTrade().acceptTrade()) {
					Sleep.sleepUntil(() -> script.getTrade().isSecondInterfaceOpen(), 10_000);
				}
			}

			else if (script.getTrade().isSecondInterfaceOpen()) {
				if (script.getTrade().acceptTrade()) {
					Sleep.sleepUntil(() -> !script.getTrade().isCurrentlyTrading(), 5000);
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
