import java.util.Arrays;

import org.osbot.rs07.script.Script;

public class FailSafe1 extends Task{

	public FailSafe1(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.failSafe1.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
		script.log("in fail safe 1");
		if (script.getWalking().walkPath(Arrays.asList(Areas.pathFailSafe1))){
			Sleep.sleepUntil(()-> Areas.rimmingtonMiningArea.contains(script.myPlayer().getPosition()), 10000);
		}
		
		
		return 100;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
