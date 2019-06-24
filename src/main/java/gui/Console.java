package gui;

import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

public class Console extends OutputStream {

	private TextArea output;

	public Console(final TextArea ta) {
		this.output = ta;
	}

	@Override
	public void write(final int i) throws IOException {
		output.appendText(String.valueOf((char) i));
	}


}
