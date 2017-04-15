package llenado_poligonos;

import java.util.ArrayList;

import processing.core.*;

public class LlenadoPoligonos extends PApplet{
    
    /* yMin - yMax: donde empieza y termina el poligono respectivamente
     * tempX y tempY: contienen la informacion del punto anterior
     *
     */
    
    int contador = 0, yMin = 0, yMax = 0, ptoX =0, ptoY = 0, tempX = 0, tempY = 0;
    int ptoX_inicial = 0, ptoY_inicial = 0, xMin = 0, xMax = 0, flag = 0;
    boolean llenar = false;
    ArrayList<Object> verticesOrg = new ArrayList<Object>(); // vertices en el orden que se ingresan
    ArrayList<NodoET> edgeTable = new ArrayList<NodoET>();
    ArrayList<NodoET> AET = new ArrayList<NodoET>();
    
    public void setup(){
        size(400, 400);
        stroke(0, 0, 255);
        background(255);
    }
    
    int numMin = 0, numMax = 0;
    
    public void draw(){
        
        if(llenar){
                        
            System.out.println("ET\n");
            for(int i = 0; i < edgeTable.size(); i++){
                System.out.println("Nodo " + i +
                        "\n YMax: " + edgeTable.get(i).yMax +
                        "\n XMin: " + edgeTable.get(i).xMin +
                        "\n 1/m: " + edgeTable.get(i).invM);
                System.out.println();
            }
                                    
            for(int j = yMin + 1; j < yMax; j++){
                
                // Inicializa el AET
                //System.out.println("Inicializa AET\n");
                numMin = 0;
                for(int ind = 0; ind < edgeTable.size(); ind++){                    
                    if(edgeTable.get(ind).yMin == j) {AET.add(edgeTable.get(ind));
                    // Ordenar ArrayList
                    ordenarAET();
                    System.out.println("Agrega el nodo: " + ind + "\n");
                    numMin++;}
                }
                
                if(numMin == 2) flag++;
                
                // Removemos aristas que se han llenado en el AET
//                boolean entrada = true;
//                int cont = 0;
//                while(entrada){
//                    if(AET.get(cont).yMax == j) {
//                        AET.remove(cont);
//                        cont = 0;
//                        // Ordenar ArrayList
//                        ordenarAET();
//                        //flag = 0;
//                    }
//                    cont++;
//                    if(cont == AET.size()) entrada = false;
//                    
//                }
                for(int ind = 0; ind < AET.size(); ind++){
                    if(AET.get(ind).yMax == j) {
                    	AET.remove(ind);
                    	if(ind > 0) ind--;
                    	}          
                }
                
                // Ordenar ArrayList
                ordenarAET();
                
                ////////////////////////////////////////// Relleno ///////////////////////////
                int indAux = 0;
                flag = 0;
                int valorX;
                for(int i = xMin; i <= xMax; i++){
                    //flag = 0;
//                    if(indAux == AET.size()) indAux = 0;
                    if(flag == 0) valorX = (int)Math.floor(AET.get(indAux).valXMin) + 1;
                    else valorX = (int)Math.floor(AET.get(indAux).valXMin);
                    if(i == valorX){
                        //flag++;
                        indAux++;
                        if(numMin == 2){
                        	numMin = 0;
                        	//if(indAux)
                        	//if(j == yMin)point(i, j); flag += 2;
                        	//point(i, j);
                        	i--;
                        }else flag++;
                        ///flag++;
//                        if(numMin == 2) {indAux += 2; flag++;}
//                        else indAux++;
                        
                        if(indAux == AET.size()) indAux = 0;                    
                        
                        //if(AET.get(indAux).xMin == AET.get(indAux + 1).xMin) indAux += 2; flag++;
                        //if(indAux == AET.size()) indAux = 0;
//                        if(indAux < AET.size() - 2){                            
//                            indAux++;
//                            flag++;
//                            if(AET.get(indAux).xMin == AET.get(indAux - 1).xMin){
//                                indAux++;
//                                point(i, j);
//                                flag = 0;
//                            }
//                        }                        
                    }
                    if(j == yMax) flag = 0;
                    if(flag > 1) flag = 0;
                    if(flag == 1) point(i, j);
                    //if(numMin == 2 && flag == 1) flag = 0;
                }
                
                // Actualiza el valor de X actual
                for(int ind = 0; ind < AET.size(); ind++){
                    double aux = ((AET.get(ind).valXMin + AET.get(ind).invM)); // Verificar dependiendo de "como ataque el llenado"
                    AET.get(ind).set_valXMin(aux);                                        
                }                
            }
            llenar = false;
        }
    }
    
    private void ordenarAET() {    
         for (int i = 0; i < AET.size(); i++) {             
             for (int j = 0; j < AET.size() - 1; j++) {                  
                  if (AET.get(j).xMin > AET.get(j+1).xMin) {
                      intercambiar(j);
                  }
             }
         }
         System.out.println("\nOrdenado listo\n");
         System.out.println("AET\n");
            for(int i = 0; i < AET.size(); i++){
                System.out.println("Nodo " + i +
                        "\n YMax: " + edgeTable.get(i).yMax +
                        "\n XMin: " + edgeTable.get(i).xMin +
                        "\n 1/m: " + edgeTable.get(i).invM);
                System.out.println();
            }
    }

    private void intercambiar(int indice) {
        NodoET nodoTemp = AET.get(indice);
        AET.set(indice, AET.get(indice + 1));
        AET.set(indice + 1, nodoTemp);
    }

    public void mousePressed(){
    	if(mouseButton != RIGHT){
        ptoX = mouseX;
        ptoY = mouseY;
    	}
        //point(ptoX, ptoY);
        
        if(contador == 0){
            yMin = ptoY;
            yMax = ptoY;
            
            xMin = ptoX;
            xMax = ptoX;
            
            // Guardo informacion del primer punto del poligono
            ptoX_inicial = ptoX;
            ptoY_inicial = ptoY;
            
            //System.out.println("ptoX: " + ptoX + " ptoY: " + ptoY);
            
            // Contienen la informacion del punto anterior
            tempX = ptoX;
            tempY = ptoY;    
        }else{
            // Determina el valor de Y min y max del poligono
            if(ptoY < yMin) yMin = ptoY;
            if(ptoY > yMax) yMax = ptoY;
            
            if(ptoX < xMin) xMin = ptoX;
            if(ptoX > xMax) xMax = ptoX;
            
            //if(contador > 0){ // tengo al menos dos puntos
                                            
                
                
                if(mouseButton == RIGHT && contador > 2){
                    ptoY = ptoY_inicial;
                    ptoX = ptoX_inicial;
                    if(ptoY > tempY) edgeTable.add(new NodoET(ptoY, tempX, pendienteInv(ptoY, ptoX, tempY, tempX), tempY));
                    else edgeTable.add(new NodoET(tempY, ptoX, pendienteInv(ptoY, ptoX, tempY, tempX), ptoY));
                    llenar = true;
                }else{
                    if(ptoY > tempY) edgeTable.add(new NodoET(ptoY, tempX, pendienteInv(ptoY, ptoX, tempY, tempX), tempY));
                    else edgeTable.add(new NodoET(tempY, ptoX, pendienteInv(ptoY, ptoX, tempY, tempX), ptoY));
                }
                
                line(tempX, tempY, ptoX, ptoY);
                
//                System.out.println("ptoX: " + ptoX + " ptoY: " + ptoY);
//                System.out.println("tempX: " + tempX + " tempY: " + tempY);
//                System.out.println();                
                
                tempY = ptoY;
                tempX = ptoX;
                
            //}    
            
        }
        
        verticesOrg.add(new Vertice(ptoX, ptoY));
        contador++;
    }
    
    public double pendienteInv(int y2, int x2, int y1, int x1) {
        if(x2 != x1) return (double) (x2 - x1) / (y2 - y1);
        else return 0;
    }    
}