package rellenopoliginos3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class Nodo implements Comparable{
	
	 int yMax=0;
	 int xMax=0;
	 int yMin=0;
	 float xMin=0;
	 float mInv=0;// 1/m 
	 
	 
	 //Estos son los constructores
	 public Nodo(){}
	 public Nodo (int ymax, int xmax, int ymin, float xmin, float minv){// xmin, ymax, min (1/m)
	    this.yMax=ymax; this.xMax=xmax; this.yMin=ymin; this.xMin=xmin; this.mInv=minv;   
	 }
	 public Nodo (Linea l){
	    float dy, dx, m;
	    dy=(l.y1-l.y0);
	    dx=(l.x1-l.x0);
	    m=dy/dx;	    
	 // esta es la segunda opcion para yMax y Xmin(corresponde al Ymin)
//	    if(l.y0>l.y1){this.yMax=l.y0; this.xMin=l.x1;}else{this.yMax=l.y1;this.xMin=l.x0;}
	    this.yMax=l.y1;
	    this.xMax=l.x1;
	    this.yMin=l.y0;
	    this.xMin=l.x0;
	    this.mInv=1/m;
	    
	 }
	 
	 public float getxMin(){
		 return xMin;
	 }
	// public String toString(){return this.yMax+";"+this.xMin+";"+this.mInv+"-";}
	 public String toString(){return ""+this.yMax;}

	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Nodo nodo = (Nodo)arg0;
        if(this.xMin < nodo.getxMin())
            return -1;
        else if(this.xMin == nodo.getxMin())
            return 0;
        else
            return 1;
	}


}

