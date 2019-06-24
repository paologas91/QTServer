package gui;

import java.io.PrintStream;
import java.net.InetAddress;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import server.MultiServer;

public class ServerGUI extends Application {
	boolean running = false;
	MultiServer ms;

	public static void main(final String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(final Stage stage) throws Exception {

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		Label portLabel = new Label("port:");
		TextField port = new TextField("8080");
		Text ip = new Text("ip locale:" + InetAddress.getLocalHost().toString().split("/")[1]);
		TextArea log = new TextArea();
		Console c = new Console(log);
		PrintStream ps = new PrintStream(c, true);
		System.setOut(ps);

		port.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue,
					final String newValue) {
				if (newValue.matches("\\d*")) {
					port.setText(newValue);
				} else {
					port.setText(oldValue);
				}
			}
		});

		Button open = new Button("Open Server");
		Button close = new Button("Close Server");
		open.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent event) {
				if (!running) {
				try {
					ms = new MultiServer(Integer.parseInt(port.getText()));
					new Thread(ms).start();
						running = true;

				} catch (NumberFormatException e) {
					System.out.println("numero non valido");
				} catch (Exception e) {
					e.printStackTrace();
					}
				}
			}
		});
		close.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent event) {
				running = false;
				if (ms != null) {
				ms.close();
				}

			}
		});


		grid.add(ip, 0, 0);
		grid.add(portLabel, 0, 1);
		grid.add(port, 1, 1);
		grid.add(log, 0, 2, 2, 2);
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(close);
		grid.add(open, 0, 4);
		grid.add(hbBtn, 1, 4);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(final WindowEvent event) {
				if (ms != null) {
					ms.close();
				}
			}
		});
		stage.setTitle("QTServer");
		stage.setScene(new Scene(grid, 300, 300));
		stage.show();
	}

}
