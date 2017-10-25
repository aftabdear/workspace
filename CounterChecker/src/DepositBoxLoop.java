import org.osbot.rs07.script.Script;

public class DepositBoxLoop extends Task{

	public DepositBoxLoop(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return Areas.depositBoxArea.contains(script.myPlayer().getPosition());
	}

	@Override
	public int execute() throws Exception {
		script.log("in the depositbox loop");
		
		if (script.getInventory().contains("Raw lobster")){
			//open up deposit box -> deposit raw lobs -> increase counter by 26 
		}
		
		
		return 5000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
