import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.Script;

public class Check extends Task{

	public Check(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return BankCache.getBankCache() != null ;
	}

	@Override
	public int execute() throws Exception {
		script.log("in check");
		
		
		script.log(Main.bankItems);
		
		if (Main.bankItems.contains("Bucket")) {
			script.log("we have a bucket in our bank");
		}
		
		if (!Main.bankItems.contains("Bucket")) {
			script.log("we DONT have a bucket in our bank");
		}
		
		
		
		if (script.getBank().isOpen()) {
			checkCache();
		}
		
		
		
		return 1000;
	}
	

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void checkCache() {
		Main.bankItems.clear();
		BankCache.grabBankCache();
		Sleep.sleepUntil(()-> !Main.bankItems.isEmpty(),2000);
	}

	
	
}
