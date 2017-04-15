import processing.core.*;
import remixlab.proscene.*;

public class Luxo extends PApplet{
	
	Scene scene;
	Lamp lamp;
	
	public void setup(){
		size(640, 360, P3D);
		scene = new Scene(this);
		scene.setAxisIsDrawn(false);
		scene.setGridIsDrawn(false);
		scene.setFrameSelectionHintIsDrawn(true);
		// press 'f' to display frame selection hints
		scene.setShortcut('f', Scene.KeyboardAction.DRAW_FRAME_SELECTION_HINT);
		lamp = new Lamp(scene);		
	}
	
	public void draw(){
		background(0);
		lights();
		lamp.draw();
		//draw the ground
		noStroke();
		fill(120, 120, 120);
		float nbPatches = 100;
		normal(0.0f,0.0f,1.0f);
		for (int j=0; j<nbPatches; ++j) {
		beginShape(QUAD_STRIP );
		for (int i=0; i<=nbPatches; ++i) {
		  vertex((200*(float)i/nbPatches-100), (200*j/nbPatches-100));
		  vertex((200*(float)i/nbPatches-100), (200*(float)(j+1)/nbPatches-100));
		}
		endShape();
		}
	}

}
