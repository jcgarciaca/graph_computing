package pintor;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Superficie {
    
    public ArrayList<Puntos> puntos = new ArrayList<Puntos>();
    public float R,G,B,zmax,zmin,xmax,xmin,ymax,ymin;
    public float a,b,c,d;
    
    public Superficie(Puntos puntos1,Puntos puntos2,Puntos puntos3) {
        puntos.add(puntos1);
        puntos.add(puntos2);
        puntos.add(puntos3);
        calcularNormalyD();
    }

     public Superficie(Puntos puntos1,Puntos puntos2,Puntos puntos3,int r, int g, int b) {
        puntos.add(puntos1);
        puntos.add(puntos2);
        puntos.add(puntos3);
        R=r;
        G=g;
        B=b;
        calcularNormalyD();
    }

    public Superficie(ArrayList<Puntos> puntos) {
        for (int i = 0; i < puntos.size(); i++) {
            this.puntos.add(puntos.get(i));
        }
    }
   
    public ArrayList<Puntos> getPuntos() {
        return puntos;
    }

    public void setPuntos(ArrayList<Puntos> puntos) {
        this.puntos = puntos;
    }
    
    public void calcularNormalyD(){
    
            //Se construye el primer vector AB del plano
            Puntos vectorAB = new Puntos(
                    puntos.get(1).getX()-puntos.get(0).getX(),
                    puntos.get(1).getY()-puntos.get(0).getY(),
                    puntos.get(1).getZ()-puntos.get(0).getZ()
                    );
            //Se construye el segundo vector AC del plano
            Puntos vectorAC = new Puntos(
                    puntos.get(2).getX()-puntos.get(0).getX(),
                    puntos.get(2).getY()-puntos.get(0).getY(),
                    puntos.get(2).getZ()-puntos.get(0).getZ()
                    );
            //Se calcula la normal con un producto Cruz
            Puntos normal = calcularProductoCruz(vectorAB,vectorAC);
            a = normal.getX();
            b = normal.getY();
            c = normal.getZ();
            //Se calcula el D
            d = -(a*(puntos.get(0).getX()) + b*(puntos.get(0).getY()) + c*(puntos.get(0).getZ()));
            
    }
    
    //Método del producto Cruz
    public Puntos calcularProductoCruz(Puntos a, Puntos b){
            return new Puntos(
                        a.getY()*b.getZ() - a.getZ()*b.getY(),
                        a.getZ()*b.getX() - a.getX()*b.getZ(),
                        a.getX()*b.getY() - a.getY()*b.getX()
                    );
    }    
}
