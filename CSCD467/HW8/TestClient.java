import java.io.*;
import java.net.Socket;
import java.util.*;

public class TestClient extends Thread {
	private BufferedReader in;
	private PrintWriter out;
	private String ip;

	public TestClient() {
		Scanner kb = new Scanner(System.in);
		boolean connected = false;
		while (connected!=true) {
			System.out.println("Enter server ip address: ");
			this.ip = kb.nextLine();
			try {
				sendMessage("EHLO");
				connected = true;
			} catch (IOException e) {
				System.out.println("FAILED TO CONNECT");
			}
		}
	}

	public TestClient(String addr) {
		this.ip = addr;
	}


	public void sendMessage(String message) throws IOException {
		Socket socket = new Socket(ip, 9898);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		out.println(message);
		String response;
		try {
			response = in.readLine();
			if (response == null || response.equals("")) {
				System.out.println("Client terminated");
				return;
				//System.exit(0);
			}
		} catch (IOException ex) {
			response = "Error: " + ex;
			System.out.println("" + response + "\n");
		}
		System.out.println(response);
	}


	@Override
	public void run() {
		String messages[] = {"ADD,5,4", "MUL,5,6", "SUB,4,2", "DIV,2,1", "AYDHE,3,2", "HEY", "WHATS UP", "AUWE,2,3,1", "Aye,1,2,3,4,", "What"}; 
		for (int i = 0; i < 10; i++) {
			try {
				sendMessage(messages[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("########TEST################");
		int clients = 100;
		TestClient[] clientArray = new TestClient[clients];
		for (int i = 0; i < clients; i++) {
			clientArray[i] = new TestClient("127.0.0.1");
			clientArray[i].start();
		}
		for (int i = 0; i < clients; i++) {
			try {
				clientArray[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		TestClient tc = new TestClient("127.0.0.1");
		try {
			tc.sendMessage("kill");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("#############TEST END################");
	}
}