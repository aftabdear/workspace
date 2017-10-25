import java.util.Arrays;

import org.osbot.rs07.api.GrandExchange;
import org.osbot.rs07.api.GrandExchange.Box;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class NoCannonballs extends Task {

	public NoCannonballs(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {

		return !script.getInventory().contains("Cannonball") && Areas.GEArea.contains(script.myPlayer().getPosition());

	}

	@Override
	public int execute() throws Exception {
		script.log("no cannonballs class");

		Entity ge = (Entity) script.getNpcs().closest("Grand Exchange Clerk");
		GrandExchange.Status box1 = script.getGrandExchange().getStatus(Box.BOX_1);

		RS2Widget buyButton = script.getWidgets().get(465, 7, 3);
		RS2Widget buyScreen = script.getWidgets().get(465, 24);
		RS2Widget cannonballWidget = script.getWidgets().get(162, 39, 1);
		RS2Widget typeScreen = script.getWidgets().get(162, 32);
		RS2Widget gameTypeScreen = script.getWidgets().get(162, 29);
		RS2Widget gameTypeScreenV2 = script.getWidgets().get(162, 29);
		RS2Widget header = script.getWidgets().get(465, 24, 25);
		RS2Widget confirmBox = script.getWidgets().get(465, 27, 0);
		RS2Widget loadingOffer = script.getWidgets().get(465, 7, 21);
		RS2Widget abortOffer = script.getWidgets().get(465, 22, 0);
		RS2Widget collectBox = script.getWidgets().get(465, 23, 2);
		RS2Widget mainPage = script.getWidgets().get(465, 7, 16);
		RS2Widget collect = script.getWidgets().get(465, 6, 0);

		if (!script.getInventory().contains("Cannonball")) {

			if (ge != null && !script.getGrandExchange().isOpen()) {
				if (ge.interact("Exchange")) {
					Sleep.sleepUntil(() -> script.getGrandExchange().isOpen(), 5000);
				}
			} else if (script.getGrandExchange().isOpen()) {

				if (buyButton != null) {
					buyButton.interact("Create <col=ff9040>Buy</col> offer");

				}

				if (typeScreen != null && mainPage != null && mainPage.getMessage().contains("Buy")) {
					int freeSlots = script.getInventory().getEmptySlots();
					if (loadingOffer != null && loadingOffer.interact("View offer")) {
						if (collectBox != null) {
							script.sleep(2000);

							if (script.getMouse().click(412, 285, false)) {
								if (script.getMouse().click(460, 287, false)) {
									script.sleep(2000);
									int freeSlotsAfterCollection = script.getInventory().getEmptySlots();
									if (freeSlotsAfterCollection < freeSlots) {
										script.log("we got the item");
										script.sleep(4000);
										script.getGrandExchange().close();
									}
									if (freeSlotsAfterCollection == freeSlots) {
										script.log("did not get item");
										script.sleep(4000);
										script.stop();
									}
									script.log("slots after collecting : " + freeSlotsAfterCollection);
									script.log("slots before collecting : " + freeSlots);
								}
							}
						}

					}

				}

				if (typeScreen != null && mainPage != null && mainPage.getMessage().contains("Empty")) {

					script.log("on the buy screen");
					if (script.keyboard.typeString("Cannonball")) {
						Sleep.sleepUntil(() -> cannonballWidget != null, 5000);
					}
				}
				if (cannonballWidget != null) {
					script.log("we can see the cannonball widget");
					if (cannonballWidget.interact()) {
						Sleep.sleepUntil(() -> typeScreen == null && buyScreen != null && cannonballWidget == null,
								5000);

						if (script.getGrandExchange().setOfferQuantity(6900)) {
							if (script.getGrandExchange().setOfferPrice(210)) {
								if (script.getGrandExchange().confirm()) {
									Sleep.sleepUntil(() -> buyButton == null, 5000);

								}
							}
						}
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
