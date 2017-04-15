package vista2d;

import java.util.ArrayList;


import processing.core.*;
import remixlab.proscene.*;

public class Vista2D_V3 extends PApplet{
   
    Scene scene, auxScene;
    PGraphics canvas, auxCanvas;
   
    ArrayList<Vertices> figura1 = new ArrayList<Vertices>();
    ArrayList<Vertices> figura1Ventana = new ArrayList<Vertices>();
    ArrayList<Vertices> figura1VentanaTemp = new ArrayList<Vertices>();
    
    ArrayList<Vertices> figura2 = new ArrayList<Vertices>();	
	ArrayList<Vertices> figura2Temp = new ArrayList<Vertices>();
	ArrayList<Vertices> figura2Ventana = new ArrayList<Vertices>();
    ArrayList<Vertices> figura2VentanaTemp = new ArrayList<Vertices>();
    
    ArrayList<Vertices> figura3 = new ArrayList<Vertices>();	
	ArrayList<Vertices> figura3Temp = new ArrayList<Vertices>();
	ArrayList<Vertices> figura3Ventana = new ArrayList<Vertices>();
    ArrayList<Vertices> figura3VentanaTemp = new ArrayList<Vertices>();
    
    
    ArrayList<MiMatriz> transformaciones = new ArrayList<MiMatriz>();
    ArrayList<MiMatriz> matricesTransformacion = new ArrayList<MiMatriz>();
    
    
    ArrayList<Vertices> lineas = new ArrayList<Vertices>();
    ArrayList<Vertices> lineasVentana = new ArrayList<Vertices>();
    ArrayList<Vertices> lineasVentanaTemp = new ArrayList<Vertices>();
    
   
    ArrayList<Vertices> orgFiguras = new ArrayList<Vertices>();
           
    float ancho = 100, alto = 100, anchoAux = 0, altoAux = 0;
    //int posX = 150, posY = 100, ancho = 100, alto = 100, anchoAux = 0, altoAux = 0;
    int[] vertVentana = new int[4];
   
    float pendiente = 0, auxVariacion = 0, angulo = 0, orgX = 0, orgY = 0, tempX = 0, tempY = 0, valorB = 0;
   
    boolean actual = false, siguiente = false;
    
    float angFig2 = 0, angFig3 = 0;
    
    
    float d = sqrt(pow(ancho/2, 2) + pow(alto/2, 2));
    
    float p1x=-(cos((45-angulo)*(float)Math.PI/180)*d)+200;
    float p1y=-(sin((45-angulo)*(float)Math.PI/180)*d)+150;
    float p2x=(cos((45+angulo)*(float)Math.PI/180)*d)+200; 
    float p2y=-(sin((45+angulo)*(float)Math.PI/180)*d)+150;
    float p3x=(cos((45-angulo)*(float)Math.PI/180)*d)+200;
    float p3y=(sin((45-angulo)*(float)Math.PI/180)*d)+150;
    float p4x=-(cos((45+angulo)*(float)Math.PI/180)*d)+200;
    float p4y=(sin((45+angulo)*(float)Math.PI/180)*d)+150;
    
    
    
    int numLineas = 0;
   
    Matriz traslacion;// = new Matriz(-posX, -posY, 0);
    Matriz escalacion;
    Matriz rotacion;// = new Matriz(0);   
    float[][] P = new float[3][1], Paux, matTemp;
       
    public void setup(){
        size(400, 600);
        noStroke();
        canvas = createGraphics(400, 300, P3D);
        auxCanvas = createGraphics(400, 300, P3D);
       
        P[0][0] = 0;
        P[1][0] = 0;
        P[2][0] = 1;
       
//        escalacion = new Matriz(auxCanvas.width / ancho, auxCanvas.height / alto, 1);
       
        ////////////////////// Figura 1 //////////////////////
        figura1.add(new Vertices(112, 50));
        figura1.add(new Vertices(50, 188));
        figura1.add(new Vertices(100, 150));
        figura1.add(new Vertices(88, 225));
        figura1.add(new Vertices(125, 188));
        figura1.add(new Vertices(137, 250));
        figura1.add(new Vertices(162, 212));
        figura1.add(new Vertices(200, 275));
        figura1.add(new Vertices(212, 225));
        figura1.add(new Vertices(275, 250));
        figura1.add(new Vertices(250, 175));
        figura1.add(new Vertices(300, 200));
        figura1.add(new Vertices(288, 137));
        figura1.add(new Vertices(360, 162));
        figura1.add(new Vertices(288, 50));
        
        // Cabeza
        figura1.add(new Vertices(225, 100));
        figura1.add(new Vertices(212, 88));
        figura1.add(new Vertices(237, 62));
        figura1.add(new Vertices(225, 13));
        figura1.add(new Vertices(212, 37));
        figura1.add(new Vertices(188, 37));
        figura1.add(new Vertices(175, 13));
        figura1.add(new Vertices(163, 62));
        figura1.add(new Vertices(188, 88));
        figura1.add(new Vertices(175, 100));
        
        // Pelos
        lineas.add(new Vertices(188, 25));
        lineas.add(new Vertices(194, 37));
        lineas.add(new Vertices(194, 25));
        lineas.add(new Vertices(200, 37));
        lineas.add(new Vertices(200, 25));
        lineas.add(new Vertices(206, 37));
        
        
        //////////////////////////// Figura 2 /////////////////////
        figura2.add(new Vertices(12, 50));
        figura2.add(new Vertices(37, 25));
        figura2.add(new Vertices(37, 37));
        figura2.add(new Vertices(24, 50));
        figura2.add(new Vertices(37, 63));
        figura2.add(new Vertices(37, 75));
        
        
        //////////////////////////// Figura 3 /////////////////////
        figura3.add(new Vertices(37, 75));
        figura3.add(new Vertices(49, 75));
        figura3.add(new Vertices(62, 88));
        figura3.add(new Vertices(75, 75));
        figura3.add(new Vertices(87, 75));
        figura3.add(new Vertices(62, 100));
        
        
        
        
        
//        figura1.add(new Vertices(50, 170));
//        figura1.add(new Vertices(90, 50));
//        figura1.add(new Vertices(300, 80));
//        figura1.add(new Vertices(180, 100));
//        figura1.add(new Vertices(250, 120));
       
//        figura1.add(new Vertices(50, 170));
//        figura1.add(new Vertices(90, 50));       
//        figura1.add(new Vertices(250, 250));
       
       
        orgFiguras.add(new Vertices(figura1.get(0).ptoX, figura1.get(0).ptoY));
    }

    public void draw(){
    	orgFiguras.clear();
    	orgFiguras.add(new Vertices(figura1.get(0).ptoX, figura1.get(0).ptoY));
    	
    	matricesTransformacion.clear();
    	transformaciones.clear();
		figura2Temp.clear();
		figura3Temp.clear();
		
		traslacion = new Matriz(-12, -50, 0);
        rotacion = new Matriz((float)(angFig2 * Math.PI / 180));
        escalacion = new Matriz(1, 1, 1);
        matTemp = producto(producto(escalacion.getMatriz(), rotacion.getMatriz()), traslacion.getMatriz());
        traslacion = new Matriz(12, 50, 0);
        
        transformaciones.add(new MiMatriz(producto(traslacion.getMatriz(), matTemp)));
        
        
        traslacion = new Matriz(-37, -75, 0);
        rotacion = new Matriz((float)(angFig3 * Math.PI / 180));
        escalacion = new Matriz(1, 1, 1);
        matTemp = producto(producto(escalacion.getMatriz(), rotacion.getMatriz()), traslacion.getMatriz());
        
        traslacion = new Matriz(37, 75, 0);
        matTemp = producto(traslacion.getMatriz(), matTemp);
        
        transformaciones.add(new MiMatriz(producto(transformaciones.get(0).matriz, matTemp)));
        
        
        for(int i = 0; i < figura2.size(); i++){
            P[0][0] = figura2.get(i).ptoX;
            P[1][0] = figura2.get(i).ptoY;
            Paux = producto(transformaciones.get(0).matriz, P);//transforma();
            figura2Temp.add(new Vertices(Paux[0][0], Paux[1][0]));
        }
        
        
        for(int i = 0; i < figura3.size(); i++){
            P[0][0] = figura3.get(i).ptoX;
            P[1][0] = figura3.get(i).ptoY;
            Paux = producto(transformaciones.get(1).matriz, P);//transforma();
            figura3Temp.add(new Vertices(Paux[0][0], Paux[1][0]));
        }
        
//        orgFiguras.add(new Vertices(figura2Temp.get(0).ptoX, figura2Temp.get(0).ptoY));
        
    	dibujaEscena();
    	              
        traslacion = new Matriz(-p1x, -p1y, 0);
        rotacion = new Matriz((float)(angulo * Math.PI / 180));
        escalacion = new Matriz((float)(auxCanvas.width / ancho), (float)(auxCanvas.height / alto), 1);
        
        matricesTransformacion.add(new MiMatriz(producto(producto(escalacion.getMatriz(), rotacion.getMatriz()), traslacion.getMatriz())));
        matricesTransformacion.add(new MiMatriz(producto(matricesTransformacion.get(0).matriz, transformaciones.get(0).matriz)));
        matricesTransformacion.add(new MiMatriz(producto(matricesTransformacion.get(0).matriz, transformaciones.get(1).matriz)));
        
        
        vistaVentana();
        dibujaVista();
    }

    public void dibujaEscena(){
        canvas.beginDraw();
        canvas.background(200);
        canvas.noStroke();
        canvas.fill(0, 0, 255);
       
        canvas.beginShape();
        for(int i = 0; i < figura1.size(); i++){
            canvas.vertex(figura1.get(i).ptoX, figura1.get(i).ptoY);
        }
        canvas.endShape(CLOSE);
        
        
        
        canvas.fill(0, 255, 255);        
        canvas.beginShape();
        for(int i = 0; i < figura2Temp.size(); i++){
            canvas.vertex(figura2Temp.get(i).ptoX, figura2Temp.get(i).ptoY);
        }
        canvas.endShape(CLOSE);
        
        
        canvas.fill(0, 0, 0);        
        canvas.beginShape();
        for(int i = 0; i < figura3Temp.size(); i++){
            canvas.vertex(figura3Temp.get(i).ptoX, figura3Temp.get(i).ptoY);
        }
        canvas.endShape(CLOSE);
        
        
        
        
       
        canvas.fill(0, 255, 0, 100);
        canvas.stroke(0);
        
        canvas.line(lineas.get(0).ptoX, lineas.get(0).ptoY, lineas.get(1).ptoX, lineas.get(1).ptoY);
        canvas.line(lineas.get(2).ptoX, lineas.get(2).ptoY, lineas.get(3).ptoX, lineas.get(3).ptoY);
        canvas.line(lineas.get(4).ptoX, lineas.get(4).ptoY, lineas.get(5).ptoX, lineas.get(5).ptoY);
        
        d = sqrt(pow(ancho/2, 2) + pow(alto/2, 2));
  		p1x=-(cos((45-angulo)*(float)Math.PI/180)*d)+mouseX;
		p1y=-(sin((45-angulo)*(float)Math.PI/180)*d)+mouseY;
		p2x=(cos((45+angulo)*(float)Math.PI/180)*d)+mouseX;
		p2y=-(sin((45+angulo)*(float)Math.PI/180)*d)+mouseY;
		p3x=(cos((45-angulo)*(float)Math.PI/180)*d)+mouseX;
		p3y=(sin((45-angulo)*(float)Math.PI/180)*d)+mouseY;
		p4x=-(cos((45+angulo)*(float)Math.PI/180)*d)+mouseX;
		p4y=(sin((45+angulo)*(float)Math.PI/180)*d)+mouseY;
        
        
        
		canvas.line(p1x, p1y, p2x, p2y);
		canvas.line(p2x, p2y, p3x, p3y);
		canvas.line(p3x, p3y, p4x, p4y);
		canvas.line(p4x, p4y, p1x, p1y);

        canvas.endDraw();
        image(canvas, 0, 0);
    }
   
    public void dibujaVista(){
//        System.out.println("Vertices: " + figura1Ventana.size());
        auxCanvas.beginDraw();
        auxCanvas.background(255);
        auxCanvas.noStroke();
        auxCanvas.fill(0, 255, 0);
        auxCanvas.beginShape();
        for(int i = 0; i < figura1Ventana.size(); i++){
            auxCanvas.vertex(figura1Ventana.get(i).ptoX, figura1Ventana.get(i).ptoY);
            //System.out.println(figura1Ventana.get(i).ptoX + ", " + figura1Ventana.get(i).ptoY);
        }
        auxCanvas.endShape(CLOSE);
        
        
        auxCanvas.fill(255, 0, 0);
        auxCanvas.beginShape();
        for(int i = 0; i < figura2Ventana.size(); i++){
            auxCanvas.vertex(figura2Ventana.get(i).ptoX, figura2Ventana.get(i).ptoY);
            //System.out.println(figura1Ventana.get(i).ptoX + ", " + figura1Ventana.get(i).ptoY);
        }
        auxCanvas.endShape(CLOSE);
        
        
        auxCanvas.fill(255, 255, 0);
        auxCanvas.beginShape();
        for(int i = 0; i < figura3Ventana.size(); i++){
            auxCanvas.vertex(figura3Ventana.get(i).ptoX, figura3Ventana.get(i).ptoY);
            //System.out.println(figura1Ventana.get(i).ptoX + ", " + figura1Ventana.get(i).ptoY);
        }
        auxCanvas.endShape(CLOSE);
        
        
        auxCanvas.stroke(0);
        
        if(numLineas == 1){
        	auxCanvas.line(lineasVentana.get(0).ptoX, lineasVentana.get(0).ptoY, lineasVentana.get(1).ptoX, lineasVentana.get(1).ptoY);
        }else if(numLineas == 2){
        	auxCanvas.line(lineasVentana.get(0).ptoX, lineasVentana.get(0).ptoY, lineasVentana.get(1).ptoX, lineasVentana.get(1).ptoY);
        	auxCanvas.line(lineasVentana.get(2).ptoX, lineasVentana.get(2).ptoY, lineasVentana.get(3).ptoX, lineasVentana.get(3).ptoY);
        }else if(numLineas == 3){
        	auxCanvas.line(lineasVentana.get(0).ptoX, lineasVentana.get(0).ptoY, lineasVentana.get(1).ptoX, lineasVentana.get(1).ptoY);
        	auxCanvas.line(lineasVentana.get(2).ptoX, lineasVentana.get(2).ptoY, lineasVentana.get(3).ptoX, lineasVentana.get(3).ptoY);
        	auxCanvas.line(lineasVentana.get(4).ptoX, lineasVentana.get(4).ptoY, lineasVentana.get(5).ptoX, lineasVentana.get(5).ptoY);
        }
        
        
        auxCanvas.endDraw();
        image(auxCanvas, 0, 300);
    }
   
    public void vistaVentana(){
       
        figura1VentanaTemp.clear();
        figura1Ventana.clear();
        
        figura2VentanaTemp.clear();
        figura2Ventana.clear();
        
        figura3VentanaTemp.clear();
        figura3Ventana.clear();
       
        anchoAux = auxCanvas.width;
        altoAux = auxCanvas.height;
       
        for(int i = 0; i < figura1.size(); i++){
            P[0][0] = figura1.get(i).ptoX;
            P[1][0] = figura1.get(i).ptoY;
            Paux = producto(matricesTransformacion.get(0).matriz, P);
            figura1VentanaTemp.add(new Vertices(Paux[0][0], Paux[1][0]));
        }
        
        lineasVentana.clear();
        lineasVentanaTemp.clear();
        
        for(int i = 0; i < lineas.size(); i++){
            P[0][0] = lineas.get(i).ptoX;
            P[1][0] = lineas.get(i).ptoY;
            Paux = transforma();
            lineasVentanaTemp.add(new Vertices(Paux[0][0], Paux[1][0]));
        }
        
        numLineas = 0;
        
        recortarLinea(lineasVentanaTemp.get(0).ptoX, lineasVentanaTemp.get(0).ptoY, lineasVentanaTemp.get(1).ptoX, lineasVentanaTemp.get(1).ptoY);
        //if(nx2 != 0 && ny2 != 0 && nx1 != 0 && ny1 != 0){// Guardar puntos
        	lineasVentana.add(new Vertices(nx1, ny1));
        	lineasVentana.add(new Vertices(nx2, ny2));
        	numLineas++;
        //}
        
        recortarLinea(lineasVentanaTemp.get(2).ptoX, lineasVentanaTemp.get(2).ptoY, lineasVentanaTemp.get(3).ptoX, lineasVentanaTemp.get(3).ptoY);
        //if(nx2 != 0 && ny2 != 0 && nx1 != 0 && ny1 != 0){// Guardar puntos
        	lineasVentana.add(new Vertices(nx1, ny1));
        	lineasVentana.add(new Vertices(nx2, ny2));
        	numLineas++;
        //}
        
        recortarLinea(lineasVentanaTemp.get(4).ptoX, lineasVentanaTemp.get(4).ptoY, lineasVentanaTemp.get(5).ptoX, lineasVentanaTemp.get(5).ptoY);
        //if(nx2 != 0 && ny2 != 0 && nx1 != 0 && ny1 != 0){// Guardar puntos
        	lineasVentana.add(new Vertices(nx1, ny1));
        	lineasVentana.add(new Vertices(nx2, ny2));
        	numLineas++;
        //}
        
        
        	
        ////////////////////////////////////////////////////// Inicia recorte poligono //////////////////////////////////////////////////////
        
        	//////////////////////////////// Arista Izquierda //////////////////////////////////
        for(int numFig = 0; numFig < 1; numFig++){
//        for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
            if(figura1VentanaTemp.size() != 0){               
                orgX = figura1VentanaTemp.get(0).ptoX;
                orgY = figura1VentanaTemp.get(0).ptoY;
            }           
            for(int nodo = 0; nodo < figura1VentanaTemp.size(); nodo++){
                if(nodo < figura1VentanaTemp.size()-1){
                    tempX = figura1VentanaTemp.get(nodo+1).ptoX;
                    tempY = figura1VentanaTemp.get(nodo+1).ptoY;
                }else{
                    tempX = orgX;
                    tempY = orgY;
                }
               
                if(figura1VentanaTemp.get(nodo).ptoX >= 0){
                    actual = true;
                }else actual = false;
                if(tempX >= 0) {
                    siguiente = true;
                }else siguiente = false;
               
               
                if(figura1VentanaTemp.get(nodo).ptoX >= 0) actual = true;
                else actual = false;
               
                if(tempX >= 0) siguiente = true;
                else siguiente = false;
               
                if(actual){// El punto actual adentro
                    figura1Ventana.add(new Vertices(figura1VentanaTemp.get(nodo).ptoX, figura1VentanaTemp.get(nodo).ptoY));
                    if(!siguiente){// De adentro hacia afuera
                        pendiente = (float)(tempY - figura1VentanaTemp.get(nodo).ptoY) / (tempX - figura1VentanaTemp.get(nodo).ptoX);
                        valorB = tempY - (pendiente * tempX);
                        figura1Ventana.add(new Vertices(0, valorB));
                    }
                }else{// El punto actual afuera
                    if(siguiente){// De afuera hacia adentro
                        pendiente = (float)(tempY - figura1VentanaTemp.get(nodo).ptoY) / (tempX - figura1VentanaTemp.get(nodo).ptoX);
                        valorB = tempY - (pendiente * tempX);
                        figura1Ventana.add(new Vertices(0, valorB));                       
                    }
                }               
            }           
        }

        // Actualiza los vertices encontrados
        figura1VentanaTemp.clear();
        for(int i = 0; i < figura1Ventana.size(); i++){
            figura1VentanaTemp.add(new Vertices(figura1Ventana.get(i).ptoX, figura1Ventana.get(i).ptoY));
        }
        figura1Ventana.clear();   
       
       
       
        //System.out.println("Superior");
        //////////////////////////////// Arista Superior //////////////////////////////////
        for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
            if(figura1VentanaTemp.size() != 0){               
                orgX = figura1VentanaTemp.get(0).ptoX;
                orgY = figura1VentanaTemp.get(0).ptoY;
            }
            for(int nodo = 0; nodo < figura1VentanaTemp.size(); nodo++){
                if(nodo < figura1VentanaTemp.size()-1){
                    tempX = figura1VentanaTemp.get(nodo+1).ptoX;
                    tempY = figura1VentanaTemp.get(nodo+1).ptoY;
                }else{
                    tempX = orgX;
                    tempY = orgY;
                }
                           
               
               
                if(figura1VentanaTemp.get(nodo).ptoY >= 0) actual = true;
                else actual = false;
               
                if(tempY >= 0) siguiente = true;
                else siguiente = false;
               
                if(actual){// El punto actual adentro
                    figura1Ventana.add(new Vertices(figura1VentanaTemp.get(nodo).ptoX, figura1VentanaTemp.get(nodo).ptoY));
                    if(!siguiente){// De adentro hacia afuera
                        if(tempX == figura1VentanaTemp.get(nodo).ptoX){
                            figura1Ventana.add(new Vertices(tempX, 0));
                        }else{
                            pendiente = (float)(tempY - figura1VentanaTemp.get(nodo).ptoY) / (tempX - figura1VentanaTemp.get(nodo).ptoX);
                            valorB = tempY - (pendiente * tempX);
                            figura1Ventana.add(new Vertices((-valorB / pendiente), 0));
                        }                       
                    }
                }else{// El punto actual afuera
                    if(siguiente){// De afuera hacia adentro
                        if(tempX == figura1VentanaTemp.get(nodo).ptoX){
                            figura1Ventana.add(new Vertices(tempX, 0));
                        }else{
                            pendiente = (float)(tempY - figura1VentanaTemp.get(nodo).ptoY) / (tempX - figura1VentanaTemp.get(nodo).ptoX);
                            valorB = tempY - (pendiente * tempX);
                            figura1Ventana.add(new Vertices((-valorB / pendiente), 0));
                        }
                    }
                }               
            }
        }
       

        // Actualiza los vertices encontrados
        figura1VentanaTemp.clear();
        for(int i = 0; i < figura1Ventana.size(); i++){
            figura1VentanaTemp.add(new Vertices(figura1Ventana.get(i).ptoX, figura1Ventana.get(i).ptoY));
        }
        figura1Ventana.clear();       
       
       
        //////////////////////////////// Arista Derecha //////////////////////////////////
        for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
            if(figura1VentanaTemp.size() != 0){               
                orgX = figura1VentanaTemp.get(0).ptoX;
                orgY = figura1VentanaTemp.get(0).ptoY;
            }           
            for(int nodo = 0; nodo < figura1VentanaTemp.size(); nodo++){
                if(nodo < figura1VentanaTemp.size()-1){
                    tempX = figura1VentanaTemp.get(nodo+1).ptoX;
                    tempY = figura1VentanaTemp.get(nodo+1).ptoY;
                }else{
                    tempX = orgX;
                    tempY = orgY;
                }
                                           
               
                if(figura1VentanaTemp.get(nodo).ptoX <= anchoAux) actual = true;
                else actual = false;
               
                if(tempX <= anchoAux) siguiente = true;
                else siguiente = false;
               
                if(actual){// El punto actual adentro
                    figura1Ventana.add(new Vertices(figura1VentanaTemp.get(nodo).ptoX, figura1VentanaTemp.get(nodo).ptoY));
                    if(!siguiente){// De adentro hacia afuera
                        pendiente = (float)(tempY - figura1VentanaTemp.get(nodo).ptoY) / (tempX - figura1VentanaTemp.get(nodo).ptoX);
                        valorB = tempY - (pendiente * tempX);
                        figura1Ventana.add(new Vertices(anchoAux, (pendiente * anchoAux) + valorB));
                    }
                }else{// El punto actual afuera
                    if(siguiente){// De afuera hacia adentro
                        pendiente = (float)(tempY - figura1VentanaTemp.get(nodo).ptoY) / (tempX - figura1VentanaTemp.get(nodo).ptoX);
                        valorB = tempY - (pendiente * tempX);
                        figura1Ventana.add(new Vertices(anchoAux, (pendiente * anchoAux) + valorB));                       
                    }
                }           
            }
        }

        // Actualiza los vertices encontrados
        figura1VentanaTemp.clear();
        for(int i = 0; i < figura1Ventana.size(); i++){
            figura1VentanaTemp.add(new Vertices(figura1Ventana.get(i).ptoX, figura1Ventana.get(i).ptoY));
        }
        figura1Ventana.clear();
       
       
        //////////////////////////////// Arista Inferior //////////////////////////////////
        for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
            if(figura1VentanaTemp.size() != 0){               
                orgX = figura1VentanaTemp.get(0).ptoX;
                orgY = figura1VentanaTemp.get(0).ptoY;
            }       
            for(int nodo = 0; nodo < figura1VentanaTemp.size(); nodo++){
                if(nodo < figura1VentanaTemp.size()-1){
                    tempX = figura1VentanaTemp.get(nodo+1).ptoX;
                    tempY = figura1VentanaTemp.get(nodo+1).ptoY;
                }else{
                    tempX = orgX;
                    tempY = orgY;
                }
               
                if(figura1VentanaTemp.get(nodo).ptoY <= altoAux) actual = true;
                else actual = false;
               
                if(tempY <= altoAux) siguiente = true;
                else siguiente = false;
               
                if(actual){// El punto actual adentro
                    figura1Ventana.add(new Vertices(figura1VentanaTemp.get(nodo).ptoX, figura1VentanaTemp.get(nodo).ptoY));
                    if(!siguiente){// De adentro hacia afuera
                        if(tempX == figura1VentanaTemp.get(nodo).ptoX){
                            figura1Ventana.add(new Vertices(tempX, altoAux));
                        }else{
                            pendiente = (float)(tempY - figura1VentanaTemp.get(nodo).ptoY) / (tempX - figura1VentanaTemp.get(nodo).ptoX);
                            valorB = tempY - (pendiente * tempX);
                            figura1Ventana.add(new Vertices(((altoAux - valorB) / pendiente), altoAux));
                        }                       
                    }
                }else{// El punto actual afuera
                    if(siguiente){// De afuera hacia adentro
                        if(tempX == figura1VentanaTemp.get(nodo).ptoX){
                            figura1Ventana.add(new Vertices(tempX, altoAux));
                        }else{
                            pendiente = (float)(tempY - figura1VentanaTemp.get(nodo).ptoY) / (tempX - figura1VentanaTemp.get(nodo).ptoX);
                            valorB = tempY - (pendiente * tempX);
                            figura1Ventana.add(new Vertices(((altoAux - valorB) / pendiente), altoAux));
                        }
                    }
                }           
            }
        }   

        // Actualiza los vertices encontrados
        figura1VentanaTemp.clear();
        for(int i = 0; i < figura1Ventana.size(); i++){
            figura1VentanaTemp.add(new Vertices(figura1Ventana.get(i).ptoX, figura1Ventana.get(i).ptoY));
        }
        ////////////////////////////////////////////////////// Termina recorte poligono //////////////////////////////////////////////////////
        
        
        
        
        
        
        
        
        
        
        
        
        ////////////////////////////////////////////////////// Figura 2 //////////////////////////////////////////////////////
        
        for(int i = 0; i < figura2Temp.size(); i++){
        	P[0][0] = figura2.get(i).ptoX;
            P[1][0] = figura2.get(i).ptoY;
            Paux = producto(matricesTransformacion.get(1).matriz, P);
//        	P[0][0] = figura2Temp.get(i).ptoX;
//            P[1][0] = figura2Temp.get(i).ptoY;
//            Paux = transforma();
            figura2VentanaTemp.add(new Vertices(Paux[0][0], Paux[1][0]));
        }
        
                
        ////////////////////////////////////////////////////// Inicia recorte poligono //////////////////////////////////////////////////////
        
    	//////////////////////////////// Arista Izquierda //////////////////////////////////
	    for(int numFig = 0; numFig < 1; numFig++){
	//    for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
	        if(figura2VentanaTemp.size() != 0){               
	            orgX = figura2VentanaTemp.get(0).ptoX;
	            orgY = figura2VentanaTemp.get(0).ptoY;
	        }           
	        for(int nodo = 0; nodo < figura2VentanaTemp.size(); nodo++){
	            if(nodo < figura2VentanaTemp.size()-1){
	                tempX = figura2VentanaTemp.get(nodo+1).ptoX;
	                tempY = figura2VentanaTemp.get(nodo+1).ptoY;
	            }else{
	                tempX = orgX;
	                tempY = orgY;
	            }
	           
	            if(figura2VentanaTemp.get(nodo).ptoX >= 0){
	                actual = true;
	            }else actual = false;
	            if(tempX >= 0) {
	                siguiente = true;
	            }else siguiente = false;
	           
	           
	            if(figura2VentanaTemp.get(nodo).ptoX >= 0) actual = true;
	            else actual = false;
	           
	            if(tempX >= 0) siguiente = true;
	            else siguiente = false;
	           
	            if(actual){// El punto actual adentro
	                figura2Ventana.add(new Vertices(figura2VentanaTemp.get(nodo).ptoX, figura2VentanaTemp.get(nodo).ptoY));
	                if(!siguiente){// De adentro hacia afuera
	                    pendiente = (float)(tempY - figura2VentanaTemp.get(nodo).ptoY) / (tempX - figura2VentanaTemp.get(nodo).ptoX);
	                    valorB = tempY - (pendiente * tempX);
	                    figura2Ventana.add(new Vertices(0, valorB));
	                }
	            }else{// El punto actual afuera
	                if(siguiente){// De afuera hacia adentro
	                    pendiente = (float)(tempY - figura2VentanaTemp.get(nodo).ptoY) / (tempX - figura2VentanaTemp.get(nodo).ptoX);
	                    valorB = tempY - (pendiente * tempX);
	                    figura2Ventana.add(new Vertices(0, valorB));                       
	                }
	            }               
	        }           
	    }
	
	    // Actualiza los vertices encontrados
	    figura2VentanaTemp.clear();
	    for(int i = 0; i < figura2Ventana.size(); i++){
	        figura2VentanaTemp.add(new Vertices(figura2Ventana.get(i).ptoX, figura2Ventana.get(i).ptoY));
	    }
	    figura2Ventana.clear();   
	   
	   
	   
	    //System.out.println("Superior");
	    //////////////////////////////// Arista Superior //////////////////////////////////
	    for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
	        if(figura2VentanaTemp.size() != 0){               
	            orgX = figura2VentanaTemp.get(0).ptoX;
	            orgY = figura2VentanaTemp.get(0).ptoY;
	        }
	        for(int nodo = 0; nodo < figura2VentanaTemp.size(); nodo++){
	            if(nodo < figura2VentanaTemp.size()-1){
	                tempX = figura2VentanaTemp.get(nodo+1).ptoX;
	                tempY = figura2VentanaTemp.get(nodo+1).ptoY;
	            }else{
	                tempX = orgX;
	                tempY = orgY;
	            }
	                       
	           
	           
	            if(figura2VentanaTemp.get(nodo).ptoY >= 0) actual = true;
	            else actual = false;
	           
	            if(tempY >= 0) siguiente = true;
	            else siguiente = false;
	           
	            if(actual){// El punto actual adentro
	                figura2Ventana.add(new Vertices(figura2VentanaTemp.get(nodo).ptoX, figura2VentanaTemp.get(nodo).ptoY));
	                if(!siguiente){// De adentro hacia afuera
	                    if(tempX == figura2VentanaTemp.get(nodo).ptoX){
	                        figura2Ventana.add(new Vertices(tempX, 0));
	                    }else{
	                        pendiente = (float)(tempY - figura2VentanaTemp.get(nodo).ptoY) / (tempX - figura2VentanaTemp.get(nodo).ptoX);
	                        valorB = tempY - (pendiente * tempX);
	                        figura2Ventana.add(new Vertices((-valorB / pendiente), 0));
	                    }                       
	                }
	            }else{// El punto actual afuera
	                if(siguiente){// De afuera hacia adentro
	                    if(tempX == figura2VentanaTemp.get(nodo).ptoX){
	                        figura2Ventana.add(new Vertices(tempX, 0));
	                    }else{
	                        pendiente = (float)(tempY - figura2VentanaTemp.get(nodo).ptoY) / (tempX - figura2VentanaTemp.get(nodo).ptoX);
	                        valorB = tempY - (pendiente * tempX);
	                        figura2Ventana.add(new Vertices((-valorB / pendiente), 0));
	                    }
	                }
	            }               
	        }
	    }
	   
	
	    // Actualiza los vertices encontrados
	    figura2VentanaTemp.clear();
	    for(int i = 0; i < figura2Ventana.size(); i++){
	        figura2VentanaTemp.add(new Vertices(figura2Ventana.get(i).ptoX, figura2Ventana.get(i).ptoY));
	    }
	    figura2Ventana.clear();       
	   
	   
	    //////////////////////////////// Arista Derecha //////////////////////////////////
	    for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
	        if(figura2VentanaTemp.size() != 0){               
	            orgX = figura2VentanaTemp.get(0).ptoX;
	            orgY = figura2VentanaTemp.get(0).ptoY;
	        }           
	        for(int nodo = 0; nodo < figura2VentanaTemp.size(); nodo++){
	            if(nodo < figura2VentanaTemp.size()-1){
	                tempX = figura2VentanaTemp.get(nodo+1).ptoX;
	                tempY = figura2VentanaTemp.get(nodo+1).ptoY;
	            }else{
	                tempX = orgX;
	                tempY = orgY;
	            }
	                                       
	           
	            if(figura2VentanaTemp.get(nodo).ptoX <= anchoAux) actual = true;
	            else actual = false;
	           
	            if(tempX <= anchoAux) siguiente = true;
	            else siguiente = false;
	           
	            if(actual){// El punto actual adentro
	                figura2Ventana.add(new Vertices(figura2VentanaTemp.get(nodo).ptoX, figura2VentanaTemp.get(nodo).ptoY));
	                if(!siguiente){// De adentro hacia afuera
	                    pendiente = (float)(tempY - figura2VentanaTemp.get(nodo).ptoY) / (tempX - figura2VentanaTemp.get(nodo).ptoX);
	                    valorB = tempY - (pendiente * tempX);
	                    figura2Ventana.add(new Vertices(anchoAux, (pendiente * anchoAux) + valorB));
	                }
	            }else{// El punto actual afuera
	                if(siguiente){// De afuera hacia adentro
	                    pendiente = (float)(tempY - figura2VentanaTemp.get(nodo).ptoY) / (tempX - figura2VentanaTemp.get(nodo).ptoX);
	                    valorB = tempY - (pendiente * tempX);
	                    figura2Ventana.add(new Vertices(anchoAux, (pendiente * anchoAux) + valorB));                       
	                }
	            }           
	        }
	    }
	
	    // Actualiza los vertices encontrados
	    figura2VentanaTemp.clear();
	    for(int i = 0; i < figura2Ventana.size(); i++){
	        figura2VentanaTemp.add(new Vertices(figura2Ventana.get(i).ptoX, figura2Ventana.get(i).ptoY));
	    }
	    figura2Ventana.clear();
	   
	   
	    //////////////////////////////// Arista Inferior //////////////////////////////////
	    for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
	        if(figura2VentanaTemp.size() != 0){               
	            orgX = figura2VentanaTemp.get(0).ptoX;
	            orgY = figura2VentanaTemp.get(0).ptoY;
	        }       
	        for(int nodo = 0; nodo < figura2VentanaTemp.size(); nodo++){
	            if(nodo < figura2VentanaTemp.size()-1){
	                tempX = figura2VentanaTemp.get(nodo+1).ptoX;
	                tempY = figura2VentanaTemp.get(nodo+1).ptoY;
	            }else{
	                tempX = orgX;
	                tempY = orgY;
	            }
	           
	            if(figura2VentanaTemp.get(nodo).ptoY <= altoAux) actual = true;
	            else actual = false;
	           
	            if(tempY <= altoAux) siguiente = true;
	            else siguiente = false;
	           
	            if(actual){// El punto actual adentro
	                figura2Ventana.add(new Vertices(figura2VentanaTemp.get(nodo).ptoX, figura2VentanaTemp.get(nodo).ptoY));
	                if(!siguiente){// De adentro hacia afuera
	                    if(tempX == figura2VentanaTemp.get(nodo).ptoX){
	                        figura2Ventana.add(new Vertices(tempX, altoAux));
	                    }else{
	                        pendiente = (float)(tempY - figura2VentanaTemp.get(nodo).ptoY) / (tempX - figura2VentanaTemp.get(nodo).ptoX);
	                        valorB = tempY - (pendiente * tempX);
	                        figura2Ventana.add(new Vertices(((altoAux - valorB) / pendiente), altoAux));
	                    }                       
	                }
	            }else{// El punto actual afuera
	                if(siguiente){// De afuera hacia adentro
	                    if(tempX == figura2VentanaTemp.get(nodo).ptoX){
	                        figura2Ventana.add(new Vertices(tempX, altoAux));
	                    }else{
	                        pendiente = (float)(tempY - figura2VentanaTemp.get(nodo).ptoY) / (tempX - figura2VentanaTemp.get(nodo).ptoX);
	                        valorB = tempY - (pendiente * tempX);
	                        figura2Ventana.add(new Vertices(((altoAux - valorB) / pendiente), altoAux));
	                    }
	                }
	            }           
	        }
	    }   
	
	    // Actualiza los vertices encontrados
	    figura2VentanaTemp.clear();
	    for(int i = 0; i < figura2Ventana.size(); i++){
	        figura2VentanaTemp.add(new Vertices(figura2Ventana.get(i).ptoX, figura2Ventana.get(i).ptoY));
	    }
	    ////////////////////////////////////////////////////// Termina recorte poligono //////////////////////////////////////////////////////
    
        
	    
	    
	    
	    
	    
        ////////////////////////////////////////////////////// Figura 3 //////////////////////////////////////////////////////
        
        for(int i = 0; i < figura3Temp.size(); i++){
        	P[0][0] = figura3.get(i).ptoX;
            P[1][0] = figura3.get(i).ptoY;
            Paux = producto(matricesTransformacion.get(2).matriz, P);
//        	P[0][0] = figura3Temp.get(i).ptoX;
//            P[1][0] = figura3Temp.get(i).ptoY;
//            Paux = transforma();
            figura3VentanaTemp.add(new Vertices(Paux[0][0], Paux[1][0]));
        }
        
                
        ////////////////////////////////////////////////////// Inicia recorte poligono //////////////////////////////////////////////////////
        
    	//////////////////////////////// Arista Izquierda //////////////////////////////////
	    for(int numFig = 0; numFig < 1; numFig++){
	//    for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
	        if(figura3VentanaTemp.size() != 0){               
	            orgX = figura3VentanaTemp.get(0).ptoX;
	            orgY = figura3VentanaTemp.get(0).ptoY;
	        }           
	        for(int nodo = 0; nodo < figura3VentanaTemp.size(); nodo++){
	            if(nodo < figura3VentanaTemp.size()-1){
	                tempX = figura3VentanaTemp.get(nodo+1).ptoX;
	                tempY = figura3VentanaTemp.get(nodo+1).ptoY;
	            }else{
	                tempX = orgX;
	                tempY = orgY;
	            }
	           
	            if(figura3VentanaTemp.get(nodo).ptoX >= 0){
	                actual = true;
	            }else actual = false;
	            if(tempX >= 0) {
	                siguiente = true;
	            }else siguiente = false;
	           
	           
	            if(figura3VentanaTemp.get(nodo).ptoX >= 0) actual = true;
	            else actual = false;
	           
	            if(tempX >= 0) siguiente = true;
	            else siguiente = false;
	           
	            if(actual){// El punto actual adentro
	                figura3Ventana.add(new Vertices(figura3VentanaTemp.get(nodo).ptoX, figura3VentanaTemp.get(nodo).ptoY));
	                if(!siguiente){// De adentro hacia afuera
	                    pendiente = (float)(tempY - figura3VentanaTemp.get(nodo).ptoY) / (tempX - figura3VentanaTemp.get(nodo).ptoX);
	                    valorB = tempY - (pendiente * tempX);
	                    figura3Ventana.add(new Vertices(0, valorB));
	                }
	            }else{// El punto actual afuera
	                if(siguiente){// De afuera hacia adentro
	                    pendiente = (float)(tempY - figura3VentanaTemp.get(nodo).ptoY) / (tempX - figura3VentanaTemp.get(nodo).ptoX);
	                    valorB = tempY - (pendiente * tempX);
	                    figura3Ventana.add(new Vertices(0, valorB));                       
	                }
	            }               
	        }           
	    }
	
	    // Actualiza los vertices encontrados
	    figura3VentanaTemp.clear();
	    for(int i = 0; i < figura3Ventana.size(); i++){
	        figura3VentanaTemp.add(new Vertices(figura3Ventana.get(i).ptoX, figura3Ventana.get(i).ptoY));
	    }
	    figura3Ventana.clear();   
	   
	   
	   
	    //System.out.println("Superior");
	    //////////////////////////////// Arista Superior //////////////////////////////////
	    for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
	        if(figura3VentanaTemp.size() != 0){               
	            orgX = figura3VentanaTemp.get(0).ptoX;
	            orgY = figura3VentanaTemp.get(0).ptoY;
	        }
	        for(int nodo = 0; nodo < figura3VentanaTemp.size(); nodo++){
	            if(nodo < figura3VentanaTemp.size()-1){
	                tempX = figura3VentanaTemp.get(nodo+1).ptoX;
	                tempY = figura3VentanaTemp.get(nodo+1).ptoY;
	            }else{
	                tempX = orgX;
	                tempY = orgY;
	            }
	                       
	           
	           
	            if(figura3VentanaTemp.get(nodo).ptoY >= 0) actual = true;
	            else actual = false;
	           
	            if(tempY >= 0) siguiente = true;
	            else siguiente = false;
	           
	            if(actual){// El punto actual adentro
	                figura3Ventana.add(new Vertices(figura3VentanaTemp.get(nodo).ptoX, figura3VentanaTemp.get(nodo).ptoY));
	                if(!siguiente){// De adentro hacia afuera
	                    if(tempX == figura3VentanaTemp.get(nodo).ptoX){
	                        figura3Ventana.add(new Vertices(tempX, 0));
	                    }else{
	                        pendiente = (float)(tempY - figura3VentanaTemp.get(nodo).ptoY) / (tempX - figura3VentanaTemp.get(nodo).ptoX);
	                        valorB = tempY - (pendiente * tempX);
	                        figura3Ventana.add(new Vertices((-valorB / pendiente), 0));
	                    }                       
	                }
	            }else{// El punto actual afuera
	                if(siguiente){// De afuera hacia adentro
	                    if(tempX == figura3VentanaTemp.get(nodo).ptoX){
	                        figura3Ventana.add(new Vertices(tempX, 0));
	                    }else{
	                        pendiente = (float)(tempY - figura3VentanaTemp.get(nodo).ptoY) / (tempX - figura3VentanaTemp.get(nodo).ptoX);
	                        valorB = tempY - (pendiente * tempX);
	                        figura3Ventana.add(new Vertices((-valorB / pendiente), 0));
	                    }
	                }
	            }               
	        }
	    }
	   
	
	    // Actualiza los vertices encontrados
	    figura3VentanaTemp.clear();
	    for(int i = 0; i < figura3Ventana.size(); i++){
	        figura3VentanaTemp.add(new Vertices(figura3Ventana.get(i).ptoX, figura3Ventana.get(i).ptoY));
	    }
	    figura3Ventana.clear();       
	   
	   
	    //////////////////////////////// Arista Derecha //////////////////////////////////
	    for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
	        if(figura3VentanaTemp.size() != 0){               
	            orgX = figura3VentanaTemp.get(0).ptoX;
	            orgY = figura3VentanaTemp.get(0).ptoY;
	        }           
	        for(int nodo = 0; nodo < figura3VentanaTemp.size(); nodo++){
	            if(nodo < figura3VentanaTemp.size()-1){
	                tempX = figura3VentanaTemp.get(nodo+1).ptoX;
	                tempY = figura3VentanaTemp.get(nodo+1).ptoY;
	            }else{
	                tempX = orgX;
	                tempY = orgY;
	            }
	                                       
	           
	            if(figura3VentanaTemp.get(nodo).ptoX <= anchoAux) actual = true;
	            else actual = false;
	           
	            if(tempX <= anchoAux) siguiente = true;
	            else siguiente = false;
	           
	            if(actual){// El punto actual adentro
	                figura3Ventana.add(new Vertices(figura3VentanaTemp.get(nodo).ptoX, figura3VentanaTemp.get(nodo).ptoY));
	                if(!siguiente){// De adentro hacia afuera
	                    pendiente = (float)(tempY - figura3VentanaTemp.get(nodo).ptoY) / (tempX - figura3VentanaTemp.get(nodo).ptoX);
	                    valorB = tempY - (pendiente * tempX);
	                    figura3Ventana.add(new Vertices(anchoAux, (pendiente * anchoAux) + valorB));
	                }
	            }else{// El punto actual afuera
	                if(siguiente){// De afuera hacia adentro
	                    pendiente = (float)(tempY - figura3VentanaTemp.get(nodo).ptoY) / (tempX - figura3VentanaTemp.get(nodo).ptoX);
	                    valorB = tempY - (pendiente * tempX);
	                    figura3Ventana.add(new Vertices(anchoAux, (pendiente * anchoAux) + valorB));                       
	                }
	            }           
	        }
	    }
	
	    // Actualiza los vertices encontrados
	    figura3VentanaTemp.clear();
	    for(int i = 0; i < figura3Ventana.size(); i++){
	        figura3VentanaTemp.add(new Vertices(figura3Ventana.get(i).ptoX, figura3Ventana.get(i).ptoY));
	    }
	    figura3Ventana.clear();
	   
	   
	    //////////////////////////////// Arista Inferior //////////////////////////////////
	    for(int numFig = 0; numFig < orgFiguras.size(); numFig++){
	        if(figura3VentanaTemp.size() != 0){               
	            orgX = figura3VentanaTemp.get(0).ptoX;
	            orgY = figura3VentanaTemp.get(0).ptoY;
	        }       
	        for(int nodo = 0; nodo < figura3VentanaTemp.size(); nodo++){
	            if(nodo < figura3VentanaTemp.size()-1){
	                tempX = figura3VentanaTemp.get(nodo+1).ptoX;
	                tempY = figura3VentanaTemp.get(nodo+1).ptoY;
	            }else{
	                tempX = orgX;
	                tempY = orgY;
	            }
	           
	            if(figura3VentanaTemp.get(nodo).ptoY <= altoAux) actual = true;
	            else actual = false;
	           
	            if(tempY <= altoAux) siguiente = true;
	            else siguiente = false;
	           
	            if(actual){// El punto actual adentro
	                figura3Ventana.add(new Vertices(figura3VentanaTemp.get(nodo).ptoX, figura3VentanaTemp.get(nodo).ptoY));
	                if(!siguiente){// De adentro hacia afuera
	                    if(tempX == figura3VentanaTemp.get(nodo).ptoX){
	                        figura3Ventana.add(new Vertices(tempX, altoAux));
	                    }else{
	                        pendiente = (float)(tempY - figura3VentanaTemp.get(nodo).ptoY) / (tempX - figura3VentanaTemp.get(nodo).ptoX);
	                        valorB = tempY - (pendiente * tempX);
	                        figura3Ventana.add(new Vertices(((altoAux - valorB) / pendiente), altoAux));
	                    }                       
	                }
	            }else{// El punto actual afuera
	                if(siguiente){// De afuera hacia adentro
	                    if(tempX == figura3VentanaTemp.get(nodo).ptoX){
	                        figura3Ventana.add(new Vertices(tempX, altoAux));
	                    }else{
	                        pendiente = (float)(tempY - figura3VentanaTemp.get(nodo).ptoY) / (tempX - figura3VentanaTemp.get(nodo).ptoX);
	                        valorB = tempY - (pendiente * tempX);
	                        figura3Ventana.add(new Vertices(((altoAux - valorB) / pendiente), altoAux));
	                    }
	                }
	            }           
	        }
	    }   
	
	    // Actualiza los vertices encontrados
	    figura3VentanaTemp.clear();
	    for(int i = 0; i < figura3Ventana.size(); i++){
	        figura3VentanaTemp.add(new Vertices(figura3Ventana.get(i).ptoX, figura3Ventana.get(i).ptoY));
	    }
	    ////////////////////////////////////////////////////// Termina recorte poligono //////////////////////////////////////////////////////

	    
	    
        
        
        
        
        
        
        
        
        
        
        
        
    }   
    
    
    ////////////////////////////////////////////////////// Recorte de Lineas //////////////////////////////////////////////////////
    
    float ymin = 0, ymax = 0, des = 0, xmin = 0, xmax = 0;
    
    public void recortarLinea(float xi, float yi, float xf, float yf){
    	
    	float tempX = 0, tempY = 0;
    	
    	xmin = 0;
		xmax = anchoAux;
		ymin = 0;
		ymax = altoAux;
		
		des = estableceCaso(xi, yi, xf, yf);
		
		if(xi >= xmin && xi <= xmax && yi >= ymin && yi <= ymax) des = 1;
		else if(xf >= xmin && xf <= xmax && yf >= ymin && yf <= ymax){
			tempX = xi;
			xi = xf;
			xf = tempX;
			
			tempY = yi;
			yi = yf;
			yf = tempY;
			
			des = 1;
		}
		
		if(des == 1) recortaLinea1(xi, yi, xf, yf);
		else if(des == 2) recortaLinea2(xi, yi, xf, yf);
		else if(des == 3) recortaLinea3(xi, yi, xf, yf);
		else if(des == 4) recortaLinea4(xi, yi, xf, yf);
		else if(des == 5) recortaLinea5(xi, yi, xf, yf);
		else if(des == 6) recortaLinea6(xi, yi, xf, yf);
		else {}//System.out.println("Error");
    }
    
    public int estableceCaso(float x, float y, float x2, float y2){
    	    	   	
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
    
    float m = 0, m1 = 0, m2 = 0, m3 = 0, m4 = 0;// pendientes a cada vertice de la ventana
    float nx1 = 0, ny1 = 0,nx2 = 0,ny2 = 0;// auxiliares
    
    public void recortaLinea1(float x1, float y1, float x2, float y2){// Punto 1 dentro de la ventana
		
		
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
	    
//	    System.out.println(nx1 + ", " + ny1);
//	    System.out.println(nx2 + ", " + ny2);
	}
	
	public void recortaLinea2(float x1, float y1, float x2, float y2){// Punto 1 a la izquierda
		nx1 = 0;
		ny1 = 0;
		nx2 = 0;
		ny2 = 0;
		
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
	    		}else {}//System.out.println("No dibuja");
	    		
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
	    		}else {}//System.out.println("No dibuja");
	    		
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
	    		}else {}//System.out.println("No dibuja");
	    		
	    	}else{// punto 2 afuera - LB
	    		nx1=xmin;
	            ny1=(int)(y1+m*(xmin-x1));
	            ny2=ymax;
	            nx2=(int)(x1+(ymax-y1)/m);
	    	}
	    }
	    
//	    System.out.println(nx1 + ", " + ny1);
//	    System.out.println(nx2 + ", " + ny2);
	}
	
	public void recortaLinea3(float x1, float y1, float x2, float y2){// Punto 1 en la esquina
		float tm1 = 0, tm2 = 0;
		
		nx1 = 0;
		ny1 = 0;
		nx2 = 0;
		ny2 = 0;
		
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
	    
//	    System.out.println(nx1 + ", " + ny1);
//	    System.out.println(nx2 + ", " + ny2);
	}
	
	public void recortaLinea4(float x1, float y1, float x2, float y2){// Punto 1 en la parte superior
		//System.out.println("Caso 4");
		
		nx1 = 0;
		ny1 = 0;
		nx2 = 0;
		ny2 = 0;
		
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
	    		}else {}//System.out.println("No hace nada");
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
	    		}else {}//System.out.println("No hace nada");
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
	    		}else {}//System.out.println("No hace nada");
	    	}else{
	    		ny1=ymin;
                nx1=(int)(x1+(ymin-y1)/m);
                nx2=xmin;
	            ny2=(int)(y1+(xmin-x1)*m);
	    	}
	    }
//	    System.out.println(nx1 + ", " + ny1);
//	    System.out.println(nx2 + ", " + ny2);
	}
	
	public void recortaLinea5(float x1, float y1, float x2, float y2){// Punto 1 en la parte superior
		//System.out.println("Caso 5");
		
		nx1 = 0;
		ny1 = 0;
		nx2 = 0;
		ny2 = 0;
		
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
//	    System.out.println(nx1 + ", " + ny1);
//	    System.out.println(nx2 + ", " + ny2);
	}

	public void recortaLinea6(float x1, float y1, float x2, float y2){// Punto 1 en la parte superior
//		System.out.println("Caso 6");
		
		nx1 = 0;
		ny1 = 0;
		nx2 = 0;
		ny2 = 0;
		
		float tm1 = 0, tm2 = 0;
		
		int flag = 0,t;
	    tm1=((float)(ymax-y1))/(xmin-x1);
	    tm2=((float)(ymin-ymax))/(xmax-xmin); //diagonal ventana
		
//	    System.out.println(tm1 + " " + tm2);
	    
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
	    
//		System.out.println(nx1 + ", " + ny1);
//		System.out.println(nx2 + ", " + ny2);
	}
    
    
    
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
   
    public float[][] transforma(){
        return producto(producto(producto(escalacion.getMatriz(), rotacion.getMatriz()), traslacion.getMatriz()), P);
    }
     
    public void keyPressed(){
        if(keyCode == RIGHT) angulo -= 5;
        if(keyCode == LEFT) angulo += 5;
        
        if(keyCode == UP){
        	ancho += 5;
        	alto += 5;
        }
        
        if(keyCode == DOWN){
        	ancho -= 10;
        	alto -= 10;
        }
        
        if(key == 'r') angFig2 += 5;
		if(key == 't') angFig2 -= 5;
		if(key == 'f') angFig3 += 5;
		if(key == 'g') angFig3 -= 5;
    }
    
    public float[][] producto(float A[][], float B[][]){       
        float suma = 0;
        float result[][] = new float[A.length][B[0].length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B[0].length; j++){
                suma = 0;
                for(int k = 0; k < B.length; k++){
                    suma += A[i][k] * B[k][j];
                }
                result[i][j] = suma;
                //System.out.println(suma);
            }
        }
        return result;
    }
   
    public static void imprimirMatriz(double matriz[][], int i,int j){
        if(i<matriz.length){
            //System.out.print("| "+matriz[i][j]+" ");
            j++;
            if(j>=matriz[0].length){
                //System.out.println("|");
                i=i+1;
                j=0;
            }
        imprimirMatriz(matriz, i,j);
        }
    }
}