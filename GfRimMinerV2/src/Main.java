import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.ui.Tab;
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
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

@ScriptManifest(author = "aftabdear", name = "GfRimMinerV2", version = 1.0, logo = "", info = "")
public class Main extends Script implements MessageListener {
	private ArrayList<Task> tasks = new ArrayList<>();
	public static boolean pauseScript = false;
	private long startTime;
	public int rangeLevel;
	public int hitpointsLevel;
	public int defenceLevel;
	public static boolean spammer = true;
	public static ArrayList<String> verifiedMules = new ArrayList<>();
	public static LinkedList<String> accounts = new LinkedList<String>();
	public static Iterator<String> it;

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
	public static int spot = 0;

	public void loadAccountsToList() throws IOException {
		String filepath = getDirectoryData() + "/MulesIGN.txt";
		try (Stream<String> stream = Files.lines(Paths.get(filepath))) {
			stream.forEach(s -> {

				verifiedMules.add(s);
				log("Added: '" + s + "'");
				log(verifiedMules);
				log(accounts);

			});
		}

	}

	public void loadAccountsToList2() throws IOException {
		String filepath = getDirectoryData() + "/AccountsSpot1" + world + ".txt";
		try (Stream<String> stream = Files.lines(Paths.get(filepath))) {
			stream.forEach(s -> {
				accounts.add(s);

				log("Added: '" + s + "'");

				log("The Accounts " + accounts);

			});
		}
		it = Main.accounts.iterator();
		String output = Main.it.next();
		String[] splitString = output.split(":");
		String username = splitString[0];
		String password = splitString[1];

		autoLogin_autoReplacement.loginUsername = username;
		autoLogin_autoReplacement.loginPassword = password;
	}

	@Override
	public void onStart() {
		if (getParameters() != null) {
			String[] params = getParameters().split("_"); // params
			spot = Integer.parseInt(params[0]);
			world = Integer.parseInt(params[1]);// world to hop when mining
			worldToTrade = Integer.parseInt(params[2]); // world used to trade
														// the mule
			lowLevelMining1 = Integer.parseInt(params[3]); // int to start low
															// level mining from
			lowLevelMining2 = Integer.parseInt(params[4]); // int to start high
															// level mining from
			oreID_1_x = Integer.parseInt(params[5]); // x co-ordinate of low
														// level ore to mine
			oreID_1_y = Integer.parseInt(params[6]); // y co-ordinate of low
														// level ore to mine
			storeMiningPos1_1 = Integer.parseInt(params[7]);
			storeMiningPos1_2 = Integer.parseInt(params[8]);
			storeMiningPos1_3 = Integer.parseInt(params[9]);
			highLevelMining1 = Integer.parseInt(params[10]);
			highLevelMining2 = Integer.parseInt(params[11]);
			oreID_2_x = Integer.parseInt(params[12]);
			oreID_2_y = Integer.parseInt(params[13]);
			storeMiningPos2_1 = Integer.parseInt(params[14]);
			storeMiningPos2_2 = Integer.parseInt(params[15]);
			storeMiningPos2_3 = Integer.parseInt(params[16]);
		}

		try {
			loadAccountsToList();
			loadAccountsToList2();
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
		tasks.add(new Banking(this)); //// covers walking to mine
		tasks.add(new TradingMule(this)); // covers walking to mine
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

		// tasks.add(new WalkToGD(this));
		// tasks.add(new GDQuest(this));
		// tasks.add(new inDialogue(this));
		// tasks.add(new GDToFallyBank(this));

		rangeLevel = getSkills().getDynamic(Skill.RANGED);
		hitpointsLevel = getSkills().getDynamic(Skill.HITPOINTS);
		defenceLevel = getSkills().getDynamic(Skill.DEFENCE);

		startTime = System.currentTimeMillis();
	}

	@Override
	public int onLoop() throws InterruptedException { //I think that's it
		int getDay = Calendar.DAY_OF_WEEK;

		try {
			if (getDay >= 2 && getDay <= 6) { //is it friday or saturday one sec  wym friday u have to bot between 7- to whatever oh yea
				if (isTimeBetweenTwoTime("12:00:00", "07:00:00")) { 
					for (Task task : tasks) {
						if (task.verify())
							try {
								return task.execute();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				} else { 
					if (getClient().isLoggedIn()) {
						if (getTabs().getLogoutTab().logOut()) {
							Sleep.sleepUntil(() -> !getClient().isLoggedIn(), 5000); //Aight, I think this works yh me too
						}
					} else {
						log("We are sleeping within hours!");
						sleep(5000);
					}
				}
			} else if (getDay == 7 || getDay == 1) { 
				for (Task task : tasks) {
					if (task.verify())
						try {
							return task.execute();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 150;
	}

	public static String convertTime() {
		Date date = new Date(System.currentTimeMillis());
		Format format = new SimpleDateFormat("HH:mm:ss");
		return format.format(date.getTime());
	}

	public static boolean isTimeBetweenTwoTime(String argStartTime, String argEndTime) throws ParseException {
		String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
		//
		if (argStartTime.matches(reg) && argEndTime.matches(reg)) {
			boolean valid = false;
			// Start Time
			java.util.Date startTime = new SimpleDateFormat("HH:mm:ss").parse(argStartTime);
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(startTime);

			// Current Time
			String currentTimeFromMethod = convertTime();
			java.util.Date currentTime = new SimpleDateFormat("HH:mm:ss").parse(currentTimeFromMethod);
			Calendar currentCalendar = Calendar.getInstance();
			currentCalendar.setTime(currentTime);

			// End Time
			java.util.Date endTime = new SimpleDateFormat("HH:mm:ss").parse(argEndTime);
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endTime);

			//
			if (currentTime.compareTo(endTime) < 0) {

				currentCalendar.add(Calendar.DATE, 1);
				currentTime = currentCalendar.getTime();

			}

			if (startTime.compareTo(endTime) < 0) {

				startCalendar.add(Calendar.DATE, 1);
				startTime = startCalendar.getTime();

			}
			//
			if (currentTime.before(startTime)) {

				System.out.println(" Time is Lesser ");

				valid = false;
			} else {

				if (currentTime.after(endTime)) {
					endCalendar.add(Calendar.DATE, 1);
					endTime = endCalendar.getTime();

				}

				System.out.println("Comparing , Start Time /n " + startTime);
				System.out.println("Comparing , End Time /n " + endTime);
				System.out.println("Comparing , Current Time /n " + currentTime);

				if (currentTime.before(endTime)) {
					System.out.println("RESULT, Time lies b/w");
					valid = true;
				} else {
					valid = false;
					System.out.println("RESULT, Time does not lies b/w");
				}

			}
			return valid;

		} else {
			throw new IllegalArgumentException("Not a valid time, expecting HH:MM:SS format");
		}

	}

	// @Override
	// public void onMessage(Message message) throws InterruptedException {
	// if (message.getType() == Message.MessageType.PLAYER &&
	// message.getMessage().contains("Hello " + myPlayer().getName())) {
	// spammer = false; //Alright, this should do now show me the slave class
	// }
	// }

	@Override
	public void onPaint(Graphics2D g) {
		super.onPaint(g);
		g.setColor(Color.CYAN);
		g.drawString("Aftabdear's Automated", 20, 150);
		g.drawString("Rim Miner + 7QP", 20, 160);
		g.drawString("Spot " + spot, 20, 170);

		g.drawString("Time Ran (Minutes) : " + (int) ((System.currentTimeMillis() - this.startTime) / 60000), 20, 300);
		g.drawRect(15, 135, 200, 180);

	}
}