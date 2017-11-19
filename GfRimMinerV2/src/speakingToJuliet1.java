import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class speakingToJuliet1 extends Task {

	public speakingToJuliet1(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return (Areas.areaOutsideJulietsHouse.contains(script.myPlayer().getPosition())
				|| Areas.firstFloorOfJulietsHouse.contains(script.myPlayer().getPosition())
				|| Areas.secondFloorOfJulietsHouseRoom1.contains(script.myPlayer().getPosition())
				|| Areas.secondFloorOfJulietsHouseRoom2.contains(script.myPlayer().getPosition())) && !(script.getConfigs().get(144) == 20) && !(script.getConfigs().get(144) == 60);
	}

	@Override
	public int execute() throws Exception {
		RS2Object doorAClosed = script.getObjects().closest(Areas.b, 11773);
		RS2Object doorAOpen = script.getObjects().closest(Areas.b, 11772);
		RS2Object doorBClosed = script.getObjects().closest(Areas.c, 11773);
		RS2Object doorBOpen = script.getObjects().closest(Areas.c, 11772);
		RS2Object doorOutsideClosed = script.getObjects().closest(Areas.a, 11773);
		RS2Object doorOutsideOpen = script.getObjects().closest(Areas.a, 11772);
		RS2Object stairs = script.getObjects().closest(11797);
		
		
		
		if (Areas.areaOutsideJulietsHouse.contains(script.myPlayer().getPosition())) {

			if (doorOutsideClosed != null) {
				script.log("door is visible");
				if (doorOutsideClosed.hasAction("Open"))
					if (doorOutsideClosed.interact("Open")) {
						script.log("Opening door");
						Sleep.sleepUntil(() -> doorOutsideOpen != null, 5000);
					}
			}
			if (doorOutsideOpen != null) {
				script.getWalking().walk(Areas.nextToStartJulietshouseGroundFloor);
			}

		}

		if (Areas.firstFloorOfJulietsHouse.contains(script.myPlayer().getPosition())) {
			script.log("inside the house");
			if (stairs != null && stairs.isVisible()) {
				if (stairs.interact("Climb-up")) {
					Sleep.sleepUntil(() -> Areas.secondFloorOfJulietsHouseRoom1.contains(script.myPlayer().getPosition()),
							5000);
				}
			}
		}

		if (Areas.secondFloorOfJulietsHouseRoom1.contains(script.myPlayer().getPosition())) {
			script.log("on second floor room 1");

			if (doorAClosed != null) {
				script.log("door is visible");
				if (doorAClosed.hasAction("Open"))
					if (doorAClosed.interact("Open")) {
						script.log("Opening door");
						Sleep.sleepUntil(() -> doorAOpen != null, 5000);
					}
			}
			if (doorAOpen != null){
				script.getWalking().walk(Areas.secondFloorOfJulietsHouseRoom2);
			}

		}
		
		if (Areas.secondFloorOfJulietsHouseRoom2.contains(script.myPlayer().getPosition())) {
			script.log("in the second room");
			NPC juliet = script.getNpcs().closest("Juliet");
			
			
			
			if (juliet != null && script.getMap().canReach(juliet)){
				if (!script.getDialogues().inDialogue()) {
					if (juliet.interact("Talk-to")) {
						Sleep.sleepUntil(() -> script.getDialogues().inDialogue(), 5000);
					}
				}
				if (script.getDialogues().isPendingContinuation()) {
					script.getDialogues().clickContinue();
				}

				if (script.getDialogues().isPendingOption()) {
					script.getDialogues().selectOption("Yes I've met him.");
					script.getDialogues().selectOption("Certainly, I'll do so straight away.");
				}
			}
			
			else{
			
			if (doorBClosed != null) {
				script.log("door is visible");
				if (doorBClosed.hasAction("Open"))
					if (doorBClosed.interact("Open")) {
						script.log("Opening door");
						Sleep.sleepUntil(() -> doorBOpen != null, 5000);
					}
			}
				if (doorBOpen != null){
					script.log("door B is open");
					WalkingEvent wEvent = new WalkingEvent(Areas.balcony1);
					wEvent.setMinDistanceThreshold(0);
					script.execute(wEvent);
						
					}
				}
		}
			
		

		return 100;
	}

	private Position Position(int i, int j, int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
