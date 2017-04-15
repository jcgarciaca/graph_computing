package importSTL;

import processing.core.*;
import toxi.geom.*;
import toxi.geom.mesh.*;
import toxi.processing.*;

public class ImportarSTL extends PApplet{
	
	TriangleMesh mesh;
	ToxiclibsSupport gfx;
	
	public void setup(){
		size(600,600,P3D);
		mesh=(TriangleMesh)new STLReader().loadBinary(sketchPath("base.stl"),STLReader.TRIANGLEMESH);
		//mesh=(TriangleMesh)new STLReader().loadBinary(sketchPath("mesh-flipped.stl"),STLReader.TRIANGLEMESH).flipYAxis();
		gfx=new ToxiclibsSupport(this); 
	}
	
	public void draw(){
		background(51);
		fill(0,255,0);
		  lights();
		  translate(width/2,height/2,0);
		  pushMatrix();
		  rotateX((float)(mouseY*0.01));
		  rotateY((float)(mouseX*0.01));
		  scale(1);
		  //gfx.origin(new Vec3D(),200);
		  noStroke();
		  gfx.mesh(mesh,false);//10);
		  popMatrix();
	}

}
