import java.util.Random;

import com.cleverfranke.util.PColor;

import de.looksgood.ani.Ani;
import de.looksgood.ani.AniSequence;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class Square {
	
	public static final int SIZE = 80;
	private static final int HALF_SIZE = SIZE / 2;
	
	private Pattern pattern;
	private PVector pos;
	private float rotation;
	private boolean blackOnWhite;
	
	private AniSequence inOutTransition;
	private float transitionProgress = 0f;
	
	public Square(PVector pos, PApplet applet) {
		this.pos = pos;
		this.blackOnWhite = randomColorStyle();
		rotation = randomRotation();
		
		float t = (float) (4f + Math.random() * 8f); 
		
		// Setup transition sequence
		inOutTransition = new AniSequence(applet);
		inOutTransition.beginSequence();
		inOutTransition.add(Ani.to(this, .5f, "transitionProgress", 1f, Ani.LINEAR)); // Fade in
		inOutTransition.add(Ani.to(this, .5f, t, "transitionProgress", 0f, Ani.LINEAR, "onEnd:change")); // Fade out
		inOutTransition.endSequence();
		
		// Randomize (and start transition)
		change();
		
	}
	
	public void draw(PGraphics g) {
		g.pushMatrix();
		g.pushStyle();
		g.translate(pos.x, pos.y);
		g.rotate(rotation);
		
		transitionProgress = Math.min(1f, Math.max(0f, transitionProgress));
		
		// Determine colors
		int colorF = blackOnWhite ? PColor.color(0) : PColor.color(255);
		int colorB = blackOnWhite ? PColor.color(255) : PColor.color(0);
		
		// BG
		g.fill(colorB);
		g.noStroke();
		g.rect(-HALF_SIZE, -HALF_SIZE, SIZE, SIZE);
		
		switch (pattern) {
			case CIRCLES_CONCENTRIC: {
				g.noFill();
				g.stroke(colorF);
				g.strokeWeight(SIZE / 10);
				g.strokeCap(PConstants.SQUARE);
				
				float start = PConstants.PI;
				float end = PConstants.PI + transitionProgress * PConstants.PI * 0.5f;
				float diff = end - start;
				
				g.arc(HALF_SIZE, HALF_SIZE, SIZE * 1.4f, SIZE * 1.4f, start, end);
				g.arc(HALF_SIZE, HALF_SIZE, SIZE * 1f, SIZE * 1f,  PConstants.PI + PConstants.PI * 0.5f - diff, PConstants.PI + PConstants.PI * 0.5f);
				g.arc(HALF_SIZE, HALF_SIZE, SIZE * .6f, SIZE * .6f,  start, end);
			} break;
			case LINES: {
				g.noFill();
				g.stroke(colorF);
				g.strokeWeight(SIZE / 9);
				g.strokeCap(PConstants.SQUARE);
				
				g.line(-HALF_SIZE, -HALF_SIZE * .5f, -HALF_SIZE + transitionProgress * SIZE, -HALF_SIZE * .5f);
				g.line(HALF_SIZE, 0, HALF_SIZE - transitionProgress * SIZE, 0);
				g.line(-HALF_SIZE, HALF_SIZE * .5f, -HALF_SIZE + transitionProgress * SIZE, HALF_SIZE * .5f);
				
			} break;
			default:
				System.err.println("Unimplemented pattern: " + pattern);
				break;
			
		}
		
		// Solid part
		g.noStroke();
		g.fill(colorF);
		g.beginShape(PConstants.TRIANGLE_FAN);
		g.vertex(-HALF_SIZE, -HALF_SIZE);
		g.vertex(-HALF_SIZE, HALF_SIZE);
		g.vertex(HALF_SIZE, -HALF_SIZE);
		g.endShape();
		
		// Edge
		g.noFill();
		g.strokeWeight(2);
		g.stroke(0);
		g.rect(-HALF_SIZE, -HALF_SIZE, SIZE, SIZE);
		
		g.popStyle();
		g.popMatrix();
	}
	
	public void change() {
		pattern = randomPattern();
//		rotation = randomRotation();
//		blackOnWhite = randomColorStyle();
		
		// Start transition
		inOutTransition.start();
	}
	
	private Pattern randomPattern() {
		int pick = new Random().nextInt(Pattern.values().length);
		return Pattern.values()[pick];
	}
	
	private float randomRotation() {
		int random = new Random().nextInt(4);
		return ((float) random) * 0.5f * PConstants.PI;
	}
	
	private boolean randomColorStyle() {
		return (new Random().nextInt(2) == 1);
	}
		
}
