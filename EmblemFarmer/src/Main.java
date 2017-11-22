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

	//tcp socket stuff
	static Socket s1 = null;
    static BufferedReader is = null;
    static PrintWriter os = null;
    BotClientListener clientListener = null;
    
    Item tier1 = getInventory().getItem("Mysterious emblem");
    Item tier2 = getInventory().getItem("Mysterious emblem (tier 2)");




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
           // getResponseForString("USERNAME:" + myPlayer().getName().toString()); //think it'll connect to server now aight sec
            clientListener = new BotClientListener();
            clientListener.start();
        } else {
            stop();
        }
	}

	@Override
	public int onLoop() throws InterruptedException { //I think that's it
		
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

	public static Integer arrayTiers[] = {
		1,2,3,4,5,6,7,8 	
	};
	
	
	private void parseServerMessage(String message) {
        log(message);
        if (message.contains("$") && message.contains(myPlayer().getName().toString())) {
        	
        	//send the tier level you have
        	getResponseForString("£"+""+ message.contains(myPlayer().getName().toString()));
        	
        }else if (message.contains("£-1")){
         	String msg[] = message.split("-");
         	String NA = msg[0];
         	String Tier = msg[1];
         	
         	
         	
         }else if (message.contains("asdasd")) {
          	
          	
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
	public void onPaint(Graphics2D g) {
		super.onPaint(g);
		g.setColor(Color.CYAN);
		g.drawString("Aftabdear's Automated", 20, 150);
		g.drawString("Emblem Farmer", 20, 160);
		g.drawString("Time Ran (Minutes) : " + (int) ((System.currentTimeMillis() - this.startTime) / 60000), 20, 300);
		g.drawRect(15, 135, 200, 180);

	}
}