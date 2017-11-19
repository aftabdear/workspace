import java.util.Arrays;
import java.util.Random;

import org.osbot.rs07.script.Script;

public class MineToFallyBank extends Task{

	public MineToFallyBank(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.rimmingtonMiningArea.contains(script.myPlayer().getPosition()) && script.getInventory().isFull();
	}

	@Override
	public int execute() throws Exception {
		script.log("in the MineToFallyBank Class");
		Random rand = new Random();
		int value = rand.nextInt(101);
		
		if (value >= 0 && value <= 20){
			script.log("walking using path 1");
			script.getWalking().walkPath(Arrays.asList(Areas.pathMineToBank1));
		}
		if (value >= 21 && value <= 40){
			script.log("walking using path 2");
			script.getWalking().walkPath(Arrays.asList(Areas.pathBankToMine2));
		}
		if (value >= 41 && value <= 60){
			script.log("walking using path 3");
			script.getWalking().walkPath(Arrays.asList(Areas.pathMineToBank3));
		}
		if (value >= 61 && value <= 80){
			script.log("walking using path 4");
			script.getWalking().walkPath(Arrays.asList(Areas.pathMineToBank4));
		}
		if (value >= 81 && value <= 101){
			script.log("walking using path 5");
			script.getWalking().walkPath(Arrays.asList(Areas.pathBankToMine5));
		}
		
		return 100;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
