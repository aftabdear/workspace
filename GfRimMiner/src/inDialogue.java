import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;

public class inDialogue extends Task{

	public inDialogue(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() { 
		return script.getConfigs().get(1021) == 192; 
	}

	@Override
	public int execute() throws Exception {
		script.log("in cut away dialogue");
		
		
		
		if(script.getDialogues().isPendingContinuation()) {
			script.getDialogues().clickContinue(); 
		}
		
		if (script.getWidgets().isVisible(277,15)){
			script.log("closing the pop up");
			script.getWidgets().interact(277, 15, "Close");
		}
		
		//widget 277 , 15
		
		return 100;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
