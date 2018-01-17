package application;

import java.util.LinkedList;
import java.util.List;
import application.Logic;
import application.Point;
import application.Board;
import application.CellValue;

/**
 * Names       : Yifat Yankocivh
 * 				 Shoham Bar-Gad
 * IDs	       : 204709224
 * 				 315706614
 * User Names  : yankovy
 * 				 bagnosh
 * 
 * Class Name  : ClassicLogic
 * Description : Defines the guidelines used within the
 * 				 classic Othello game.
 */
public class ClassicLogic implements Logic{
	private Board  gameBoard;
	
	/** Function name	: ClassicLogic
	 *  Parameters		: board pointer.
	 *  Return value	: The created ClassicLogic.
	 *  General flow	: Initializes a ClassLogic with a game board .
	 */
	public ClassicLogic(Board board) {
		int half = board.getGameBoardWidth() / 2;
		this.gameBoard = board;
		this.gameBoard.setCellValue(half, half, CellValue.WHITE);
		this.gameBoard.setCellValue(half + 1, half + 1, CellValue.WHITE);
		this.gameBoard.setCellValue(half, half + 1, CellValue.BLACK);
		this.gameBoard.setCellValue(half + 1, half, CellValue.BLACK);

	} 
	
	/** Function name	: getLegalMoves
	 * Parameters		: A player's color.
	 *  Return value	: A vector containing all of the legal
	 *  				  moves the player may choose from.
	 *  General flow	: Check all cells in the game board to see
	 *  				  if they count as legal moves.
	 *  			      If a cell answers the criteria,
	 *  			      the function adds the its location to the vector.
	 */
	@Override
	public List<Point> getLegalMoves(CellValue value) {
		int height = this.gameBoard.getGameBoardWidth();

		List<Point> moves_list = new LinkedList<Point>();
		// Goes trough all the array cells and search for a legal move.
		for (int index = 1; index <= height; index++) {
			for (int jndex = 1; jndex <= height; jndex++) {
				if (this.gameBoard.getCellValue(index, jndex) == CellValue.EMPTY) {
					// Check up
					if ((this.gameBoard.getCellValue(index - 1, jndex) != CellValue.BORDER)
							&& (this.gameBoard.getCellValue(index - 1, jndex)
									!= CellValue.EMPTY)
							&& (this.gameBoard.getCellValue(index - 1, jndex)
									!= value)
							&& (this.playDirection(index - 1, jndex, -1, 0, value,
									true))) {
						Point  p = new Point(index, jndex);
						moves_list.add(p);
						// Check down
					} else if ((this.gameBoard.getCellValue(index + 1, jndex)
							!= CellValue.BORDER)
							&& (this.gameBoard.getCellValue(index + 1, jndex)
									!= CellValue.EMPTY)
							&& (this.gameBoard.getCellValue(index + 1, jndex)
									!= value)
							&& (this.playDirection(index + 1, jndex, 1, 0, value,
									true))) {
						Point  p = new Point(index, jndex);
						moves_list.add(p);
						// Check left.
					} else if ((this.gameBoard.getCellValue(index, jndex - 1)
							!= CellValue.BORDER)
							&& (this.gameBoard.getCellValue(index, jndex - 1)
									!= CellValue.EMPTY)
							&& (this.gameBoard.getCellValue(index, jndex - 1)
									!= value)
							&& (this.playDirection(index, jndex - 1, 0, -1, value,
									true))) {
						Point  p = new Point(index, jndex);
						moves_list.add(p);
						// Check right.
					} else if ((this.gameBoard.getCellValue(index, jndex + 1)
							!= CellValue.BORDER)
							&& (this.gameBoard.getCellValue(index, jndex + 1)
									!= CellValue.EMPTY)
							&& (this.gameBoard.getCellValue(index, jndex + 1)
									!= value)
							&& (this.playDirection(index, jndex + 1, 0, 1, value,
									true))) {
						Point  p = new Point(index, jndex);
						moves_list.add(p);
						// Check up right
					} else if ((this.gameBoard.getCellValue(index + 1,
							jndex + 1) != CellValue.BORDER)
							&& (this.gameBoard.getCellValue(index + 1,
									jndex + 1) != CellValue.EMPTY)
							&& (this.gameBoard.getCellValue(index + 1,
									jndex + 1) != value)
							&& (this.playDirection(index + 1, jndex + 1, 1, 1,
									value, true))) {
						Point  p = new Point(index, jndex);
						moves_list.add(p);
						// 	Check up left
					} else if ((this.gameBoard.getCellValue(index + 1,
							jndex - 1) != CellValue.BORDER)
							&& (this.gameBoard.getCellValue(index + 1,
									jndex - 1) != CellValue.EMPTY)
							&& (this.gameBoard.getCellValue(index + 1,
									jndex - 1) != value)
							&& (this.playDirection(index + 1, jndex - 1, 1, -1,
									value, true))) {
						Point  p = new Point(index, jndex);
						moves_list.add(p);
						// 	Check down right
					} else if ((this.gameBoard.getCellValue(index - 1,
							jndex + 1) != CellValue.BORDER)
							&& (this.gameBoard.getCellValue(index - 1,
									jndex + 1) != CellValue.EMPTY)
							&& (this.gameBoard.getCellValue(index - 1,
									jndex + 1) != value)
							&& (this.playDirection(index - 1, jndex + 1, -1, 1,
									value, true))) {
						Point  p = new Point(index, jndex);
						moves_list.add(p);
						// 	Check down left
					} else if ((this.gameBoard.getCellValue(index - 1,
							jndex - 1) != CellValue.BORDER)
							&& (this.gameBoard.getCellValue(index - 1,
									jndex - 1) != CellValue.EMPTY)
							&& (this.gameBoard.getCellValue(index - 1,
									jndex - 1) != value)
							&& (this.playDirection(index - 1, jndex - 1, -1, -1,
									value, true))) {
						Point  p = new Point(index, jndex);
						moves_list.add(p);
					}
				}
			}
		}
		return moves_list;
	}

	/** Function name	: playMove
	 * Parameters		: The player's color, player's chosen move.
	 *  Return value	: boolean value depending on the move's legality.
	 *  General flow	: Check if the move is legal.
	 *  				  if it is, play the move.
	 */
	@Override
	public boolean playMove(Point move, CellValue value) {
		boolean isLegal= false;

		this.gameBoard.setCellValue(move.getX(), move.getY(), value);

		// Change up
		if ((this.gameBoard.getCellValue(move.getX() - 1, move.getY()) != CellValue.BORDER)
				&& (this.gameBoard.getCellValue(move.getX() - 1, move.getY())
						!= CellValue.EMPTY)
				&& (this.gameBoard.getCellValue(move.getX() - 1, move.getY())
						!= value)
				&& (this.playDirection(move.getX() - 1, move.getY(), -1, 0, value,
						false))) {
			isLegal= true;
		}

		// Change down
		if ((this.gameBoard.getCellValue(move.getX() + 1, move.getY()) != CellValue.BORDER)
				&& (this.gameBoard.getCellValue(move.getX() + 1, move.getY())
						!= CellValue.EMPTY)
				&& (this.gameBoard.getCellValue(move.getX() + 1, move.getY())
						!= value)
				&& (this.playDirection(move.getX() + 1, move.getY(), 1, 0, value,
						false))) {
			isLegal= true;
		}

		// Check left.
		if ((this.gameBoard.getCellValue(move.getX(), move.getY() - 1) != CellValue.BORDER)
				&& (this.gameBoard.getCellValue(move.getX(), move.getY() - 1)
						!= CellValue.EMPTY)
				&& (this.gameBoard.getCellValue(move.getX(), move.getY() - 1)
						!= value)
				&& (this.playDirection(move.getX(), move.getY() - 1, 0, -1, value,
						false))) {
			isLegal= true;
		}

		// Check right.
		if ((this.gameBoard.getCellValue(move.getX(), move.getY() + 1) != CellValue.BORDER)
				&& (this.gameBoard.getCellValue(move.getX(), move.getY() + 1)
						!= CellValue.EMPTY)
				&& (this.gameBoard.getCellValue(move.getX(), move.getY() + 1)
						!= value)
				&& (this.playDirection(move.getX(), move.getY() + 1, 0, 1, value,
						false))) {
			isLegal= true;
		}

		// Check up right
		if ((this.gameBoard.getCellValue(move.getX() + 1, move.getY() + 1)
				!= CellValue.BORDER)
				&& (this.gameBoard.getCellValue(move.getX() + 1,
						move.getY() + 1) != CellValue.EMPTY)
				&& (this.gameBoard.getCellValue(move.getX() + 1,
						move.getY() + 1) != value)
				&& (this.playDirection(move.getX() + 1, move.getY() + 1, 1, 1,
						value, false))) {
			isLegal= true;
		}

		// Check up left
		if ((this.gameBoard.getCellValue(move.getX() + 1, move.getY() - 1)
				!= CellValue.BORDER)
				&& (this.gameBoard.getCellValue(move.getX() + 1,
						move.getY() - 1) != CellValue.EMPTY)
				&& (this.gameBoard.getCellValue(move.getX() + 1,
						move.getY() - 1) != value)
				&& (this.playDirection(move.getX() + 1, move.getY() - 1, 1, -1,
						value, false))) {
			isLegal= true;
		}

		// Check down right
		if ((this.gameBoard.getCellValue(move.getX() - 1, move.getY() + 1)
				!= CellValue.BORDER)
				&& (this.gameBoard.getCellValue(move.getX() - 1,
						move.getY() + 1) != CellValue.EMPTY)
				&& (this.gameBoard.getCellValue(move.getX() - 1,
						move.getY() + 1) != value)
				&& (this.playDirection(move.getX() - 1, move.getY() + 1, -1, 1,
						value, false))) {
			isLegal= true;
		}

		// Check down left
		if ((this.gameBoard.getCellValue(move.getX() - 1, move.getY() - 1)
				!= CellValue.BORDER)
				&& (this.gameBoard.getCellValue(move.getX() - 1,
						move.getY() - 1) != CellValue.EMPTY)
				&& (this.gameBoard.getCellValue(move.getX() - 1,
						move.getY() - 1) != value)
				&& (this.playDirection(move.getX() - 1, move.getY() - 1, -1, -1,
						value, false))) {
			isLegal= true;
		}

		return isLegal;
	}

	/**
	 * Function name    : getBoard
	 * Parameters       : none
	 * Return value     : the game's board (pointer)
	 * General flow     : returns a pointer to the game's
	 * 	                  current board.
	 */
	@Override
	public Board getBoard() {
		return this.gameBoard;
	}
	
	/**
	 * Function name    : playDirection.
	 * Parameters       : two start coordinates, two new x,y values,
	 * 					  player's color, boolean flag
	 * Return value     : boolean flag
	 * General flow     : determines the direction of advance
	 * 					  and checks if there are any tiles to flip
	 * 					  in that direction. Returns answer as boolean flag.
	 */
	boolean playDirection(int x, int y, int xMove, int yMove, CellValue value,
			boolean checkMode){
		boolean isTurned;

		// If out of range
		if (this.gameBoard.getCellValue(x, y) == CellValue.BORDER) {
			return false;
		}

		// If we reached an CellValue.EMPTY spot.
		if (this.gameBoard.getCellValue(x, y) == CellValue.EMPTY) {
			// 1 means false
			return false;
		}

		// If we reaches a cell with the current player's color
		if (this.gameBoard.getCellValue(x, y) == value) {
			return true;
		}

		// We are in a cell with the opponent's color
		// Checks if there is a cell with the players color in the end
		isTurned = this.playDirection(x + xMove, y + yMove, xMove, yMove, value,
				checkMode);
		// If we have to turn the cell's color
		if (isTurned) {
			if (!checkMode) {
				this.gameBoard.setCellValue(x, y, value);
			}
		}
		return isTurned;
	}
}