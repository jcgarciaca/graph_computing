import processing.core.*;

public class AlgoritmoDDA extends PApplet{
	
	private int contador = 0;	
	private int[] pto1 = new int[2];
	private int[] pto2 = new int[2];
	private double pendiente = 0, yTemp = 0, pendienteAux = 0, xTemp = 0;
	
	public void setup(){
		size(400, 400);		
	}
	
	public void draw(){
		
		//background(255);
		
		if(contador == 0) background(255);		
		if(contador == 1){ // Cuando se ha seleccionado el primer punto
			stroke(0, 0, 255);
			if(pto1[0] == mouseX){ // Si la pendiente tiende a infinito
				if(pto1[1] <= mouseY){ // Si la línea es hacia abajo
					for(int i = pto1[1]; i <= mouseY; i++){
						point(pto1[0], i);
					}
				}else{// Si la línea es hacia arriba
					for(int i = pto1[1]; i >= mouseY; i--){
						point(pto1[0], i);
					}
				}
			}else if(pto1[1] == mouseY){ // Si la pendiente es cero
				if(pto1[0] <= mouseX){// Si la linea es hacia la derecha
					for(int i = pto1[0]; i <= mouseX; i++){
						point(i, pto1[1]);
					}
				}else{// Si la linea es hacia la izquierda
					for(int i = pto1[0]; i >= mouseX; i--){
						point(i, pto1[1]);
					}
				}				
			}else{// Si la pendiente es finita
				yTemp = pto1[1];
				pendiente = (double) (mouseY - pto1[1]) / (mouseX - pto1[0]);
				if(pendiente > 0){ // Pendiente positiva
					if(pendiente <= 1){
						if(pto1[0] <= mouseX){// La recta es hacia la derecha
							for(int i = pto1[0]; i <= mouseX; i++){
								point(i, Math.round(yTemp));
								yTemp += pendiente;
							}
						}else{// La recta es hacia la izquierda
							for(int i = pto1[0]; i >= mouseX; i--){
								point(i, Math.round(yTemp));
								yTemp -= pendiente;
							}
						}
					}else{ // Pendiente mayor a 1
						xTemp = pto1[0];
						pendienteAux = (double) (mouseX - pto1[0]) / (mouseY - pto1[1]);
						if(pto1[0] <= mouseX){// La recta es hacia la derecha
							for(int i = pto1[1]; i <= mouseY; i++){
								point(Math.round(xTemp), i);
								xTemp += pendienteAux;
							}
						}else{// La recta es hacia la izquierda
							for(int i = pto1[1]; i >= mouseY; i--){
								point(Math.round(xTemp), i);
								xTemp -= pendienteAux;
							}
						}
					}
					
				}else{// Pendiente negativa					
					if(pendiente >= -1){ // Pendiente entre cero y uno
						if(pto1[0] <= mouseX){// La recta es hacia la derecha
							for(int i = pto1[0]; i <= mouseX; i++){
								point(i, Math.round(yTemp));
								yTemp += pendiente;
							}
						}else{// La recta es hacia la izquierda
							for(int i = pto1[0]; i >= mouseX; i--){
								point(i, Math.round(yTemp));
								yTemp -= pendiente;
							}
						}
					}else{// Pendiente menor a -1
						xTemp = pto1[0];
						pendienteAux = (double) (mouseX - pto1[0]) / (mouseY - pto1[1]);
						if(pto1[0] <= mouseX){// La recta es hacia la derecha
							for(int i = pto1[1]; i >= mouseY; i--){
								point(Math.round(xTemp), i);
								xTemp -= pendienteAux;
							}
						}else{// La recta es hacia la izquierda
							for(int i = pto1[1]; i <= mouseY; i++){
								point(Math.round(xTemp), i);
								xTemp += pendienteAux;
							}
						}
					}
				}
			}
		}
		else if (contador == 2){ // Cuando se ha seleccionado el segundo punto
			point(pto2[0], pto2[1]);
			stroke(204, 102, 0);
			line(pto1[0], pto1[1], pto2[0], pto2[1]);			
		}
	}
	
	public void mousePressed(){
		contador++;
		if(contador == 1){
			pto1[0] = mouseX;
			pto1[1] = mouseY;
		}else if(contador == 2){
			pto2[0] = mouseX;
			pto2[1] = mouseY;
		}else contador = 0;
	}

}
