package nl.sensorlab.screensaver;

import de.looksgood.ani.Ani;
import nl.sensorlab.screensaver.SensorLabAnimation.SensorLabAnimation;
import processing.core.PApplet;
import processing.event.KeyEvent;

public class Main extends PApplet {
	
	private FileHandler fileHandler;
	private DisplayRenderer currentRenderer;
	private SensorLabAnimation animation;
	private ImageViewer imageViewer;
	
	public void settings() {
		size(1280, 960);
//		fullScreen();
	}
	
	public void setup() {
		
		frameRate(60);
		
		// Init Ani library
		Ani.init(this);
		Ani.noAutostart();
		Ani.setDefaultTimeMode("SECONDS");
		
		fileHandler = new FileHandler("C:\\tmp");
		
		// Create renderers
		animation = new SensorLabAnimation(this);
		imageViewer = new ImageViewer(this);
		
		currentRenderer = animation;
		
	}
	
	@Override
	public void keyReleased(KeyEvent event) {
		switch (event.getKeyCode()) {
			case 37: // Left
				
				break;
			case 39: // Right
				
				break;
		}
	}
	
	public void draw() {
		currentRenderer.draw(g);
		
		
		if (frameCount % 120 == 0) {
			fileHandler.pollForChanges();
		}
		
	}

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}

}
