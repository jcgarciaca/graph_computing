package relleno;

import processing.core.PApplet;

import relleno.Linea;
import relleno.Nodo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class RellenoPoligonos extends PApplet{
	
	int Beginx=0, Beginy=0;
	int npoints =0;
	int tmpx=0, tmpy=0;
	int cont=0;
	int paridad=1;
	
	int auxYmin=0;
    int auxYmax=0;
    int auxXmin1=0, auxXmin2=0, auxXmin=0;
    int auxXmax1=0, auxXmax2=0, auxXmax=0;
	
	ArrayList<Linea> aristas = new ArrayList<Linea>();
	ArrayList<Nodo> ET = new ArrayList<Nodo>();
	ArrayList<Nodo> AET = new ArrayList<Nodo>();
	
	Nodo q=new Nodo();
		
    public void setup() {
		background(255, 255, 255);
		size(480, 520);		
	}

	public void draw() {
		if(cont==1){
			for(int j=auxYmin;j<=auxYmax;j++){
				
				for(int index=0;index<ET.size();index++){
					if(ET.get(index).yMin==j){// Agrega nodo AET						
						AET.add(ET.get(index));						
					}
				}
				
				for(int index=0;index<AET.size();index++){
					if(AET.get(index).yMax==j){// Quita nodo AET
						AET.remove(index);
						index--;
					}
				}
				
				Collections.sort(AET);// Ordena AET

				// Relleno
				int pxinicial=0,pxfinal=0;
				for(int i=0;i<(AET.size()/2);i++){
					pxinicial=(int)Math.floor(AET.get(2*i).xMin);
					pxfinal=(int)Math.floor(AET.get(2*i+1).xMin);
					for(int k=pxinicial; k<=pxfinal;k++){
						point(k,j);
					}
				}
								
				// Actualiza nodos para la siguiente iteración
				
				for(int i=0;i<AET.size();i++){
					int auxyMax=0;
					int auxxMax=0;
					int auxyMin=0;
					float auxxMin=0;
					float auxmInv=0;// 1/m 
					auxyMax=AET.get(i).yMax;
					auxxMax=AET.get(i).xMax;
					auxyMin=AET.get(i).yMin;
					auxxMin=AET.get(i).xMin+AET.get(i).mInv;
					auxmInv=AET.get(i).mInv;
					Nodo auxnodo=new Nodo(auxyMax, auxxMax, auxyMin, auxxMin, auxmInv);	
					AET.set(i, auxnodo);
			}
			}
			cont=0;
		}		
		
	}
	
	public void mousePressed(){
		stroke(0,0,0);// color
		if(mouseButton == LEFT){
			println(mouseX+","+mouseY);
			point(mouseX, mouseY);
		}
	    if(mouseButton == RIGHT && npoints>2){
	    	println(Beginx+","+Beginy);
	    	point(Beginx, Beginy);	    	
	    }
	     if(mouseButton == LEFT && npoints==0){
	    	 background(255, 255, 255);
	         point(mouseX,mouseY);
	         point(Beginx, Beginy);
	         Beginx=mouseX; Beginy=mouseY; tmpx= mouseX; tmpy=mouseY; npoints++;
	         // tmpx,y es el punto anterior que genera la arista
	     }else{
	         if(mouseButton == LEFT && npoints>0){
	           Linea tmpL= new Linea(tmpx, tmpy, mouseX, mouseY);//Ordenamiento por Y de menor a mayor donde yx1 > yx0 en funcion de y
	           Nodo tmpN = new Nodo(tmpL);//Nuevo Nodo
//	           line(tmpx,tmpy,mouseX,mouseY);//Dibujar la Linea
	           ET.add(tmpN);//guarda el nodo en ET
//	           println(ET.get(ET.size()-1));//Muestra el ultimo nodo insertado
	           tmpx= mouseX; tmpy=mouseY; npoints++;
	         }      
	  
	         if (mouseButton == RIGHT && npoints>2){//se cierra el poligono (primer y ultimo punto agregados)
	           Linea tmpL= new Linea(Beginx, Beginy, tmpx, tmpy);//Arista que cierra el poligono
	           													 //Ordenamiento por Y de menor a mayor donde yx1 > yx0 en funcion de y
	           Nodo tmpN = new Nodo(tmpL);//Nuevo Nodo
//	           line(Beginx,Beginy,tmpx,tmpy);//Dibujar la Linea
	           ET.add(tmpN);//guarda el nodo en ET
	           npoints=0;//Limpiar Lineas y Limpiar arreglo
// hasta este punto se tiene ET. Cada nodo esta ubicado en funcion del orden en que se agregan a la tabla
	           for(int i=0; i<ET.size();i++){
	        	   System.out.println(ET.get(i).xMax+", "+ET.get(i).yMax+", "+ET.get(i).xMin+", "+ET.get(i).yMin+", ");
	           }
	           
	          auxYmin=getYMin(ET);
//	          System.out.println(auxYmin);
	          auxYmax=getYMax(ET);
//	          System.out.println(auxYmax); 
	          auxXmin1=getXMin1(ET);
	          auxXmin2=getXMin2(ET);
	          auxXmax1=getXMax1(ET);
	          auxXmax2=getXMax2(ET);
	          
	          if(auxXmin1>auxXmax1){auxXmin=auxXmax1;} else {auxXmin=auxXmin1;}
	          if(auxXmin2>auxXmax2){auxXmax=auxXmin2;} else {auxXmax=auxXmax2;}
	          cont=1;
	         } 
	     }    
	}// end mousePressed
	
	
	public int getYMin(ArrayList<Nodo> tmpListET){
		int aux=0, yMin = ET.get(0).yMin;
		for(int index=1; index < ET.size(); index++){
	   	      aux=ET.get(index).yMin;
	   	      if(yMin>aux)yMin=aux;
	    } 
		return yMin;
	}
	
	
	public int getYMax(ArrayList<Nodo> tmpListET){
		int aux=0, yMax = ET.get(0).yMax;
		for(int index=1; index < tmpListET.size(); index++){
	   	      aux=ET.get(index).yMax;
	   	      if(yMax<aux)yMax=aux;
	    } 
		return yMax;
	}
	public int getXMin1(ArrayList<Nodo> tmpListET){
		int aux=0, xMin = (int)ET.get(0).xMin;
		for(int index=1; index < ET.size(); index++){
	   	      aux=(int)ET.get(index).xMin;
	   	      if(xMin>aux)xMin=aux;
	    } 
		return xMin;
	}
	public int getXMin2(ArrayList<Nodo> tmpListET){
		int aux=0, xMin = (int)ET.get(0).xMin;
		for(int index=1; index < ET.size(); index++){
	   	      aux=(int)ET.get(index).xMin;
	   	      if(xMin<aux)xMin=aux;
	    } 
		return xMin;
	}
	
	public int getXMax1(ArrayList<Nodo> tmpListET){
		int aux=0, xMax = ET.get(0).xMax;
		for(int index=1; index < ET.size(); index++){
	   	      aux=ET.get(index).xMax;
	   	      if(xMax>aux)xMax=aux;
	    } 
		return xMax;
	}
	
	public int getXMax2(ArrayList<Nodo> tmpListET){
		int aux=0, xMax = ET.get(0).xMax;
		for(int index=1; index < ET.size(); index++){
	   	      aux=ET.get(index).xMax;
	   	      if(xMax<aux)xMax=aux;
	    } 
		return xMax;
	}
	
}
