package pl.edu.anstar.milionerzy.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MenuController {

	@FXML
	Button startButton;

	@FXML
	ImageView backgroundView;

	@FXML
	private void switchToSecondary() throws IOException {
		Main.setRoot("secondary");
	}
}
