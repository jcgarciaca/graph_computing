package pintor;

/**
 *
 * @author Jhon
 */
import java.util.ArrayList;
import processing.core.*;
import remixlab.proscene.*;

public class AlgoritmoPintor extends PApplet {

    Scene scene;
    //arreglo de superficies
    public ArrayList<Superficie> superficies = new ArrayList<Superficie>();
    //boolean q permite hacer o no hacer el algoritmo del pintor
    public boolean pintor = true;
    

    public void setup() {
        size(640, 360, P3D);
        scene = new Scene(this);


        hint(DISABLE_DEPTH_TEST);

    }

    public void draw() {
        //borra el fondo
        background(0, 0, 0);
        //el algoritmo del pintor
        if(pintor){
        CalcularVertices();
        OrdenarSuperficies();
        Restricciones();
        }
        Pintar();
    }



    //aplica las pruebas del algoritmo del pintor una vez ordenado las superficies
    public void Restricciones(){
        Superficie aux;
        boolean prueba;
        if(superficies.size()>1){ //mira ssi hay máss de dos superficies
            
            for (int i = 0; i < superficies.size(); i++) {
                for (int j = i; j < superficies.size(); j++) {
                    prueba = false;
                    //Restriccion 1 no se translapa en X
                    if(superficies.get(i).xmax <= superficies.get(j).xmin){
                        prueba=true;
                    //Restriccion 1 no se translapa en Y
                    }else{ 
                        if(superficies.get(i).ymax <= superficies.get(j).ymin){
                            prueba = true;
                        //S está detrás del plano de S' 
                        }else{ 
                            if(
                                superficies.get(j).a * superficies.get(i).puntos.get(0).getX() +
                                superficies.get(j).b * superficies.get(i).puntos.get(0).getY() +
                                superficies.get(j).c * superficies.get(i).puntos.get(0).getZ() +
                                superficies.get(j).d <= 0 &&
                            
                                superficies.get(j).a * superficies.get(i).puntos.get(1).getX() +
                                superficies.get(j).b * superficies.get(i).puntos.get(1).getY() +
                                superficies.get(j).c * superficies.get(i).puntos.get(1).getZ() +
                                superficies.get(j).d <= 0 &&
                            
                                superficies.get(j).a * superficies.get(i).puntos.get(2).getX() +
                                superficies.get(j).b * superficies.get(i).puntos.get(2).getY() +
                                superficies.get(j).c * superficies.get(i).puntos.get(2).getZ() +
                                superficies.get(j).d <= 0 
                            
                            )
                            {
                                prueba = true;
                            }
                            //S' está delante del plano de S
                            else{
                                if(
                                superficies.get(i).a * superficies.get(j).puntos.get(0).getX() +
                                superficies.get(i).b * superficies.get(j).puntos.get(0).getY() +
                                superficies.get(i).c * superficies.get(j).puntos.get(0).getZ() +
                                superficies.get(i).d >= 0 &&
                            
                                superficies.get(i).a * superficies.get(j).puntos.get(1).getX() +
                                superficies.get(i).b * superficies.get(j).puntos.get(1).getY() +
                                superficies.get(i).c * superficies.get(j).puntos.get(1).getZ() +
                                superficies.get(i).d >= 0 &&
                            
                                superficies.get(i).a * superficies.get(j).puntos.get(2).getX() +
                                superficies.get(i).b * superficies.get(j).puntos.get(2).getY() +
                                superficies.get(i).c * superficies.get(j).puntos.get(2).getZ() +
                                superficies.get(i).d >= 0 
                                )
                                {
                                    prueba = true;
                                }
                            }
                        }
                    }

                    if(!prueba){
                        aux = superficies.get(i);
                        superficies.set(i, superficies.get(j));
                        superficies.set(j, aux);
                        j=superficies.size();
                    }

                }
        }
    }

    }
    //simplemente me calcula para cada superficie los xmax, ymax ....
    public void CalcularVertices() {
        for (Superficie sup : superficies) {
            Puntos a1 = getPuntoCamara(sup.getPuntos().get(0));
            Puntos a2 = getPuntoCamara(sup.getPuntos().get(1));
            Puntos a3 = getPuntoCamara(sup.getPuntos().get(2));
            sup.xmax=Math.abs(maxPuntos(a1, a2, a3, 0));
            sup.xmin=Math.abs(minPuntos(a1, a2, a3, 0));
            sup.ymax=Math.abs(maxPuntos(a1, a2, a3, 1));
            sup.ymin=Math.abs(minPuntos(a1, a2, a3, 1));
            sup.zmax=Math.abs(maxPuntos(a1, a2, a3, 2));
            sup.zmin=Math.abs(minPuntos(a1, a2, a3, 2));

        }
    }


    //metodo q pinta superfice por superficie
    public void Pintar() {
        for (Superficie sup : superficies) {
            beginShape();
            fill(sup.R, sup.G, sup.B);
            for (Puntos pu : sup.getPuntos()) {

                vertex(pu.getX(), pu.getY(), pu.getZ());

            }
            endShape();

        }

    }


    public void keyPressed() {

        //me agrega 2 superficies que comparten un vertice
        if (key == 'p') {
            //primera superfice
            Puntos p1 = new Puntos((int) scene.camera().position().x, (int) scene.camera().position().y, (int) scene.camera().position().z);
            Puntos p2 = new Puntos(p1.getX() + (int) (Math.random() * 100), p1.getY() + (int) (Math.random() * 80), p1.getZ() + (int) (Math.random() * 120));
            Puntos p3 = new Puntos(p2.getX() + (int) (Math.random() * 80), p2.getY() - (int) (Math.random() * 140), p2.getZ() + (int) (Math.random() * 80));

            //me crea la superficie con los puntos y los colores R,G,B
            superficies.add(new Superficie(p1, p2, p3, (int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));


            p3 = new Puntos(p2.getX() - (int) (Math.random() * 40), p2.getY()  + (int) (Math.random() * 80), p2.getZ() - (int) (Math.random() * 140));
            //me crea la segunda superficie con los puntos y los colores R,G,B
            //superficies.add(new Superficie(p1, p2, p3, (int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
        } else if (key == 'o') {
            pintor = !pintor;
            if (pintor) {
                System.out.println("PINTOR ON");
            } else {
                System.out.println("PINTOR OFF");
            }
        }
    }

   

    //me transforma los puntos a coordenadas de camara
    public Puntos getPuntoCamara(Puntos p) {
        PVector pV = scene.camera().frame().coordinatesOf(new PVector(p.getX(), p.getY(), p.getZ()));
        return new Puntos((int) pV.x, (int) pV.y, (int) pV.z);
    }

    //me ordena las superficies por zmax
    public void OrdenarSuperficies() {
        Superficie aux;
        for (int i = 0; i < superficies.size(); i++) {
            for (int j = i; j < superficies.size(); j++) {
                if(superficies.get(i).zmax < superficies.get(j).zmax){
                    aux = superficies.get(i);
                    superficies.set(i, superficies.get(j));
                    superficies.set(j, aux);
                }

            }

        }

    }


    //me devuelve el minimo, dependiendo del valor u, si u=0 me devuelve el xmin, si u=1 me devuelve ymin....
    public int minPuntos(Puntos p1, Puntos p2, Puntos p3, int u) {
        if (u == 0) {
            if (p1.x <= p2.x && p1.x <= p3.x) {
                return p1.x;
            } else if (p2.x <= p1.x && p2.x <= p3.x) {
                return p2.x;
            } else {
                return p3.x;
            }
        } else if (u == 1) {
            if (p1.y <= p2.y && p1.y <= p3.y) {
                return p1.y;
            } else if (p2.y <= p1.y && p2.y <= p3.y) {
                return p2.y;
            } else {
                return p3.x;
            }
        } else {
            if (p1.z <= p2.z && p1.z <= p3.z) {
                return p1.z;
            } else if (p2.z <= p1.z && p2.z <= p3.z) {
                return p2.z;
            } else {
                return p3.z;
            }
        }
    }

    //me devuelve el maximo, dependiendo del valor u, si u=0 me devuelve el xmas, si u=1 me devuelve ymax....
     public int maxPuntos(Puntos p1, Puntos p2, Puntos p3, int u) {
        if (u == 0) {
            if (p1.x >= p2.x && p1.x >= p3.x) {
                return p1.x;
            } else if (p2.x >= p1.x && p2.x >= p3.x) {
                return p2.x;
            } else {
                return p3.x;
            }
        } else if (u == 1) {
            if (p1.y >= p2.y && p1.y >= p3.y) {
                return p1.y;
            } else if (p2.y >= p1.y && p2.y >= p3.y) {
                return p2.y;
            } else {
                return p3.x;
            }
        } else {
            if (p1.z >= p2.z && p1.z >= p3.z) {
                return p1.z;
            } else if (p2.z >= p1.z && p2.z >= p3.z) {
                return p2.z;
            } else {
                return p3.z;
            }
        }
    }

    public static void main(String args[]) {
        PApplet.main(new String[]{"pintor.AlgoritmoPintor"});
    }
}