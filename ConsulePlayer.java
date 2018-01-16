package advanced;

import java.util.List;
import advanced.CellValue;
import java.lang.StringBuilder;

/**
 * Names : Yifat Yankocivh Shoham Bar-Gad IDs : 204709224 315706614 User Names :
 * yankovy bagnosh
 *
 * Class Name : ConsolePlayer.h Description : Defines the attributes and methods
 * of a human player in the game. (Interface)
 */
public class ConsulePlayer implements Player {
	private CellValue value;
	ConsuleDisplay display;

	/**
	 * Function name : ConsolePlayer Parameters : Player's value ('color').
	 * Return value : The created console player. General flow : Assignment of
	 * the members.
	 */
	public ConsulePlayer(CellValue value) {
		this.value = value;
		this.display = new ConsuleDisplay();
	}

	/**
	 * Function name : move Parameters : list of possible moves, last move of
	 * the opponent, game board. Return value : The point chosen between the
	 * points provided. General flow : The function lets the user choose their
	 * next move, checks if the point is legal, and returns the point finally
	 * chosen.
	 */
	@Override
	public Point move(List<Point> moves, Point opponentMove, Board board) {
		int xChoise = 0, yChoise = 0;
		boolean isValid = false;
		StringBuilder str = new StringBuilder();

		this.display.DisplayBoard(board);
		if (!opponentMove.equals(new Point(-1, -1))) {
			if (this.value == CellValue.WHITE) {
				str.append("X played: (");
			} else {
				str.append("O played: (");
			}
			str.append(opponentMove.getX());
			str.append(",");
			str.append(opponentMove.getY());
			str.append(")");
			this.display.DisplayMessage(str.toString());
			this.display.DisplayMessage("");
		}

		if (this.value == CellValue.WHITE) {
			this.display.DisplayMessage("O: Your turn!");
		} else {
			this.display.DisplayMessage("X: Your turn!");
		}

		if (moves.size() == 0) {
			this.display.DisplayMessage("No moves left. Turn passes on!");
			return new Point(-1, -1);
		}

		str.delete(0, str.capacity());

		while (!isValid) {
			this.display.DisplayMessage("Your possible moves: ");
			for (Point p : moves) {
				str.append("(" + p.getX() + "," + p.getY() + "),");
			}

			// Delete the last coma ','.
			str.deleteCharAt(str.capacity());
			this.display.DisplayMessage(str.toString());
			this.display.DisplayMessage("Please enter your next move (row col):");

			xChoise = this.display.getNum();
			yChoise = this.display.getNum();

			// Checks if the move the user chose is valid.
			for (Point p : moves) {
				if (p.getX() == xChoise && p.getY() == yChoise) {
					isValid = true;
					break;
				}
			}

			if (!isValid) {
				this.display.DisplayMessage("Invalid choice, please try again.");
				this.display.DisplayMessage("");
			}
		}
		
		return new Point(xChoise, yChoise);
	}

	/**
	 * Function name : getValue 
	 * Parameters : None. 
	 * Return value : a player's
	 * value, their "color". 
	 * General flow : The function returns the player's
	 * value, the player's color.
	 */
	@Override
	public CellValue getValue() {
		return this.value;
	}
}
