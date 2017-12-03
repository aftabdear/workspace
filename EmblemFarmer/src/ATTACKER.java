import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;

public class ATTACKER extends Task{

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
	
		
		int x = script.myPlayer().getX();
		int y = 3524;
		
		Position runToSafeArea = new Position(x,y,0);
		
		
		//if underattack by the wrong player 
		if (script.myPlayer().isUnderAttack()){
			if (!Main.set.contains(interactingPlayerName())){
				if (script.myPlayer().getY() != 3524){	
					WalkingEvent wEvent = new WalkingEvent(runToSafeArea);
					wEvent.setMinDistanceThreshold(0);
				}
				
				
				if (script.myPlayer().getY() == 3524){
						Main.RELOCATE = true;
				}
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
