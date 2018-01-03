package nl.sensorlab.screensaver.SensorLabAnimation;

import java.util.ArrayList;

import nl.sensorlab.screensaver.DisplayRenderer;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class SensorLabAnimation extends DisplayRenderer {
	
	private ArrayList<Square> squares = new ArrayList<>();

	public SensorLabAnimation(PApplet applet) {
		super(applet);
		
		// Create squares
		for (int y = 0; y < applet.height; y += Square.SIZE) {
			for (int x = 0; x < applet.width; x += Square.SIZE) {
				squares.add(new Square(new PVector(x + Square.SIZE / 2, y + Square.SIZE / 2), applet));
			}
		}
	}
	
	public void draw(PGraphics g) {
		g.background(0);
		
		for (Square s: squares) {
			s.draw(g);
		}
	}

}
