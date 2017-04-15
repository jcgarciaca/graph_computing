package hermite;

import java.util.ArrayList;

import processing.core.*;
import remixlab.proscene.*;

public class Hermite extends PApplet{
	
	Scene scene;
	InteractiveFrame keyFrame[];
	int nbKeyFrames;
	
	// Variables del algoritmo
	ArrayList<PuntosCurva> puntos = new ArrayList<PuntosCurva>();
	float[][] coeficientesX;
	float[][] coeficientesY;
	float[][] coeficientesZ;
	float[][] matrizHermite = {{2, -2, 1, 1}, {-3, 3, -2, -1}, {0, 0, 1, 0}, {1, 0, 0, 0}};
	float[][] vectorGH = new float[4][1];
	
	float valorX = 0, valorY = 0, valorZ = 0, valorXTemp = 0, valorYTemp = 0, valorZTemp = 0;
	float dx = 0, dy = 0, dz = 0;
	//////////////////////////
	
	public void setup(){
		size(640, 360, P3D);
		nbKeyFrames = 8;
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
		keyFrame[2].applyTransformation();
		sphere(5);
		popMatrix();
		
		fill(255, 0, 0);
		pushMatrix();
		keyFrame[4].applyTransformation();
		sphere(5);
		popMatrix();
		
		fill(255, 255, 0);
		pushMatrix();
		keyFrame[6].applyTransformation();
		sphere(5);
		popMatrix();
		
		stroke(0, 255, 0);
				
		dx = keyFrame[1].position().x - keyFrame[0].position().x;
		dy = keyFrame[1].position().y - keyFrame[0].position().y;
		dz = keyFrame[1].position().z - keyFrame[0].position().z;		
		puntos.add(new PuntosCurva(keyFrame[0].position().x, keyFrame[0].position().y, keyFrame[0].position().z, dx, dy, dz));
		line(keyFrame[0].position().x, keyFrame[0].position().y, keyFrame[0].position().z, keyFrame[0].position().x + dx, keyFrame[0].position().y + dy, keyFrame[0].position().z + dz);
		
		dx = keyFrame[3].position().x - keyFrame[2].position().x;
		dy = keyFrame[3].position().y - keyFrame[2].position().y;
		dz = keyFrame[3].position().z - keyFrame[2].position().z;
		puntos.add(new PuntosCurva(keyFrame[2].position().x, keyFrame[2].position().y, keyFrame[2].position().z, dx, dy, dz));
		line(keyFrame[2].position().x, keyFrame[2].position().y, keyFrame[2].position().z, keyFrame[2].position().x + dx, keyFrame[2].position().y + dy, keyFrame[2].position().z + dz);
		
		dx = keyFrame[5].position().x - keyFrame[4].position().x;
		dy = keyFrame[5].position().y - keyFrame[4].position().y;
		dz = keyFrame[5].position().z - keyFrame[4].position().z;
		puntos.add(new PuntosCurva(keyFrame[4].position().x, keyFrame[4].position().y, keyFrame[4].position().z, dx, dy, dz));
		line(keyFrame[4].position().x, keyFrame[4].position().y, keyFrame[4].position().z, keyFrame[4].position().x + dx, keyFrame[4].position().y + dy, keyFrame[4].position().z + dz);
		
		dx = keyFrame[7].position().x - keyFrame[6].position().x;
		dy = keyFrame[7].position().y - keyFrame[6].position().y;
		dz = keyFrame[7].position().z - keyFrame[6].position().z;
		puntos.add(new PuntosCurva(keyFrame[6].position().x, keyFrame[6].position().y, keyFrame[6].position().z, dx, dy, dz));
		line(keyFrame[6].position().x, keyFrame[6].position().y, keyFrame[6].position().z, keyFrame[6].position().x + dx, keyFrame[6].position().y + dy, keyFrame[6].position().z + dz);
		
		for(int i = 0; i < puntos.size() - 1; i++){
			valorXTemp = puntos.get(i).x;
			valorYTemp = puntos.get(i).y;
			valorZTemp = puntos.get(i).z;
			
			vectorGH[0][0] = puntos.get(i).x;
			vectorGH[1][0] = puntos.get(i+1).x;
			vectorGH[2][0] = puntos.get(i).dx;
			vectorGH[3][0] = puntos.get(i+1).dx;
			coeficientesX = producto(matrizHermite, vectorGH);
			
			vectorGH[0][0] = puntos.get(i).y;
			vectorGH[1][0] = puntos.get(i+1).y;
			vectorGH[2][0] = puntos.get(i).dy;
			vectorGH[3][0] = puntos.get(i+1).dy;			
			coeficientesY = producto(matrizHermite, vectorGH);
			
			vectorGH[0][0] = puntos.get(i).z;
			vectorGH[1][0] = puntos.get(i+1).z;
			vectorGH[2][0] = puntos.get(i).dz;
			vectorGH[3][0] = puntos.get(i+1).dz;			
			coeficientesZ = producto(matrizHermite, vectorGH);
							
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
				stroke(255);
				line(valorXTemp, valorYTemp, valorZTemp, valorX, valorY, valorZ);
				valorXTemp = valorX;
				valorYTemp = valorY; 
				valorZTemp = valorZ;
			}
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
	
	
	
	
	
	
	
//	ArrayList<PuntosCurva> puntos = new ArrayList<PuntosCurva>();
//	float[][] coeficientesX;
//	float[][] coeficientesY;
//	float[][] matrizHermite = {{2, -2, 1, 1}, {-3, 3, -2, -1}, {0, 0, 1, 0}, {1, 0, 0, 0}};
//	float[][] vectorGH = new float[4][1];
//	
//	float valorX = 0, valorY = 0, valorXTemp = 0, valorYTemp = 0;
//	
//	public void setup(){
//		size(400, 400);
//						
//		puntos.add(new PuntosCurva(50, 125, -180));
//		puntos.add(new PuntosCurva(100, 75, 0));
//		puntos.add(new PuntosCurva(200, 200, 200));
//		puntos.add(new PuntosCurva(300, 300, 0));
//		
//		stroke(0, 0, 255);
//	}
//	
//	public void draw(){
//		background(255);		
//		
//		for(int i = 0; i < puntos.size() - 1; i++){
//			valorXTemp = puntos.get(i).x;
//			valorYTemp = puntos.get(i).y;
//			vectorGH[0][0] = puntos.get(i).x;
//			vectorGH[1][0] = puntos.get(i+1).x;
//			vectorGH[2][0] = 25;
//			vectorGH[3][0] = 25;
//			coeficientesX = producto(matrizHermite, vectorGH);
//			
//			vectorGH[0][0] = puntos.get(i).y;
//			vectorGH[1][0] = puntos.get(i+1).y;
//			vectorGH[2][0] = puntos.get(i).d;
//			vectorGH[3][0] = puntos.get(i+1).d;			
//			coeficientesY = producto(matrizHermite, vectorGH);
//							
//			for(float u = 0; u <= 1; u += 0.01){
//				valorX = coeficientesX[0][0]*(float)pow(u,3) + 
//						coeficientesX[1][0]*(float)pow(u,2) + 
//						coeficientesX[2][0]*u + 
//						coeficientesX[3][0];
//				
//				valorY = coeficientesY[0][0]*(float)pow(u,3) + 
//						coeficientesY[1][0]*(float)pow(u,2) + 
//						coeficientesY[2][0]*u + 
//						coeficientesY[3][0];	
//				line(valorXTemp, valorYTemp, valorX, valorY);
//				valorXTemp = valorX;
//				valorYTemp = valorY; 
//			}
//		}
//	}
//	
//	public float[][] producto(float A[][], float B[][]){       
//        float suma = 0;
//        float result[][] = new float[A.length][B[0].length];
//        for(int i = 0; i < A.length; i++){
//            for(int j = 0; j < B[0].length; j++){
//                suma = 0;
//                for(int k = 0; k < B.length; k++){
//                    suma += A[i][k] * B[k][j];
//                }
//                result[i][j] = suma;
//            }
//        }
//        return result;
//    }
	
	
	
	
//	Scene scene;
//	InteractiveFrame keyFrame[];
//	KeyFrameInterpolator kfi;
//	int nbKeyFrames;
//	
//	public void setup(){
//		size(640, 360, P2D);
//		nbKeyFrames = 4;
//		scene = new Scene(this);  
//		scene.setAxisIsDrawn(false);
//		scene.setGridIsDrawn(false);
//		scene.setRadius(70);
//		scene.showAll();
//		scene.setFrameSelectionHintIsDrawn(true);
//		
//		keyFrame = new InteractiveFrame[nbKeyFrames];
//		for (int i=0; i<nbKeyFrames; i++) {			
//		    keyFrame[i] = new InteractiveFrame(scene);
//		    keyFrame[i].setPosition(-100 + 200*i/(nbKeyFrames-1), 0, 0);
//		}
//		
//		
//	}
//	
//	public void draw(){
//		
//		background(0);
//		pushMatrix();
//		scene.drawAxis(30);
//		popMatrix();
//		
//	}

}
