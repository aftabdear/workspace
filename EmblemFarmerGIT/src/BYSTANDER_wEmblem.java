import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.Script;

public class BYSTANDER_wEmblem extends Task {

	public BYSTANDER_wEmblem(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		Item tier1 = script.getInventory().getItem("Mysterious emblem");
		Item tier2 = script.getInventory().getItem("Mysterious emblem (tier 2)");
		Item tier3 = script.getInventory().getItem("Mysterious emblem (tier 3)");
		Item tier4 = script.getInventory().getItem("Mysterious emblem (tier 4)");
		Item tier5 = script.getInventory().getItem("Mysterious emblem (tier 5)");
		Item tier6 = script.getInventory().getItem("Mysterious emblem (tier 6)");
		Item tier7 = script.getInventory().getItem("Mysterious emblem (tier 7)");
		Item tier8 = script.getInventory().getItem("Mysterious emblem (tier 8)");
		Item tier9 = script.getInventory().getItem("Mysterious emblem (tier 8)");
		Item tier10 = script.getInventory().getItem("Mysterious emblem (tier 10)");
		return (tier1 != null || tier2 != null || tier3 != null || tier4 != null || tier5 != null || tier6 != null
				|| tier7 != null || tier8 != null || tier9 != null || tier10 != null) && Main.BYSTANDER;
	}

	@Override
	public int execute() throws Exception {
		script.log("in BYSTANDER_wEmblem class");
		script.log("inventory contains emblem!");

		Item tier1 = script.getInventory().getItem("Mysterious emblem");
		Item tier2 = script.getInventory().getItem("Mysterious emblem (tier 2)");
		Item tier3 = script.getInventory().getItem("Mysterious emblem (tier 3)");
		Item tier4 = script.getInventory().getItem("Mysterious emblem (tier 4)");
		Item tier5 = script.getInventory().getItem("Mysterious emblem (tier 5)");
		Item tier6 = script.getInventory().getItem("Mysterious emblem (tier 6)");
		Item tier7 = script.getInventory().getItem("Mysterious emblem (tier 7)");
		Item tier8 = script.getInventory().getItem("Mysterious emblem (tier 8)");
		Item tier9 = script.getInventory().getItem("Mysterious emblem (tier 8)");
		Item tier10 = script.getInventory().getItem("Mysterious emblem (tier 10)");

		if (!Banks.EDGEVILLE.contains(script.myPlayer().getPosition())) {
			script.getWalking().webWalk(Banks.EDGEVILLE);
		}

		if (Banks.EDGEVILLE.contains(script.myPlayer().getPosition())) {
			if (!script.getBank().isOpen()) {
				if (script.getBank().open()) {
					Sleep.sleepUntil(() -> script.getBank().isOpen(), 5000);
				} else if (script.getBank().isOpen()) {
					if (tier1 != null) {
						if (script.getBank().depositAll("Mysterious emblem")) {
							Sleep.sleepUntil(() -> tier1 == null, 5000);
						}
					} else if (tier2 != null) {
						if (script.getBank().depositAll("Mysterious emblem (tier 2)")) {
							Sleep.sleepUntil(() -> tier2 == null, 5000);
						}
					} else if (tier3 != null) {
						if (script.getBank().depositAll("Mysterious emblem (tier 3)")) {
							Sleep.sleepUntil(() -> tier3 == null, 5000);
						}
					} else if (tier4 != null) {
						if (script.getBank().depositAll("Mysterious emblem (tier 4)")) {
							Sleep.sleepUntil(() -> tier4 == null, 5000);
						}
					} else if (tier5 != null) {
						if (script.getBank().depositAll("Mysterious emblem (tier 5)")) {
							Sleep.sleepUntil(() -> tier5 == null, 5000);
						}
					} else if (tier6 != null) {
						if (script.getBank().depositAll("Mysterious emblem (tier 6)")) {
							Sleep.sleepUntil(() -> tier6 == null, 5000);
						}
					} else if (tier7 != null) {
						if (script.getBank().depositAll("Mysterious emblem (tier 7)")) {
							Sleep.sleepUntil(() -> tier7 == null, 5000);
						}
					} else if (tier8 != null) {
						if (script.getBank().depositAll("Mysterious emblem (tier 8)")) {
							Sleep.sleepUntil(() -> tier8 == null, 5000);
						}
					} else if (tier9 != null) {
						if (script.getBank().depositAll("Mysterious emblem (tier 9)")) {
							Sleep.sleepUntil(() -> tier9 == null, 5000);
						}
					} else if (tier10 != null) {
						if (script.getBank().depositAll("Mysterious emblem (tier 10)")) {
							Sleep.sleepUntil(() -> tier10 == null, 5000);
						}
					}
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
