import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;

public class TradeMule extends Task {
	public static ArrayList<String> verifiedSlavers = new ArrayList<>();

	public TradeMule(Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return script.getInventory().contains("Iron ore");
	}

	@Override
	public int execute() throws UnknownHostException, IOException {

		script.log("in the trade mule class");
		Main.getResponseForString("MULE*"+script.myPlayer().getName().toString());
		
		
		
		
		return 5000;
	}

	public RS2Widget lobbyButton() {
		return script.getWidgets().getWidgetContainingText(378, "CLICK HERE TO PLAY");
	}

	private LoginEvent loginEvent;

	public void autoLogin() {
		loginEvent = new LoginEvent("aftaba1679@googlemail.com", "fortest123");
		script.getBot().addLoginListener(loginEvent);

		script.execute(loginEvent);
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
