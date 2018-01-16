package advanced;
import advanced.Board;
import advanced.Point;
import java.util.List;;

/**
 * Names       : Yifat Yankocivh
 * 				 Shoham Bar-Gad
 * IDs	       : 204709224
 * 				 315706614
 * User Names  : yankovy
 * 				 bagnosh
 *
 * Class Name  : GameLogic.h
 * Description : Defines the guidelines used within the game. (Interface)
 */

public interface Logic {
	/** Function name	: getLegalMoves
	 *  Parameters		: A player's color.
	 *  Return value	: A vector containing all of the legal
	 *  				  moves the player may choose from.
	 *  General flow	: Check all cells in the game board to see
	 *  				  if they count as legal moves.
	 *  			      If a cell answers the criteria,
	 *  			      the function adds the its location to the vector.
	 */
	public List<Point> getLegalMoves(CellValue value);

	/** Function name	: playMove
	 *  Parameters		: The player's color, player's chosen move.
	 *  Return value	: boolean value depending on the move's legality.
	 *  General flow	: Check if the move is legal.
	 *  				  if it is, play the move.
	 */
	public boolean playMove(Point move, CellValue value);

	/**
	 * Function name    : getBoard
	 * Parameters       : none
	 * Return value     : the game's board (pointer)
	 * General flow     : returns a pointer to the game's
	 * 	                  current board.
	 */
	public Board getBoard();
}
