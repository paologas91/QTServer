package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import data.Data;
import data.EmptyDatasetException;
import mining.ClusteringRadiusException;
import mining.QTMiner;

public class ServerOneClient extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private QTMiner qt;

	public ServerOneClient(final Socket s) throws IOException {
		socket = s;
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
		start();
	}

	@Override
	public void run() {
		Data data = null;
		String tabName = "";
		Double radius = null;

		try {
			while (true) {
				System.out.println("in attesa del operazione");
				final int operation = (int) in.readObject();
				System.out.println(operation);
				switch (operation) {
				case 0:
					tabName = (String) in.readObject();
					data = new Data(tabName);
					out.writeObject("OK");
					break;
				case 1:
					radius = (Double) in.readObject();
					qt = new QTMiner(radius);
					final int num = qt.compute(data);
					out.writeObject("OK");
					out.writeObject(num);
					out.writeObject(qt.toString());
					break;
				case 2:

					qt.salva(tabName + "_" + radius + ".dmp");
					out.writeObject("OK");
					break;
				case 3:
					final String file = (String) in.readObject() + "_" + (double) in.readObject()
							+ ".dmp";
					qt = new QTMiner(file);
					out.writeObject(qt.toString());
					break;
				default:
					;
				}
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} catch (final ClusteringRadiusException e) {
			e.printStackTrace();
		} catch (final EmptyDatasetException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}

	}

}
