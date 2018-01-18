package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.*;

import javafx.event.ActionEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FXMLDemoController {

	ObservableList<String> blackColorChoice = FXCollections.observableArrayList("Black", "Pruple", "Blue", "green",
			"Yellow", "Orange", "Red");

	ObservableList<String> whiteColorChoice = FXCollections.observableArrayList("White", "Pruple", "Blue", "green",
			"Yellow", "Orange", "Red");

	ObservableList<String> sizeChoice = FXCollections.observableArrayList("4", "6", "8", "10", "12", "14", "16", "18",
			"20");

	@FXML
	private RadioButton blackBox;

	@FXML
	private RadioButton whiteBox;

	@FXML
	private ToggleGroup starter;

	@FXML
	private ChoiceBox<String> blackChoice;

	@FXML
	private ChoiceBox<String> whiteChoice;

	@FXML
	private ChoiceBox<String> size;

	@FXML
	private Button ready;

	@FXML
	private void initialize() {
		msg.setText("");
		blackChoice.setValue("Black");
		whiteChoice.setValue("White");
		blackChoice.setItems(blackColorChoice);
		whiteChoice.setItems(whiteColorChoice);
		size.setValue("8");
		size.setItems(sizeChoice);
	}

	public void saveSettings() {
		try {
			Writer fileWriter = new FileWriter("src/application/setting_config.txt", false);
			fileWriter.write(((RadioButton) starter.getSelectedToggle()).getText() + "\n");
			fileWriter.write(blackChoice.getValue() + "\n");
			fileWriter.write(whiteChoice.getValue() + "\n");
			fileWriter.write(size.getValue() + "\n");
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private Label msg;

	@FXML
	private void HandleButtonClicked(ActionEvent event) throws IOException {
		if (whiteChoice.getValue() == blackChoice.getValue()) {
			msg.setTextFill(Color.RED);
			msg.setText("same color");
			return;
		}
		saveSettings();
		Stage stage = null;
		Parent root = null;
		if (event.getSource() == ready) {
			// get stage
			stage = (Stage) ready.getScene().getWindow();
			// create game scene
			root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
		}
		// set stage
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setTitle("Reversi");
		stage.setScene(scene);
		stage.show();
	}

	public FXMLDemoController() {
	}
}
