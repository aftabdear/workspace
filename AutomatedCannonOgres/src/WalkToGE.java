import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class WalkToGE extends Task{

	public WalkToGE(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		
		return 
				(!script.getEquipment().contains(StaticStrings.dart) && !Areas.GEArea.contains(script.myPlayer().getPosition())) ||
				(!script.getInventory().contains("Ranging Potion(1)", "Ranging Potion(2)","Ranging Potion(3)","Ranging Potion(4)") && !Areas.GEArea.contains(script.myPlayer().getPosition())) ||
				(!script.getInventory().contains("Cannonball") && !Areas.GEArea.contains(script.myPlayer().getPosition())) ||
				(!script.getInventory().contains("Ardougne teleport") && !Areas.GEArea.contains(script.myPlayer().getPosition())) ||
				(!script.getInventory().contains("Varrock teleport") && !Areas.GEArea.contains(script.myPlayer().getPosition())) ||
				((script.getEquipment().contains(1704) || script.getInventory().contains(1704)) && !Areas.GEArea.contains(script.myPlayer().getPosition())) 
				
				;
	}

	@Override
	public int execute() throws Exception {
		script.log("walking / teleporting to ge");
		
		if(Areas.VarrockArea.contains(script.myPlayer().getPosition())){
			script.getWalking().webWalk(Areas.GEArea);
		}
		
		if(!Areas.VarrockArea.contains(script.myPlayer().getPosition())){
			if (script.getInventory().contains("Varrock teleport")) {

				script.getInventory().getItem("Varrock teleport").interact("Break");
				
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
