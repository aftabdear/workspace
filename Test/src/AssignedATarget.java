import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;

public class AssignedATarget extends Task{
	public static String TargetName;

	public AssignedATarget(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		RS2Widget Target = script.getWidgets().get(90, 37);
		return (Target != null && !Target.getMessage().equals("None"));
	}
	
	

	@Override
	public int execute() throws Exception {
		
		script.log("In the AssignedATarget class");
		script.log("We have been assigned a Target");
		
		Item tier1 = script.getInventory().getItem("Mysterious emblem");
		Item tier2 = script.getInventory().getItem("Mysterious emblem (tier 2)");
		Item tier3 = script.getInventory().getItem("Mysterious emblem (tier 3)");
		Item tier4 = script.getInventory().getItem("Mysterious emblem (tier 4)");
		Item tier5 = script.getInventory().getItem("Mysterious emblem (tier 5)");
		Item tier6 = script.getInventory().getItem("Mysterious emblem (tier 6)");
		Item tier7 = script.getInventory().getItem("Mysterious emblem (tier 7)");
		Item tier8 = script.getInventory().getItem("Mysterious emblem (tier 8)");
		Item tier9 = script.getInventory().getItem("Mysterious emblem (tier 8)");
		Item tier10 = script.getInventory().getItem("Mysterious emblem (tier 10)");

		
		RS2Widget Target = script.getWidgets().get(90, 37);
		TargetName = Target.getMessage();
		
		script.log("Currently assigned to " + TargetName);
		
		Main.getResponseForString("$"+TargetName);
		
		if (tier1 != null){
			Main.getResponseForString("£-1-"+TargetName);
		}
		if (tier2 != null){
			Main.getResponseForString("£-2-"+TargetName);
		}
		if (tier3 != null){
			Main.getResponseForString("£-3-"+TargetName);
		}
		if (tier4 != null){
			Main.getResponseForString("£-4-"+TargetName);
		}
		if (tier5 != null){
			Main.getResponseForString("£-5-"+TargetName);
		}
		if (tier6 != null){
			Main.getResponseForString("£-6-"+TargetName);
		}
		if (tier7 != null){
			Main.getResponseForString("£-7-"+TargetName);
		}
		if (tier8 != null){
			Main.getResponseForString("£-8-"+TargetName);
		}
		if (tier9 != null){
			Main.getResponseForString("£-9-"+TargetName);
		}
		
		if (tier10 != null){
			Main.getResponseForString("£-10-"+TargetName);
		}
		
		
		return 10000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
