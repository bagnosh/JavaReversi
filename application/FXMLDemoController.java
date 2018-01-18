package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.*;

import javafx.event.ActionEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FXMLDemoController {

	Image black = new Image("src/application/black40px.png");
	Image white = new Image("src/application/white40px.png");
	Image tem = new Image("src/application/temmie40px.png");
	Image kermit = new Image("src/application/kermit40px.png");
	Image bob = new Image("src/application/bob40px.png");
	Image mario = new Image("src/application/mario40px.png");
	Image luigi = new Image("src/application/luigi40px.png");
	
	ObservableList<Image> blackColorChoice = FXCollections.observableArrayList(black, tem, kermit, bob, mario, luigi);

	ObservableList<Image> whiteColorChoice = FXCollections.observableArrayList(black, tem, kermit, bob, mario, luigi);

	ObservableList<String> sizeChoice = FXCollections.observableArrayList("4", "6", "8", "10", "12", "14", "16", "18", "20");

	@FXML
	private RadioButton blackBox;

	@FXML
	private RadioButton whiteBox;

	@FXML
	private ToggleGroup starter;
	
	@FXML
	private ComboBox<Image> blackChoice;

	@FXML
	private ComboBox<Image> whiteChoice;

	@FXML
	private ChoiceBox<String> size;
	
	@FXML
	private Button ready;

	@FXML
	private void initialize() {
		blackChoice.setValue(black);
		whiteChoice.setValue(white);
		blackChoice.setItems(blackColorChoice);
		whiteChoice.setItems(whiteColorChoice);
		size.setValue("8");
		size.setItems(sizeChoice);
	}

	@SuppressWarnings("deprecation")
	public void saveSettings() {
		try {
			Writer fileWriter = new FileWriter("src/application/setting_config.txt", false);
			fileWriter.write(((RadioButton)starter.getSelectedToggle()).getText() + "\n");
			fileWriter.write(blackChoice.getValue().impl_getUrl() + "\n");
			fileWriter.write(whiteChoice.getValue().impl_getUrl() + "\n");
			fileWriter.write(size.getValue() + "\n");
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML	
	private void HandleButtonClicked(ActionEvent event) throws IOException {
		saveSettings();
		Stage stage = null;
		Parent root = null;
		if(event.getSource() == ready) {
			//get stage
			stage = (Stage) ready.getScene().getWindow();
			//create game scene
			root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
		}
		//set stage
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setTitle("Reversi");
		stage.setScene(scene);
		stage.show();
	}
	
	public FXMLDemoController() {
	}
}
