package SafeMule;
import org.osbot.rs07.api.Players;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.listener.MessageListener;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;


import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ScriptManifest(author = "aftabdear", name = "GfRimSafeMule", version = 1.0, logo = "", info = "") // Okay.
public class Main extends Script implements MessageListener {

	static String[] itemNames = { "Pot of flour", "Egg", "Bucket of milk", "Blue dye", "Orange dye", "Clay",
			"Goblin mail", "Iron ore", "Copper ore", "Iron pickaxe", "Steel pickaxe", "Mithril pickaxe",
			"Adamant pickaxe", "Rune pickaxe" };
	private ArrayList<Task> tasks = new ArrayList<>();
	public static ArrayList<String> verifiedSlavers = new ArrayList<>();
	public static List<String> tradingItems = Arrays.asList(itemNames);
	public static String[] authorisedSlaves;
	private long startTime;
	private LoginEvent last;
	private LoginEvent loginEvent;
	public static String loginUsername = "";
	public static String loginPassword = "";
	public static String ign = "";

	
	
	
	//tcp socket stuff
		static Socket s1 = null;
	    static BufferedReader is = null;
	    static PrintWriter os = null;
	    BotClientListener clientListener = null;
	

	@Override
	public void onStart() throws InterruptedException {
		grabFirstAccount(); 
        loginEvent = new LoginEvent(loginUsername, loginPassword);
        getBot().addLoginListener(loginEvent);
        last = loginEvent; 
        sleep(1000); 
        execute(loginEvent);
		sleep(20000);
		ign = myPlayer().getName().toString();
		getTabs().open(Tab.LOGOUT);
		getWidgets().get(182, 6).interact("Logout");

		
		startTime = System.currentTimeMillis();

		tasks.add(new FailSafe1(this));
		tasks.add(new FailSafe2(this));
		tasks.add(new FailSafe3(this));
		tasks.add(new Mule(this));
		//tasks.add(new LogIn(this));
		
		
		
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
        if (message.contains("NEEDMULE")) {
        	
        	
        	
        	Player lastRequesting = getTrade().getLastRequestingPlayer();
        	log("we recieved needmule");
        	if (last != null) {
    			getBot().removeLoginListener(last);
    		}
    		grabFirstAccount();
    		loginEvent = new LoginEvent(loginUsername, loginPassword);
    		getBot().addLoginListener(loginEvent);
    		last = loginEvent;
    		execute(loginEvent);

    		if (getClient().isLoggedIn()) {
    			Main.getResponseForString("MULE*" + ign);

    			if (lastRequesting != null && lastRequesting.isVisible()) {
    				if (!getTrade().isCurrentlyTrading()) {
    					if (lastRequesting.interact("Trade with")) {
    						Sleep.sleepUntil(() -> getTrade().isCurrentlyTrading(), 10_000);
    					}
    				}
    			}

    		}
    		
    		
    		
    		
        }else if (message.contains("gadga")) {
         	//getResponseForString(myPlayer().getName().toString());
         	
         }else if (message.contains("asdasd")) {
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
  
  public void grabFirstAccount() { 
      InputStream in = null;
      try {
          in = new FileInputStream(new File(getDirectoryData() + "/SafeMules.txt"));
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      }
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      StringBuilder out = new StringBuilder();
      String line;
      try {
          if ((line = reader.readLine()) != null) {
              out.append(line);
          }

          String grabFullName = out.toString();
          String[] splitString = grabFullName.split(":");
          String username = splitString[0]; 
          String password = splitString[1]; 

          loginUsername = username; 
          loginPassword = password;
      } catch (IOException e) {
          e.printStackTrace();
      }

      log(loginUsername);
      log(loginPassword);


      try {
          reader.close();
      } catch (IOException e) {
          e.printStackTrace();
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