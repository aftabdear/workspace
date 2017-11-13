import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.Script;

public class MuleFirstInterface extends Task{
long timeInTrade;
	public MuleFirstInterface(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getTrade().isFirstInterfaceOpen() && Banks.FALADOR_EAST.contains(script.myPlayer().getPosition())
				;
		//add && noted items in inventory <3000
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
