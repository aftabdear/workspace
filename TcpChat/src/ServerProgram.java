import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerProgram implements OnSocketListener {
	private Server server;
	public static boolean relayMessage1 = false;
	public static ArrayList<String> verifiedSlavers = new ArrayList<>();
	//Socket socket = new Socket("localhost", 3333);

	private Scanner reader;
	private Socket socket;
	
	
	@Override
	public void onConnected(Channel channel) {
		Socket socket = channel.getSocket();
		String hostName = socket.getInetAddress().getHostName();
		int port = socket.getPort();

		String msg = "Client connected from " + hostName + ":" + port;
		System.out.println(msg);

		for (Channel c : server.getChannels()) {
			if (c != channel)
				c.send(msg);
		}
	}

	@Override
	public void onDisconnected(Channel channel) {
		server.remove(channel);

		Socket socket = channel.getSocket();
		String hostName = socket.getInetAddress().getHostName();
		int port = socket.getPort();

		String msg = "Client disconnected from " + hostName + ":" + port;
		System.out.println();

		server.broadcast(msg);
	}

	@Override
	public void onReceived(Channel channel, String msg) {
		System.out.println(msg);
		server.broadcast(msg);
	}

	public void start() throws IOException {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Port : ");
		int port = Integer.parseInt(scanner.nextLine());

		server = new Server(this);
		server.bind(port); // Open Server
		server.start(); // Start Accept Thread
		System.out.println("Server has started.");

		// Send
		while (true) {
			System.out.println("in the while true loop");
			
			
			//String msg = scanner.nextLine();
			InputStream inputStream = socket.getInputStream();
			reader = new Scanner(inputStream);
			
			
				
				String msg = reader.nextLine();
				while (msg.contains("hey server")){
					server.broadcast("wowowoowow");
					break;
				}
			
			
			

			
			

			
			
		}

		// scanner.close();
		// server.stop();

		// System.out.println("Server has closed.");
	}

	public static void main(String[] args) throws IOException {
		ServerProgram program = new ServerProgram();
		program.start();
	}

}
