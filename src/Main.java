import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {
	
	private ArrayList<Square> squares = new ArrayList<>();
	
	public void settings() {
		size(1280, 960);
	}
	
	public void setup() {
		frameRate(30);
		
		// Init Ani library
		Ani.init(this);
		Ani.noAutostart();
		Ani.setDefaultTimeMode("SECONDS");
		
		// Create squares
		for (int y = 0; y < height; y += Square.SIZE) {
			for (int x = 0; x < width; x += Square.SIZE) {
				squares.add(new Square(new PVector(x + Square.SIZE / 2, y + Square.SIZE / 2), this));
			}
		}
		
	}
	
	public void draw() {
		background(0);
		
		for (Square s: squares) {
			s.draw(g);
		}
		
	}

	public static void main(String[] args) {
		// Program execution starts here
		PApplet.main(Main.class.getName());
	}

}
