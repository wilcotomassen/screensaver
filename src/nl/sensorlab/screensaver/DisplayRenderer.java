package nl.sensorlab.screensaver;

import processing.core.PApplet;
import processing.core.PGraphics;

public abstract class DisplayRenderer {
	
	protected PApplet applet;
	
	public DisplayRenderer(PApplet applet) {
		this.applet = applet;
	}
	
	public abstract void draw(PGraphics g);
	
}
