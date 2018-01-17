package application;

import application.Player;
import application.Logic;
import application.Display;
import application.CellValue;
import java.util.List;

/**
 * Names       : Yifat Yankocivh
 * 				 Shoham Bar-Gad
 * IDs	       : 204709224
 * 				 315706614
 * User Names  : yankovy
 * 				 bagnosh
 *
 * Class Name  : GameManager.h
 * Description : Defines the attributes and methods needed to
 * 				 sustain and manage a running game and all of
 * 				 its components.
 */
public class GameManager {
	private Player white;
	private	Player black;
	private	Logic logic;
	private	Board gameBoard;
	private	Display display;
	private	CellValue winner;

	/**
	 * Function name    : GameManager
	 * Parameters       : two Player pointers and board width
	 * Return value     : Initialized GameManager
	 * General Flow     : Constructs a GameManager.
	 */
	public GameManager(Player white, Player black, int width){
		this.white = white;
		this.black = black;
		this.gameBoard = new Board(width);
		this.logic = new ClassicLogic(this.gameBoard);
		this.display = new ConsuleDisplay();
		this.winner = CellValue.EMPTY;
	}

	/** Function name	: play
	 *  Parameters		: None.
	 *  Return value	: None.
	 *  General flow	: Plays the game, until it ends.
	 *  				  Controls game flow.
	 */
	public void play(){
		boolean isRunning = true;
		Point p = new Point(-1, -1);
		while (isRunning) {
			boolean isValidMove = false, hasPlayed;

			// Play white player.
			// Get all the possible moves for the white player.
			List<Point> moves = this.logic.getLegalMoves(this.white.getValue());

			// If the white player does not have legal moves.
			if (moves.size() == 0) {
				hasPlayed = false;

				// If the game has not over yet
				if (this.logic.getBoard().getEmptyCellsNumber() != 0) {
					this.white.move(moves, p, this.logic.getBoard());
				}
			} else {
				hasPlayed = true;
				isValidMove = false;
				while (!isValidMove) {
					p = this.white.move(moves, p, this.logic.getBoard());

					// If one of the player suddenly qiuted.
					if (p.getX() == -2 || p.getY() == -2) {
						isRunning = false;
						continue;
					}

					isValidMove = this.logic.playMove(p, this.white.getValue());

					// If the user has enter an in valid move.
					// Get the list of possible moves, again.
					if (!isValidMove) {
						moves = this.logic.getLegalMoves(
								this.white.getValue());
					}
				}
			}

			// Play Black player.
			// Get all the possible moves for the black player.
			moves = this.logic.getLegalMoves(this.black.getValue());

			// If the black player does not have legal moves.
			if (moves.size() == 0) {
				// If the game has not over yet
				if (this.logic.getBoard().getEmptyCellsNumber() != 0) {
					this.black.move(moves, p, this.logic.getBoard());
				}

				// If the white player does not have legal moves as well.
				if (!hasPlayed) {
					isRunning = false;
				}
			} else {
				isValidMove = false;
				while (!isValidMove) {

					p = this.black.move(moves, p, this.logic.getBoard());

					// If one of the player suddenly qiuted.
					if (p.getX()== -2 || p.getY() == -2) {
						isRunning = false;
						continue;
					}

					isValidMove = this.logic.playMove(p,
							this.black.getValue());

					// If the user has enter an in valid move.
					// Get the list of possible moves, again.
					if (!isValidMove) {
						moves = this.logic.getLegalMoves(
								this.black.getValue());
					}
				}
			}
		}

		//Announce the winner
		this.display.DisplayBoard(this.logic.getBoard());
		if (this.logic.getBoard().getWhiteCellsNumber()
				> this.logic.getBoard().getBlackCellsNumber()) {
			this.winner = this.white.getValue();
			this.display.DisplayMessage("GAME OVER!");
			this.display.DisplayMessage("~~~ The Winner Is: O ~~~");
		} else {
			this.winner = this.black.getValue();
			this.display.DisplayMessage("GAME OVER!");
			this.display.DisplayMessage("~~~ The Winner Is: X ~~~");
		}
	}
	
	/** Function name	: getWinner
	 *  Parameters		: None.
	 *  Return value	: The game's winning color value.
	 *  General flow	: Returns the games winner's colors.
	 *  				  However, if the game has not over yet,
	 *  				  the function returns the value EMPTY.
	 */
	public CellValue getWinner(){
		return this.winner;
	}
}

