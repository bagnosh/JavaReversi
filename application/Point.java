package application;

public class Point {
 private int x;
 private int y;
 
 public Point(int xCoord ,int yCoord){
	 this.x = xCoord;
	 this.y = yCoord;
 }
 
 public int getX() {
	  	return this.x;
 }
 
 public int getY() {
	  	return this.y;
 }
 
 public void setX(int xCoord){
	 this.x = xCoord;
 }

 public void setY(int yCoord){
	 this.x = yCoord;
 }
 
 public boolean equals(Point p){
	 if(p.getX() == this.x && p.getY() == this.y){
		 return true;
	 }
	 return false;
 }
}