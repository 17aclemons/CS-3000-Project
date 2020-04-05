package josephus;

import javafx.scene.image.Image;

public class Person { //Nikki, Andrew, Matt

	private boolean alive;
	private int posX;
	private int posY;
	Image image;
	
	Person(int x, int y){
	alive = true;
	posX = x;
	posY = y;
	image = new Image("alive.png");
		
	}
	
	public void kill() {
		alive = false;
		image = new Image("dead.png");
	}
	
	public void reset() {
		alive = true;
		image = new Image("alive.png");
	}
}
