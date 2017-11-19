import java.util.Arrays;

import org.osbot.rs07.script.Script;

public class FailSafe2 extends Task{

	public FailSafe2(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.failSafe2.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
		script.log("in fail safe 2");
		if (script.getWalking().walkPath(Arrays.asList(Areas.pathFailSafe2))){
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
