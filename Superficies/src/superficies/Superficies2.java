package superficies;

import java.util.ArrayList;

import processing.core.*;
import remixlab.proscene.*;
import saito.objloader.*;


public class Superficies2 extends PApplet{
	
	Scene scene, camScene;
	PGraphics canvas, auxCanvas;
	OBJModel model, model2, model3;
	
	ArrayList<Triangulos> puntos = new ArrayList<Triangulos>();
	int[] color1 = {0, 0, 255}, color2 = {128, 128, 0}, color3 = {200, 128, 90};

	public void setup(){
		size(600, 600, P3D);
		
		canvas = createGraphics(600, 300, P3D);	
		scene = new Scene(this, (PGraphics3D) canvas);	
		scene.setAxisIsDrawn(false);	
		scene.setGridIsDrawn(false);	
		scene.setRadius(110);	
		scene.showAll();		
		
		auxCanvas = createGraphics(600, 300, P3D);	
		camScene = new Scene(this, (PGraphics3D) auxCanvas, 0, canvas.height);	
		camScene.setAxisIsDrawn(false);	
		camScene.setGridIsDrawn(false);	
		camScene.setRadius(50);	
//		model = new OBJModel(this, "BaseMesaRobot.obj", "absolute", TRIANGLES);
		
		
		canvas.hint(DISABLE_DEPTH_TEST);
		auxCanvas.hint(DISABLE_DEPTH_TEST);
		
		model = new OBJModel(this, "MesaPC.obj", "absolute", TRIANGLES);
		
		model2 = new OBJModel(this, "base.obj", "absolute", TRIANGLES);
//		model2.translate(new PVector(80, 80, 0));
		
		model3 = new OBJModel(this, "BaseMesaRobot.obj", "absolute", TRIANGLES);
		
		model3.translate(new PVector(80, 80, 0));
		
//		model3.translate(new PVector(0, 0, -320));
				
		model.scale(1);
		model2.scale(1);
		model3.scale(1);
	
	}

	public void draw(){
		
		handleMouse();
		
		puntos.clear();
		
		canvas.beginDraw();	
		canvas.lights();	
		canvas.background(200);	
		scene.beginDraw();	
		canvas.noStroke();	
		canvas.fill(color1[0], color1[1], color1[2]);
	
		for (int k = 0; k < model.getFaceCount(); k++) {
			PVector[] faceVertices = model.getFaceVertices(k);	
			canvas.beginShape(TRIANGLES);	
			for (int i = 0; i < faceVertices.length; i++){		
				canvas.vertex(faceVertices[i].x, faceVertices[i].y, faceVertices[i].z);				
			}			
			canvas.endShape();	
		}		
		
//		canvas.translate(80, 80);
		
		canvas.fill(color2[0], color2[1], color2[2]);
		for (int k = 0; k < model2.getFaceCount(); k++) {
			PVector[] faceVertices = model2.getFaceVertices(k);	
			canvas.beginShape(TRIANGLES);	
			for (int i = 0; i < faceVertices.length; i++){		
				canvas.vertex(faceVertices[i].x, faceVertices[i].y, faceVertices[i].z);
			}			
			canvas.endShape();	
		}
		
		canvas.fill(color3[0], color3[1], color3[2]);
		for (int k = 0; k < model3.getFaceCount(); k++) {
			PVector[] faceVertices = model3.getFaceVertices(k);	
			canvas.beginShape(TRIANGLES);	
			for (int i = 0; i < faceVertices.length; i++){		
				canvas.vertex(faceVertices[i].x, faceVertices[i].y, faceVertices[i].z);				
			}
			canvas.endShape();	
		}
		
		scene.endDraw();	
		canvas.endDraw();	
		image(canvas, 0, 0);
		
		
		// Dibuja la vista de la camara		
		auxCanvas.beginDraw();	
		auxCanvas.lights();	
		auxCanvas.background(150);	
		camScene.beginDraw();	
		auxCanvas.noStroke();
		
		for (int i = 0; i < model.getFaceCount(); i++) {
			PVector[] faceVerticesWorld =model.getFaceVertices(i);
			float [] vert1={scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).z};
			float [] vert2={scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).z};
			float [] vert3={scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).z};
			puntos.add(new Triangulos(vert1, vert2, vert3, color1));
		}
		
//		auxCanvas.translate(80, 80);
		
//		auxCanvas.translate(scene.camera().cameraCoordinatesOf(new PVector(80, 80, 0)).x, 
//				scene.camera().cameraCoordinatesOf(new PVector(80, 80, 0)).y);
				
		for (int i = 0; i < model2.getFaceCount(); i++) {
			PVector[] faceVerticesWorld =model2.getFaceVertices(i);
			float [] vert1={scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).z};
			float [] vert2={scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).z};
			float [] vert3={scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).z};
			puntos.add(new Triangulos(vert1, vert2, vert3, color2));
		}
		
		
		for (int i = 0; i < model3.getFaceCount(); i++) {
			PVector[] faceVerticesWorld =model3.getFaceVertices(i);
			float [] vert1={scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[0]).z};
			float [] vert2={scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[1]).z};
			float [] vert3={scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).x, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).y, scene.camera().cameraCoordinatesOf(faceVerticesWorld[2]).z};
			puntos.add(new Triangulos(vert1, vert2, vert3, color3));
		}
		
		organizar();
		
		algoritmoPintor2();
		
//		algoritmoPintor();
		
		for(int i = 0; i < puntos.size(); i++){
			auxCanvas.fill(puntos.get(i).color[0], puntos.get(i).color[1], puntos.get(i).color[2]);
			auxCanvas.beginShape(TRIANGLES);
			auxCanvas.vertex(puntos.get(i).vert1[0],
					puntos.get(i).vert1[1],
					puntos.get(i).vert1[2]);
			auxCanvas.vertex(puntos.get(i).vert2[0],
					puntos.get(i).vert2[1],
					puntos.get(i).vert2[2]);
			auxCanvas.vertex(puntos.get(i).vert3[0],
					puntos.get(i).vert3[1],
					puntos.get(i).vert3[2]);
			auxCanvas.endShape();
		}		
		camScene.endDraw();	
		auxCanvas.endDraw();	
		image(auxCanvas, 0, canvas.height);
	}
	
	public void algoritmoPintor2(){
		boolean prueba;
		Triangulos aux;
		for (int i = 0; i < puntos.size(); i++) {
            for (int j = i; j < puntos.size(); j++) {
                prueba = false;
                //Restriccion 1 no se translapa en X
                if(puntos.get(i).xMax <= puntos.get(j).xMin){
                    prueba=true;
                //Restriccion 1 no se translapa en Y
                }else{ 
                    if(puntos.get(i).yMax <= puntos.get(j).yMin){
                        prueba = true;
                    //S está detrás del plano de S' 
                    }else{
                    	
                    	if(
                    		puntos.get(j).aV * puntos.get(i).vert1[0] +
                            puntos.get(j).bV * puntos.get(i).vert1[1] +
                            puntos.get(j).cV * puntos.get(i).vert1[2] +
                            puntos.get(j).dV <= 0 &&
                        
                    		puntos.get(j).aV * puntos.get(i).vert2[0] +
                            puntos.get(j).bV * puntos.get(i).vert2[1] +
                            puntos.get(j).cV * puntos.get(i).vert2[2] +
                            puntos.get(j).dV <= 0 &&
                        
                    		puntos.get(j).aV * puntos.get(i).vert3[0] +
                            puntos.get(j).bV * puntos.get(i).vert3[1] +
                            puntos.get(j).cV * puntos.get(i).vert3[2] +
                            puntos.get(j).dV <= 0 
                        
                        )
                        {
                            prueba = true;
                        }
                        //S' está delante del plano de S
                        else{
                            if(
                    		puntos.get(i).aV * puntos.get(j).vert1[0] +
                            puntos.get(i).bV * puntos.get(j).vert1[1] +
                            puntos.get(i).cV * puntos.get(j).vert1[2] +
                            puntos.get(i).dV >= 0 &&
                        
                    		puntos.get(i).aV * puntos.get(j).vert2[0] +
                            puntos.get(i).bV * puntos.get(j).vert2[1] +
                            puntos.get(i).cV * puntos.get(j).vert2[2] +
                            puntos.get(i).dV >= 0 &&
                        
                    		puntos.get(i).aV * puntos.get(j).vert3[0] +
                            puntos.get(i).bV * puntos.get(j).vert3[1] +
                            puntos.get(i).cV * puntos.get(j).vert3[2] +
                            puntos.get(i).dV >= 0 
                            )
                            {
                                prueba = true;
                            }
                        }
                    }
                }

                if(!prueba){
                    aux = puntos.get(i);
                    puntos.set(i, puntos.get(j));
                    puntos.set(j, aux);
                    j=puntos.size();
                }

            }
		}
	}
	
	public void algoritmoPintor(){
		int pos=0, intercambiar=0;// variable de recursividad
		System.out.println("Tama–o total: "+puntos.size());
		for(int k=0; k<puntos.size();k++){
//			System.out.println("entr— en k");
			for(int i=k; i<puntos.size();i++){
//				System.out.println("entr— en i");
//				System.out.println("entr—");
				
				if(verificacion(puntos.get(k), puntos.get(i))){
					System.out.println("intercambiar la posici—n "+k+" con la posici—n "+i);
					Triangulos aux=puntos.get(k);
					puntos.set(k,puntos.get(i));
					puntos.set(i,aux);
					i=puntos.size();
				}
			}

		}
	}
	
	public boolean verificacion(Triangulos s, Triangulos sp){
		boolean resultado=false;
		int aux=0; // aumenta en 1 cuando es verdadera alguna de las condiciones
		
		// COND 1: Las superficies se traslapan  en x y/o en y
		if((s.getXMax()>sp.getXMin() && s.getXMax()<sp.getXMax()) || (s.getXMin()>sp.getXMin() && s.getXMin()<sp.getXMax()) || 
		   (sp.getXMax()>s.getXMin() && sp.getXMax()<s.getXMax()) || (sp.getXMin()>s.getXMin() && sp.getXMin()<s.getXMax()) ||
		   (s.getYMax()>sp.getYMin() && s.getYMax()<sp.getYMax()) || (s.getYMin()>sp.getYMin() && s.getYMin()<sp.getYMax()) || 
		   (sp.getYMax()>s.getYMin() && sp.getYMax()<s.getYMax()) || (sp.getYMin()>s.getYMin() && sp.getYMin()<s.getYMax()) ){
			aux++;
		}
		
		
		// se determina la ecuaci—n del plano para cada uno de los triangulos
		float[] eqs = s.ecuacionPlano();
		float[] eqsp = sp.ecuacionPlano();	
		
		// COND 2:  s no est‡ por completo detr‡s de ps
		// se determina si los puntos de s estan delante de sp ()con la ecuacion de sp
		// s’ hay uno o m‡s, se aumenta aux2-> aux++

		int aux2=0;// cuenta los puntos del tringulo s que estan por delante de sp
		float zsp=0;// es el valor en z de la proyeccion de los vertices de s en sp
		zsp=(-eqsp[3]-eqsp[1]*s.vert1[1]-eqsp[0]*s.vert1[0])/(eqsp[2]);
		if(zsp<s.vert1[2])aux2++;// s estar’a por delante de sp
		zsp=(-eqsp[3]-eqsp[1]*s.vert2[1]-eqsp[0]*s.vert2[0])/(eqsp[2]);
		if(zsp<s.vert2[2])aux2++;// s estar’a por delante de sp
		zsp=(-eqsp[3]-eqsp[1]*s.vert3[1]-eqsp[0]*s.vert3[0])/(eqsp[2]);
		if(zsp<s.vert3[2])aux2++;// s estar’a por delante de sp
		if(aux2>0)aux++;
		
		//COND 3:  sp no est‡ por completo en frente de s
		// se determina si los puntos de sp estan detr‡s de s, con la ecuacion de s
		// s’ hay uno o m‡s, se aumenta aux3-> aux++
		int aux3=0;//cuenta los puntos del tringulo sp que estan por detr‡s de sp
		float zs=0;// es el valor en z de la proyeccion de los vertices de s en sp
		zs=(-eqs[3]-eqs[1]*sp.vert1[1]-eqs[0]*sp.vert1[0])/(eqs[2]);
		if(zs>sp.vert1[2])aux3++;// s estar’a por delante de sp
		zs=(-eqs[3]-eqs[1]*sp.vert2[1]-eqs[0]*sp.vert2[0])/(eqs[2]);
		if(zs>sp.vert2[2])aux3++;// s estar’a por delante de sp
		zs=(-eqs[3]-eqs[1]*sp.vert3[1]-eqs[0]*sp.vert3[0])/(eqs[2]);
		if(zs>sp.vert3[2])aux3++;// s estar’a por delante de sp
		if(aux3>0)aux++;
		
		if(aux==3) {resultado=true;}// indica que se cumplieron todas las condiciones para que se intercambien las posiciones
		
		return resultado;
	}
				
	public void organizar(){
		for (int i = 0; i < puntos.size(); i++) {             
            for (int j = 0; j < puntos.size() - 1; j++) {                  
            	if (puntos.get(j).getZMin() > puntos.get(j+1).getZMin()) {//if (puntos.get(j).z > puntos.get(j+1).z) {
                     intercambiar(j);
                 }
            }
        }
	}
	
	
	public void intercambiar(int indice) {
        Triangulos trianTemp = puntos.get(indice);
        puntos.set(indice, puntos.get(indice + 1));
        puntos.set(indice + 1, trianTemp);
    }
	
	public void handleMouse() {
		if (mouseY < canvas.height) {
		    scene.enableMouseHandling();
		    scene.enableKeyboardHandling();
		    camScene.disableMouseHandling();
		    camScene.disableKeyboardHandling();
		}else{
			scene.disableMouseHandling();
			scene.disableKeyboardHandling();
			camScene.enableMouseHandling();
			camScene.enableKeyboardHandling();
		}		  
	}

}