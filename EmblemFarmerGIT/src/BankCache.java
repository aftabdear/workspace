import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.util.ItemContainer;
import org.osbot.rs07.script.Script;

public class BankCache extends Task {
	public static ItemContainer bankCache;

	public BankCache(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return getBankCache() == null && !Main.ATTACKER && !Main.BYSTANDER && !Main.RELOCATE;
	}

	@Override
	public int execute() throws Exception {
		script.log("in the bank cache class");

		if (Banks.EDGEVILLE.contains(script.myPlayer().getPosition())) {
			if (script.getBank().isOpen()) {
				setBankCache(script.getBank());
			} else {
				script.getBank().open();
			}
		}
		
		else {
			script.getWalking().webWalk(Banks.EDGEVILLE);
		}

		return 100;
	}

	public static void grabBankCache() {
		if (getBankCache() != null) {
			for (Item i : getBankCache().getItems()) {
				if (i != null) {
					Main.bankItems.add(i.getName().toString());
				}
			}
		}
	}

	public static ItemContainer getBankCache() {
		return bankCache;
	}

	public void setBankCache(ItemContainer bankCache) {
		this.bankCache = bankCache;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
