package gui;

import java.io.PrintStream;
import java.net.InetAddress;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.MultiServer;

public class ServerGUI extends Application {
	private boolean running = false;
	private MultiServer ms;

	public static void main(final String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(final Stage stage) throws Exception {

		final GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		final Label portLabel = new Label("port:");
		final TextField port = new TextField("8080");
		final Text ip = new Text("ip locale:" + InetAddress.getLocalHost().toString().split("/")[1]);
		final TextArea log = new TextArea();
		final Console c = new Console(log);
		final PrintStream ps = new PrintStream(c, true);
		System.setOut(ps);

		port.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
			if (newValue.matches("\\d*")) {
				port.setText(newValue);
			} else {
				port.setText(oldValue);
			}
		});

		final Button open = new Button("Open Server");
		final Button close = new Button("Close Server");
		open.setOnAction(event -> {
			if (!running) {
				try {
					ms = new MultiServer(Integer.parseInt(port.getText()));
					new Thread(ms).start();
					running = true;

				} catch (final NumberFormatException e1) {
					System.out.println("numero non valido");
				} catch (final Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		close.setOnAction(event -> {
			running = false;
			if (ms != null) {
				ms.close();
			}

		});

		grid.add(ip, 0, 0);
		grid.add(portLabel, 0, 1);
		grid.add(port, 1, 1);
		grid.add(log, 0, 2, 2, 2);
		final HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(close);
		grid.add(open, 0, 4);
		grid.add(hbBtn, 1, 4);
		stage.setOnCloseRequest(event -> {
			if (ms != null) {
				ms.close();
			}
		});
		stage.setTitle("QTServer");
		stage.setScene(new Scene(grid, 300, 300));
		stage.show();
	}

}
