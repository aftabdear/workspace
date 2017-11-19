
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.Script;

public class WalkToDQ extends Task {

	public WalkToDQ(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getQuests().getQuestPoints() == 6 && Areas.romeoArea.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
		script.log("in the walk to dorics quest class");

		if (script.getWidgets().isVisible(277,15)){
			script.log("closing the pop up");
			script.getWidgets().interact(277, 15, "Close");
		}
		
		Random rand = new Random();

		int value = rand.nextInt(101);

		if (value >= 0 && value <= 25) {
			script.log("using path 1");
			script.getWalking().walkPath(Arrays.asList(Areas.pathRJToDQ1));
		}
		
		if (value >= 26 && value <= 50) {
			script.log("using path 2");
			script.getWalking().walkPath(Arrays.asList(Areas.pathRJToDQ2));
		}
		
		if (value >= 51 && value <= 75) {
			script.log("using path 3");
			script.getWalking().walkPath(Arrays.asList(Areas.pathRJToDQ3));
		}
		
		if (value >= 76 && value <= 101) {
			script.log("using path 4");
			script.getWalking().walkPath(Arrays.asList(Areas.pathRJToDQ3));
		}

		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
