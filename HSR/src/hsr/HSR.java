package hsr;

import java.util.ArrayList;

import processing.core.*;
import remixlab.proscene.*;

public class HSR extends PApplet{
	
	Scene scene, camScene;
	PGraphics canvas, auxCanvas;
	ArrayList<Triangulos> puntos = new ArrayList<Triangulos>();
	ArrayList<Triangulos> escena = new ArrayList<Triangulos>();
	
	int[] color1 = {0, 0, 255}, color2 = {128, 128, 0}, color3 = {200, 128, 90};
	
	float[] pTriangulo1 = new float[3];
	float[] pTriangulo2 = new float[3];
	float[] pTriangulo3 = new float[3];
		
	
	public void setup(){
		size(600, 600, P3D);
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
	
		canvas.hint(DISABLE_DEPTH_TEST);
		auxCanvas.hint(DISABLE_DEPTH_TEST);
		
		pTriangulo1[0] = 300;
		pTriangulo1[1] = 0;
		pTriangulo1[2] = 0;
		
		pTriangulo2[0] = 0;
		pTriangulo2[1] = 300;
		pTriangulo2[2] = -100;
		
		pTriangulo3[0] = 0;
		pTriangulo3[1] = 0;
		pTriangulo3[2] = 300;
		
		escena.add(new Triangulos(pTriangulo1, pTriangulo2, pTriangulo3, color1));
		
		pTriangulo1[0] = 100;
		pTriangulo1[1] = 0;
		pTriangulo1[2] = 0;
		
		pTriangulo2[0] = 0;
		pTriangulo2[1] = 100;
		pTriangulo2[2] = 0;
		
		pTriangulo3[0] = 0;
		pTriangulo3[1] = 0;
		pTriangulo3[2] = 100;
		
		escena.add(new Triangulos(pTriangulo1, pTriangulo2, pTriangulo3, color2));
		
		pTriangulo1[0] = -100;
		pTriangulo1[1] = 0;
		pTriangulo1[2] = -100;
		
		pTriangulo2[0] = -50;
		pTriangulo2[1] = -50;
		pTriangulo2[2] = 700;
		
		pTriangulo3[0] = 50;
		pTriangulo3[1] = 50;
		pTriangulo3[2] = 700;
		
		escena.add(new Triangulos(pTriangulo1, pTriangulo2, pTriangulo3, color3));
	}
	
	public void draw(){
		background(0);	
		
		puntos.clear();		
		
		dibujarEscena();
		
		for (int i = 0; i < escena.size(); i++) {
//			Triangulos faceVerticesWorld = escena.get(i);
			PVector vect1 = new PVector(escena.get(i).vert1[0], escena.get(i).vert1[1], escena.get(i).vert1[2]);
			float [] vert1={scene.camera().cameraCoordinatesOf(vect1).x, scene.camera().cameraCoordinatesOf(vect1).y, scene.camera().cameraCoordinatesOf(vect1).z};
			PVector vect2 = new PVector(escena.get(i).vert1[0], escena.get(i).vert1[1], escena.get(i).vert1[2]);
			float [] vert2={scene.camera().cameraCoordinatesOf(vect2).x, scene.camera().cameraCoordinatesOf(vect2).y, scene.camera().cameraCoordinatesOf(vect2).z};
			PVector vect3 = new PVector(escena.get(i).vert1[0], escena.get(i).vert1[1], escena.get(i).vert1[2]);
			float [] vert3={scene.camera().cameraCoordinatesOf(vect3).x, scene.camera().cameraCoordinatesOf(vect3).y, scene.camera().cameraCoordinatesOf(vect3).z};
			puntos.add(new Triangulos(vert1, vert2, vert3, color1));
		}
		
		System.out.println(puntos.size());
		
		organizar();
		algoritmoPintor();
		dibujaVista();
	}
	
	public void dibujarEscena(){
		canvas.beginDraw();	
		canvas.lights();	
		canvas.background(200);	
		scene.beginDraw();	
		canvas.noStroke();
		
		PVector p1a = new PVector(300, 0, 0);
		PVector p1b = new PVector(0, 300, -100);
		PVector p1c = new PVector(0, 0, 300);
				
		canvas.fill(color1[0], color1[1], color1[2]);
		canvas.beginShape(TRIANGLES);
		canvas.vertex(p1a.x, p1a.y, p1a.z);
		canvas.vertex(p1b.x, p1b.y, p1b.z);
		canvas.vertex(p1c.x, p1c.y, p1c.z);
		canvas.endShape();		
		
		PVector p2a = new PVector(100, 0, 0);
		PVector p2b = new PVector(0, 100, 0);
		PVector p2c = new PVector(0, 0, 100);
				
		canvas.fill(color2[0], color2[1], color2[2]);
		canvas.beginShape(TRIANGLES);
		canvas.vertex(p2a.x, p2a.y, p2a.z);
		canvas.vertex(p2b.x, p2b.y, p2b.z);
		canvas.vertex(p2c.x, p2c.y, p2c.z);
		canvas.endShape();
		
		PVector p3a = new PVector(-100, 0, -100);
		PVector p3b = new PVector(-50, -50, 700);
		PVector p3c = new PVector(50, 50, 700);
				
		canvas.fill(color3[0], color3[1], color3[2]);
		canvas.beginShape(TRIANGLES);
		canvas.vertex(p3a.x, p3a.y, p3a.z);
		canvas.vertex(p3b.x, p3b.y, p3b.z);
		canvas.vertex(p3c.x, p3c.y, p3c.z);
		canvas.endShape();
		
		
		scene.endDraw();	
		canvas.endDraw();	
		image(canvas, 0, 0);
	}
	
	public void organizar(){
		for (int i = 0; i < puntos.size(); i++) {
			System.out.println("entra");
            for (int j = 0; j < puntos.size() - 1; j++) {                  
            	if (puntos.get(j).getZMin() > puntos.get(j+1).getZMin()) {//if (puntos.get(j).z > puntos.get(j+1).z) {
                     intercambiar(j);
                 }
            }
        }
	}
	
	public void intercambiar(int indice) {
		System.out.println("intercambiar");
        Triangulos trianTemp = puntos.get(indice);
        puntos.set(indice, puntos.get(indice + 1));
        puntos.set(indice + 1, trianTemp);
    }
	
	public void algoritmoPintor(){
		boolean prueba;
		Triangulos aux;
		for (int i = 0; i < puntos.size(); i++) {
            for (int j = i; j < puntos.size(); j++) {
                prueba = false;
                //Restriccion 1 no se translapa en X
                if(puntos.get(i).xMax <= puntos.get(j).xMin){
                    prueba=true;
                //Restriccion 1 no se translapa en Y
                }else{ 
                    if(puntos.get(i).yMax <= puntos.get(j).yMin){
                        prueba = true;
                    //S está detrás del plano de S' 
                    }else{
                    	
                    	if(
                    		puntos.get(j).aV * puntos.get(i).vert1[0] +
                            puntos.get(j).bV * puntos.get(i).vert1[1] +
                            puntos.get(j).cV * puntos.get(i).vert1[2] +
                            puntos.get(j).dV <= 0 &&
                        
                    		puntos.get(j).aV * puntos.get(i).vert2[0] +
                            puntos.get(j).bV * puntos.get(i).vert2[1] +
                            puntos.get(j).cV * puntos.get(i).vert2[2] +
                            puntos.get(j).dV <= 0 &&
                        
                    		puntos.get(j).aV * puntos.get(i).vert3[0] +
                            puntos.get(j).bV * puntos.get(i).vert3[1] +
                            puntos.get(j).cV * puntos.get(i).vert3[2] +
                            puntos.get(j).dV <= 0 
                        
                        )
                        {
                            prueba = true;
                        }
                        //S' está delante del plano de S
                        else{
                            if(
                    		puntos.get(i).aV * puntos.get(j).vert1[0] +
                            puntos.get(i).bV * puntos.get(j).vert1[1] +
                            puntos.get(i).cV * puntos.get(j).vert1[2] +
                            puntos.get(i).dV >= 0 &&
                        
                    		puntos.get(i).aV * puntos.get(j).vert2[0] +
                            puntos.get(i).bV * puntos.get(j).vert2[1] +
                            puntos.get(i).cV * puntos.get(j).vert2[2] +
                            puntos.get(i).dV >= 0 &&
                        
                    		puntos.get(i).aV * puntos.get(j).vert3[0] +
                            puntos.get(i).bV * puntos.get(j).vert3[1] +
                            puntos.get(i).cV * puntos.get(j).vert3[2] +
                            puntos.get(i).dV >= 0 
                            )
                            {
                                prueba = true;
                            }
                        }
                    }
                }

                if(!prueba){
                    aux = puntos.get(i);
                    puntos.set(i, puntos.get(j));
                    puntos.set(j, aux);
                    j=puntos.size();
                }

            }
		}
	}
	public void dibujaVista(){
		auxCanvas.beginDraw();	
		auxCanvas.lights();
		auxCanvas.background(150);	
		camScene.beginDraw();	
		auxCanvas.noStroke();
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

}
