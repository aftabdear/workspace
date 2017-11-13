import org.osbot.rs07.script.Script;

public class FirstInterface extends Task{
	public static long timeInTrade = System.currentTimeMillis();

	public FirstInterface(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getTrade().isFirstInterfaceOpen();
	}

	@Override
	public int execute() throws Exception {
		
		script.getTrade().acceptTrade();
		if ((System.currentTimeMillis() - timeInTrade) / 60000 > 0) {
			script.log("we've been in this trade for more then 1 min");
			script.getTrade().declineTrade();
			timeInTrade = System.currentTimeMillis();
			script.log("Everthing has been set");

		}
		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
