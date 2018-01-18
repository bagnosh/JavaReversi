package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

public class fxBoard extends HBox implements EventHandler<MouseEvent> {
	private Board gameBoard;
	private GameFxManager manager;
	private GridPane gameGrid;
	private GridCell cells[][];
	private Color white;
	private Color black;
	private Label whiteScore;
	private Label blackScore;
	private Label commentForUser;
	private Label toMainMenu;
	private CellClicked mouseOn;
	private VBox gridContainer;
	
	
	public fxBoard(CellClicked mouseOn) {		
		this.mouseOn = mouseOn;
		this.white = Color.BLACK;
		this.black = Color.RED;
		this.gameBoard = null;
		this.manager = null;

		// Initiates the game
		this.gridContainer = new VBox();
		
		this.gameGrid = new GridPane();
		this.gameGrid.setPrefSize(300, 300);
		
		

		// Initiateing the scores of the users.'
		this.whiteScore = new Label("Not initialized");
		this.blackScore = new Label("Not initialized");
		this.gridContainer = new VBox();
		this.gridContainer.getChildren().add(gameGrid);
		this.gridContainer.getChildren().add(this.whiteScore);
		this.gridContainer.getChildren().add(this.blackScore);
		this.commentForUser = new Label("Not initialized");
		this.gridContainer.getChildren().add(this.commentForUser);
		
		
		// Set the to setings lable
		this.toMainMenu = new Label("Back to main menu");
		this.gridContainer.getChildren().add(this.toMainMenu);
		this.getChildren().add(this.gridContainer);
		this.toMainMenu.setOnMouseClicked(event -> {
			Stage stage = null;
			GridPane root = null;
			try{
				root = (GridPane)FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
			} catch (Exception e){
				e.printStackTrace();
			}
			stage = (Stage) this.toMainMenu.getScene().getWindow();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage .setTitle("Reversi");
			stage .setScene(scene);
			stage .show();
		});
	}
	
	public void draw() {
		this.gameGrid.getChildren().clear();
		if (this.gameBoard != null) {
			int lenth = this.gameBoard.getGameBoardWidth();
			this.cells = new GridCell[lenth + 1][lenth + 1];
			int gridHeigt = (int) this.gameGrid.getPrefHeight();
			int gridWidth = (int) this.gameGrid.getPrefWidth();
			int cellHight = (int) gridHeigt / lenth;
			int cellWidth = (int) gridWidth / lenth;

			for (int i = 1; i <= lenth; i++) {
				for (int j = 1; j <= lenth; j++) {
					GridCell cell = new GridCell(i, j);
					switch (this.gameBoard.getCellValue(i, j)) {
					case WHITE:
						cell.setText("O");
						cell.setTextFill(this.white);
						break;
					case BLACK:
						cell.setText("O");
						cell.setTextFill(this.black);
						break;
					case EMPTY:
						cell.setText(" ");
						break;
					default:
						break;
					}

					cell.setAlignment(Pos.CENTER);
					cell.setPrefWidth(cellWidth);
					cell.setPrefHeight(cellHight);
					cell.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
							BorderWidths.DEFAULT)));
					cell.setOnMouseClicked(this);
					this.gameGrid.add(cell, i, j);
					this.cells[i][j] = cell;
				}
			}
			
			String strWhite = String.valueOf("White Score : " + gameBoard.getWhiteCellsNumber());
			this.whiteScore.setText(strWhite);
			String strBlack = String.valueOf("Black Score : " + gameBoard.getBlackCellsNumber()); 
			this.blackScore.setText(strBlack);
			this.commentForUser.setText(this.manager.getMassage());
				
			// If the game ended
			if(this.manager.getWinner() != CellValue.EMPTY){
				if(this.manager.getWinner() == CellValue.WHITE){
					this.commentForUser.setText("Game ended. winner white");
				}
				else {
					this.commentForUser.setText("Game ended. winner black");
				}
			}

		}
	}

	public void setBoard(Board board, GameFxManager mamager) {
		this.gameBoard = board;
		this.manager = mamager;
		this.draw();
	}

	@Override
	public void handle(MouseEvent event) {
		this.mouseOn.handle(event);
		for (int i = 1; i < this.cells.length; ++i) {
			for (int j = 1; j < this.cells[0].length; j++) {
				this.cells[i][j].removeEventHandler(MouseEvent.MOUSE_PRESSED, this);
			}
		}
		this.draw();
	}
	
	public void setWhiteColor(Color color){
		this.white = color;
		this.draw();
	}
	
	public void setBlackColor(Color color){
		this.black = color;
		this.draw();
	}
}