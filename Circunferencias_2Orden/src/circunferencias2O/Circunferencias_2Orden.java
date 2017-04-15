package circunferencias2O;

import processing.core.*;

public class Circunferencias_2Orden extends PApplet{
	
	private int contador = 0, h = 0, radio = 0, posX = 0, posY = 0, radio_sqrt2 = 0, de = 0, dse = 0;
	private int[] centro = new int[2], punto = new int[2];
	private boolean flagde = false, flagdse = false;
		
	public void setup(){
		size(400, 400);
	}
	
	public void draw(){
		background(255);
		//if(contador == 0) background(255);
		if(contador == 1){
			radio = (int) Math.sqrt((Math.pow((mouseX - centro[0]), 2) + Math.pow((mouseY - centro[1]), 2)));
			point(centro[0], centro[1] + radio);
			radio_sqrt2 = (int) (radio / Math.sqrt(2));
			
			h = 1 - radio;// h_inicio
			posY = radio;			
			
			/////////////////////// Para 1 /////////////////////////////
			flagde = false;
			flagdse = false;
			for(int i = 1; i <= radio_sqrt2; i++){
				if(h <= 0){
					if(!flagde){						
						flagde = true;
						de = 2 * i + 3;						
					}else{
						de += 2;
						dse += 2;
					}
					h = h + de;
				}else{
					posY--;
					if(!flagdse){
						flagdse = true;
						dse = 2 * i - 2 * posY + 5;
					}else{
						de += 2;
						dse += 4; 
					}
					
					h = h + dse;
				}
				point(centro[0]+i, centro[1]+posY);
			}
			
			
			/////////////////////// Para 2 /////////////////////////////
			posX = radio;
			h = 1 - radio;
			flagde = false;
			flagdse = false;
			for(int i = 1; i <= radio_sqrt2; i++){
				if(h <= 0){
					if(!flagde){						
						flagde = true;
						de = 2 * i + 3;						
					}else{
						de += 2;
						dse += 2;
					}
					h = h + de;
				}else{
					posX--;
					if(!flagdse){
						flagdse = true;
						dse = 2 * i - 2 * posX + 5;
					}else{
						de += 2;
						dse += 4; 
					}
					
					h = h + dse;
				}
				point(centro[0]+posX, centro[1]+i);
			}
					
			
			/////////////////////// Para 3 /////////////////////////////
			posX = radio;
			h = 1 - radio;
			flagde = false;
			flagdse = false;
			for(int i = 0; i >= -radio_sqrt2; i--){
				if(h <= 0){
					if(!flagde){						
						flagde = true;
						de = -2 * i + 3;						
					}else{
						de += 2;
						dse += 2;
					}
					h = h + de;
				}else{
					posX--;
					if(!flagdse){
						flagdse = true;
						dse = -2 * i - 2 * posX + 5;
					}else{
						de += 2;
						dse += 4; 
					}
					
					h = h + dse;
				}
				point(centro[0]+posX, centro[1]+i);
			}		
			
			/////////////////////// Para 4 /////////////////////////////
			posY = -radio;
			h = 1 - radio;
			flagde = false;
			flagdse = false;
			for(int i = 0; i <= radio_sqrt2; i++){
				if(h <= 0){
					if(!flagde){						
						flagde = true;
						de = 2 * i + 3;						
					}else{
						de += 2;
						dse += 2;
					}
					h = h + de;
				}else{
					posY++;
					if(!flagdse){
						flagdse = true;
						dse = 2 * i + 2 * posY + 5;
					}else{
						de += 2;
						dse += 4; 
					}
					
					h = h + dse;
				}
				point(centro[0]+i, centro[1]+posY);
			}

			
			/////////////////////// Para 5 /////////////////////////////
			posY = -radio;
			h = 1 - radio;
			flagde = false;
			flagdse = false;
			for(int i = -1; i >= -radio_sqrt2; i--){
				if(h <= 0){
					if(!flagde){						
						flagde = true;
						de = -2 * i + 3;						
					}else{
						de += 2;
						dse += 2;
					}
					h = h + de;
				}else{
					posY++;
					if(!flagdse){
						flagdse = true;
						dse = -2 * i + 2 * posY + 5;
					}else{
						de += 2;
						dse += 4; 
					}
					
					h = h + dse;
				}
				point(centro[0]+i, centro[1]+posY);
			}
			
			
			/////////////////////// Para 6 /////////////////////////////
			posX = -radio;
			h = 1 - radio;
			flagde = false;
			flagdse = false;
			for(int i = -1; i >= -radio_sqrt2; i--){
				if(h <= 0){
					if(!flagde){						
						flagde = true;
						de = -2 * i + 3;						
					}else{
						de += 2;
						dse += 2;
					}
					h = h + de;
				}else{
					posX++;
					if(!flagdse){
						flagdse = true;
						dse = -2 * i + 2 * posX + 5;
					}else{
						de += 2;
						dse += 4; 
					}
					
					h = h + dse;
				}
				point(centro[0]+posX, centro[1]+i);
			}
			
			
			/////////////////////// Para 7 /////////////////////////////
			posX = -radio;
			h = 1 - radio;
			flagde = false;
			flagdse = false;
			for(int i = 0; i <= radio_sqrt2; i++){
				if(h <= 0){
					if(!flagde){						
						flagde = true;
						de = 2 * i + 3;						
					}else{
						de += 2;
						dse += 2;
					}
					h = h + de;
				}else{
					posX++;
					if(!flagdse){
						flagdse = true;
						dse = 2 * i + 2 * posX + 5;
					}else{
						de += 2;
						dse += 4; 
					}
					
					h = h + dse;
				}
				point(centro[0]+posX, centro[1]+i);
			}
					
			
			/////////////////////// Para 8 /////////////////////////////
			posY = radio;
			h = 1 - radio;
			flagde = false;
			flagdse = false;
			for(int i = 1; i >= -radio_sqrt2; i--){
				if(h <= 0){
					if(!flagde){						
						flagde = true;
						de = -2 * i + 3;						
					}else{
						de += 2;
						dse += 2;
					}
					h = h + de;
				}else{
					posY--;
					if(!flagdse){
						flagdse = true;
						dse = -2 * i - 2 * posY + 5;
					}else{
						de += 2;
						dse += 4; 
					}
					
					h = h + dse;
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