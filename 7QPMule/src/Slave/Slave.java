package Slave;
import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.script.Script;

public class Slave extends Task {

	public Slave(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	// Hmm, let's try using arraylist

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.lumbridgeTowerFloor1.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {

		String Items[] = { "Egg", "Bucket of milk", "Clay", "Iron ore", "Copper ore", "Steel pickaxe",
				"Mithril pickaxe", "Adamant pickaxe", "Rune pickaxe" };
		if (!script.getTrade().isCurrentlyTrading()) {
			for (Player p : script.getPlayers().getAll()) {
				if (Main.verifiedSlavers.contains(p.getName())) {
					if (p != null && p.interact("Trade with")) {
						Sleep.sleepUntil(() -> script.getTrade().isCurrentlyTrading(), 10000);
					}
				} else {
					script.log("Can't find any slaves close by!");
				}
			}
		} else if (script.getTrade().isFirstInterfaceOpen()) {
			script.log("Trading Items...");
			if (!script.getTrade().getOurOffers().contains("Pot of flour")) {
				if (script.getTrade().offer("Pot of flour", 1)) {
					Sleep.sleepUntil(() -> script.getTrade().getOurOffers().getItem("Pot of flour").getAmount() == 1,
							5000);
				}
			} else if (!script.getTrade().getOurOffers().contains("Egg")) {
				if (script.getTrade().offer("Egg", 1)) {
					Sleep.sleepUntil(() -> script.getTrade().getOurOffers().getItem("Egg").getAmount() == 1, 5000);
				}
			} else if (!script.getTrade().getOurOffers().contains("Bucket of milk")) {
				if (script.getTrade().offer("Bucket of milk", 1)) {
					Sleep.sleepUntil(() -> script.getTrade().getOurOffers().getItem("Bucket of milk").getAmount() == 1,
							5000);
				}
			} else if (!script.getTrade().getOurOffers().contains("Clay")) {
				if (script.getTrade().offer("Clay", 6)) {
					Sleep.sleepUntil(() -> script.getTrade().getOurOffers().getItem("Clay").getAmount() == 6, 5000);
				}
			}

			else if (!script.getTrade().getOurOffers().contains("Iron ore")) {
				if (script.getTrade().offer("Iron ore", 2)) {
					Sleep.sleepUntil(() -> script.getTrade().getOurOffers().getItem("Iron ore").getAmount() == 2, 5000);
				}
			} else if (!script.getTrade().getOurOffers().contains("Copper ore")) {
				if (script.getTrade().offer("Copper ore", 4)) {
					Sleep.sleepUntil(() -> script.getTrade().getOurOffers().getItem("Copper ore").getAmount() == 4,
							5000);
				}

				// PICKAXES v
			} else if (!script.getTrade().getOurOffers().contains("Steel pickaxe")) {
				if (script.getTrade().offer("Steel pickaxe", 1)) {
					Sleep.sleepUntil(() -> script.getTrade().getOurOffers().getItem("Steel pickaxe").getAmount() == 1,
							5000);
				}
			} else if (!script.getTrade().getOurOffers().contains("Mithril pickaxe")) {
				if (script.getTrade().offer("Mithril pickaxe", 1)) {
					Sleep.sleepUntil(() -> script.getTrade().getOurOffers().getItem("Mithril pickaxe").getAmount() == 1,
							5000);
				}
			} else if (!script.getTrade().getOurOffers().contains("Adamant pickaxe")) {
				if (script.getTrade().offer("Adamant pickaxe", 1)) {
					Sleep.sleepUntil(() -> script.getTrade().getOurOffers().getItem("Adamant pickaxe").getAmount() == 1,
							5000);
				}
			} else if (!script.getTrade().getOurOffers().contains("Rune pickaxe")) {
				if (script.getTrade().offer("Rune pickaxe", 1)) {
					Sleep.sleepUntil(() -> script.getTrade().getOurOffers().getItem("Rune pickaxe").getAmount() == 1,
							5000);
				}
			} else if (script.getTrade().getOurOffers().contains(Items)) {
				if (script.getTrade().acceptTrade()) {
					Sleep.sleepUntil(() -> script.getTrade().isSecondInterfaceOpen(), 5000);
				}
			}
		} else if (script.getTrade().isSecondInterfaceOpen()) {
			if (script.getTrade().acceptTrade()) {
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
