import java.util.Arrays;

import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.Script;

public class FailSafe4 extends Task{

	public FailSafe4(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.rimmingtonMiningArea.contains(script.myPlayer().getPosition()) && !script.getInventory().contains(Banking.pickaxes);
	}

	@Override
	public int execute() throws Exception {
		script.log("in fail safe 4");
		if (script.getWalking().walkPath(Arrays.asList(Areas.pathMineToBank1))){
			Sleep.sleepUntil(()-> Banks.FALADOR_EAST.contains(script.myPlayer().getPosition()), 10000);
		}
		
		
		return 100;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
