package SafeMule;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.constants.ResponseCode;
import org.osbot.rs07.event.Event;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.listener.LoginResponseCodeListener;
import org.osbot.rs07.utility.ConditionalSleep;

import java.awt.*;
import java.io.*;

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
                return getLobbyButton() != null || getClient().getLoginUIState() == 4 || isDisabledMessageVisible();
            }
        }.sleep();
        
//        if(getClient().getLoginUIState() == 4 || isDisabledMessageVisible()){
//        	getMouse().click(new RectangleDestination(getBot(), 398, 308, 126, 27));
//        	Main.nextAccount(); //umm btw i needed to add
//        }

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
    	//if(ResponseCode.isDisabledError(responseCode)) {

         //   getMouse().click(new RectangleDestination(getBot(), 398, 308, 126, 27)); //to click try again
            
            log(responseCode); //lets see what happens if wcomment this oute 
            //i liked the other one cuz it deleted it as well :p, *inhale*
            //i will fight you, im not sure if you understand
         
            getMouse().click(new RectangleDestination(getBot(), 398, 308, 126, 27));
					try {
						removeFirstAccount();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Main.loginPassword = null;
					Main.loginPassword = null;
					grabNewAccount();
            	//badabingbadaboom
					
            	//redo
            	
            	//Main.nextAccount();
					//event looks like it's firing twice? yh it's
					
					
					
					
					
					
					
					
					//grabNewAccount();
					
					//u there? hy, is the notepad shared between multiple scripts? nope, oh then just load them all at run and itterate? wait wym
					/*
					 *  > onstart > load text file all lines into list 
					 *  > start iterrator on list of strings of login
					 *  > getfirst, login
					 *  > if banned getnext from itterator until empty?
					 *  do u mind showing me if u know how to
					 * */
					
					
					//yo yo so basically we need to add a method for adding new account i think your grabaccount method will do? Yea
					//the method just looks for the first line and gets it right? Yea
					
					//remove first account
					//set strings to null
					//grab new account
					
					
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
				
            setFailed();
            return;
        }

        if(ResponseCode.isConnectionError(responseCode)) {
            log("Connection error, attempts exceeded");
            setFailed(); //Hmm, sec lemme see
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

    public void removeFirstAccount() throws IOException {
        File fileName = new File(main.getDirectoryData(), "/SafeMules.txt");
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
        raf.close(); //sec
    }
    
    public void grabNewAccount() {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(main.getDirectoryData() + "/SafeMules.txt"));
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

            Main.loginUsername = username; 
            Main.loginPassword = password;
        } catch (IOException e) {
            e.printStackTrace();
        }

        log(Main.loginUsername);
        log(Main.loginPassword);


        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}