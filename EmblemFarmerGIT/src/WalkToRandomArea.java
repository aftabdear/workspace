import java.util.Arrays;
import java.util.Random;

import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.script.Script;

public class WalkToRandomArea extends Task {

	public WalkToRandomArea(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// walk to the area if not attacker or bystander
		// not in wild
		return (Main.ATTACKER || Main.BYSTANDER) && Main.RELOCATE && BankCache.getBankCache() != null; // and not in
																	// wilderness
	}

	@Override
	public int execute() throws Exception {
		Random rn = new Random();

		int max = 10;
		int min = 1;

		RS2Widget Target = script.getWidgets().get(90, 37);
		int ans = rn.nextInt(max - min + 1);

		if (Target != null) {
			String TargetName = Target.getMessage();
			if (Main.set.contains(TargetName)) {

				if (ans == 1) {
					if (!Areas.a.contains(script.myPlayer().getPosition())) {
						Main.getResponseForString("^-"+TargetName+"-a");
						script.getWalking().walkPath(Arrays.asList(Areas.pathToA));

					}

					if (Areas.a.contains(script.myPlayer().getPosition())) {
						Main.RELOCATE = false;
					}

				}
			}
			
			if (!Main.set.contains(TargetName)){
				Main.RELOCATE = false;
				Main.BYSTANDER = false;
				Main.ATTACKER = false;
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
