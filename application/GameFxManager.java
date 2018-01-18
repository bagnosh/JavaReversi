package application;

import java.util.List;

public class GameFxManager implements CellClickedReciver {
	private Logic logic;
	private CellValue winner;
	private CellValue currPlayer;
	private String currentStatus;
	private boolean isMissedTurn;
	
	public GameFxManager(Board gameBoard, Logic logic, CellValue startPlayer) {
		this.logic = logic;
		this.winner = CellValue.EMPTY;
		this.currPlayer = startPlayer;
		this.winner = CellValue.EMPTY;
		this.isMissedTurn = false;
	}

	@Override
	public void clicked(GridCell cell) {
		// Try to play the player's move
		List<Point> moves = this.logic.getLegalMoves(this.currPlayer); 
		
		boolean isValid = false;
		Point p = new Point(cell.getX(), cell.getY());
		for(Point move : moves) {
			if(move.equals(p)) {
				isValid = true;
			}
		}
		
		if (isValid) { 
			this.currentStatus = "";
			this.logic.playMove(new Point(cell.getX(), cell.getY()), this.currPlayer);
			this.ChangePlayersTurn();
			this.isMissedTurn = false;
			
			// Check the player can move
			if (this.logic.getLegalMoves(this.currPlayer).size() == 0) {
				this.isMissedTurn = true;
				this.currentStatus += "No moves Avalable, turn goes to othe player\n";
				this.ChangePlayersTurn();
				
				// Check the other player can move
				if (this.logic.getLegalMoves(this.currPlayer).size() == 0) {
					this.ChangePlayersTurn();
					this.currentStatus += "Game ended\n";
					
					// Set the winner
					if (this.logic.getBoard().getWhiteCellsNumber()
							> this.logic.getBoard().getBlackCellsNumber()) {
						this.winner = CellValue.WHITE;
					} else {
						this.winner = CellValue.BLACK;
					}
				}
			}
		}
	}

	private void ChangePlayersTurn() {
		// Change the players turn
		if (this.currPlayer == CellValue.BLACK) {
			this.currPlayer = CellValue.WHITE;
			this.currentStatus += "White is playing\n";
		} else {
			this.currPlayer = CellValue.BLACK;
			this.currentStatus += "Black is playing\n";
		}
	}
	
	public String getMassage() {
		return this.currentStatus;
	}
	
	public CellValue getWinner(){
		return this.winner;
	}
	
	public CellValue getCurrPlayer(){
		return this.currPlayer;
	}
	
	public boolean isMissed(){
		return this.isMissedTurn;
	}
}
