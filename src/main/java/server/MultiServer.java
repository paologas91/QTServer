package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

	private int port = 8080;

	MultiServer(final int port) {
		this.port = port;
		run();
	}

	public void run() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			while (true) {
				System.out.println("Aspetto.....");
				final Socket socket = server.accept();
				new ServerOneClient(socket);
				System.out.println("Servito");
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("la vecchia porta");
				server.close();
				System.out.println("la sbarra");
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(final String[] args) {
		new MultiServer(8080);
	}

}
