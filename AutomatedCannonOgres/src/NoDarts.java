import java.util.Arrays;

import org.osbot.rs07.api.GrandExchange;
import org.osbot.rs07.api.GrandExchange.Box;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.api.ui.RS2Widget;
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
		

		RS2Widget hitGELimit = script.getWidgets().get(465, 7, 21);

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
		
		
	
		
		

		if (!script.getInventory().contains(StaticStrings.dart)) {
			if (ge.isVisible() && ge != null) {
				if (ge.interact("Exchange")) {
					script.log(" Using GE to buy Mith darts");
					script.sleep(script.random(3000,5000));
					script.getGrandExchange().buyItem(809, StaticStrings.dart, 40, 7000);

					new ConditionalSleep(script.random(3000, 5000)) {
						public boolean condition() {
							return script.getGrandExchange().collect();
						}
					}.sleep();

					
					

						if (script.getGrandExchange().close()){
							script.sleep(5000);
							script.log("we slept for 5 seconds since ge interface just closed");
						}
					
				}
			}
		}
				
				/*else{
				script.log("confirming");
				confirmBox.interact("Confirm");
			}*/
		
				/*if (collect != null && buyButton != null){
				script.log("can see collect");
				if (collect.interact()){ //"Collect to inventory"
					
					script.sleep(2000);
					int freeSlotsAfterCollection = script.getInventory().getEmptySlots();
					script.log("slots after collecting : " + freeSlotsAfterCollection);
					script.log("slots before collecting : " + freeSlots);
					if (freeSlotsAfterCollection < freeSlots){
						script.log("we got the item");
					}
					if (freeSlotsAfterCollection == freeSlots){
						script.log("did not get item");
					}
				}
			}
			else{
				script.log("cant see collect");
			}*/
		
		
		/*
		if (ge != null && !script.getGrandExchange().isOpen()) {
			if (ge.interact("Exchange")) {
				Sleep.sleepUntil(() -> script.getGrandExchange().isOpen(), 5000);
			}
		} else if (box1.EMPTY.e) { //go for it sec //Damn, dunno. Show me Explv's ge api, is he using widgets? lemme find it //Fam, I gotta go do some work, I'll ask someone today see if they have used getStatus() lately, might be broken dunno no worries mans ty anayways glgl ttyl
			if (script.getGrandExchange().buyItem(809, "Mithril Dart", script.random(35, 38), 7000)) {
				Sleep.sleepUntil(() -> script.getGrandExchange().getAmountRemaining(Box.BOX_1) == 0, 100_000);
			}
		} else if (script.getGrandExchange().getStatus(Box.BOX_1).EMPTY.equals(false)){ //serc sec
			script.log("hit ge limit logging out");
			script.stop();*/
		
		
		
		
		
		// Let's try this

			// else if (script.getGrandExchange().getStatus(Box.BOX_1).EMPTY !=
			// null) { //should we hard code it like step by step? Hmmm, we can
			// sec let's try
			// if (script.getGrandExchange().getStatus(Box.BOX_1).FINISHED_BUY
			// != null) { //so weird..yhhh
			// script.log("hit ge limit logging out");
			// script.stop();
			// }
			// } else if (script.getGrandExchange().buyItem(809, "Mithril dart",
			// 35, 7000)) {
			// Sleep.sleepUntil(() ->
			// script.getGrandExchange().getAmountRemaining(Box.BOX_1) == 0,
			// 100_000);
			// }

			// else {
			// script.log("Logging Out: "); //yup
			// script.stop();
			// } //alright, try this

			// if (ge.isVisible() && ge != null) {
			// if (ge.interact("Exchange")) {
			// if (script.getGrandExchange().isOpen()) {
			// if (script.getGrandExchange().getStatus(Box.BOX_1).PENDING_BUY !=
			// null) {
			// script.log("hit ge limit logging out");
			// script.stop();
			// } else {
			// script.log(" Using GE to buy Mith darts");
			// script.sleep(script.random(3000, 5000));
			// script.getGrandExchange().buyItem(809, "Mithril dart", 35, 7000);
			//
			// new ConditionalSleep(script.random(3000, 5000)) {
			// public boolean condition() {
			// return script.getGrandExchange().collect();
			// }
			// }.sleep();
			//
			// if (script.getGrandExchange().close()) {
			// script.sleep(5000);
			// script.log("we slept for 5 seconds since ge interface just
			// closed");
			// }
			//
			// }
			// }
			// }
			// }
		

		return 2000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
