package application;

import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FXMLDemoController {

	ObservableList<String> blackColorChoice = FXCollections.observableArrayList("Black", "Purple", "Blue", "Green",
			"Yellow", "Orange", "Red");

	ObservableList<String> whiteColorChoice = FXCollections.observableArrayList("White", "Purple", "Blue", "Green",
			"Yellow", "Orange", "Red");

	ObservableList<String> sizeChoice = FXCollections.observableArrayList("4", "6", "8", "10", "12", "14", "16", "18", "20");

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
		blackChoice.setValue("Black");
		whiteChoice.setValue("White");
		blackChoice.setItems(blackColorChoice);
		whiteChoice.setItems(whiteColorChoice);
		size.setValue("8");
		size.setItems(sizeChoice);
	}

	@FXML
	private void settingsDone() {}
	
	public FXMLDemoController() {
	}
}
