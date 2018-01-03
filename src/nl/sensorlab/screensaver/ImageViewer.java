package nl.sensorlab.screensaver;

import java.io.File;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public class ImageViewer extends DisplayRenderer {
	
	private PImage image;

	public ImageViewer(PApplet applet) {
		super(applet);
	}
	
	public void setImage(File imagePath) {
		image = imagePath.exists() ?
				applet.loadImage(imagePath.toString()) 
				: null;
	}

	@Override
	public void draw(PGraphics g) {
		g.background(0);
		if (image != null) {
			g.imageMode(PConstants.CENTER);
			g.translate(applet.width / 2, applet.height / 2);
			g.image(image, 0, 0);
		}
	}

}
