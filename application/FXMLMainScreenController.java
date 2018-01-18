package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
	private void handleStartClicked() {
		//
	}
	
	@FXML
	private void handleSettingsClicked() {
		Stage stage = null;
		Parent root = null;
		//get stage
		stage = (Stage) settingsLabel.getScene().getWindow();
		//load setting's FXML
		try {
			root = FXMLLoader.load(getClass().getResource("FXMLDemo.fxml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//set stage
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setTitle("Reversi");
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void startGame() {}
	
	@FXML
	private void goSettings() {}
	
	public FXMLMainScreenController() {}
}
