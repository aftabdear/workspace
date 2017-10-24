import java.util.Arrays;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class NoDarts extends Task {

	public NoDarts(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {

		return !script.getEquipment().isWearingItem(EquipmentSlot.WEAPON, StaticStrings.dart)
				&& Areas.GEArea.contains(script.myPlayer().getPosition());

	}

	@Override
	public int execute() throws Exception {
		script.log("no darts class");
		Item dart = script.getInventory().getItem(StaticStrings.dart);

		Entity ge = (Entity) script.getNpcs().closest("Grand Exchange Clerk");

		if (dart != null) {
			if (script.getInventory().contains(StaticStrings.dart)) {
				script.log("equipping Mith darts");
				if (script.getInventory().getItem(StaticStrings.dart).interact("Wield")) {
					script.getEquipment();

				}
			}
		}

		if (!script.getEquipment().isWearingItem(EquipmentSlot.WEAPON, StaticStrings.dart)
				&& !script.getInventory().contains(StaticStrings.dart)) {
			if (ge.isVisible() && ge != null) {
				if (ge.interact("Exchange")) {
					script.log(" Using GE to buy Mith darts");
					script.sleep(script.random(3000, 5000));
					script.getGrandExchange().buyItem(809, "Mithril dart", 35, 7000); // iron
					// arrow

					new ConditionalSleep(script.random(3000, 5000)) {
						public boolean condition() {
							return script.getGrandExchange().collect();
						}
					}.sleep();

					if (script.getGrandExchange().close()) {
						script.sleep(5000);
						script.log("we slept for 5 seconds since ge interface just closed");
					}

				}
			}
		}

		return 2000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
