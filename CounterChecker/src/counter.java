import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;

public class counter extends Task {

	public counter(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int execute() throws Exception { 

		//this is working 100%
		//this is working 100%
		//this is working 100%
		//this is working 100%
		//this is working 100%
		
		
		
		
		RS2Widget orangeArmour = (RS2Widget) script.getWidgets().get(192, 6);
		RS2Widget depositWidgetDialogue = (RS2Widget) script.getWidgets().getWidgetContainingText("The Bank of RuneScape - Deposit Box");

		if (script.getDepositBox().isOpen()) {
			script.log("Deposit Box is open");
			if (script.getDepositBox().contains("Bronze battleaxe")) {
				if (script.getDepositBox().depositAll("Bronze battleaxe")) {
					++Main.Counter;
					Sleep.sleepUntil(() -> !script.getDepositBox().contains("Bronze battleaxe")
							|| !script.getInventory().contains("Bronze battleaxe"), 5000);
				}
			}
			if (!script.getDepositBox().contains("Bronze battleaxe")
					|| !script.getInventory().contains("Bronze battleaxe")) {
				script.log("no more spade in inv");
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
