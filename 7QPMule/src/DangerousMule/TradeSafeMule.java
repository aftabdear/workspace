package DangerousMule;

import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.script.Script;

public class TradeSafeMule extends Task {

	public TradeSafeMule(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getTrade().isFirstInterfaceOpen() && Banks.FALADOR_EAST.contains(script.myPlayer().getPosition())
				&& script.getInventory().getItem(454).getAmount() > 2999
				&& script.getInventory().getItem(439).getAmount() > 2999
				&& script.getInventory().getItem(445).getAmount() > 2999
				&& script.getInventory().getItem(441).getAmount() > 2999
				&& script.getInventory().getItem(435).getAmount() > 2999
				&& script.getInventory().getItem(437).getAmount() > 2999;
	}

	@Override
	public int execute() throws Exception {
		Main.getResponseForString("NEEDMULE");
		
		

		if (!script.getTrade().isCurrentlyTrading()) {
			for (Player p : script.getPlayers().getAll()) {
				if (p != null && p.isVisible() && Main.safeMuleName.equals(p)) { 
					if (p.interact("Trade with")) {
						Sleep.sleepUntil(() -> script.getTrade().isCurrentlyTrading(), 10000);
					}
				} else {
					script.log("Can't find any safe mules close by!");
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
