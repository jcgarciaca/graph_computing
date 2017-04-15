package recortelineas;

import processing.core.*;

public class RecorteLineas extends PApplet{
	
	int xi = 0, yi = 0, xf = 0, yf = 0, xmin = 0, xmax = 0;//, tempX = 0, tempY = 0;
	int ymin = 0, ymax = 0, d = 0;
	
	
	public void setup(){		
		size(400, 400);
		
		// Puntos de la linea
		xi = 50;
		yi = 300;
		xf = 350;
		yf = 200;
		
//		if(xi>xf){
//			tempX = xf;
//			tempY = yf;
//			xf = xi;
//			yf = yi;
//			xi = tempX;
//			yi = tempY;
//		}
		
		// Vertices de la ventana
		xmin = 150;
		xmax = 300;
		ymin = 100;
		ymax = 250;
	}
	
	public void draw(){
		background(255);
		rect(xmin, ymin, 150, 150);
		line(xi, yi, xf, yf);
		
		d = estableceCaso(xi, yi);
		if(d == 1) recortaLinea1(xi, yi, xf, yf);
		else if(d == 2) recortaLinea2(xi, yi, xf, yf);
		else if(d == 3) recortaLinea3(xi, yi, xf, yf);
		else if(d == 4) recortaLinea4(xi, yi, xf, yf);
		else if(d == 5) recortaLinea5(xi, yi, xf, yf);
		else if(d == 6) recortaLinea6(xi, yi, xf, yf);
		else System.out.println("Error");
	}
	
	public int estableceCaso(int x, int y){
		if(x>=xmin && x<=xmax) {
			if(y>=ymin && y<=ymax) return 1;
			else if(y<ymin) return 4;
			else return 5;
		}
		else if(x<xmin && y>=ymin){
			if(y<=ymax) return 2;
			else return 6;
		}
		else if(x<=xmin && y<=ymin) return 3;
		else return 0;
	}
	
	public void recortaLinea1(int x1, int y1, int x2, int y2){// Punto 1 dentro de la ventana
		float m = 0, m1 = 0, m2 = 0, m3 = 0, m4 = 0;// pendientes a cada vertice de la ventana
		int nx1 = 0, ny1 = 0,nx2 = 0,ny2 = 0;// auxiliares
		m = ((float)(y2-y1))/(x2-x1);
	    m1 = ((float)(ymin-y1))/(xmin-x1);
	    m2 = ((float)(ymin-y1))/(xmax-x1);
	    m3 = ((float)(ymax-y1))/(xmax-x1);
	    m4 = ((float)(ymax-y1))/(xmin-x1);
	    
	    nx1 = x1;
	    ny1 = y1;
	    
	    if(((abs(m)>=m1 && x2<x1) || (abs(m)>abs(m2) && x2>x1)) && y1>y2){ // p2 arriba
	    	if(y2>ymin){// punto 2 adentro
	    		nx2=x2;
	            ny2=y2;
	    	}else{// punto 2 afuera
	    		ny2=ymin;
	            nx2= (int) (x1+(ymin-y1)/m);
	    	}
	    }else if(m>m2 && m<m3){// punto 2 al lado derecho
	    	if(x2<xmax){// punto 2 adentro
	    		nx2=x2;
	            ny2=y2;
	    	}else{// punto 2 afuera
	    		nx2=xmax;
	            ny2=(int)(y1+(xmax-x1)*m);
	    	}
	    }else if((abs(m)>=m3 && x2>x1) || (abs(m)>abs(m4) && x2<x1)){// punto 2 abajo
	    	if(y2<ymax){// punto 2 adentro
	    		nx2=x2;
	            ny2=y2;
	    	}else{// punto 2 afuera 
	    		ny2=ymax;
	            nx2=(int)(x1+(ymax-y1)/m);
	    	}
	    }else if(m>m4 && m<m1){// punto 2 a la izquierda
	    	if(x2>xmin){// punto 2 adentro
	    		nx2=x2;
	            ny2=y2; 
	    	}else{// punto 2 afuera
	    		nx2=xmin;
	            ny2=(int)(y1+(xmin-x1)*m);
	    	}
	    }
	    
	    System.out.println(nx1 + ", " + ny1);
	    System.out.println(nx2 + ", " + ny2);
	}
	
	public void recortaLinea2(int x1, int y1, int x2, int y2){// Punto 1 a la izquierda
		float m = 0, m1 = 0, m2 = 0, m3 = 0, m4 = 0;// pendientes a cada vertice de la ventana
		int nx1 = 0, ny1 = 0,nx2 = 0,ny2 = 0;// auxiliares
		m = ((float)(y2-y1))/(x2-x1);
	    m1 = ((float)(ymin-y1))/(xmin-x1);
	    m2 = ((float)(ymin-y1))/(xmax-x1);
	    m3 = ((float)(ymax-y1))/(xmax-x1);
	    m4 = ((float)(ymax-y1))/(xmin-x1);
	    
	    if(m>m1 && m<m2){// Region L - LT
	    	if(y2>ymin){// punto 2 dentro - L
	    		if(x2>xmin){
	    			nx1=xmin;
		            ny1=(int)(y1+m*(xmin-x1));
		            nx2=x2;
		            ny2=y2;
	    		}else System.out.println("No dibuja");
	    		
	    	}else{// punto 2 afuera - LT
	    		nx1=xmin;
	            ny1=(int)(y1+m*(xmin-x1));
	            ny2=ymin;
	            nx2=(int)(x1+(ymin-y1)/m);
	    	}
	    }else if(m>m2 && m<m3){// Region L - LR
	    	if(x2<xmax){// punto 2 adentro - L
	    		if(x2>xmin){
	    			nx1=xmin;
		            ny1=(int)(y1+m*(xmin-x1));
		            nx2=x2;
		            ny2=y2;
	    		}else System.out.println("No dibuja");
	    		
	    	}else{// punto 2 afuera - LR
	    		nx1=xmin;
	            ny1=(int)(y1+m*(xmin-x1));
	            nx2=xmax;
	            ny2=(int)(y1+(xmax-x1)*m);
	    	}
	    }else if(m>m3 && m<m4){// Region L - LB
	    	if(y2<ymax){// punto 2 adentro - L
	    		if(x2>xmin){
	    			nx1=xmin;
		            ny1=(int)(y1+m*(xmin-x1));
		            nx2=x2;
		            ny2=y2;
	    		}else System.out.println("No dibuja");
	    		
	    	}else{// punto 2 afuera - LB
	    		nx1=xmin;
	            ny1=(int)(y1+m*(xmin-x1));
	            ny2=ymax;
	            nx2=(int)(x1+(ymax-y1)/m);
	    	}
	    }
	    
	    System.out.println(nx1 + ", " + ny1);
	    System.out.println(nx2 + ", " + ny2);
	}
	
	public void recortaLinea3(int x1, int y1, int x2, int y2){// Punto 1 en la esquina
		float m = 0, m1 = 0, m2 = 0, m3 = 0, m4 = 0, tm1 = 0, tm2 = 0;// pendientes a cada vertice de la ventana
		int nx1 = 0, ny1 = 0,nx2 = 0,ny2 = 0;// auxiliares
		
		int flag,t;
	    tm1=((float)(ymin-y1))/(xmin-x1);
	    tm2=((float)(ymax-ymin))/(xmax-xmin); //diagonal ventana
		
		m = ((float)(y2-y1))/(x2-x1);
	    m1 = ((float)(ymin-y1))/(xmin-x1);
	    m2 = ((float)(ymin-y1))/(xmax-x1);
	    m3 = ((float)(ymax-y1))/(xmax-x1);
	    m4 = ((float)(ymax-y1))/(xmin-x1);
	    
	    if(tm1<tm2){
	    	flag=2;
	        t=(int)m2;
	        m2=m3;
	        m3=t;
	    }else flag = 1;
	    
	    if(m>m1 && m<m2){// Region T - TR
	    	if(x2>xmax && y2>ymin){// punto 2 afuera - TR
	    		ny1=ymin;
	            nx1=(int)(x1+(ymin-y1)/m);
	            nx2=xmax;
	            ny2=(int)(y1+m*(xmax-x1));
	    	}else if(y2>ymin && x2<xmax){// punto 2 adentro 
	    		ny1=ymin;
	            nx1=(int)(x1+(ymin-y1)/m);
	            ny2=y2;
	            nx2=x2;
	    	}
	    }else if(m>m2 && m<m3){// LR - TB
	    	if(flag==1){
	    		if(y2>=ymax){// punto 2 afuera - TB
	    			ny1=ymin;
	                nx1=(int)(x1+(ymin-y1)/m);
	                nx2=(int)(x1+(ymax-y1)/m);
	                ny2=ymax;
	    		}else if(y2>=ymin){// punto 2 adentro - T
	    			ny1=ymin;
	                nx1=(int)(x1+(ymin-y1)/m);
	                nx2=x2;
	                ny2=y2;
	    		}
	    	}else{
	    		if(x2>=xmax){// punto 2 afuera - TR LR
	    			nx1=xmin;
	                ny1=(int)(y1+m*(xmin-x1));
	                nx2=xmax;
	                ny2=(int)(y1+m*(xmax-x1));
	    		}else if(x2>=xmin){// punto 2 adentro
	    			nx1=xmin;
	                ny1=(int)(y1+m*(xmin-x1));
	                nx2=x2;
	                ny2=y2;
	    		}
	    	}
	    }else if(m>m3 && m<m4){// Region LB
	    	if(y2>=ymax){// punto 2 afuera - LB
	    		nx1=xmin;
	            ny1=(int)(y1+m*(xmin-x1));
	            nx2=(int)(x1+(ymax-y1)/m);
	            ny2=ymax;
	    	}else if(y2>=ymin){// punto 2 adentro
	    		nx1=xmin;
	            ny1=(int)(y1+m*(xmin-x1));
	            ny2=y2;
	            nx2=x2;
	    	}
	    }
	    
	    System.out.println(nx1 + ", " + ny1);
	    System.out.println(nx2 + ", " + ny2);
	}
	
	public void recortaLinea4(int x1, int y1, int x2, int y2){// Punto 1 en la parte superior
		//System.out.println("Caso 4");
		float m = 0, m1 = 0, m2 = 0, m3 = 0, m4 = 0;// pendientes a cada vertice de la ventana
		int nx1 = 0, ny1 = 0,nx2 = 0,ny2 = 0;// auxiliares
		m = ((float)(y2-y1))/(x2-x1);
	    m1 = ((float)(ymin-y1))/(xmin-x1);
	    m2 = ((float)(ymin-y1))/(xmax-x1);
	    m3 = ((float)(ymax-y1))/(xmax-x1);
	    m4 = ((float)(ymax-y1))/(xmin-x1);
	    
	    if(m>m2 && m<m3){
	    	if(x2<xmax){
	    		if(x2>ymin){
	    			ny1=ymin;
	                nx1=(int)(x1+(ymin-y1)/m);
	    			nx2=x2;
	    			ny2=y2;
	    		}else System.out.println("No hace nada");
	    	}else{
	    		ny1=ymin;
                nx1=(int)(x1+(ymin-y1)/m);
                nx2=xmax;
                ny2=(int)(y1+m*(xmax-x1));
	    	}
	    }else if(abs(m)>abs(m3) && abs(m)>abs(m4)){
	    	if(y2<ymax){
	    		if(y2>ymin){
	    			ny1=ymin;
	                nx1=(int)(x1+(ymin-y1)/m);
	    			nx2=x2;
	    			ny2=y2;
	    		}else System.out.println("No hace nada");
	    	}else{
	    		ny1=ymin;
                nx1=(int)(x1+(ymin-y1)/m);
                nx2=(int)(x1+(ymax-y1)/m);
	            ny2=ymax;
	    	}
	    }else if(m<m1 && m>m4){
	    	if(x2>xmin){
	    		if(y2>ymin){
	    			ny1=ymin;
	                nx1=(int)(x1+(ymin-y1)/m);
	    			nx2=x2;
	    			ny2=y2;
	    		}else System.out.println("No hace nada");
	    	}else{
	    		ny1=ymin;
                nx1=(int)(x1+(ymin-y1)/m);
                nx2=xmin;
	            ny2=(int)(y1+(xmin-x1)*m);
	    	}
	    }
	    System.out.println(nx1 + ", " + ny1);
	    System.out.println(nx2 + ", " + ny2);
	}
	
	public void recortaLinea5(int x1, int y1, int x2, int y2){// Punto 1 en la parte superior
		//System.out.println("Caso 5");
		float m = 0, m1 = 0, m2 = 0, m3 = 0, m4 = 0;// pendientes a cada vertice de la ventana
		int nx1 = 0, ny1 = 0,nx2 = 0,ny2 = 0;// auxiliares
		m = ((float)(y2-y1))/(x2-x1);
	    m1 = ((float)(ymin-y1))/(xmin-x1);
	    m2 = ((float)(ymin-y1))/(xmax-x1);
	    m3 = ((float)(ymax-y1))/(xmax-x1);
	    m4 = ((float)(ymax-y1))/(xmin-x1);
	    
//	    System.out.println("m: " + m);
//	    System.out.println("m2: " + m2);
//	    System.out.println("m3: " + m3);
	    
	    if(m<m1 && m>m4){
	    	if(x2>xmin){
	    		if(y2<ymax){
	    			ny1=ymax;
		            nx1=(int)(x1+(ymax-y1)/m);
	    			nx2=x2;
	    			ny2=y2;
	    		}else System.out.println("No hace nada");
	    	}else{
	    		ny1=ymax;
	            nx1=(int)(x1+(ymax-y1)/m);
	            nx2=xmin;
	            ny2=(int)(y1+m*(xmin-x1));
	    	}
	    }else if(abs(m)>abs(m1) && abs(m)<abs(m2)){
	    	if(y2>ymin){
	    		if(y2<ymax){
	    			ny1=ymax;
		            nx1=(int)(x1+(ymax-y1)/m);
	    			nx2=x2;
	    			ny2=y2;
	    		}else System.out.println("No hace nada");
	    	}else{
	    		ny1=ymax;
	            nx1=(int)(x1+(ymax-y1)/m);
	            nx2=xmin;
	            ny2=(int)(y1+m*(xmin-x1));
	    	}
	    }else if(m<m3 && m>m2){
	    	if(x2<xmax){
	    		if(y2<ymax){
	    			ny1=ymax;
		            nx1=(int)(x1+(ymax-y1)/m);
	    			nx2=x2;
	    			ny2=y2;
	    		}else System.out.println("No hace nada");
	    	}else{
	    		ny1=ymax;
	            nx1=(int)(x1+(ymax-y1)/m);
	            nx2=xmax;
                ny2=(int)(y1+m*(xmax-x1));
	    	}
	    }
	    System.out.println(nx1 + ", " + ny1);
	    System.out.println(nx2 + ", " + ny2);
	}

	public void recortaLinea6(int x1, int y1, int x2, int y2){// Punto 1 en la parte superior
//		System.out.println("Caso 6");
		float m = 0, m1 = 0, m2 = 0, m3 = 0, m4 = 0, tm1 = 0, tm2 = 0;// pendientes a cada vertice de la ventana
		int nx1 = 0, ny1 = 0,nx2 = 0,ny2 = 0;// auxiliares
		
		int flag = 0,t;
	    tm1=((float)(ymax-y1))/(xmin-x1);
	    tm2=((float)(ymin-ymax))/(xmax-xmin); //diagonal ventana
		
	    System.out.println(tm1 + " " + tm2);
	    
		m = ((float)(y2-y1))/(x2-x1);
	    m1 = ((float)(ymin-y1))/(xmin-x1);
		m2 = ((float)(ymin-y1))/(xmax-x1);
	    m3 = ((float)(ymax-y1))/(xmax-x1);
	    m4 = ((float)(ymax-y1))/(xmin-x1);
	    
	    if(tm1>tm2){
//	    	flag=2;
//	        t=(int)m3;
//	        m3=m2;
//	        m2=t;
        }else flag = 1;
	    
	    
	    if(flag==0){
	    	if(m>m1 && m<m2){
	    		if(y2<ymin){
	    			nx1=xmin;
	    			ny1=(int)(y1+m*(xmin-x1));
	    			ny2=ymin;
	    			nx2=(int)(x1+(ymin-y1)/m);
	    		}else if(x2>xmin){
	    			nx1=xmin;
	    			ny1=(int)(y1+m*(xmin-x1));
	    			ny2=y2;
	    			nx2=x2;
	    		}
	    	}else if(m>m2 && m<m4){
	    		if(x2>xmax){
	    			nx1=xmin;
	    			ny1=(int)(y1+m*(xmin-x1));
	    			ny2=(int)(y1+m*(xmax-x1));
	    			nx2=xmax;
	    		}else if(x2>xmin){
	    			nx1=xmin;
	    			ny1=(int)(y1+m*(xmin-x1));
	    			ny2=y2;
	    			nx2=x2;
	    		}
	    	}else if(m>m4 && m<m3){
	    		if(x2>xmax){
	    			nx1=(int)(x1+(ymax-y1)/m);
	    			ny1=ymax;
	    			ny2=(int)(y1+m*(xmax-x1));
	    			nx2=xmax;
	    		}else if(y2<ymax){
	    			nx1=(int)(x1+(ymax-y1)/m);
	    			ny1=ymax;
	    			ny2=y2;
	    			nx2=x2;
	    		}
	    	}
	    }else{// flag = 1
	    	if(m>m1 && m<m4){
	    		if(y2<ymin){
	    			nx1=xmin;
	    			ny1=(int)(y1+m*(xmin-x1));
	    			ny2=ymin;
	    			nx2=(int)(x1+(ymin-y1)/m);
	    		}else if(x2>xmin){
	    			nx1=xmin;
	    			ny1=(int)(y1+m*(xmin-x1));
	    			ny2=y2;
	    			nx2=x2;
	    		}
	    	}else if(m>m4 && m<m2){
	    		if(y2<ymin){
	    			nx1=(int)(x1+(ymax-y1)/m);
	    			ny1=ymax;
	    			ny2=ymin;
	    			nx2=(int)(x1+(ymin-y1)/m);
	    		}else if(y2<ymax){
	    			nx1=(int)(x1+(ymax-y1)/m);
	    			ny1=ymax;
	    			ny2=y2;
	    			nx2=x2;
	    		}
	    	}else if(m>m2 && m<m3){
	    		if(x2>xmax){
	    			nx1=(int)(x1+(ymax-y1)/m);
	    			ny1=ymax;
	    			ny2=(int)(y1+m*(xmax-x1));
	    			nx2=xmax;
	    		}else if(y2<ymax){
	    			nx1=(int)(x1+(ymax-y1)/m);
	    			ny1=ymax;
	    			ny2=y2;
	    			nx2=x2;
	    		}
	    	}
	    }
	    
		System.out.println(nx1 + ", " + ny1);
		System.out.println(nx2 + ", " + ny2);
	}
}
