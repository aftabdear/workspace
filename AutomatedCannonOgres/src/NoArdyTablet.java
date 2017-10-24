import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class NoArdyTablet extends Task{

	public NoArdyTablet(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return !script.getInventory().contains("Ardougne Teleport")
				&& Areas.GEArea.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
Entity ge = (Entity) script.getNpcs().closest("Grand Exchange Clerk");

		script.log("no adrytab class");


		
		
		
		
		
		if (!script.getInventory().contains("Ardougne teleport")) {
			if (ge.isVisible() && ge != null) {
				if (ge.interact("Exchange")) {
					script.log(" Using GE to buy Ardougne teleport");
					script.sleep(script.random(3000,5000));
					script.getGrandExchange().buyItem(8011, "Ardougne teleport", 1000, 40); 
					
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
