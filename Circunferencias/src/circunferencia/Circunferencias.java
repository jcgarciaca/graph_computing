package circunferencia;

import processing.core.*;

public class Circunferencias extends PApplet{ // Algoritmo de Bress
	
	private int contador = 0, h = 0, radio = 0, posX = 0, posY = 0, cuadrante = 0, radio_sqrt2 = 0;
	private int[] centro = new int[2], punto = new int[2];
		
	public void setup(){
		size(400, 400);
	}
	
	public void draw(){
		//background(255);
		if(contador == 0) background(255);
		if(contador == 1){
			radio = (int) Math.sqrt((Math.pow((mouseX - centro[0]), 2) + Math.pow((mouseY - centro[1]), 2)));
			point(centro[0], centro[1] + radio);
			radio_sqrt2 = (int) (radio / Math.sqrt(2));
			
			h = 1 - radio;// h_inicio
			posY = radio;			
			
			/////////////////////// Para 1 /////////////////////////////
			for(int i = 1; i <= radio_sqrt2; i++){
				if(h <= 0){
					h = h + 2 * i + 3;
				}else{
					posY--;
					h = h + 2 * i - 2 * posY + 5;					
				}
				point(centro[0]+i, centro[1]+posY);
			}
			
			
			/////////////////////// Para 2 /////////////////////////////
//			posX = radio_sqrt2;
//			h = 1 - radio_sqrt2;
//			for(int i = radio_sqrt2; i >= 0; i--){
//				if(h >= 0){
//					h = h - 2 * i + 3;					
//				}else{
//					h = h - 2 * i + 2 * radio_sqrt2 + 5;
//					posX++;
//				}
//				point(centro[0]+posX, centro[1]+i);
//			}
			
			posX = radio;
			h = 1 - radio;
			for(int i = 1; i <= radio_sqrt2; i++){
				if(h <= 0){
					h = h + 2 * i + 3;					
				}else{
					posX--;
					h = h + 2 * i - 2 * posX + 5;					
				}
				point(centro[0]+posX, centro[1]+i);
			}
			
			
			
			/////////////////////// Para 3 /////////////////////////////
			posX = radio;
			h = 1 - radio;
			for(int i = 0; i >= -radio_sqrt2; i--){
				if(h >= 0){
					posX--;
					h = h - 2 * i + 5 - 2 * posX;					
				}else{
					h = h - 2 * i;					
				}
				point(centro[0]+posX, centro[1]+i);
			}
			
			
			/////////////////////// Para 4 /////////////////////////////
			posY = -radio;
			h = 1 - radio;
			for(int i = 0; i <= radio_sqrt2; i++){
				if(h <= 0){
					h = h + 2 * i + 3;
				}else{
					posY++;
					h = h + 2 * i + 2 * posY + 5;					
				}
				point(centro[0]+i, centro[1]+posY);
			}
			
			
			/////////////////////// Para 5 /////////////////////////////
			posY = -radio;
			h = 1 - radio;
			for(int i = -1; i >= -radio_sqrt2; i--){
				if(h <= 0){
					h = h - 2 * i + 3;
				}else{
					posY++;
					h = h - 2 * i + 2 * posY + 5;					
				}
				point(centro[0]+i, centro[1]+posY);
			}
			
			
			/////////////////////// Para 6 /////////////////////////////
			posX = -radio;
			h = 1 - radio;
			for(int i = -1; i >= -radio_sqrt2; i--){
				if(h >= 0){
					posX++;
					h = h - 2 * i + 5 + 2 * posX;					
				}else{
					h = h - 2 * i + 3;					
				}
				point(centro[0]+posX, centro[1]+i);
			}
			
			
			/////////////////////// Para 7 /////////////////////////////
			posX = -radio;
			h = 1 - radio;
			for(int i = 0; i <= radio_sqrt2; i++){
				if(h >= 0){
					posX++;
					h = h + 2 * i + 5 + 2 * posX;					
				}else{
					h = h + 2 * i + 3;					
				}
				point(centro[0]+posX, centro[1]+i);
			}
					
			
			/////////////////////// Para 8 /////////////////////////////
			posY = radio;
			h = 1 - radio;
			for(int i = 1; i >= -radio_sqrt2; i--){
				if(h <= 0){
					h = h - 2 * i + 3;
				}else{
					posY--;
					h = h - 2 * i - 2 * posY + 5;					
				}
				point(centro[0]+i, centro[1]+posY);
			}	
			
			
		}else if(contador == 2){}
	}
	
	
	public void mousePressed(){
		contador++;
		if(contador == 1){
			centro[0] = mouseX;
			centro[1] = mouseY;
		}else if(contador == 2){
			punto[0] = mouseX;
			punto[1] = mouseY;
		}else contador = 0;
	}

}
