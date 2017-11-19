package SafeMule;

import org.osbot.rs07.api.Players;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class Mule extends Task {

	public Mule(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getClient().isLoggedIn();
	}

	@Override
	public int execute() throws Exception {
		Position tradingSquare = new Position(3009, 3356, 0);

		Player lastRequesting = script.getTrade().getLastRequestingPlayer();
		
		if (script.getWidgets().get(220, 16) != null){
			script.getWidgets().get(220, 16).interact("Close");
			script.getWidgets().get(220, 16).interact();
		}

		if (!Banks.FALADOR_EAST.contains(script.myPlayer())) {
			script.getWalking().webWalk(Banks.FALADOR_EAST);
		}

		else if (!script.getTrade().isCurrentlyTrading()) {
			
			if (script.getWidgets().get(220, 16) != null){
				script.getWidgets().get(220, 16).interact("Close");
				script.getWidgets().get(220, 16).interact();
			}
			
			if (!script.myPlayer().getPosition().equals(tradingSquare)) {
				script.log("walking to correct position");

				WalkingEvent wEvent = new WalkingEvent(tradingSquare);
				wEvent.setMinDistanceThreshold(0);
				script.execute(wEvent);
			}
			if (lastRequesting != null && lastRequesting.interact("Trade with")) {
				Sleep.sleepUntil(() -> script.getTrade().isCurrentlyTrading(), 10000);

			} else {
				script.log("Cant find any of our dangerous mules");

			}
		} else if (script.getTrade().isFirstInterfaceOpen()) {
			if (!script.getTrade().getTheirOffers().isEmpty()) {
				if (script.getTrade().acceptTrade()) {
					Sleep.sleepUntil(() -> script.getTrade().isSecondInterfaceOpen(), 5000);
				}
			}
		}

		else if (script.getTrade().isSecondInterfaceOpen()) {
			if (script.getTrade().didOtherAcceptTrade()) {
				if (script.getTrade().acceptTrade()) {
					Sleep.sleepUntil(() -> !script.getTrade().isCurrentlyTrading(), 5000);
					if (script.getTabs().open(Tab.LOGOUT)) {
						script.getWidgets().get(182, 6).interact("Logout");
					}
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
