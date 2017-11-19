import org.osbot.rs07.script.Script;

public class Achieved7QP extends Task{

	public Achieved7QP(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getQuests().getQuestPoints() == 7;
	}

	@Override
	public int execute() throws Exception {
		script.log("Achieved 7 QP"); 
		
		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
