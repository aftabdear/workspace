package DangerousMule2;

import org.osbot.rs07.api.Players;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class Mule extends Task {
	long Timer = System.currentTimeMillis();
	long Timer2 = System.currentTimeMillis();

	public Mule(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {

		return Banks.FALADOR_EAST.contains(script.myPlayer().getPosition())

		;
	}

	@Override
	public int execute() throws Exception {
		Item coal = script.getInventory().getItem(454);
		Item iron = script.getInventory().getItem(441);
		Item clay = script.getInventory().getItem(435);
		Item tin = script.getInventory().getItem(439);
		Item copper = script.getInventory().getItem(437);
		Item gold = script.getInventory().getItem(445);
		// Main.getResponseForString("NEEDMULE");
		Position tradingSquare = new Position(3010, 3356, 0);

		Player player = script.getPlayers().closest(Main.username);
		Player lastRequesting = script.getTrade().getLastRequestingPlayer();

		
			if (!script.myPlayer().getPosition().equals(tradingSquare)) {
				script.log("walking to correct position");

				WalkingEvent wEvent = new WalkingEvent(tradingSquare);
				wEvent.setMinDistanceThreshold(0);
				script.execute(wEvent);
			}

					

		// trading bots
		if ((coal != null && coal.getAmount() < 5000) || (iron != null && iron.getAmount() < 5000)
				|| (clay != null && clay.getAmount() < 5000) || (tin != null && tin.getAmount() < 5000)
				|| (copper != null && copper.getAmount() < 5000) || (gold != null && gold.getAmount() < 5000)) {
			// get last requesting player and trade him

			if (script.getWidgets().get(220, 16) != null) {
				script.getWidgets().get(220, 16).interact("Close");
				script.getWidgets().get(220, 16).interact();
			}

			if (!script.getTrade().isCurrentlyTrading()) {
				if (lastRequesting != null && lastRequesting.isVisible()) {
					if (lastRequesting.interact("Trade with")) {
						Sleep.sleepUntil(() -> script.getTrade().isCurrentlyTrading(), 10_000);
					}
				}
			}

			if (script.getTrade().isFirstInterfaceOpen()) {

				Sleep.sleepUntil(() -> !script.getTrade().getTheirOffers().isEmpty(), 5000);
				script.log(!script.getTrade().getTheirOffers().isEmpty());

				if (script.getTrade().getTheirOffers().contains(454) || script.getTrade().getTheirOffers().contains(441)
						|| script.getTrade().getTheirOffers().contains(435)
						|| script.getTrade().getTheirOffers().contains(439)
						|| script.getTrade().getTheirOffers().contains(437)
						|| script.getTrade().getTheirOffers().contains(445)) {
					script.log("we can see noted ores");
					if (script.getTrade().acceptTrade()) {
						Sleep.sleepUntil(() -> script.getTrade().isSecondInterfaceOpen(), 5000);
					}
				} else if (script.getTrade().getTheirOffers().isEmpty()) {

					script.getTrade().declineTrade();

				}

				else if (!script.getTrade().getTheirOffers().contains(454)
						|| !script.getTrade().getTheirOffers().contains(441)
						|| !script.getTrade().getTheirOffers().contains(435)
						|| !script.getTrade().getTheirOffers().contains(439)
						|| !script.getTrade().getTheirOffers().contains(437)
						|| !script.getTrade().getTheirOffers().contains(445)) {
					script.getTrade().declineTrade();

				}
			}

			if (script.getTrade().isSecondInterfaceOpen()) {
				if (script.getTrade().acceptTrade()) {
					Sleep.sleepUntil(() -> !script.getTrade().isCurrentlyTrading(), 5000);
				}
			}

		}

		// trading safe mule
		if ((coal != null && coal.getAmount() > 4999) || (iron != null && iron.getAmount() > 4999)
				|| (clay != null && clay.getAmount() > 4999) || (tin != null && tin.getAmount() > 4999)
				|| (copper != null && copper.getAmount() > 4999) || (gold != null && gold.getAmount() > 4999)
				|| !script.getTrade().getOurOffers().isEmpty()) {
			Main.getResponseForString("NEEDMULE");
			
			if (!script.myPlayer().getPosition().equals(tradingSquare)) {
				script.log("walking to correct position");

				WalkingEvent wEvent = new WalkingEvent(tradingSquare);
				wEvent.setMinDistanceThreshold(0);
				script.execute(wEvent);
			}

			if (script.getWidgets().get(220, 16) != null) {
				script.getWidgets().get(220, 16).interact("Close");
				script.getWidgets().get(220, 16).interact();
			}

			if (!script.getTrade().isCurrentlyTrading()) {
				if (player != null && player.isVisible()) {
					if (player.interact("Trade with")) {
						Sleep.sleepUntil(() -> script.getTrade().isCurrentlyTrading(), 5000);
					}
				}
			}

			if (script.getTrade().isFirstInterfaceOpen() || !script.getTrade().getOurOffers().isEmpty()) {

				if (coal != null && coal.getAmount() > 3000 && coal.interact("Offer-All")) {
					Sleep.sleepUntil(() -> !script.getTrade().getOurOffers().isEmpty(), 5000);
					if (script.getTrade().acceptTrade()) {
						Sleep.sleepUntil(() -> script.getTrade().isSecondInterfaceOpen(), 5000);
					}
				}

				if (tin != null && tin.getAmount() > 3000 && tin.interact("Offer-All")) {
					Sleep.sleepUntil(() -> !script.getTrade().getOurOffers().isEmpty(), 5000);
					if (script.getTrade().acceptTrade()) {
						Sleep.sleepUntil(() -> script.getTrade().isSecondInterfaceOpen(), 5000);
					}
				}

				if (gold != null && gold.getAmount() > 3000 && gold.interact("Offer-All")) {
					Sleep.sleepUntil(() -> !script.getTrade().getOurOffers().isEmpty(), 5000);
					if (script.getTrade().acceptTrade()) {
						Sleep.sleepUntil(() -> script.getTrade().isSecondInterfaceOpen(), 5000);
					}
				}

				if (iron != null && iron.getAmount() > 3000 && iron.interact("Offer-All")) {
					Sleep.sleepUntil(() -> !script.getTrade().getOurOffers().isEmpty(), 5000);
					if (script.getTrade().acceptTrade()) {
						Sleep.sleepUntil(() -> script.getTrade().isSecondInterfaceOpen(), 5000);
					}
				}

				if (clay != null && clay.getAmount() > 3000 && clay.interact("Offer-All")) {
					Sleep.sleepUntil(() -> !script.getTrade().getOurOffers().isEmpty(), 5000);
					if (script.getTrade().acceptTrade()) {
						Sleep.sleepUntil(() -> script.getTrade().isSecondInterfaceOpen(), 5000);
					}
				}

				if (copper != null && copper.getAmount() > 3000 && copper.interact("Offer-All")) {
					Sleep.sleepUntil(() -> !script.getTrade().getOurOffers().isEmpty(), 5000);
					if (script.getTrade().acceptTrade()) {
						Sleep.sleepUntil(() -> script.getTrade().isSecondInterfaceOpen(), 5000);
					}
				}
			}

			// Sleep.sleepUntil(() ->
			// script.getTrade().getOurOffers().contains(454) ||
			// script.getTrade().getOurOffers().contains(441)
			// || script.getTrade().getOurOffers().contains(435)
			// || script.getTrade().getOurOffers().contains(439)
			// || script.getTrade().getOurOffers().contains(437)
			// || script.getTrade().getOurOffers().contains(445), 5000);
			// }

			// if (script.getTrade().getOurOffers().contains(454) ||
			// script.getTrade().getOurOffers().contains(441)
			// || script.getTrade().getOurOffers().contains(435)
			// || script.getTrade().getOurOffers().contains(439)
			// || script.getTrade().getOurOffers().contains(437)
			// || script.getTrade().getOurOffers().contains(445)) {
			// if (script.getTrade().acceptTrade()){
			// Sleep.sleepUntil(()-> script.getTrade().isSecondInterfaceOpen(),
			// 5000);
			// }
			// }

			if (script.getTrade().isSecondInterfaceOpen()) {
				if (script.getTrade().acceptTrade()) {
					Sleep.sleepUntil(() -> !script.getTrade().isCurrentlyTrading(), 5000);
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
