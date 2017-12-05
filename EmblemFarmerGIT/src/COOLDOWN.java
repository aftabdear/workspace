import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.Script;

public class COOLDOWN extends Task{

	public COOLDOWN(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Main.COOLDOWN && !Main.ATTACKER && !Main.BYSTANDER && !Main.RELOCATE && BankCache.getBankCache() != null;
	}

	@Override
	public int execute() throws Exception {
		script.log("We are cooling down!");
		
		if (Banks.EDGEVILLE.contains(script.myPlayer().getPosition())) {
		script.sleep(2100000);
		Main.COOLDOWN=false;
	}
		else {
			script.getWalking().webWalk(Banks.EDGEVILLE);
		}
		
		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
