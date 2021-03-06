import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class NoVarrockTablet extends Task{

	public NoVarrockTablet(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return !script.getInventory().contains("Varrock teleport")
				&& Areas.GEArea.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
Entity ge = (Entity) script.getNpcs().closest("Grand Exchange Clerk");

		script.log("no varrock tabs class");


		
		
		
		
		
		if (!script.getInventory().contains("Varrock teleport")) {
			if (ge.isVisible() && ge != null) {
				if (ge.interact("Exchange")) {
					script.log(" Using GE to buy Varrock tablet");
					script.sleep(script.random(3000,5000));
					script.getGrandExchange().buyItem(8007, "varrock", 800, 40); 
					
					new ConditionalSleep(script.random(3000, 5000)) {
						public boolean condition() {
							return script.getGrandExchange().collect();
						}
					}.sleep();

					
					

						if (script.getGrandExchange().close()){
							script.sleep(5000);
							script.log("we slept for 5 seconds since ge interface just closed");
						}
					
				}
			}
		}
		
		return 2000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
