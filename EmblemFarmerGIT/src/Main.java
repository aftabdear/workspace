import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Item;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

@ScriptManifest(author = "aftabdear", name = "EmblemFarmer", version = 1.0, logo = "", info = "")
public class Main extends Script implements MessageListener {
	private ArrayList<Task> tasks = new ArrayList<>();
	public static boolean pauseScript = false;
	private long startTime;
	public int rangeLevel;
	public int hitpointsLevel;
	public int defenceLevel;
	public static boolean ATTACKER = false;
	public static boolean BYSTANDER = false;
	public static boolean RELOCATE = false;
	public static ArrayList<String> verifiedBots = new ArrayList<>();
	public static LinkedList<String> accounts = new LinkedList<String>();
	public static Iterator<String> it;
	public static int ourTier = 2;
	public static String IGN;
	public static String relocationArea;
	private LoginEvent loginEvent;
	public static HashSet<String> set = new HashSet<>(verifiedBots);

	// tcp socket stuff
	static Socket s1 = null;
	static BufferedReader is = null;
	static PrintWriter os = null;
	BotClientListener clientListener = null;

	public static Integer arrayTiers[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	public static String loginUsername = "micahxbrisk@yahoo.com";
	public static String loginPassword = "oldschool123";

	@Override
	public void onStart() throws InterruptedException {

		loginEvent = new LoginEvent(loginUsername, loginPassword);
		getBot().addLoginListener(loginEvent);
		sleep(20000);
		execute(loginEvent);
		sleep(5000);
		IGN = myPlayer().getName().toString();
		getSettings().getLogoutTab().logOut();

		tasks.add(new WaitingForTarget(this));
		tasks.add(new AssignedATarget(this));
		tasks.add(new TargetWidgetIsNotVisible(this));
		tasks.add(new ATTACKER(this));
		tasks.add(new WalkToRandomArea(this));

		startTime = System.currentTimeMillis();

		try {
			s1 = new Socket("localhost", 1338);
			is = new BufferedReader(new InputStreamReader(s1.getInputStream()));
			os = new PrintWriter(s1.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (s1.isConnected()) {
			clientListener = new BotClientListener();
			clientListener.start();
		} else {
			stop();
		}
	}

	@Override
	public int onLoop() throws InterruptedException { // I think that's it

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

	private void parseServerMessage(String message) {
		log(message);
		if (message.contains("$") && message.contains(myPlayer().getName().toString())) {

			Item tier1 = getInventory().getItem("Mysterious emblem");
			Item tier2 = getInventory().getItem("Mysterious emblem (tier 2)");
			Item tier3 = getInventory().getItem("Mysterious emblem (tier 3)");
			Item tier4 = getInventory().getItem("Mysterious emblem (tier 4)");
			Item tier5 = getInventory().getItem("Mysterious emblem (tier 5)");
			Item tier6 = getInventory().getItem("Mysterious emblem (tier 6)");
			Item tier7 = getInventory().getItem("Mysterious emblem (tier 7)");
			Item tier8 = getInventory().getItem("Mysterious emblem (tier 8)");
			Item tier9 = getInventory().getItem("Mysterious emblem (tier 8)");
			Item tier10 = getInventory().getItem("Mysterious emblem (tier 10)");

		} else if (message.contains("£-1") && message.contains(myPlayer().getName().toString())) { // the
																									// targets
																									// tier
																									// is
																									// 1

			Item tier1 = getInventory().getItem("Mysterious emblem");

			String msg[] = message.split("-");
			String NA = msg[0];
			String StringTier = msg[1];
			String Username = msg[2];

			int Player2sTier = Integer.parseInt(StringTier);

			if (ourTier > Player2sTier) {
				log("We have a higher Tier");
				ATTACKER = true;
			}
			if (ourTier == Player2sTier) {
				log("We have an equal Tier");
			}
			if (ourTier < Player2sTier) {
				log("We have a lower Tier");
				BYSTANDER = true;
			}

		} else if (message.contains("connected from")) {
			// request usernames to be resent to update arrayList
			getResponseForString("&SENDUSERNAMES");

		} else if (message.contains("&")) {
			// send your username
			getResponseForString("+-" + IGN);

		} else if (message.contains("+")) {
			// split string and add to arrayList
			String msg[] = message.split("-");
			String addSymbol = msg[0];
			String arrayUsername = msg[1];

			verifiedBots.add(arrayUsername);

			log(set);

		} else if (message.contains("^") && message.contains(myPlayer().getName().toString())) {

			// split string and add to arrayList
			String msg[] = message.split("-");
			String symbol = msg[0];
			String myName = msg[1];
			String Area = msg[2];

			if (Area.equals("a")) {
				relocationArea = "a";
			}

		} else {
			switch (message) {
			case "LEVEL":
				break;
			}
		}
	}

	// more like send message but named stupidly
	public static void getResponseForString(String message) {
		if (os == null) {
			try {
				s1 = new Socket("localhost", 1337);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				is = new BufferedReader(new InputStreamReader(s1.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				os = new PrintWriter(s1.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		os.println(message);
		os.flush();

	}

	// monitors messages from server and parses them
	private class BotClientListener extends Thread {

		@Override
		public void run() {
			InputStream is = null;
			try {
				is = s1.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			while (s1.isConnected() || s1.isBound()) {
				String message = null;
				try {
					message = br.readLine();
					parseServerMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void onPaint(Graphics2D g) {
		super.onPaint(g);
		g.setColor(Color.CYAN);
		g.drawString("Aftabdear's Automated", 20, 150);
		g.drawString("EF", 20, 160);
		g.drawString("Time Ran (Minutes) : " + (int) ((System.currentTimeMillis() - this.startTime) / 60000), 20, 300);
		g.drawRect(15, 135, 200, 180);

	}
}