package application;

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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class fxBoard extends HBox implements EventHandler<MouseEvent> {
	private Board gameBoard;
	private GameFxManager manager;
	private GridPane gameGrid;
	private GridCell cells[][];
	private char white;
	private char black;
	private char space;
	private Label whiteScore;
	private Label blackScore;
	private Label commentForUser;
	private CellClicked mouseOn;
	private VBox scores;
	private VBox gridContainer;

	public fxBoard(CellClicked mouseOn) {		
		this.mouseOn = mouseOn;
		this.white = 'X';
		this.black = 'Y';
		this.space = ' ';
		this.gameBoard = null;
		this.manager = null;

		// Initiates the game
		this.gridContainer = new VBox();
		this.commentForUser = new Label("Not initialized");
		this.gameGrid = new GridPane();
		this.gameGrid.setPrefSize(300, 300);
		this.gridContainer.getChildren().add(gameGrid);
		this.gridContainer.getChildren().add(this.commentForUser);
		this.getChildren().add(this.gridContainer);

		// Initiateing the scores of the users.'
		this.whiteScore = new Label("Not initialized");
		this.blackScore = new Label("Not initialized");
		this.scores = new VBox();
		this.scores.getChildren().add(this.whiteScore);
		this.scores.getChildren().add(this.blackScore);
		this.getChildren().add(this.scores);
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
						break;
					case BLACK:
						cell.setText("X");
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
					cell.setOnMouseClicked(this); // lbl.setText("c");
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
}