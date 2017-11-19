import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;

public class GDQuest extends Task {

	public GDQuest(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// whys this not loading
		return Areas.zoomedOutGDArea.contains(script.myPlayer().getPosition())
				&& script.getQuests().getQuestPoints() == 2;
	}

	@Override
	public int execute() throws Exception {
		script.log("in the GD Quest class");
		RS2Object largeDoor = script.getObjects().closest("Large door");
		NPC goblin = script.getNpcs().closest("General Bentnoze");
		Item orangeDye = script.getInventory().getItem("Orange dye");
		Item goblinMail = script.getInventory().getItem("Goblin mail");
		Item orangeGoblinMail = script.getInventory().getItem("Orange goblin mail");
		Item blueDye = script.getInventory().getItem("Blue dye");
		Item blueGoblinMail = script.getInventory().getItem("Blue goblin mail");

		RS2Widget blueArmour = (RS2Widget) script.getWidgets()
				.getWidgetContainingText("How am I meant to get blue armour?");
		RS2Widget orangeArmour = (RS2Widget) script.getWidgets()
				.getWidgetContainingText("Where am I meant to get orange");
		RS2Widget brownArmour = (RS2Widget) script.getWidgets()
				.getWidgetContainingText("I have some brown armour here");

		if (largeDoor != null && largeDoor.isVisible()) {
			if (largeDoor.hasAction("Open")) {
				largeDoor.interact("Open");
			}

			// Now we know the cut scene has this config 1021: 192 so what you
			// can do is like this

			if (goblin != null && goblin.isVisible()) {
				script.log("NPC VISIBLE");
				if (!script.getDialogues().inDialogue()) {
					if (goblin.interact("Talk-to") || goblin.interact("Blue goblin mail") || goblin.interact("Orange goblin mail") || goblin.interact("Goblin mail") || goblin.interact("Use")) {
						Sleep.sleepUntil(() -> script.getDialogues().inDialogue(), 5000);
					}
				}

				if (script.getDialogues().isPendingContinuation()) {
					script.getDialogues().clickContinue();
				}

				if (orangeGoblinMail != null) {
					if (orangeGoblinMail.interact("Use")) {
						Sleep.sleepUntil(() -> script.getInventory().isItemSelected(), 5000);
						goblin.interact("Use"); 
					}
				}

				if (blueGoblinMail != null) {
					if (blueGoblinMail.interact("Use")) {
						Sleep.sleepUntil(() -> script.getInventory().isItemSelected(), 5000);
						goblin.interact("Use");
					}
				}

				if (script.getDialogues().isPendingOption()) {
					script.getDialogues().selectOption("Do you want me to pick");
					script.getDialogues().selectOption("What about a different colour");
					script.getDialogues().selectOption("No, he doesn't");

					if (orangeArmour != null) {
						script.log("we can see the blue armour text");
						if (orangeDye != null) {
							if (orangeDye.interact("Use")) {
								Sleep.sleepUntil(() -> script.getInventory().isItemSelected(), 5000);
								if (goblinMail.interact("Use")) {
									Sleep.sleepUntil(() -> script.getInventory().contains("Orange goblin mail"), 5000); //basically for the orange and blue armour you need to get the gye on the goblin mail
								}
							}
						}

					}

					if (blueArmour != null) {
						script.log("we can see the blue armour text");
						if (blueDye != null) {
							if (blueDye.interact("Use")) {
								Sleep.sleepUntil(() -> script.getInventory().isItemSelected(), 5000);
								if (goblinMail.interact("Use")) {
									Sleep.sleepUntil(() -> script.getInventory().contains("Blue goblin mail"), 5000); 
								}
							}
						}

					}

					if (brownArmour != null) {//wym? how we gonna test it
						script.log("we can the brown armour text");
						if (goblinMail.interact("Use")) {
							Sleep.sleepUntil(() -> script.getInventory().isItemSelected(), 5000); 
							goblin.interact("Use");
								
							} 
						}
					}

				}
			}

		

		return 1000;

	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
