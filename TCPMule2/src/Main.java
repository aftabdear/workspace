import org.osbot.rs07.api.Tabs;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;



import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

@ScriptManifest(author = "aftabdear", name = "TCPMule2", version = 1.0, logo = "", info = "")
public class Main extends Script {
	private ArrayList<Task> tasks = new ArrayList<>();
	private long startTime;
    static Socket s1 = null;
    static BufferedReader is = null;
    static PrintWriter os = null;
    BotClientListener clientListener = null;
    private final LinkedList<String> accounts = new LinkedList<>();
    private static String un;
    private static String pw;
    private static Iterator<String> accountsIterator;
    
    
//    public static String username = ""; //now connect it to notepad
//    public static String password = ""; //What are all those non sense for? xd We using them? lol we did oh
    
//    public static String usernameAndPass = null; //ugh dont think so sec
    
//    public static String loginUsername = "";
//    public static String loginPassword = "";
//    private LoginEvent loginEvent;

   
	//fock send me the acc again lmao ac locicpdweeb@icloud.com	TgSCnsKzAzwH //You got a loop? lol
	
    
//    public void loadAccountsToList() throws IOException{
//    	String filepath = getDirectoryData() + "/Accounts.txt";
//    	try(Stream<String> stream = Files.lines(Paths.get(filepath))){
//    		stream.forEach(s ->{
//    			accounts.add(s);
//    			log("Added: '"+s+"'");
//    		});
//    	}
//    }
//    
//    public void initIterator(){
//    	accountsIterator = accounts.iterator();
//    	if(accountsIterator.hasNext()){
//    		String raw = accountsIterator.next();
//    		un = raw.split(":")[0];
//    		pw = raw.split(":")[1];
//    		LogIn.loginUsername = un;
//    		LogIn.loginPassword = pw;
//    	}
//    }
//	
//    
//    public static void nextAccount(){
//    	if(accountsIterator.hasNext()){
//    		String raw = accountsIterator.next();
//    		un = raw.split(":")[0];
//    		pw = raw.split(":")[1];
//    		LogIn.loginUsername = un;
//    		LogIn.loginPassword = pw;
//    		//something is wrong in the text file
//    	}else{
//    		//uh whatever lol
//    	}
//    }
    
	@Override
	public void onStart() throws InterruptedException {
//		try {
//			loadAccountsToList();		
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		initIterator();
		
//		while(!getClient().isLoggedIn()) { 
//			InputStream in = null;
//	        try {
//	            in = new FileInputStream(new File(getDirectoryData() + "/Accounts.txt"));
//	        } catch (FileNotFoundException e) {
//	            e.printStackTrace();
//	        }
//	        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//	        StringBuilder out = new StringBuilder();
//	        String line;
//	        try {
//	            if ((line = reader.readLine()) != null) {
//	                out.append(line);
//	            }
//
//	            String grabFullName = out.toString();
//	            String[] splitString = grabFullName.split(":");
//	            String username = splitString[0]; // 004
//	            String password = splitString[1]; // 034556
//
//	            loginUsername = username;
//	            loginPassword = password;
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//
//	        log(loginUsername);
//	        log(loginPassword);
//
//
//	        try {
//	            reader.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	     
//			
//	        sleep(5000); //l0l
//			
//			autoLogin();
//		}
		
		
		
//		sleep(20000);
//		getTabs().open(Tab.LOGOUT); //ah yh
//		getWidgets().get(182, 6).interact("Logout");
		tasks.add(new LogIn(this));
		startTime = System.currentTimeMillis(); 
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
	
        //getBot().getUsername()
	
	}
	
//	public void autoLogin() { //may as well do the checks in here, hmm, sec
//		LoginEvent loginEvent = new LoginEvent(LogIn.loginUsername, LogIn.loginPassword);
//		getBot().addLoginListener(loginEvent);
//
//		
//		execute(loginEvent);
//	}
	
	private void parseServerMessage(String message) {
        log(message);
        if (message.contains("BANNED") && message.contains(getBot().getUsername())) {
            //close client
        	log("in banned statement");
        } else if (message.contains("Msadasd")) {
           //mule ect
        	log("in mule statement");
        }else if (message.contains("YOYO")) {
            // ect
         	log("in hi baby statement");
         	//on = true;
         	getResponseForString(myPlayer().getName().toString());
         	
         }else if (message.contains("MEOWMEOW")) {
             // ect
          	log("in meowmeow statement");
          	
          	getResponseForString("not much babe wbu?");
          	
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
	 public void onExit() throws InterruptedException {
	        try {
	            getResponseForString("QUIT");
	            s1.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
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
		g.drawString("TCP Mule", 20, 150);
		g.drawString("Time Ran (Minutes) : " + (int) ((System.currentTimeMillis() - this.startTime) / 60000), 20, 300);
		g.drawRect(15, 135, 200, 180);
		

	}
}