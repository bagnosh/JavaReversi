package application;
import advanced.CellValue;
/**
 * Names       : Yifat Yankocivh
 * 				 Shoham Bar-Gad
 * IDs	       : 204709224
 * 				 315706614
 * User Names  : yankovy
 * 				 bagnosh
 *
 * Class Name  : Board.cpp
 * Description : Implements the methods used to manage a
 * 				 game board.
 */

public class Board {
	private CellValue gameBoard[][];
	private int boardWidth;
	private int emptyCellsNum;
	private int whiteCellsNum;
	private int blackCellsNum;

	/** Function name	: Board
	 *  Parameters		: board width (integer).
	 *  Return value	: The created board.
	 *  General flow	: Board constructor.
	 *  				  Creates and initializes the board.
	 */
	public Board(int width){
		// Add the borders
		this.boardWidth = width + 2;

		// Creates the board
		this.gameBoard = new CellValue[this.boardWidth][this.boardWidth];

		// Initiate the board
		for(int i = 0; i < this.boardWidth; ++i){
			for(int j =0; j < this.boardWidth; ++j){
				// If the is a border cell
				if ((i == 0) || (j == 0)
						 || (i == this.boardWidth - 1)
						 || (j == this.boardWidth - 1)) {
					this.gameBoard[i][j] = CellValue.BORDER;
				} else {
					this.gameBoard[i][j] = CellValue.EMPTY;
				}
			}
		}

		this.blackCellsNum = 0;
		this.whiteCellsNum = 0;
		this.emptyCellsNum = (this.boardWidth - 2) * (this.boardWidth - 2);
	}

	/** Function name	: getCellValue
	 *  Parameters		: cell coordinates
	 *  Return value	: cell's inner value.
	 *  General flow	: returns the cell's value -
	 *  				  either a color, 'border' or 'empty'.
	 */
	public CellValue getCellValue(int heigth, int width) {
		return this.gameBoard[heigth][width];
	}
	
	/** Function name	: getBlackCellsNumber
	 *  Parameters		: None.
	 *  Return value	: The number of black cells in the board.
	 *  General flow	: The function returns the number of black cells in the board.
	 */
	public int getBlackCellsNumber() {
		return this.blackCellsNum ;
	}

	/** Function name	: getWhiteCellsNumber
	 *  Parameters		: None.
	 *  Return value	: The number of white cells in the board.
	 *  General flow	: The function returns the number of white cells in the board.
	 */
	public int getWhiteCellsNumber() {
		return this.whiteCellsNum;
	}

	/** Function name	: getEmptyCellsNumber
	 *  Parameters		: None.
	 *  Return value	: The number of empty cells in the board.
	 *  General flow	: The function returns the number of empty cells in the board.
	 */
	public int getEmptyCellsNumber() {
		return this.emptyCellsNum;
	}

	/** Function name	: getGameBoardWidth
	 *  Parameters		: None.
	 *  Return value	: The game board's width.
	 *  General flow	: The function returns the game board's width.
	 */
	public int getGameBoardWidth() {
		return this.boardWidth - 2;
	}

	/**
	 * Function name    : setCellValue
	 * Parameters       : two coordinates and a color value
	 * Return value     : none
	 * General flow     : sets given value in cell of given coordinates.
	 */
	public void setCellValue(int x, int y, CellValue value) {
		if(this.gameBoard[x][y] == CellValue.WHITE) {
			this.whiteCellsNum--;
			this.emptyCellsNum++;
		} else if(this.gameBoard[x][y] == CellValue.BLACK) {
			this.blackCellsNum --;
			this.emptyCellsNum ++;
		}

		if(value == CellValue.WHITE) {
			this.gameBoard[x][y] = CellValue.WHITE;
			this.whiteCellsNum ++;
			this.emptyCellsNum --;
		} else if(value == CellValue.BLACK){
			this.gameBoard[x][y] = CellValue.BLACK;
			this.blackCellsNum ++;
			this.emptyCellsNum --;
		}
	}
}

