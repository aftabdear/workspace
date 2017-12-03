import java.util.Arrays;

import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.script.Script;

public class WalkToRandomArea extends Task{

	public WalkToRandomArea(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		//walk to the area if not attacker or bystander 
		//not in wild
		return Main.ATTACKER || Main.BYSTANDER && Main.RELOCATE; //and not in wilderness
	}

	@Override
	public int execute() throws Exception {
		int a = script.random(1,10);
		
		if (a == 1) {
			if (!Areas.a.contains(script.myPlayer().getPosition())) {
				script.getWalking().walkPath(Arrays.asList(Areas.pathToA));
			
			}
			
			if (Areas.a.contains(script.myPlayer().getPosition())) {
				Main.RELOCATE = false;
			}
			
		}
		
		
		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
