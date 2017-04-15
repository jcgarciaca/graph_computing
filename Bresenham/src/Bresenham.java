import processing.core.*;

public class Bresenham extends PApplet{
	
	private int contador = 0, dy = 0, dx = 0, a = 0, b = 0, yTemp = 0, xTemp = 0, d;
	private int[] pto1 = new int[2];
	private int[] pto2 = new int[2];
	private double pendiente = 0;
		
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
				dy = mouseY - pto1[1];
				dx = mouseX - pto1[0];
				
				a = dy;
				b = -dx;
				
				pendiente = (double) dy / dx;
				point(pto1[0], pto1[1]);
				yTemp = pto1[1];
				xTemp = pto1[0];
				
				if(pendiente > 0){// Pendiente positiva
					if(pendiente <= 1){
						if(pto1[0] <= mouseX){// La recta es hacia la derecha
							d = 2 * dy - dx; // d_inicio
							for(int i = pto1[0]; i < mouseX; i++){
								if(d <= 0){// Elegimos E									
									d = d + a;
								}else{// Elegimos NE
									yTemp++;
									d = d + a + b;
								}
								point(i+1, yTemp);
							}
						}else{// La recta es hacia la izquierda
							d = dx - 2 * dy;
							for(int i = pto1[0]; i > mouseX; i--){
								if(d <= 0){// Elegimos E									
									d = d - a;
								}else{// Elegimos NE
									yTemp--;
									d = d - a - b;
								}
								point(i-1, yTemp);
							}
						}
					}else{// Pendiente mayor a 1
						if(pto1[0] <= mouseX){// La recta es hacia la derecha
							d = dy - 2 * dx; // d_inicio
							for(int i = pto1[1]; i < mouseY; i++){
								if(d >= 0){// Elegimos E									
									d = d + b;
								}else{// Elegimos NE
									xTemp++;
									d = d + a + b;
								}
								point(xTemp, i+1);
							}
						}else{// La recta es hacia la izquierda
							d = 2 * dx - dy;
							for(int i = pto1[1]; i > mouseY; i--){
								if(d >= 0){// Elegimos E									
									d = d - b;
								}else{// Elegimos NE
									xTemp--;
									d = d - a - b;
								}
								point(xTemp, i-1);
							}
						}
					}
				}else{// Pendiente negativa
					if(pendiente >= -1){
						if(pto1[0] <= mouseX){// La recta es hacia la derecha
							d = 2 * dy + dx; // d_inicio
							for(int i = pto1[0]; i < mouseX; i++){
								if(d >= 0){// Elegimos E									
									d = d + a;
								}else{// Elegimos NE
									yTemp--;
									d = d + a - b;
								}
								point(i+1, yTemp);
							}
						}else{// La recta es hacia la izquierda
							d = -2*dy - dx;
							for(int i = pto1[0]; i > mouseX; i--){
								if(d >= 0){// Elegimos E									
									d = d - a;
								}else{// Elegimos NE
									yTemp++;
									d = d - a + b;
								}
								point(i-1, yTemp);
							}
						}
					}else{// Pendiente menor a -1
						if(pto1[0] <= mouseX){// La recta es hacia la derecha
							d = dy + 2 * dx; // d_inicio
							for(int i = pto1[1]; i > mouseY; i--){
								if(d <= 0){// Elegimos E									
									d = d - b;
								}else{// Elegimos NE
									xTemp++;
									d = d + a - b;
								}
								point(xTemp, i-1);
							}
						}else{// La recta es hacia la izquierda
							d = -dy - 2 * dx;
							for(int i = pto1[1]; i < mouseY; i++){
								if(d <= 0){// Elegimos E									
									d = d + b;
								}else{// Elegimos NE
									xTemp--;
									d = d - a + b;
								}
								point(xTemp, i+1);
							}
						}
					}
				}				
			}
		}else if (contador == 2){ // Cuando se ha seleccionado el segundo punto
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
