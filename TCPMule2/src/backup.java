import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;

public class backup extends Task {

	public backup(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int execute() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 6666);
		script.log("connected to the server");

		OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream()); // for
																					// sending
																					// data
		PrintWriter out = new PrintWriter(os); // for sending data
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		out.println("yo server");
		os.flush();

		for (String message = br.readLine(); message != null; message = br.readLine()) {

			script.log("Server sent: " + message); 
			
			if (message.equals("muleWakeup")) {

				while (!script.getClient().isLoggedIn()) { //i mean, our main worry now is this fucker writing the password in-game lol, might be the loop now sure might be a coinciden ce did u run it again? I tyhink I saw it before, but we can try
					// LoginEvent loginEvent = new LoginEvent(); 
					if (!script.getClient().isLoggedIn()) { 
						script.log("Logging In...");
						autoLogin();
					} else if (script.getClient().isLoggedIn()) { 
						script.log("Interacting with Button");
						if (lobbyButton() != null && lobbyButton().interact()) {
							Sleep.sleepUntil(() -> lobbyButton() == null, 10_000);
							if (lobbyButton() == null) {
								break;
							}
						} //yeah seems to be saying the pass hmm //How the fuck is this class not looping?!?! It's aa normal task class, no? yh lol ohhhhhhhhhhhhhhhhhhhhhh, I think it's the for loop, since it'll only read the message once... Let's test lol, look at this kek
					}
				} //wait before u destroy my 3 days of work let me save it somewhere just wanted to comment out too risky lel npnp
				
				if (script.getClient().isLoggedIn() && lobbyButton() == null) {
						script.log("Traversing...");
						script.getWalking().webWalk(Banks.AL_KHARID);
					}
				
			}
		}
		return 1000;
	}

	public RS2Widget lobbyButton() {
		return script.getWidgets().getWidgetContainingText(378, "CLICK HERE TO PLAY");
	}

	public void autoLogin() {
//		LoginEvent loginEvent = new LoginEvent();
//		script.getBot().addLoginListener(loginEvent);
//
//		loginEvent.setUsername("aftaba1679@googlemail.com");
//		loginEvent.setPassword("fortest123");
//		script.execute(loginEvent);
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
