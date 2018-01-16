package application;

import advanced.Display;
import java.util.*;

/**
 * Names : Yifat Yankocivh Shoham Bar-Gad IDs : 204709224 315706614 User Names :
 * yankovy bagnosh
 *
 * Class Name : ConsuleDisplay.h Description : Defines the methods used to
 * display the surface of the game on the console.
 */

public class ConsuleDisplay implements Display {
	private Scanner reader;
	
	/**
	 * Function name : ConsuleDisplay Parameters : None. Return value : None.
	 * General flow : Empty constructor.
	 */
	public ConsuleDisplay() {
		this.reader = new Scanner(System.in);
	}

	/**
	 * Function name : DisplayBoard 
	 * Parameters : game Board. 
	 * Return value : None. 
	 * General flow : Displays the game board.
	 */
	@Override
	public void DisplayBoard(Board bord) {
		int index, jndex;

		System.out.print(" |");
		for (index = 1; index <= bord.getGameBoardWidth(); ++index) {
			System.out.print(" " + index + " |");
		}

		System.out.println();

		System.out.print("--");
		for (int i = 0; i < bord.getGameBoardWidth(); ++i) {
			System.out.print("----");
		}
		System.out.println();

		for (index = 1; index <= bord.getGameBoardWidth(); ++index) {
			System.out.print("|");
			for (jndex = 1; jndex <= bord.getGameBoardWidth(); ++jndex) {
				switch (bord.getCellValue(index, jndex)) {
					case EMPTY: {
						System.out.print("   |");
						break;
					}
					case WHITE: {
						System.out.print(" O |");
						break;
					}
					case BLACK: {
						System.out.print(" X |");
						break;
					}
					default: {
						break;
					}
				}
			}

			System.out.println();
			System.out.print("--");
			for (int i = 0; i < bord.getGameBoardWidth(); ++i) {
				System.out.print("----");
			}
			System.out.println();
		}
	}

	/**
	 * Function name : DisplayMessage 
	 * Parameters : A string. 
	 * Return value : None. 
	 * General flow : Displays the massage to the player.
	 */
	public void DisplayMessage(String massage) {
		System.out.println(massage);
	}
	
	/**
	 * Function name : getNum
	 * Parameters : None. 
	 * Return value : A number (int).
	 * General flow : Gets a number from the user, and return itS.
	 */
	public int getNum(){
		int num;
		num = reader.nextInt();
		return num;
	}
}
