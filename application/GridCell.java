package application;

import javafx.scene.control.Label;

public class GridCell extends Label {
	private int x;
	private int y;
	
	GridCell(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	int getX() {
		return this.x;
	}
	
	int getY() {
		return this.y;
	}
}