import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;

public class AssignedATarget extends Task{

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
		
		RS2Widget Target = script.getWidgets().get(90, 37);
		String TargetName = Target.getMessage();
		
		script.log("Currently assigned to " + TargetName);
		
		Main.getResponseForString("$"+TargetName);
		
		
		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
