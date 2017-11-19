package DangerousMule2;
import org.osbot.rs07.api.Players;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.listener.MessageListener;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ScriptManifest(author = "aftabdear", name = "GfRimDangerousMule", version = 1.0, logo = "", info = "") // Okay.
public class Main extends Script implements MessageListener {

	static String[] itemNames = { "Pot of flour", "Egg", "Bucket of milk", "Blue dye", "Orange dye", "Clay",
			"Goblin mail", "Iron ore", "Copper ore", "Iron pickaxe", "Steel pickaxe", "Mithril pickaxe",
			"Adamant pickaxe", "Rune pickaxe" };
	private ArrayList<Task> tasks = new ArrayList<>();
	public static ArrayList<String> verifiedSlavers = new ArrayList<>();
	public static List<String> tradingItems = Arrays.asList(itemNames);
	public static String[] authorisedSlaves;
	private long startTime;
	//public static String safeMuleName;
	public static ArrayList<String> safeMuleName = new ArrayList<>();
	public static String username;
	
	//tcp socket stuff
		static Socket s1 = null;
	    static BufferedReader is = null;
	    static PrintWriter os = null;
	    BotClientListener clientListener = null;
	

	@Override
	public void onStart() {

		
		startTime = System.currentTimeMillis();

		tasks.add(new LogIn(this));
		tasks.add(new FailSafe1(this));
		tasks.add(new FailSafe2(this));
		tasks.add(new FailSafe3(this));
		tasks.add(new Mule(this));
		
		
		
		
		
		try {
			//sleep(20000);
            s1 = new Socket("localhost", 1337); 
            is = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            os = new PrintWriter(s1.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (s1.isConnected()) {  //sec
           // getResponseForString("USERNAME:" + myPlayer().getName().toString()); //think it'll connect to server now aight sec
            clientListener = new BotClientListener();
            clientListener.start();
        } else {
            stop();
        }

	}
	
	private void parseServerMessage(String message) {
        log(message);
        if (message.contains("*")) {
        	String[] splitString = message.split("\\*");
            String task = splitString[0]; 
            username = splitString[1];
           
            log(username);

            
        }else if (message.contains("gadga")) {
         	//getResponseForString(myPlayer().getName().toString());
         	
         }else if (message.contains("MEOWMEOW")) {
          	//getResponseForString("not much babe wbu?");
          	
          }else {
            switch (message) {
                case "LEVEL":
                    break;
            }
        }
    }
	
	//more like send message but named stupidly
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
    
    //monitors messages from server and parses them
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
	public void onPaint(Graphics2D g) {
		super.onPaint(g);
		g.setColor(Color.CYAN);
		g.drawString("Aftabdear's Automated", 20, 150);
		g.drawString("7QP Mule/Slave script", 20, 165);
		g.drawString("Fally Bank = Mule", 20, 180);
		g.drawString("Lumby Strairs = Slave", 20, 195);
		g.drawString("Time Ran (Minutes) : " + (int) ((System.currentTimeMillis() - this.startTime) / 60000), 20, 300);
		g.drawRect(15, 135, 200, 180);

	}
}