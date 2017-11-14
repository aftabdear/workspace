package DangerousMule;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.Script;

public class MuleFirstInterface2 extends Task{
long timeInTrade;
	public MuleFirstInterface2(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getTrade().isSecondInterfaceOpen() && Banks.FALADOR_EAST.contains(script.myPlayer().getPosition())
				&& script.getInventory().getItem(454).getAmount() < 3000
				&& script.getInventory().getItem(439).getAmount() < 3000
				&& script.getInventory().getItem(445).getAmount() < 3000
				&& script.getInventory().getItem(441).getAmount() < 3000
				&& script.getInventory().getItem(435).getAmount() < 3000
				&& script.getInventory().getItem(437).getAmount() < 3000;
	}

	@Override
	public int execute() throws Exception {
		
		script.getTrade().acceptTrade();
		
		
		if ((System.currentTimeMillis() - timeInTrade) / 60000 > 0) {
			script.log("we've been in this trade for more then 1 min");
			script.getTrade().declineTrade();
			timeInTrade = System.currentTimeMillis();
		}
		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
