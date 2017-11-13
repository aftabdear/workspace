import java.util.Arrays;
import java.util.Random;

import org.osbot.rs07.api.Bank;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

public class Banking extends Task {
	public static String ores[] = { "Iron ore", "Steel Ore", "Clay", "Gold ore", "Tin ore", "Copper ore", "Uncut" };
	public static String pickaxes[] = { "Steel pickaxe", "Mithril pickaxe", "Adamant pickaxe", "Rune pickaxe" };
	public static int[] notedOres = { 435, 437, 441, 445, 443, 454 }; // change
																		// this
																		// to
																		// check
																		// for
																		// more
																		// ore
																		// ids

	public Banking(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Banks.FALADOR_EAST.contains(script.myPlayer()) && !script.getInventory().contains(notedOres)
				&& !script.getTrade().isCurrentlyTrading();
	}

	@Override
	public int execute() throws Exception {

		script.log("in the Banking Class");

		if (!script.getBank().isOpen() && !script.getInventory().contains(notedOres)) {
			script.log("opening bank");
			if (script.getBank().open()) {
				Sleep.sleepUntil(() -> script.getBank().isOpen(), 5000);
			}

		}

		if (script.getWidgets().isVisible(220, 15)) {
			script.log("closing the pop up");
			script.getWidgets().interact(220, 16, "Close");
		}

		if (script.getBank().isOpen() && !script.getInventory().contains(notedOres)) {

			if (script.getSkills().getDynamic(Skill.MINING) < 21) {
				if (!script.getInventory().contains("Steel pickaxe")) {
					if (script.getBank().getWithdrawMode() == Bank.BankMode.WITHDRAW_NOTE) {
						script.getBank().enableMode(Bank.BankMode.WITHDRAW_ITEM);
					}

					else {
						if (script.getBank().depositAll()) {
							if (script.getBank().withdraw("Steel pickaxe", 1)) {
								Sleep.sleepUntil(() -> script.getInventory().contains("Steel pickaxe"), 5000);
							}
						}
					}
				}

			}

			if (script.getSkills().getDynamic(Skill.MINING) >= 21 && script.getSkills().getDynamic(Skill.MINING) < 31) {
				if (script.getBank().getWithdrawMode() == Bank.BankMode.WITHDRAW_NOTE) {
					script.getBank().enableMode(Bank.BankMode.WITHDRAW_ITEM);
				}

				else {
					if (script.getInventory().contains("Steel pickaxe")) {
						if (script.getBank().depositAll("Steel pickaxe")) {
							Sleep.sleepUntil(() -> !script.getInventory().contains("Steel pickaxe"), 5000);
							if (script.getBank().withdraw("Mithril Pickaxe", 1)) {
								Sleep.sleepUntil(() -> script.getInventory().contains("Mithril pickaxe"), 5000);
							}
						}
					}
				}

			}

			if (script.getSkills().getDynamic(Skill.MINING) >= 31 && script.getSkills().getDynamic(Skill.MINING) < 41) {
				if (script.getBank().getWithdrawMode() == Bank.BankMode.WITHDRAW_NOTE) {
					script.getBank().enableMode(Bank.BankMode.WITHDRAW_ITEM);
				}

				else {
					if (script.getInventory().contains("Mithril pickaxe")) {
						if (script.getBank().depositAll("Mithril pickaxe")) {
							Sleep.sleepUntil(() -> script.getInventory().isEmpty(), 5000);
							if (script.getBank().withdraw("Adamant pickaxe", 1)) {
								Sleep.sleepUntil(() -> script.getInventory().contains("Adamant pickaxe"), 5000);
							}
						}
					}
				}
			}

			if (script.getSkills().getDynamic(Skill.MINING) >= 31 && script.getSkills().getDynamic(Skill.MINING) > 40) {
				if (script.getBank().getWithdrawMode() == Bank.BankMode.WITHDRAW_NOTE) {
					script.getBank().enableMode(Bank.BankMode.WITHDRAW_ITEM);
				}

				else {
					if (script.getInventory().contains("Adamant pickaxe")) {
						if (script.getBank().depositAll("Adamant pickaxe")) {
							Sleep.sleepUntil(() -> script.getInventory().isEmpty(), 5000);
							if (script.getBank().withdraw("Rune pickaxe", 1)) {
								Sleep.sleepUntil(() -> script.getInventory().contains("Rune pickaxe"), 5000);
							}
						}
					}
				}
			}

			if (script.getInventory().contains(ores)) {
				script.log("inventory contains non noted ores - depositing them!");
				script.getBank().depositAll(ores);
			}

			if (script.getBank().getAmount("Copper ore") > 100 || script.getBank().getAmount("Iron ore") > 100
					|| script.getBank().getAmount("Clay") > 100 || script.getBank().getAmount("Tin ore") > 100
					|| script.getBank().getAmount("Gold ore") > 100) {

				script.log("We see there are lots of ores in bank");

				if (script.getBank().getWithdrawMode() == Bank.BankMode.WITHDRAW_ITEM) {
					if (script.getBank().enableMode(Bank.BankMode.WITHDRAW_NOTE)) {
						if (script.getBank().getAmount("Copper ore") > 100) {

							if (script.getBank().withdrawAll("Copper ore")) {
								Sleep.sleepUntil(() -> script.getInventory().getItem("Copper ore").isNote(), 5000);
							}
						}

						else if (script.getBank().getAmount("Iron ore") > 100) {

							if (script.getBank().withdrawAll("Iron ore")) {
								Sleep.sleepUntil(() -> script.getInventory().getItem("Iron ore").isNote(), 5000);
							}
						} else if (script.getBank().getAmount("Clay") > 100) {

							if (script.getBank().withdrawAll("Clay")) {
								Sleep.sleepUntil(() -> script.getInventory().getItem("Clay").isNote(), 5000);
							}
						} else if (script.getBank().getAmount("Tin ore") > 100) {

							if (script.getBank().withdrawAll("Tin ore")) {
								Sleep.sleepUntil(() -> script.getInventory().getItem("Tin ore").isNote(), 5000);
							}
						} else if (script.getBank().getAmount("Gold ore") > 100) {

							if (script.getBank().withdrawAll("Gold ore")) {
								Sleep.sleepUntil(() -> script.getInventory().getItem("Gold ore").isNote(), 5000);
							}
						}
					}
				}
			}
			if ((script.getBank().getAmount("Copper ore") < 101 || script.getBank().getAmount("Iron ore") < 101
					|| script.getBank().getAmount("Clay") < 101 || script.getBank().getAmount("Tin ore") < 101
					|| script.getBank().getAmount("Gold ore") < 101) && !script.getInventory().contains(ores) && !script.getInventory().contains(notedOres)) {
				script.log("time to walk to mine!");
				Random rand = new Random();

				int value = rand.nextInt(101);

				if (value >= 0 && value <= 20) {
					script.log("walking using path 1");
					script.getWalking().walkPath(Arrays.asList(Areas.pathMineToBank1));
				}
				if (value >= 21 && value <= 40) {
					script.log("walking using path 2");
					script.getWalking().walkPath(Arrays.asList(Areas.pathMineToBank2));
				}
				if (value >= 41 && value <= 60) {
					script.log("walking using path 3");
					script.getWalking().walkPath(Arrays.asList(Areas.pathBankToMine3));
				}
				if (value >= 61 && value <= 80) {
					script.log("walking using path 4");
					script.getWalking().walkPath(Arrays.asList(Areas.pathBankToMine4));
				}
				if (value >= 81 && value <= 101) {
					script.log("walking using path 5");
					script.getWalking().walkPath(Arrays.asList(Areas.pathBankToMine5));
				}
			}
		}

		return 200;

	}

	// actually you know what i'll make it easier just make the bot drop
	// everything when quests are finished hmm yea

	// TO-DO LIST
	// deposit ores (iron copper clay gold)
	// if above 300 in any of the ores then trade mule ("aftab")
	// withdraw ores noted when > 300, then put it bank to unoted
	// think thats it

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
