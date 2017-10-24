import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class WalkToOgres extends Task{

	public WalkToOgres(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		
		return 
			    script.getEquipment().contains(StaticStrings.dart) &&
				(script.getInventory().contains("Ranging Potion(4)") && !script.getInventory().getItem("Ranging potion(4)").isNote()) &&
				script.getInventory().contains("Cannonball") &&
				script.getInventory().contains("Ardougne teleport") &&
				script.getInventory().contains("Varrock teleport")
				;
	}

	@Override
	public int execute() throws Exception {
		script.log("walking / teleporting to ogres");
		Item ardyTab = script.getInventory().getItem("Ardougne teleport");
		
		if(Areas.GEArea.contains(script.myPlayer().getPosition())){
			if (ardyTab != null){
				if (ardyTab.interact("Break")){
					Sleep.sleepUntil(()-> Areas.ardyTeleportArea.contains(script.myPlayer().getPosition()), 5000);
				}
			}
		}
		
		if (Areas.ardyTeleportArea.contains(script.myPlayer().getPosition())){
			script.getWalking().webWalk(Areas.cannonOgreaArea);
		}
		
		
		
		
		return 2000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
