import org.osbot.rs07.api.Chatbox;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Message.MessageType;
import org.osbot.rs07.script.Script;

public class BYSTANDER extends Task {

	public BYSTANDER(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Main.BYSTANDER && !Main.ATTACKER && !Main.RELOCATE && BankCache.getBankCache() != null;
	}

	@Override
	public int execute() throws Exception {

		if (Main.relocationArea.contains("a")) {
			script.log("we need to relocate to area A");

			if (!Areas.a.contains(script.myPlayer().getPosition())) {
				script.getWalking().webWalk(Areas.a);
			}
			if (script.getChatbox().contains(Chatbox.MessageType.GAME,"Oh dear")){
				Main.BYSTANDER = false;
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
