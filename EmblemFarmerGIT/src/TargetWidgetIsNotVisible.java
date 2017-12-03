import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;

public class TargetWidgetIsNotVisible extends Task{

	public TargetWidgetIsNotVisible(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		RS2Widget Target = script.getWidgets().get(90, 37);
		return Target == null && !Main.BYSTANDER && !Main.ATTACKER;
	}

	@Override
	public int execute() throws Exception {
		script.log("in the TargetWidgetIsNotVisble class");
		script.log("Not in Wilderness");
		
		if (script.getBank().contains("Amulet of glory")){
			script.log("we have a glory");
		}
		//only activates when first logged in
		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
