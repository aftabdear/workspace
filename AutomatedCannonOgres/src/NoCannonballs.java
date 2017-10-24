import java.util.Arrays;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class NoCannonballs extends Task {

	public NoCannonballs(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		
		return !script.getInventory().contains("Cannonball")
				&& Areas.GEArea.contains(script.myPlayer().getPosition());

	}

	@Override
	public int execute() throws Exception {
		script.log("no cannonballs class");

		Entity ge = (Entity) script.getNpcs().closest("Grand Exchange Clerk");

		


		
		
		
		
		
		if (!script.getInventory().contains("Cannonball")) {
			if (ge.isVisible() && ge != null) {
				if (ge.interact("Exchange")) {
					script.log(" Using GE to buy Cannonballs");
					script.sleep(script.random(3000,5000));
					script.getGrandExchange().buyItem(2, "Cannonball", 210, 4000);

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
