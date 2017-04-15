package vista2d;

import processing.core.*;
import remixlab.proscene.*;

public class Vista2D_V4 extends PApplet{
	
	Scene scene, auxScene;
	PGraphics canvas, auxCanvas;
				
	int posX = 150, posY = 100, ancho = 100, alto = 100;
	double angulo = 0;
	
	Matriz traslacion = new Matriz(-posX, -posY, 0);
	Matriz escalacion = new Matriz(1, 1, 1);
	Matriz rotacion = new Matriz(0);
	
	double[][] transformacion, P = new double[3][1];
	
//	double[][] mat1 = {{1, 2, 3}, {4, 5, 6}};
//	double[][] mat2 = {{1}, {3}, {5}};
	
//	double[][] res = new double[mat1.length][mat2[0].length];// producto(mat1, mat2);
	
	public void setup(){
		size(400, 600);
		noStroke();
		canvas = createGraphics(400, 300, P3D);
		auxCanvas = createGraphics(400, 300, P3D);
		
		P[0][0] = 350;
		P[1][0] = 250;
		P[2][0] = 1;
		
//		System.out.println(mat1.length);
//		System.out.println(mat2.length);
//		
//		res = producto(mat1, mat2);
//		
//		imprimirMatriz(mat1,0,0);
//	    System.out.println ("*");
//	    imprimirMatriz(mat2,0,0);
//	    System.out.println ("=");
//	    imprimirMatriz(res,0,0);
	}
	
	public void draw(){
		
		dibujaEscena();
		fill(0, 255, 0, 100);
		rect(posX, posY, ancho, alto);
		
		// Se realiza la transformacion
		
		traslacion.changeMat(-posX, -posY, 0);// Se lleva al origen
		rotacion.changeMat((float)angulo);
		//escalacion.changeMat(valorX, valorY, 1);// Se hace la escalación
		realizaTransformacion();
	}
	
	public void dibujaEscena(){
		canvas.beginDraw();
		canvas.background(180);
		canvas.line(50, 50, 350, 250);
		canvas.endDraw();
		image(canvas, 0, 0);
	}
	
	public void realizaTransformacion(){
		//transformacion = producto(producto(producto(escalacion.getMatriz(), rotacion.getMatriz()), traslacion.getMatriz()), P);
	}
	
	public void keyPressed(){
		if(keyCode == UP) posY -= 5;
		if(keyCode == DOWN) posY += 5;
		if(keyCode == LEFT) posX -= 5;
		if(keyCode == RIGHT) posX += 5;
		verificarVentana();		
	}
	
	public void verificarVentana(){
		if(posX > 300) posX = 300;
		if(posX < 0) posX = 0;
		if(posY > 200) posY = 200;
		if(posY < 0) posY = 0;	
	}
	
	public double[][] producto(double A[][], double B[][]){		
		double suma = 0;
		double result[][] = new double[A.length][B[0].length];  
        for(int i = 0; i < A.length; i++){  
            for(int j = 0; j < B[0].length; j++){  
                suma = 0;
                for(int k = 0; k < B.length; k++){  
                    suma += A[i][k] * B[k][j];  
                }
                result[i][j] = suma;
                //System.out.println(suma);
            }  
        }  
        return result;
    }
	
	public static void imprimirMatriz(double matriz[][], int i,int j){
		if(i<matriz.length){
			System.out.print("| "+matriz[i][j]+" ");
			j++;
			if(j>=matriz[0].length){
				System.out.println("|");
				i=i+1;
				j=0;
			}
	    imprimirMatriz(matriz, i,j);
	    }
	}
	
}
