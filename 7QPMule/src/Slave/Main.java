package Slave;
import org.osbot.rs07.api.Players;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.listener.MessageListener;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ScriptManifest(author = "aftabdear", name = "GFRimSlave", version = 1.0, logo = "", info = "") // Okay.
public class Main extends Script implements MessageListener {

	static String[] itemNames = { "Pot of flour", "Egg", "Bucket of milk", "Blue dye", "Orange dye", "Clay",
			"Goblin mail", "Iron ore", "Copper ore", "Iron pickaxe", "Steel pickaxe", "Mithril pickaxe",
			"Adamant pickaxe", "Rune pickaxe" };
	private ArrayList<Task> tasks = new ArrayList<>();
	public static ArrayList<String> verifiedSlavers = new ArrayList<>();
	public static List<String> tradingItems = Arrays.asList(itemNames);
	public static String[] authorisedSlaves;
	private long startTime;
	public int miningLevel;
	public int hitpointsLevel;
	public int defenceLevel;
	public static String hello;

	@Override
	public void onStart() {

		miningLevel = getSkills().getDynamic(Skill.MINING);
		startTime = System.currentTimeMillis();

		tasks.add(new Slave(this));
		

	}

	@Override
	public int onLoop() throws InterruptedException {

		for (Task task : tasks) {
			if (task.verify())
				try {
					return task.execute();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return 150;
	} 

	@Override
	public void onMessage(Message message) throws java.lang.InterruptedException {
		
		
		String msg = message.getMessage();
		String caBotString = message.getUsername(); // Yeah, would work cuz we
													// can't access this
													// variable <--- hmm, and we
													// have to store them I
													// guess sec

		Player caBotPlayer = getPlayers().closest(caBotString);
		
		log(msg);
		
		

		if (msg.contains("Hello")) {
			if (caBotString != myPlayer().getName() && !verifiedSlavers.contains(caBotString)) {
				getKeyboard().typeString("Hello " + caBotString);
				verifiedSlavers.add(caBotString);
				log(caBotPlayer + "requires items for cooks assistant");
			}
		} 
	}

	@Override
	public void onPaint(Graphics2D g) {
		super.onPaint(g);
		g.setColor(Color.CYAN);
		g.drawString("Aftabdear's Automated", 20, 150);
		g.drawString("7QP Mule/Slave script", 20, 165);
		g.drawString("Fally Bank = Mule", 20, 180);
		g.drawString("Lumby Strairs = Slave", 20, 195);
		// g.drawString("Killer Single Spot 1", 20, 180);
		// g.drawString("Current Mining Level: " +
		// this.getSkills().getDynamic(Skill.MINING) + "("
		// + (this.getSkills().getDynamic(Skill.MINING) - miningLevel) + ")",
		// 20, 200);
		// g.drawString("Current Defence Level: " +
		// this.getSkills().getDynamic(Skill.DEFENCE) + "(" +
		// (this.getSkills().getDynamic(Skill.DEFENCE) - defenceLevel) + ")",
		// 20, 250);
		//
		// g.drawString("Current Hitpoints Level: " +
		// this.getSkills().getDynamic(Skill.HITPOINTS) + "(" +
		// (this.getSkills().getDynamic(Skill.HITPOINTS) - hitpointsLevel) +
		// ")", 20, 225);
		g.drawString("Time Ran (Minutes) : " + (int) ((System.currentTimeMillis() - this.startTime) / 60000), 20, 300);
		g.drawRect(15, 135, 200, 180);

	}
}