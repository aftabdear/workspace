import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.Player;
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
			Main.ourTier = 1;
		}
		if (tier2 != null){
			Main.getResponseForString("£-2-"+TargetName);
			Main.ourTier = 2;
		}
		if (tier3 != null){
			Main.getResponseForString("£-3-"+TargetName);
			Main.ourTier = 3;
		}
		if (tier4 != null){
			Main.getResponseForString("£-4-"+TargetName);
			Main.ourTier = 4;
		}
		if (tier5 != null){
			Main.getResponseForString("£-5-"+TargetName);
			Main.ourTier = 5;
		}
		if (tier6 != null){
			Main.getResponseForString("£-6-"+TargetName);
			Main.ourTier = 6;
		}
		if (tier7 != null){
			Main.getResponseForString("£-7-"+TargetName);
			Main.ourTier = 7;
		}
		if (tier8 != null){
			Main.getResponseForString("£-8-"+TargetName);
			Main.ourTier = 8;
		}
		if (tier9 != null){
			Main.getResponseForString("£-9-"+TargetName);
			Main.ourTier = 9;
		}
		
		if (tier10 != null){
			Main.getResponseForString("£-10-"+TargetName);
			Main.ourTier = 10;
		}
		
		
		if(script.myPlayer().isUnderAttack()){
			
			
			
			script.log("The Player that is attacink us is: " + interactingPlayerName());
		}
		
		return 10000;
	}
	
	 
	public String interactingPlayerName(){
	       Player p = script.getPlayers().closest(new Filter<Player>() {
	    	   @Override
	           public boolean match(Player player) {
	               return player != null && player.getInteracting() != null && player.getInteracting().equals(script.myPlayer());
	           }
	       });
	       return (p != null ? p.getName() : null);
	    }

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
