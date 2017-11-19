import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.constants.ResponseCode;
import org.osbot.rs07.event.Event;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.listener.LoginResponseCodeListener;
import org.osbot.rs07.utility.ConditionalSleep;

import java.awt.*;
import java.io.*;
import java.util.Iterator;
import java.awt.event.KeyEvent;

/**
 * Created by Viston on 12/11/2017.
 */

public final class LoginEvent extends Event implements LoginResponseCodeListener { //fam  u carry on just gonna grab food for gf aight

    Main main = new Main();
    private final String username, password;

    public LoginEvent(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    @ Override
    public final int execute() throws InterruptedException {
        if (getClient().isLoggedIn() && getLobbyButton() == null) {
            setFinished();
        } else if (getLobbyButton() != null) {
            clickLobbyButton();
        } else if (isOnWorldSelectorScreen()) {
            cancelWorldSelection();
        } else if (!isPasswordEmpty()) {
            clickCancelLoginButton();
        } else {
            login();
        }
        return random(100, 150);
    }

    private boolean isOnWorldSelectorScreen() {
        return getColorPicker().isColorAt(50, 50, Color.BLACK);
    }

    private void cancelWorldSelection() {
        if (getMouse().click(new RectangleDestination(getBot(), 712, 8, 42, 8))) {
            new ConditionalSleep(3000) {
                @ Override
                public boolean condition() throws InterruptedException {
                    return !isOnWorldSelectorScreen();
                }
            }.sleep();
        }
    }

    private boolean isPasswordEmpty() {
        return !getColorPicker().isColorAt(350, 274, Color.WHITE);
    }

    private boolean clickCancelLoginButton() {
        return getMouse().click(new RectangleDestination(getBot(), 398, 308, 126, 27));
    }

    private void login() {
        switch (getClient().getLoginUIState()) {
            case 0:
                clickExistingUsersButton();
                break;
            case 1:
                clickLoginButton();
                break;
            case 2:
                enterUserDetails();
                break;
            case 3:
                clickTryAgainButton();
                break;
        }
    }

    private void clickExistingUsersButton() {
        getMouse().click(new RectangleDestination(getBot(), 400, 280, 120, 20));
    }

    private void clickLoginButton() {
        getMouse().click(new RectangleDestination(getBot(), 240, 310, 120, 20));
    }

    private void enterUserDetails() {

        typeStringInstant(username);
        try {
            sleep(800);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        typeStringInstant(password);


        new ConditionalSleep(30_000) {
            @ Override
            public boolean condition() throws InterruptedException {
                return getLobbyButton() != null || getClient().getLoginUIState() == 4 || isDisabledMessageVisible() || getClient().getLoginUIState() == 5;
            }
        }.sleep();
        
       /* if(getClient().getLoginUIState() == 5 ){
        	log("login state is 5");
        	getMouse().click(new RectangleDestination(getBot(), 398, 308, 126, 27));
        	nextAccount();
        }*/

        if (!getClient().isLoggedIn()) {
            setFailed();
        }
    }

    private boolean clickTryAgainButton() {
        return getMouse().click(new RectangleDestination(getBot(), 318, 262, 130, 26));
    }

    private boolean isDisabledMessageVisible() {
        return getColorPicker().isColorAt(480, 211, Color.YELLOW);
    }

    private void clickLobbyButton() {
        if (getLobbyButton().interact()) {
            new ConditionalSleep(10_000) {
                @ Override
                public boolean condition() throws InterruptedException {
                    return getLobbyButton() == null;
                }
            }.sleep();
        }
    }

    private RS2Widget getLobbyButton() {
        return getWidgets().getWidgetContainingText("CLICK HERE TO PLAY");
    }

    @Override
    public final void onResponseCode(final int responseCode) throws InterruptedException {
        if(responseCode == 4){
            log(responseCode); 
         
            getMouse().click(new RectangleDestination(getBot(), 398, 308, 126, 27));
//					try {
//						removeFirstAccount();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					autoLogin_autoReplacement.loginPassword = null;
//					autoLogin_autoReplacement.loginPassword = null;
//					grabNewAccount();
            	try {
            		sleep(random(600000,900000)); 
					grabAndDelete();
				} catch (IOException e) {
					// TODO Auto-generated catch block //skype
					e.printStackTrace();
				}
				
            setFailed();
            return;
        }
        
        if (responseCode == 5) { //seems like this isn't working //It's not getting executed hmmm //so weird fam yhh //damn, fuck dunno fam :/, //Fam, I gotta go for an hour or two, sorting some stuff out ttyl tonight or tomorrow  (NY)np
        	log("response code is 5");
        	
        	
        	getMouse().click(new RectangleDestination(getBot(), 398, 308, 126, 27)); //Click cancel and reset 
        	
        	
        	//nextAccount();
        	setFailed(); 
        
        }

        if(ResponseCode.isConnectionError(responseCode)) { //come on my other monitor 
            log("Connection error, attempts exceeded"); //its executing this just move the code here, hmm don't think so too :/ Yeah, that's only if I pause then run again, same with this
            setFailed(); 
            return;
        }
    }

    public final void typeStringInstant(final String output){
        for(int i = 0; i < output.length(); i ++){
            getBot().getKeyEventHandler().generateBotKeyEvent(KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, output.charAt(i));
        }
        try {
            sleep(250);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        keyboard.pressKey(KeyEvent.VK_ENTER);
        //getBot().getKeyEventHandler().generateBotKeyEvent(KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, (char) KeyEvent.VK_ENTER);
    }

//    public void removeFirstAccount() throws IOException {
//        File fileName = new File(main.getDirectoryData(), "/Accounts.txt");
//        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
//        //Initial write position
//        long writePosition = raf.getFilePointer(); //The first write position in our Accounts.txt file
//        raf.readLine(); //Reads the file aka goes to the second line after reading the first line
//        long readPosition = raf.getFilePointer(); //Moves our position back to the first line which we've just read in our Accounts.txt
//
//        byte[] buff = new byte[1024];
//        int n;
//        while (-1 != (n = raf.read(buff))) {
//            raf.seek(writePosition);
//            raf.write(buff, 0, n);
//            readPosition += n;
//            writePosition += n;
//            raf.seek(readPosition);
//        }
//        raf.setLength(writePosition);
//        raf.close(); //sec
//    }
//
//    public void grabNewAccount() {
//        InputStream in = null;
//        try {
//            in = new FileInputStream(new File(main.getDirectoryData() + "/Accounts.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        StringBuilder out = new StringBuilder();
//        String line;
//        try {
//        	
//            if ((line = reader.readLine()) != null) {
//                out.append(line);
//            }
//
//            String grabFullName = out.toString();
//            String[] splitString = grabFullName.split(":");
//            String username = splitString[0]; 
//            String password = splitString[1]; 
//
//            autoLogin_autoReplacement.loginUsername = username; 
//            autoLogin_autoReplacement.loginPassword = password;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        log(autoLogin_autoReplacement.loginUsername);
//        log(autoLogin_autoReplacement.loginPassword);
//
//
//        try {
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    
//    private int lineCounter = 0;
//    private int pickLine = 0;
//    
//    public void grabSecondAccount() { 
//        InputStream in = null;
//        try {
//            in = new FileInputStream(new File(main.getDirectoryData() + "/Accounts.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        StringBuilder out = new StringBuilder();
//        String line;
//        try {
//        	
//        	while ((line = reader.readLine()) != null) {
//        		
//        		lineCounter++; //Counts the line
//        		if (lineCounter == pickLine) { //If line number == our chosen number given in the responseCode
//        			out.append(line); //Then use the text in the line. /lol we did this in the wrong class lmao
//        			break;
//        		}
//        	}
//        	
////            if ((line = reader.readLine()) != null) {
////                out.append(line);
////            }
//
//            String grabFullName = out.toString();
//            String[] splitString = grabFullName.split(":");
//            String username = splitString[0]; 
//            String password = splitString[1]; 
//
//            autoLogin_autoReplacement.loginUsername = username; 
//            autoLogin_autoReplacement.loginPassword = password;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        log(autoLogin_autoReplacement.loginUsername);
//        log(autoLogin_autoReplacement.loginPassword);
//
//
//        try {
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    public  void nextAccount(){ 
    	//so this is the method im calling to iterate
    	 //so basically if i get response code 5 goes to the next string
    	String output = Main.it.next();
    	//why's it taking so long to add the ccs... how many are in your list..
    	
    	//try agaoin
        String[] splitString = output.split(":");
        String username = splitString[0]; 
        String password = splitString[1]; 

        autoLogin_autoReplacement.loginUsername = username; 
        autoLogin_autoReplacement.loginPassword = password;
    	
    	log("we're using this next " +output);
    	
    }
    
    public void grabAndDelete() throws IOException{
    	File fileName = new File(main.getDirectoryData() + "/AccountsSpot"+Main.spot+"_"+Main.world+".txt");
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        //Initial write position
        long writePosition = raf.getFilePointer(); //The first write position in our Accounts.txt file
        raf.readLine(); //Reads the file aka goes to the second line after reading the first line
        long readPosition = raf.getFilePointer(); //Moves our position back to the first line which we've just read in our Accounts.txt

        byte[] buff = new byte[1024];
        int n;
        while (-1 != (n = raf.read(buff))) {
            raf.seek(writePosition);
            raf.write(buff, 0, n);
            readPosition += n;
            writePosition += n;
            raf.seek(readPosition);
        }
        raf.setLength(writePosition);
        raf.close(); 
        
        autoLogin_autoReplacement.loginPassword = null;
		autoLogin_autoReplacement.loginPassword = null;
        
        //grabfirst account
        InputStream in = null;
        try {
            in = new FileInputStream(new File(main.getDirectoryData() + "/AccountsSpot"+Main.spot+"_"+Main.world+".txt"));
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

            autoLogin_autoReplacement.loginUsername = username; 
            autoLogin_autoReplacement.loginPassword = password;
        } catch (IOException e) {
            e.printStackTrace();
        }

        log(autoLogin_autoReplacement.loginUsername);
        log(autoLogin_autoReplacement.loginPassword);


        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}