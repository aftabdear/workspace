package DangerousMule2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.osbot.rs07.script.Script;

public class LogIn extends Task{ 
	
	private LoginEvent last;

	public LogIn(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
         
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return !script.getClient().isLoggedIn();
	}
	
	private LoginEvent loginEvent;

	@Override
	public int execute() throws Exception {
		if(last != null){
			script.getBot().removeLoginListener(last); 
		}
		grabFirstAccount(); 
        loginEvent = new LoginEvent(loginUsername, loginPassword);
        script.getBot().addLoginListener(loginEvent);
        last = loginEvent; 
        script.sleep(1000); 
        script.execute(loginEvent);
        
       
		return 1000;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String loginUsername = "";
    public static String loginPassword = "";

    public void grabFirstAccount() { 
        InputStream in = null;
        try {
            in = new FileInputStream(new File(script.getDirectoryData() + "/Mules.txt"));
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

        script.log(loginUsername);
        script.log(loginPassword);


        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
