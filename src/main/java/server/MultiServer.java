package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

	private int port = 8080;

	MultiServer(final int port) {
		this.port = port;
		this.run();
	}

	public void run() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			while (true) {
				System.out.println("Aspetto.....");
				Socket socket = server.accept();
				ServerOneClient client = new ServerOneClient(socket);
				System.out.println("Servito");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				System.out.println("la vecchia porta");
				server.close();
				System.out.println("la sbarra");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(final String[] args) {
		MultiServer server = new MultiServer(8080);

	}

}
