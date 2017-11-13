import org.osbot.rs07.api.Players;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.Script;

public class Mule extends Task {

	public Mule(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Banks.FALADOR_EAST.contains(script.myPlayer());
	}

	@Override
	public int execute() throws Exception {
		
		
		
		Player lastRequesting = script.getTrade().getLastRequestingPlayer();

		if (!Banks.FALADOR_EAST.contains(script.myPlayer())){
			script.getWalking().webWalk(Banks.FALADOR_EAST);
		}
		
		else if (!script.getTrade().isCurrentlyTrading()) {
			if (lastRequesting != null && lastRequesting.interact("Trade with")) {
					Sleep.sleepUntil(() -> script.getTrade().isCurrentlyTrading(), 10000);
				
			} else {
				script.log("Cant find any of our bots");
			
		}
	}

		
		
		else if (script.getTrade().isFirstInterfaceOpen()) {
			
			if (script.getTrade().acceptTrade()){
				Sleep.sleepUntil(() -> script.getTrade().isSecondInterfaceOpen(), 5000);
			}
		}
		
		else if (script.getTrade().isSecondInterfaceOpen()) {
			
			if (script.getTrade().acceptTrade()){
				Sleep.sleepUntil(() -> !script.getTrade().isCurrentlyTrading(), 5000);
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
