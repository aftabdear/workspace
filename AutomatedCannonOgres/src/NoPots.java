import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class NoPots extends Task {

	public NoPots(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}
	// uses ge to buy tuna

	@Override
	public boolean verify() {

		return !(script.getInventory().contains("Ranging potion(1)")
				|| script.getInventory().contains("Ranging potion(2)")
				|| script.getInventory().contains("Ranging potion(3)")
				|| script.getInventory().contains("Ranging potion(4)"))
				|| script.getInventory().getItem("Ranging potion(4)").isNote()
				&& script.getInventory().contains("Ardougne teleport")
				&& script.getInventory().contains("Varrock teleport")
				&& script.getInventory().contains("Cannonball")
				&& script.getEquipment().contains(StaticStrings.dart)
				&& Areas.GEArea.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
		script.log("no pots class");
		Entity ge = (Entity) script.getNpcs().closest("Grand Exchange Clerk");
		

		if (script.getInventory().contains("Vial")){
			if (script.getInventory().dropAll("Vial")){
				Sleep.sleepUntil(()-> !script.getInventory().contains("Vial"), 10000);
			}
		}
		
		if (!script.getInventory().contains("Ranging potion(4)") && !script.getInventory().contains("Vial")) {
			if (ge.isVisible() && ge != null) {
				if (ge.interact("Exchange")) {
					int freeSlots = script.getInventory().getEmptySlotCount();
					
					script.log("Using GE to buy " + freeSlots + " Ranging Potion (4)");
					script.sleep(script.random(5000, 10000));
					script.getGrandExchange().buyItem(2444, "Ranging Potion(4)", 2000, freeSlots);

					new ConditionalSleep(script.random(3000, 5000)) {
						public boolean condition() {
							return script.getGrandExchange().collect();
						}
					}.sleep();

					if (script.getGrandExchange().close()) {
						script.sleep(5000);
						script.log("we slept for 5 seconds since ge interface just closed");
					}

				}
			}
		}
		
		if (script.getInventory().getItem("Ranging potion(4)").isNote()){
			script.log("ranging potion is noted");
			if (!script.getInventory().isItemSelected() && !script.getDialogues().isPendingOption()) {
				
				if (script.getInventory().getItem("Ranging potion(4)").interact("Use") && !script.getDialogues().isPendingOption()) {
					Sleep.sleepUntil(() -> script.getInventory().isItemSelected(), 5000);
				}
			} else if (script.getNpcs().closest("Banker").interact("Use") && !script.getDialogues().isPendingOption()) { 
					Sleep.sleepUntil(() -> script.getDialogues().isPendingOption(), 5000); 
					
				} else if (script.getDialogues().isPendingOption()) {
					if (script.getDialogues().selectOption(1)) {
						Sleep.sleepUntil(() -> !script.getInventory().getItem("Ranging potion(4)").isNote(), 5000); 
					}
				}
			
		}

		return 300;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}
}
