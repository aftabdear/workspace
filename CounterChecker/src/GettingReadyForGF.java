import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

public class GettingReadyForGF extends Task {

	public GettingReadyForGF(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getInventory().contains("Fly fishing rod")
				&& script.getInventory().contains("Feather") && script.getInventory().contains("Small fishing net")
				|| script.getInventory().contains("Salmon")
				|| script.getInventory().contains("Trout");
	}
	
	//script.getSkills().getDynamic(Skill.FISHING) >= 40 && 
	
	

	@Override
	public int execute() throws Exception {
		script.log("getting bot ready");
		
		if (Areas.lumbridgeFishingSpot2.contains(script.myPlayer().getPosition())){
			//walk to the bloody deposit box
		}
		
		if (Areas.depositBoxArea.contains(script.myPlayer().getPosition())){
			if (!script.getDepositBox().isOpen()){
				if (script.getDepositBox().open()){
					Sleep.sleepUntil(()-> script.getDepositBox().isOpen(), 5000);
				}
			}
			
			if (script.getDepositBox().isOpen()) {
				script.log("Deposit Box is open");
				if (script.getDepositBox().contains("Fly fishing rod")) {
					if (script.getDepositBox().depositAll("Fly fishing rod")) {
						Sleep.sleepUntil(() -> !script.getDepositBox().contains("Fly fishing rod")
								|| !script.getInventory().contains("Fly fishing rod"), 5000);
					}
				}
				if (script.getDepositBox().contains("Feather")) {
					if (script.getDepositBox().depositAll("Feather")) {
						Sleep.sleepUntil(() -> !script.getDepositBox().contains("Feather")
								|| !script.getInventory().contains("Feather"), 5000);
					}
				}
				if (script.getDepositBox().contains("Small fishing net")) {
					if (script.getDepositBox().depositAll("Small fishing net")) {
						Sleep.sleepUntil(() -> !script.getDepositBox().contains("Small fishing net")
								|| !script.getInventory().contains("Small fishing net"), 5000);
					}
				}
				if (script.getDepositBox().contains("Salmon")) {
					if (script.getDepositBox().depositAll("Salmon")) {
						Sleep.sleepUntil(() -> !script.getDepositBox().contains("Salmon")
								|| !script.getInventory().contains("Salmon"), 5000);
					}
				}
				if (script.getDepositBox().contains("Trout")) {
					if (script.getDepositBox().depositAll("Trout")) {
						Sleep.sleepUntil(() -> !script.getDepositBox().contains("Trout")
								|| !script.getInventory().contains("Trout"), 5000);
					}
				}
				

			}
		}
		
		
		return 5000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
