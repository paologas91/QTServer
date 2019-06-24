package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer implements Runnable {

	private int port = 8080;
	private boolean running;
	ServerSocket server;

	public MultiServer(final int port) {
		this.port = port;
		running = true;
		// run();
	}

	@Override
	public void run() {
		// ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			while (running) {
				System.out.println("Aspetto.....");
				final Socket socket = server.accept();
				new ServerOneClient(socket);
				System.out.println("Servito");
			}
			System.out.println("Sto chiudendo....");
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

	public void close() {
		running = false;
	}

	public static void main(final String[] args) {
		new MultiServer(8080);
	}

}
