package superficies;

import java.util.ArrayList;

import processing.core.*;
import remixlab.proscene.*;
import saito.objloader.*;


public class Superficies extends PApplet{
	
	Scene scene, camScene;
	PGraphics canvas, auxCanvas;
	OBJModel model, model2, model3;
	
	ArrayList<Triangulos> puntos = new ArrayList<Triangulos>();
	int[] color1 = {0, 0, 255}, color2 = {128, 128, 0}, color3 = {200, 128, 90};

	public void setup(){
		size(600, 600, P3D);	
		hint(DISABLE_DEPTH_TEST);	
		canvas = createGraphics(600, 300, P3D);	
		scene = new Scene(this, (PGraphics3D) canvas);	
		scene.setAxisIsDrawn(false);	
		scene.setGridIsDrawn(false);	
		scene.setRadius(110);	
		scene.showAll();
		
		
		
		auxCanvas = createGraphics(600, 300, P3D);	
		camScene = new Scene(this, (PGraphics3D) auxCanvas, 0, canvas.height);	
		camScene.setAxisIsDrawn(false);	
		camScene.setGridIsDrawn(false);	
		camScene.setRadius(50);	
//		model = new OBJModel(this, "BaseMesaRobot.obj", "absolute", TRIANGLES);
		
		
		canvas.hint(DISABLE_DEPTH_TEST);
		auxCanvas.hint(DISABLE_DEPTH_TEST);
		
		model = new OBJModel(this, "MesaPC.obj", "absolute", TRIANGLES);
		
		model2 = new OBJModel(this, "base.obj", "absolute", TRIANGLES);
		model2.translate(new PVector(80, 80, 0));
		
		model3 = new OBJModel(this, "BaseMesaRobot.obj", "absolute", TRIANGLES);
		
		model3.translate(new PVector(80, 80, 0));
		
//		model3.translate(new PVector(0, 0, -320));
				
		model.scale(1);
		model2.scale(1);
		model3.scale(1);
	
	}

	public void draw(){
		
		handleMouse();
		
		puntos.clear();
		
		canvas.beginDraw();	
		canvas.lights();	
		canvas.background(200);	
		scene.beginDraw();	
		canvas.noStroke();	
		canvas.fill(color1[0], color1[1], color1[2]);
	
		for (int k = 0; k < model.getFaceCount(); k++) {
			PVector[] faceVertices = model.getFaceVertices(k);	
			canvas.beginShape(TRIANGLES);	
			for (int i = 0; i < faceVertices.length; i++){		
				canvas.vertex(faceVertices[i].x, faceVertices[i].y, faceVertices[i].z);				
			}			
			canvas.endShape();	
		}		
		
//		canvas.translate(80, 80);
		
		canvas.fill(color2[0], color2[1], color2[2]);
		for (int k = 0; k < model2.getFaceCount(); k++) {
			PVector[] faceVertices = model2.getFaceVertices(k);	
			canvas.beginShape(TRIANGLES);	
			for (int i = 0; i < faceVertices.length; i++){		
				canvas.vertex(faceVertices[i].x, faceVertices[i].y, faceVertices[i].z);
			}			
			canvas.endShape();	
		}
		
		canvas.fill(color3[0], color3[1], color3[2]);
		for (int k = 0; k < model3.getFaceCount(); k++) {
			PVector[] faceVertices = model3.getFaceVertices(k);	
			canvas.beginShape(TRIANGLES);	
			for (int i = 0; i < faceVertices.length; i++){		
				canvas.vertex(faceVertices[i].x, faceVertices[i].y, faceVertices[i].z);				
			}
			canvas.endShape();	
		}
		
		scene.endDraw();	
		canvas.endDraw();	
		image(canvas, 0, 0);
		
		
		// Dibuja la vista de la camara		
		auxCanvas.beginDraw();	
		auxCanvas.lights();	
		auxCanvas.background(150);	
		camScene.beginDraw();	
		auxCanvas.noStroke();
		
		for (int i = 0; i < model.getFaceCount(); i++) {
			PVector[] faceVerticesWorld =model.getFaceVertices(i);
			float [] vert1={scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).z};
			float [] vert2={scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).z};
			float [] vert3={scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).z};
			puntos.add(new Triangulos(vert1, vert2, vert3, color1));
		}
		
//		auxCanvas.translate(80, 80);
		
//		auxCanvas.translate(scene.camera().cameraCoordinatesOf(new PVector(80, 80, 0)).x, 
//				scene.camera().cameraCoordinatesOf(new PVector(80, 80, 0)).y);
				
		for (int i = 0; i < model2.getFaceCount(); i++) {
			PVector[] faceVerticesWorld =model2.getFaceVertices(i);
			float [] vert1={scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).z};
			float [] vert2={scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).z};
			float [] vert3={scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).z};
			puntos.add(new Triangulos(vert1, vert2, vert3, color2));
		}
		
		
		for (int i = 0; i < model3.getFaceCount(); i++) {
			PVector[] faceVerticesWorld =model3.getFaceVertices(i);
			float [] vert1={scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).z};
			float [] vert2={scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).z};
			float [] vert3={scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).z};
			puntos.add(new Triangulos(vert1, vert2, vert3, color3));
		}
		
		organizar();
		
		for(int i = 0; i < puntos.size(); i++){
			auxCanvas.fill(puntos.get(i).color[0], puntos.get(i).color[1], puntos.get(i).color[2]);
			auxCanvas.beginShape(TRIANGLES);
			auxCanvas.vertex(puntos.get(i).vert1[0],
					puntos.get(i).vert1[1],
					puntos.get(i).vert1[2]);
			auxCanvas.vertex(puntos.get(i).vert2[0],
					puntos.get(i).vert2[1],
					puntos.get(i).vert2[2]);
			auxCanvas.vertex(puntos.get(i).vert3[0],
					puntos.get(i).vert3[1],
					puntos.get(i).vert3[2]);
			auxCanvas.endShape();
		}		
		camScene.endDraw();	
		auxCanvas.endDraw();	
		image(auxCanvas, 0, canvas.height);
	}
	
	public void handleMouse() {
		if (mouseY < canvas.height) {
		    scene.enableMouseHandling();
		    scene.enableKeyboardHandling();
		    camScene.disableMouseHandling();
		    camScene.disableKeyboardHandling();
		}else{
			scene.disableMouseHandling();
			scene.disableKeyboardHandling();
			camScene.enableMouseHandling();
			camScene.enableKeyboardHandling();
		}		  
	}
	
		
	public void organizar(){
		for (int i = 0; i < puntos.size(); i++) {             
            for (int j = 0; j < puntos.size() - 1; j++) {                  
            	if (puntos.get(j).getZMin() > puntos.get(j+1).getZMin()) {//if (puntos.get(j).z > puntos.get(j+1).z) {
                     intercambiar(j);
                 }
            }
        }
	}
	
	
	public void intercambiar(int indice) {
        Triangulos trianTemp = puntos.get(indice);
        puntos.set(indice, puntos.get(indice + 1));
        puntos.set(indice + 1, trianTemp);
    }

}