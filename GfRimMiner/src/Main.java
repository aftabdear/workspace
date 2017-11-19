import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.listener.MessageListener;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.util.StringTokenizer;
import java.util.stream.Stream;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@ScriptManifest(author = "aftabdear", name = "GfRimMiner", version = 1.0, logo = "", info = "")
public class Main extends Script implements MessageListener {
	private ArrayList<Task> tasks = new ArrayList<>();
	private long startTime;
	public int rangeLevel;
	public int hitpointsLevel;
	public int defenceLevel;
	public static boolean spammer = true;
	public static ArrayList<String> verifiedMules = new ArrayList<>();
	
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
	
	
	
    public void loadAccountsToList() throws IOException{
    	String filepath = getDirectoryData() + "/MulesIGN.txt";
    	try(Stream<String> stream = Files.lines(Paths.get(filepath))){
    		stream.forEach(s ->{
//    			accounts.add(s);
    			verifiedMules.add(s);
    			log("Added: '"+s+"'");
    			log(verifiedMules);
    		});
    	}
    }
	
	@Override
	public void onStart() {
		if (getParameters() != null) {
			String[] params = getParameters().split("_"); //params
			world = Integer.parseInt(params[0]);//world to hop when mining 
			worldToTrade = Integer.parseInt(params[1]); //world used to trade the mule
			lowLevelMining1 = Integer.parseInt(params[2]); //int to start low level mining from
			lowLevelMining2 = Integer.parseInt(params[3]); //int to start high level mining from
			oreID_1_x = Integer.parseInt(params[4]); //x co-ordinate of low level ore to mine
			oreID_1_y = Integer.parseInt(params[5]); //y co-ordinate of low level ore to mine
			storeMiningPos1_1 = Integer.parseInt(params[6]);
			storeMiningPos1_2 = Integer.parseInt(params[7]);
			storeMiningPos1_3 = Integer.parseInt(params[8]);
			highLevelMining1 = Integer.parseInt(params[9]);
			highLevelMining2 = Integer.parseInt(params[10]);
			oreID_2_x = Integer.parseInt(params[11]);
			oreID_2_y = Integer.parseInt(params[12]);
			storeMiningPos2_1 = Integer.parseInt(params[13]);
			storeMiningPos2_2 = Integer.parseInt(params[14]);
			storeMiningPos2_3 = Integer.parseInt(params[15]);
		}
			
		try {
			loadAccountsToList();
			sleep(10000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log("make sure to start with alow -norandoms otherwise it wont autoreplace the accounts");
		log("position to mine before 16 mining" + Areas.miningPos1);
		log("world to hop to when at the mine" + world);
		log("ore x=" + " " + oreID_1_x + "ore y=" + " " + oreID_1_y);
		
		tasks.add(new autoLogin_autoReplacement(this));
		tasks.add(new WalkToRJ(this));
		tasks.add(new speakingToJuliet1(this));
		tasks.add(new InBalcony(this));
		tasks.add(new Config1(this));
		tasks.add(new Config2(this));
		tasks.add(new Config3(this));
		tasks.add(new Config4(this));
		tasks.add(new Config5(this));
		tasks.add(new WaitingForBerry(this));
		tasks.add(new WalkToBerries(this));
		tasks.add(new DQToBank(this));
		
		tasks.add(new FailSafe1(this));
		tasks.add(new FailSafe2(this));
		tasks.add(new FailSafe3(this));
		tasks.add(new FailSafe4(this));
		tasks.add(new Banking(this)); ////covers walking to mine
		tasks.add(new TradingMule(this)); //covers walking to mine
		tasks.add(new RimmingtonMine(this));
		tasks.add(new MineToFallyBank(this));
		
		tasks.add(new FreshAcc1(this));
		tasks.add(new WaitingForItems(this));
		tasks.add(new WalkToCA(this));
		tasks.add(new WalkToCA2(this));
		tasks.add(new WalkToCA3(this));
		tasks.add(new WalkToDQ(this));
		
		tasks.add(new WalkToBankToUnote(this));
		tasks.add(new CAQuest(this));
		tasks.add(new DQQuest(this));
		
		//tasks.add(new WalkToGD(this));
		//tasks.add(new GDQuest(this));
		//tasks.add(new inDialogue(this));
		//tasks.add(new GDToFallyBank(this));
		
		
		

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
	
	
	 


//	@Override
//	public void onMessage(Message message) throws InterruptedException {
//		if (message.getType() == Message.MessageType.PLAYER && message.getMessage().contains("Hello " + myPlayer().getName())) {
//			spammer = false; //Alright, this should do now show me the slave class
//		}
//	}

	@Override
	public void onPaint(Graphics2D g) {
		super.onPaint(g);
		g.setColor(Color.CYAN);
		g.drawString("Aftabdear's Automated", 20, 150);
		g.drawString("Rim Miner + 7QP", 20, 160);

		g.drawString("Time Ran (Minutes) : " + (int) ((System.currentTimeMillis() - this.startTime) / 60000), 20,
				300);
		g.drawRect(15, 135, 200, 180);

	}
}