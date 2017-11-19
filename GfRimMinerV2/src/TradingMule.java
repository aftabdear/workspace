import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Stream;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.script.Script;



public class TradingMule extends Task {

	public TradingMule(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return (script.getInventory().contains(Banking.notedOres) || script.getTrade().isCurrentlyTrading())
				&& script.getQuests().getQuestPoints() == 7;
	}

	@Override
	public int execute() throws Exception {
		script.log("in the trading mule class");

		Item oresToOffer = script.getInventory().getItem(435, 437, 441, 445, 443, 454);

		if (!(Main.worldToTrade == script.worlds.getCurrentWorld())) {
			script.log("not in correct world");

			if (script.getBank().isOpen()) {
				if (script.getBank().close()) {
					Sleep.sleepUntil(() -> !script.getBank().isOpen(), 5000);
				}
			}

			else {
				script.getWorlds().hop(Main.worldToTrade);
			}

		}

		if (Main.worldToTrade == script.worlds.getCurrentWorld()) {
			script.log("in the correct world");

			if (script.getBank().isOpen()) {
				if (script.getBank().close()) {
					Sleep.sleepUntil(() -> !script.getBank().isOpen(), 5000);
				}
			}

			if (!script.getTrade().isCurrentlyTrading()) {
				if (!script.getTrade().isCurrentlyTrading()) {
					loadAccountsToList();
					
					for (Player p : script.getPlayers().getAll()){
						loadAccountsToList();
						if (p != null && p.isVisible() && Main.verifiedMules.contains(p.getName().toString())) { 
							if (p.interact("Trade with")) {
								Sleep.sleepUntil(() -> script.getTrade().isCurrentlyTrading(), 10000);
							}
						} else {
							script.log("Can't find any safe mules close by!");
						}
					}
				}
			}

			if (script.getTrade().isFirstInterfaceOpen()) {
				script.log("first interface is open");
				if (oresToOffer != null && oresToOffer.interact("Offer-All")) {
					Sleep.sleepUntil(() -> script.getTrade().getOurOffers().contains(Banking.notedOres), 5000);
				}

				if (script.getTrade().getOurOffers().contains(Banking.notedOres)) {
					script.getTrade().acceptTrade();
				}

			}

			if (script.getTrade().isSecondInterfaceOpen()) {
				script.log("seconds interface is open");
				if (script.getTrade().acceptTrade()) {
					Sleep.sleepUntil(() -> !script.getTrade().isCurrentlyTrading(), 5000);
					if (!script.getInventory().contains(Banking.notedOres) && !script.getTrade().isCurrentlyTrading()) {
						Random rand = new Random();

						int value = rand.nextInt(101);

						if (value >= 0 && value <= 20) {
							script.log("walking using path 1");
							script.getWalking().walkPath(Arrays.asList(Areas.pathBankToMine1));
						}
						if (value >= 21 && value <= 40) {
							script.log("walking using path 2");
							script.getWalking().walkPath(Arrays.asList(Areas.pathBankToMine2));
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
			}
		}
		return 5000;
	}
	 public void loadAccountsToList() throws IOException{
	    	String filepath = script.getDirectoryData() + "/MulesIGN.txt";
	    	try(Stream<String> stream = Files.lines(Paths.get(filepath))){
	    		stream.forEach(s ->{
	    			Main.verifiedMules.add(s);
	    			script.log("Added: '"+s+"'");

	    			Object[] st = Main.verifiedMules.toArray();
	    		      for (Object s1 : st) {
	    		        if (Main.verifiedMules.indexOf(s1) != Main.verifiedMules.lastIndexOf(s1)) {
	    		        	Main.verifiedMules.remove(Main.verifiedMules.lastIndexOf(s1));
	    		         }
	    		      }
	    			
	    			
	    			script.log(Main.verifiedMules);
	    		});
	    	}
	    	
	    }
	 
	 public void tradeTo(String name, HashMap<String, Integer> itemSet, boolean acceptLast){
		 Player player = script.getPlayers().closest(name.replace(' ','\u00A0'));
			 if (player != null && script.getTrade().isCurrentlyTrading() && player.interact("Trade with")){
				 Sleep.sleepUntil(() -> script.getTrade().isCurrentlyTrading(), 10_000);
		 }
	 }
	 

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
