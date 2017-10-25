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

@ScriptManifest(author = "aftabdear", name = "AutomatedCannonOgre", version = 1.0, logo = "", info = "") // Okay.
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
	public static boolean beenCrashed = false;

	@Override
	public void onStart() {
		
		getExperienceTracker().start(Skill.RANGED);

		//int rangexp =getExperienceTracker().getGainedXPPerHour(Skill.RANGED);
		startTime = System.currentTimeMillis();

		tasks.add(new AttackingOgres(this));
		tasks.add(new WalkToGE(this));
		tasks.add(new WalkToOgres(this));
		tasks.add(new NoDarts(this));
		tasks.add(new NoCannonballs(this));
		tasks.add(new NoVarrockTablet(this));
		tasks.add(new NoArdyTablet(this));
		tasks.add(new NoPot(this));
		
		
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
	} // think we got rid of the listener he no its

	@Override
	public void onMessage(Message message) throws java.lang.InterruptedException {
		
		String msg = message.getMessage();
		
//		if (msg.contains("That isn't your cannon!")){
//			beenCrashed= true;
//		}
//		
		if (msg.contains("There isn't enough space to set up here.")){
			beenCrashed= true;
		}
		
		/*String msg = message.getMessage();
		String caBotString = message.getUsername();

		Player caBotPlayer = getPlayers().closest(caBotString);
		
		log(msg);
		
		

		if (msg.contains("Hello")) {
			if (caBotString != myPlayer().getName() && !verifiedSlavers.contains(caBotString)) {
				getKeyboard().typeString("Hello " + caBotString);
				verifiedSlavers.add(caBotString);
				log(caBotPlayer + "requires items for cooks assistant");
			}
		} */
	}

	@Override
	public void onPaint(Graphics2D g) {
		super.onPaint(g);
		g.setColor(Color.CYAN);
		g.drawString("Aftabdear's Automated", 20, 150);
		g.drawString("Xp per hour: " +  String.valueOf(getExperienceTracker().getGainedXPPerHour(Skill.RANGED)) , 20, 165);
		g.drawString("TTL: " +  String.valueOf(getExperienceTracker().getTimeToLevel(Skill.RANGED)) , 20, 180);
		g.drawString("Time Ran (Minutes) : " + (int) ((System.currentTimeMillis() - this.startTime) / 60000), 20, 300);
		g.drawRect(15, 135, 200, 180);

	}
}