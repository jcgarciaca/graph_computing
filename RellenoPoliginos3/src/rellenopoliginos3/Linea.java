package rellenopoliginos3;

public class Linea {
	int y0, x0;
    int y1, x1;
    public Linea(int xt, int yt, int x, int y) {
      //Ordenamiento por Y de menor a mayor donde yx1 > yx0 en funcion de y
      if (yt<y){this.y1=y; this.y0=yt; this.x1=x; this.x0=xt;}
      else{this.y1=yt; this.y0=y; this.x1=xt; this.x0=x;}
    }
    public String toString(){return this.x0+","+this.y0+";"+this.x1+","+this.y1+"-";}
}


