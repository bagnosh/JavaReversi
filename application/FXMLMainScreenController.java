package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;

import application.Board;
import application.CellClicked;
import application.CellValue;
import application.ClassicLogic;
import application.GameFxManager;
import application.Logic;
import application.fxBoard;
import javafx.fxml.*;
import java.lang.Integer;

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
		settingsLabel.setTextFill(Color.CRIMSON);
	}

	@FXML
	private void startChangeBack() {
		startLabel.setTextFill(Color.BLACK);
		;
	}

	@FXML
	private void settingsChangeBack() {
		settingsLabel.setTextFill(Color.BLACK);
		;
	}

	@FXML
	private void handleStartClicked() {
		Stage stage = null;
		fxBoard root = null;
		stage = (Stage) startLabel.getScene().getWindow();

		String startPlayer = "white", firstPlayer, secoundPlayer, size= "4";

		try (BufferedReader br = new BufferedReader(new FileReader("src/application/setting_config.txt"))) {
			startPlayer = br.readLine();
			firstPlayer = br.readLine();
			secoundPlayer = br.readLine();
			size = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Board game = new Board(Integer.parseInt(size));
		Logic logic = new ClassicLogic(game);
		CellValue start = CellValue.WHITE;
		if(startPlayer != "white"){
			start = CellValue.BLACK;
		}
		GameFxManager manager = new GameFxManager(game, logic,start);
		CellClicked mouseOnMethod = new CellClicked();
		mouseOnMethod.AddRciver(manager);
		root = new fxBoard(mouseOnMethod);
		root.setPrefSize(400, 400);
		root.setBoard(game, manager);

		Scene scene = new Scene(root, 400, 400);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void handleSettingsClicked() {
		Stage stage = null;
		Parent root = null;
		// get stage
		stage = (Stage) settingsLabel.getScene().getWindow();
		// load setting's FXML
		try {
			root = FXMLLoader.load(getClass().getResource("FXMLDemo.fxml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// set stage
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setTitle("Reversi");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void startGame() {
	}

	@FXML
	private void goSettings() {
	}

	public FXMLMainScreenController() {
	}
}
