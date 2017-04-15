package elipse;

import processing.core.*;

public class ElipsesV2 extends PApplet{
	
	private int contador = 0, a = 0, b = 0, d = 0, h = 0, posY = 0, posX = 0;
	private int[] centro = new int[2], pto1 = new int[2];
	
	public void setup(){
		size(600, 600);		
	}
	
	public void draw(){
		if(contador<3) background(255);
		//if(contador == 0) background(255);
		if(contador == 2){
			b = Math.abs(pto1[1] - centro[1]);
		
			a = Math.abs(mouseX - centro[0]);
						
						
				///////////////////////// Region 1 - 1 Cuadrante /////////////////////
				posY = b;
				d = (int) (Math.pow(b, 2) + Math.pow(a, 2) * (-b + 1/4));// d_inicio
				for(int i = 0; Math.pow(b, 2)*(i + 1) < Math.pow(a, 2)*(posY - 1 / 2); i++){ // Region 1
					if(d <= 0){// Se escoge E
						d = d + (int) Math.pow(b, 2) * (2 * i + 3);						
					}else{// Se escoge SE
						posY--;
						d = d + (int) Math.pow(b, 2) * (2 * i + 3) + (int) Math.pow(a, 2) * (- 2 * posY + 2);
					}
					point(centro[0] + i, centro[1] + posY);
					point(centro[0] - i, centro[1] + posY);
					point(centro[0] + i, centro[1] - posY);
					point(centro[0] - i, centro[1] - posY);
					posX = i;
				}
			
				///////////////////////// Region 2 - 1 Cuadrante /////////////////////
				h = (int)(Math.pow(b, 2)*Math.pow(posX + 1/2, 2) + Math.pow(a, 2)*(Math.pow(posY - 1, 2))-Math.pow(a, 2)*Math.pow(b, 2));
				for(int i = posY; i >= 0; i--){// Region 2
					if(h <= 0){
						posX++;
						h = (int) (h + Math.pow(b, 2) * (2 * posX + 2) + Math.pow(a, 2) * (-2 * i + 3));						
					}else{
						h = (int)(h + Math.pow(a, 2) * (-2 * i + 3)); 
					}
					point(centro[0] + posX, centro[1] + i);
					point(centro[0] - posX, centro[1] + i);
					point(centro[0] + posX, centro[1] - i);
					point(centro[0] - posX, centro[1] - i);				
				}
			
		}
	}
	
	public void mousePressed(){
		contador++;
		if(contador == 1){
			centro[0] = mouseX;
			centro[1] = mouseY;
		}else if(contador == 2){
			pto1[0] = mouseX;
			pto1[1] = mouseY;
		}else if(contador == 4) contador = 0;
	}
}
