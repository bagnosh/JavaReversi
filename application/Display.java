package application;

/**
 * Names       : Yifat Yankocivh
 * 				 Shoham Bar-Gad
 * IDs	       : 204709224
 * 				 315706614
 * User Names  : yankovy
 * 				 bagnosh
 *
 * Class Name  : GameDisplay.h
 * Description : Defines the methods used to display
 * 				 the surface of the game.
 */

/**
 * The class is an interface that represents how the game is displayed for the
 * user.
 */
public interface Display {
	/**
	 * Function name : Display 
	 * Parameters : game Board. 
	 * Return value : None.
	 * General flow : Displays the game board.
	 */
	public void DisplayBoard(Board bord);
	
	/**
	 * Function name : DisplayMessage 
	 * Parameters : A string. 
	 * Return value : None. 
	 * General flow : Displays the massage to the player.
	 */
	public void DisplayMessage(String massage);
	
	/**
	 * Function name : getNum
	 * Parameters : None. 
	 * Return value : A number (int).
	 * General flow : Gets a number from the user, and return itS.
	 */
	public int getNum();
}
