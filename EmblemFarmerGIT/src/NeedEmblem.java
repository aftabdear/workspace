import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class NeedEmblem extends Task {

	public NeedEmblem(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		Item Itier1 = script.getInventory().getItem("Mysterious emblem");
		Item Itier2 = script.getInventory().getItem("Mysterious emblem (tier 2)");
		Item Itier3 = script.getInventory().getItem("Mysterious emblem (tier 3)");
		Item Itier4 = script.getInventory().getItem("Mysterious emblem (tier 4)");
		Item Itier5 = script.getInventory().getItem("Mysterious emblem (tier 5)");
		Item Itier6 = script.getInventory().getItem("Mysterious emblem (tier 6)");
		Item Itier7 = script.getInventory().getItem("Mysterious emblem (tier 7)");
		Item Itier8 = script.getInventory().getItem("Mysterious emblem (tier 8)");
		Item Itier9 = script.getInventory().getItem("Mysterious emblem (tier 8)");
		Item Itier10 = script.getInventory().getItem("Mysterious emblem (tier 10)");
		return (!Main.bankItems.contains(Main.tier1) && Itier1 == null)
				|| (!Main.bankItems.contains(Main.tier2) && Itier2 == null)
				|| (!Main.bankItems.contains(Main.tier3) && Itier3 == null)
				|| (!Main.bankItems.contains(Main.tier4) && Itier4 == null)
				|| (!Main.bankItems.contains(Main.tier5) && Itier5 == null)
				|| (!Main.bankItems.contains(Main.tier6) && Itier6 == null)
				|| (!Main.bankItems.contains(Main.tier7) && Itier7 == null)
				|| (!Main.bankItems.contains(Main.tier8) && Itier8 == null)
				|| (!Main.bankItems.contains(Main.tier9) && Itier9 == null) && BankCache.getBankCache() != null;
	}

	@Override
	public int execute() throws Exception {
		Position waitingPosition = new Position(3096, 3494, 0);

		Player lastRequesting = script.getTrade().getLastRequestingPlayer();

		script.log("Bank and inventory dont contain an emblem");
		Main.getResponseForString("!-" + Main.IGN);

		if (!Banks.EDGEVILLE.contains(script.myPlayer().getPosition())) {
			script.getWalking().webWalk(Banks.EDGEVILLE);
		}

		if (Banks.EDGEVILLE.contains(script.myPlayer().getPosition())) {
			// check if on correct spot
			// wait for lastrequesting
			// accept
			// accept

			if (waitingPosition.equals(script.myPlayer().getPosition())) {
				if (lastRequesting != null) {
					if (!script.getTrade().isCurrentlyTrading()) {
						if (lastRequesting.interact("Trade with")) {
							Sleep.sleepUntil(()-> script.getTrade().isCurrentlyTrading(),5000);
						}
					}
					if (script.getTrade().isFirstInterfaceOpen()) {
						if (script.getTrade().acceptTrade()){
							Sleep.sleepUntil(()->script.getTrade().isSecondInterfaceOpen(),5000);
						}
					}
					if (script.getTrade().isSecondInterfaceOpen()) {
						if (script.getTrade().acceptTrade()){
							Sleep.sleepUntil(()->!script.getTrade().isCurrentlyTrading(),5000);
						}
					}
				}
			}

			if (!waitingPosition.equals(script.myPlayer().getPosition())) {
				WalkingEvent wEvent = new WalkingEvent(waitingPosition);
				wEvent.setMinDistanceThreshold(0);
				script.execute(wEvent);
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
