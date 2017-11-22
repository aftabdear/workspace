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
	public static boolean TASK = false;
	public static ArrayList<String> verifiedMules = new ArrayList<>();
	public static LinkedList<String> accounts = new LinkedList<String>();
	public static Iterator<String> it;

	// tcp socket stuff
	static Socket s1 = null;
	static BufferedReader is = null;
	static PrintWriter os = null;
	BotClientListener clientListener = null;

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

	@Override
	public void onStart() throws InterruptedException {

		tasks.add(new WaitingForTarget(this));
		tasks.add(new AssignedATarget(this));
		tasks.add(new TargetWidgetIsNotVisible(this));

		startTime = System.currentTimeMillis();

		try {
			sleep(5000);
			s1 = new Socket("localhost", 1337);
			is = new BufferedReader(new InputStreamReader(s1.getInputStream()));
			os = new PrintWriter(s1.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (s1.isConnected()) {
			// getResponseForString("USERNAME:" +
			// myPlayer().getName().toString()); //think it'll connect to server
			// now aight sec
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

	public static Integer arrayTiers[] = { 1, 2, 3, 4, 5, 6, 7, 8 };

	private void parseServerMessage(String message) {
		log(message);
		if (message.contains("\\$") && message.contains(myPlayer().getName().toString())) {

			if (tier1 != null) {
				getResponseForString("�-1-" + message.contains(myPlayer().getName().toString()));
			}
			if (tier2 != null) {
				getResponseForString("�-2" + "" + message.contains(myPlayer().getName().toString()));
			}
			if (tier3 != null) {
				getResponseForString("�-3" + "-" + message.contains(myPlayer().getName().toString()));
			}
			if (tier4 != null) {
				getResponseForString("�-4" + "-" + message.contains(myPlayer().getName().toString()));
			}
			if (tier5 != null) {
				getResponseForString("�-5" + "-" + message.contains(myPlayer().getName().toString()));
			}
			if (tier6 != null) {
				getResponseForString("�-6" + "-" + message.contains(myPlayer().getName().toString()));
			}
			if (tier7 != null) {
				getResponseForString("�-7" + "-" + message.contains(myPlayer().getName().toString()));
			}
			if (tier8 != null) {
				getResponseForString("�-8" + "-" + message.contains(myPlayer().getName().toString()));
			}
			if (tier9 != null) {
				getResponseForString("�-9" + "-" + message.contains(myPlayer().getName().toString()));
			}
			if (tier10 != null) {
				getResponseForString("�-10" + "-" + message.contains(myPlayer().getName().toString()));
			}

		} else if (message.contains("�-1") && message.contains(myPlayer().getName().toString())) {
			String msg[] = message.split("-");
			String NA = msg[0];
			String StringTier = msg[1];
			String Username = msg[2];
			
			int Tier = Integer.parseInt(StringTier);

			if (tier1 != null) {
				log("we have a tier 1");
				int oTier1 = 1;

				if (Tier >= oTier1) {
					log("They have a higher tier then us");
				}
				if (Tier == oTier1) {
					log("They have the same tier as us");
				}
				if (Tier <= oTier1) {
					log("They have a lower tier then us");
				}
			}

		} else if (message.contains("asdasd")) {

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
		g.drawString("Emblem Farmer", 20, 160);
		g.drawString("Time Ran (Minutes) : " + (int) ((System.currentTimeMillis() - this.startTime) / 60000), 20, 300);
		g.drawRect(15, 135, 200, 180);

	}
}