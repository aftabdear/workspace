import java.util.Arrays;
import java.util.Random;

import org.osbot.rs07.script.Script;

public class WalkToBerries extends Task{

	public WalkToBerries(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getQuests().getQuestPoints() == 1 && Areas.CA.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
		
		Random rand = new Random();

		int value = rand.nextInt(101);

		if (value >= 0 && value <= 50) {
			script.log("using path 1");
			script.getWalking().walkPath(Arrays.asList(Areas.CAToBerries1));
		}
		
		if (value >= 51 && value <= 101) {
			script.log("using path 2");
			script.getWalking().walkPath(Arrays.asList(Areas.CAToBerries2));
		}
		
		return 2000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
