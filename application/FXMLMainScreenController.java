package application;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.fxml.*;

public class FXMLMainScreenController {
	@FXML
	private Label startLabel;
	
	@FXML
	private Label settingsLabel;
	
	@FXML
	private void startChange() {
		startLabel.setTextFill(Color.CRIMSON);
	}
	
	@FXML
	private void settingsChange() {
		settingsLabel.setTextFill(Color.CRIMSON);;
	}
	
	@FXML
	private void startChangeBack() {
		startLabel.setTextFill(Color.BLACK);;
	}
	
	@FXML
	private void settingsChangeBack() {
		settingsLabel.setTextFill(Color.BLACK);;
	}
	
	@FXML
	private void startGame() {}
	
	@FXML
	private void goSettings() {}
	
	public FXMLMainScreenController() {}
}
