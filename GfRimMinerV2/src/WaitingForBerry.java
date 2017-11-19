import java.awt.geom.Area;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

public class WaitingForBerry extends Task{

	public WaitingForBerry(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.berryArea.contains(script.myPlayer().getPosition()) && !script.getInventory().contains("Cadava berries");
	}

	@Override
	public int execute() throws Exception {
		RS2Object Berries0 = script.getObjects().closest(23627);
		RS2Object Berries1 = script.getObjects().closest(23626);
		RS2Object Berries2 = script.getObjects().closest(23625);
		
		
		
		if (Berries1 != null && Berries1.isVisible()){
			if (Berries1.interact("Pick-from")){
				script.getTabs().open(Tab.INVENTORY);
			}
		}
		
		else if (Berries2 != null && Berries2.isVisible()){
			if (Berries2.interact("Pick-from")){
				script.getTabs().open(Tab.INVENTORY);
			}
		}
		
		else{
			script.log("waiting for berries");
		}
		
		
		return 2000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
