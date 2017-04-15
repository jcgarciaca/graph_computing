package bezier;

import java.util.ArrayList;

import processing.core.*;
import remixlab.proscene.InteractiveFrame;
import remixlab.proscene.Scene;

public class Bezier extends PApplet{
	
	Scene scene;
	InteractiveFrame keyFrame[];
	int nbKeyFrames;
	
	// Variables del algoritmo
	ArrayList<PuntosCurva> puntos = new ArrayList<PuntosCurva>();
	float[][] coeficientesX;
	float[][] coeficientesY;
	float[][] coeficientesZ;
	float[][] matrizBezier = {{-1, 3, -3, 1}, {3, -6, 3, 0}, {-3, 3, 0, 0}, {1, 0, 0, 0}};
	float[][] vectorP = new float[4][1];
	
	float valorX = 0, valorY = 0, valorZ = 0, valorXTemp = 0, valorYTemp = 0, valorZTemp = 0;
	//////////////////////////
	
	public void setup(){
		size(640, 360, P3D);
		nbKeyFrames = 4;
		scene = new Scene(this);
		scene.setAxisIsDrawn(false);
		scene.setGridIsDrawn(false);
		scene.showAll();
		scene.setFrameSelectionHintIsDrawn(true);
		scene.setShortcut('f', Scene.KeyboardAction.DRAW_FRAME_SELECTION_HINT);
		
		keyFrame = new InteractiveFrame[nbKeyFrames];
		
		for (int i=0; i<nbKeyFrames; i++) {			
		    keyFrame[i] = new InteractiveFrame(scene);
		    keyFrame[i].setPosition(-100 + 200*i/(nbKeyFrames-1), 0, 0);
		}
	}
	
	public void draw(){
		puntos.clear();
		background(0);
		
		noStroke();
		
		fill(0, 0, 255);
		pushMatrix();
		keyFrame[0].applyTransformation();
		sphere(5);
		popMatrix();
		
		fill(0, 255, 0);
		pushMatrix();
		keyFrame[1].applyTransformation();
		sphere(5);
		popMatrix();
		
		fill(255, 0, 0);
		pushMatrix();
		keyFrame[2].applyTransformation();
		sphere(5);
		popMatrix();
		
		fill(255, 255, 0);
		pushMatrix();
		keyFrame[3].applyTransformation();
		sphere(5);
		popMatrix();
		
		puntos.add(new PuntosCurva(keyFrame[0].position().x, keyFrame[0].position().y, keyFrame[0].position().z));
		puntos.add(new PuntosCurva(keyFrame[1].position().x, keyFrame[1].position().y, keyFrame[1].position().z));
		puntos.add(new PuntosCurva(keyFrame[2].position().x, keyFrame[2].position().y, keyFrame[2].position().z));
		puntos.add(new PuntosCurva(keyFrame[3].position().x, keyFrame[3].position().y, keyFrame[3].position().z));
		
		valorXTemp = puntos.get(0).x;
		valorYTemp = puntos.get(0).y;
		valorZTemp = puntos.get(0).z;
		
		vectorP[0][0] = puntos.get(0).x;
		vectorP[1][0] = puntos.get(1).x;
		vectorP[2][0] = puntos.get(2).x;
		vectorP[3][0] = puntos.get(3).x;
		coeficientesX = producto(matrizBezier, vectorP);
		
		vectorP[0][0] = puntos.get(0).y;
		vectorP[1][0] = puntos.get(1).y;
		vectorP[2][0] = puntos.get(2).y;
		vectorP[3][0] = puntos.get(3).y;
		coeficientesY = producto(matrizBezier, vectorP);
		
		vectorP[0][0] = puntos.get(0).z;
		vectorP[1][0] = puntos.get(1).z;
		vectorP[2][0] = puntos.get(2).z;
		vectorP[3][0] = puntos.get(3).z;
		coeficientesZ = producto(matrizBezier, vectorP);
		
		stroke(255);
		for(float u = 0; u <= 1; u += 0.001){
			valorX = coeficientesX[0][0]*(float)pow(u,3) + 
					coeficientesX[1][0]*(float)pow(u,2) + 
					coeficientesX[2][0]*u + 
					coeficientesX[3][0];
			
			valorY = coeficientesY[0][0]*(float)pow(u,3) + 
					coeficientesY[1][0]*(float)pow(u,2) + 
					coeficientesY[2][0]*u + 
					coeficientesY[3][0];
			valorZ = coeficientesZ[0][0]*(float)pow(u,3) + 
					coeficientesZ[1][0]*(float)pow(u,2) + 
					coeficientesZ[2][0]*u + 
					coeficientesZ[3][0];
			line(valorXTemp, valorYTemp, valorZTemp, valorX, valorY, valorZ);
			valorXTemp = valorX;
			valorYTemp = valorY;
			valorZTemp = valorZ;
		}
	}
	
	public float[][] producto(float A[][], float B[][]){       
        float suma = 0;
        float result[][] = new float[A.length][B[0].length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B[0].length; j++){
                suma = 0;
                for(int k = 0; k < B.length; k++){
                    suma += A[i][k] * B[k][j];
                }
                result[i][j] = suma;
            }
        }
        return result;
    }
	
}
