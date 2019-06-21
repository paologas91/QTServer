package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import data.Data;
import data.EmptyDatasetException;
import database.DatabaseConnectionException;
import database.DbAccess;
import mining.ClusteringRadiusException;
import mining.QTMiner;

public class ServerOneClient extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private QTMiner qt;

	public ServerOneClient(final Socket s) throws IOException {
		socket = s;
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		start();
	}

	@Override
	public void run() {
		Data data = null;
		String tabName = "";
		Double radius = null;
		Boolean cicle = true;

		try {
			while (cicle == true) {
				System.out.println("in attesa dell'operazione");
				int operation = (int) in.readObject();
				System.out.println("scelta operazione numero: " + operation);
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
						System.out.println(file);
						qt = new QTMiner(file);
						out.writeObject("OK");
						System.out.println(qt);
						out.writeObject(qt.toString());
						break;
					case 4:
						DbAccess db = new DbAccess();
						LinkedList<String> tables = new LinkedList<String>();
						try {
							db.initConnection();
							Connection c = db.getConnection();
							Statement s = c.createStatement();
							ResultSet r = s.executeQuery("show tables");
							while (r.next()) {
								tables.add(r.getString(1));
							}
							out.writeObject(tables);
							s.close();
						} catch (DatabaseConnectionException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							db.closeConnection();
						}
						break;
					case 5: 
						cicle = false;
						break;
					default:
						break;
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
				System.out.println("Socket chiusa con successo!");
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}

	}

}
