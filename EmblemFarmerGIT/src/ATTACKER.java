import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.Script;

public class ATTACKER extends Task{

	public ATTACKER(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int execute() throws Exception {
	Position p = script.myPlayer().getPosition();	
	int y = p.getY();
		
		
		//if underattack by the wrong player 
	
		
		
		return 100;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
