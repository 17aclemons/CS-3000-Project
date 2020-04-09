package josephus;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Person { //Nikki, Andrew, Matt

	private boolean alive;
	private int posX;
	private int posY;
	public Image image;
	public ImageView iView;
	public int id;
	
	Person(int x, int y, int id){
	alive = true;
	posX = x;
	posY = y;
	image = new Image("alive.png");
	iView = new ImageView(image);
	this.id = id;
		
	}
	
	public boolean isAlive() {return alive;}
	
	public void kill() {
		alive = false;
		image = new Image("dead.png");
		iView = new ImageView(image);
	}
	
	public void reset() {
		alive = true;
		image = new Image("alive.png");
	}
}
