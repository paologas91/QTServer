package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Gestisce le richieste dei client delegando la gestione della singola
 * richiesta a ServerOneClient.
 */
public class MultiServer implements Runnable {

	private int port = 8080;
	private boolean running;

	/**
	 * Inizializza la porta.
	 * 
	 * @param port numero di porta su cui il server è in ascolto
	 */
	public MultiServer(final int port) {
		this.port = port;
		running = true;
	}

	@Override
	public void run() {
		ServerSocket server = null;
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

	/**
	 * Impedisce di accettare ulteriori connessioni per poi chiudere il server.
	 */
	public void close() {
		running = false;
	}

	public static void main(final String[] args) {
		new Thread(new MultiServer(8080)).start();
	}

}
