import org.osbot.rs07.api.Players;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class ATTACKER extends Task {

	public ATTACKER(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return !Main.RELOCATE && Main.ATTACKER && !Main.BYSTANDER;
	}

	@Override
	public int execute() throws Exception {
		script.log("in Attacker class");

		RS2Widget Target = script.getWidgets().get(90, 37);

		int x = script.myPlayer().getX();
		int y = 3524;

		Position runToSafePos = new Position(x, y, 0);

		// if underattack by the wrong player
		if (script.myPlayer().isUnderAttack() && !Main.set.contains(interactingPlayerName())) {

			if (script.myPlayer().getY() != 3524) {
				WalkingEvent wEvent = new WalkingEvent(runToSafePos);
				wEvent.setMinDistanceThreshold(0);
			}

			if (script.myPlayer().getY() == 3524) {
				Main.RELOCATE = true;
			}

		}
		
		if (script.myPlayer().getY() == 3524) {
			Main.RELOCATE = true;
		}

		if (Target != null && !script.myPlayer().isUnderAttack()) {
			String TargetName = Target.getMessage();
			if (Areas.a.contains(script.myPlayer().getPosition())){
				Main.getResponseForString("^-"+ TargetName + "-a");
			}

			if (Main.set.contains(TargetName)) {
				for (Player p : script.getPlayers().getAll()) {
					if (p != null && p.isVisible() && p.equals(TargetName)) {
						if (p.interact("Attack")) {
							Sleep.sleepUntil(() -> script.getCombat().isFighting() || script.myPlayer().getInteracting() != null, 10_000);
						}
					}
				}

			}
			if (!Main.set.contains(TargetName)) {
				Main.ATTACKER = false;
			}
		}

		

		return 100;
	}

	public String interactingPlayerName() {
		Player p = script.getPlayers().closest(new Filter<Player>() {
			@Override
			public boolean match(Player player) {
				return player != null && player.getInteracting() != null
						&& player.getInteracting().equals(script.myPlayer());
			}
		});
		return (p != null ? p.getName() : null);
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
