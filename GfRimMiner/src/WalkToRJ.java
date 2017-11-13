import java.util.Arrays;

import org.osbot.rs07.script.Script;

public class WalkToRJ extends Task{

	public WalkToRJ(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.berryArea.contains(script.myPlayer().getPosition()) && script.getInventory().contains("Cadava berries");
	}

	@Override
	public int execute() throws Exception {
		script.getWalking().walkPath(Arrays.asList(Areas.pathBToRJ));
		return 2000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
