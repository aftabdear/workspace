import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.listener.MessageListener;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import java.util.StringTokenizer;

import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "aftabdear", name = "Counter", version = 1.0, logo = "", info = "")
public class Main extends Script implements MessageListener {
	private ArrayList<Task> tasks = new ArrayList<>();
	private long startTime;
	public int rangeLevel;
	public int hitpointsLevel;
	public int defenceLevel;
	public static boolean spammer = true;
	
	
	public static int oreID_1_x = 0;
	public static int oreID_1_y = 0;
	public static int storeMiningPos1_1 = 0;
	public static int storeMiningPos1_2 = 0;
	public static int storeMiningPos1_3 = 0;
	public static int world = 0;
	public static int storeMiningPos2_1 = 0;
	public static int storeMiningPos2_2 = 0;
	public static int storeMiningPos2_3 = 0;
	public static int lowLevelMining1 = 0;
	public static int lowLevelMining2 = 0;
	public static int highLevelMining1 = 0;
	public static int highLevelMining2 = 0;
	public static int oreID_2_x = 0;
	public static int oreID_2_y = 0;
	public static String Mule = "";
	public static String Slave = "";
	public static int worldToTrade = 0;
	public static int Counter = 0;
	
	@Override
	public void onStart() {
		
		//		tasks.add(new Achieved7QP(this));
		//tasks.add(new counter(this));
		tasks.add(new Fishing(this));
		tasks.add(new Fishing2(this));
		tasks.add(new GettingReadyForGF(this));
		tasks.add(new DepositBoxLoop(this));
		
		
		rangeLevel = getSkills().getDynamic(Skill.RANGED);
		hitpointsLevel = getSkills().getDynamic(Skill.HITPOINTS);
		defenceLevel = getSkills().getDynamic(Skill.DEFENCE);

		startTime = System.currentTimeMillis();
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
	public void onMessage(Message message) throws InterruptedException {
		if (message.getType() == Message.MessageType.PLAYER && message.getMessage().contains("Hello " + myPlayer().getName())) {
			spammer = false; 
		}
	}

	@Override
	public void onPaint(Graphics2D g) {
		super.onPaint(g);
		g.setColor(Color.CYAN);
		g.drawString("Aftabdear's Automated", 20, 150);
		g.drawString("Counter = " + Counter, 20, 160);
		g.drawString("Lobster GoldFarmer", 20, 170);

		/*
		 * g.drawString("Current Range Level: " +
		 * this.getSkills().getDynamic(Skill.RANGED) + "(" +
		 * (this.getSkills().getDynamic(Skill.RANGED) - rangeLevel) + ")", 20,
		 * 200); g.drawString("Current Defence Level: " +
		 * this.getSkills().getDynamic(Skill.DEFENCE) + "(" +
		 * (this.getSkills().getDynamic(Skill.DEFENCE) - defenceLevel) + ")",
		 * 20, 250);
		 * 
		 * g.drawString("Current Hitpoints Level: " +
		 * this.getSkills().getDynamic(Skill.HITPOINTS) + "(" +
		 * (this.getSkills().getDynamic(Skill.HITPOINTS) - hitpointsLevel) +
		 * ")", 20, 225);
		 */ g.drawString("Time Ran (Minutes) : " + (int) ((System.currentTimeMillis() - this.startTime) / 60000), 20,
				300);
		g.drawRect(15, 135, 200, 180);

	}
}