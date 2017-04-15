package objcanvas;

import processing.core.*;
import saito.objloader.*;

public class OBJCanvas extends PApplet{
	
	OBJModel model;
	float rotX, rotY;
	
	public void setup(){
		size(600, 600, P3D);
		model = new OBJModel(this, "base.obj", "absolute", TRIANGLES);
		model.enableDebug();
		model.translateToCenter();
		model.scale(5);
	}
	
	public void draw(){
		background(180);
		noStroke();
		lights();
		
		pushMatrix();
	    translate(width/2, height/2, 0);
	    rotateX(rotY);
	    rotateY(rotX);		
		model.draw();
		popMatrix();
	}
	
	public void mouseDragged(){
	    rotX += (mouseX - pmouseX) * 0.01;
	    rotY -= (mouseY - pmouseY) * 0.01;
	}

}
