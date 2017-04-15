package superficies;
import java.util.ArrayList;
import java.util.Collections;

import processing.core.*;
import remixlab.proscene.*;
import saito.objloader.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import superficies.FaceTriangle;

public class Superficie2 extends PApplet{
	
	Scene sceneMain, sceneCamera;
	PGraphics canvasMain, canvasCamera;
	
	OBJModel model1, model2, model3;
	
	// lista de colores de los diferentes modelos de la escena
	int[] color1={255,0,255};
	int[] color2={0,0,255};
	int[] color3={255,255,0};
	
	ArrayList<FaceTriangle> faces=new ArrayList<FaceTriangle>();// aqu’ estan todas las caras de los objetos OBJ
	
	public void setup(){
		
		size(1000, 500,P3D);
		
		// se cargan los OBJ
		model1 = new OBJModel(this, "MesaPC.obj", "absolute", TRIANGLES);
		model1.scale(1);
//		System.out.println(model.getFaceCount());
		model2 = new OBJModel(this, "base.obj", "absolute", TRIANGLES);
		model2.scale(1);
		model2.translate(new PVector (80,80,80));
//		System.out.println(model.getFaceCount());
		model3 = new OBJModel(this, "BaseMesaRobot.obj", "absolute", TRIANGLES);
		model3.scale(1);
		model3.translate(new PVector (100,100,100));
//		System.out.println(model.getFaceCount());
		
		
		hint(DISABLE_DEPTH_TEST);// deshabilita  el z buffer
		canvasMain = createGraphics(500, 500, P3D);
		sceneMain = new Scene(this, (PGraphics3D) canvasMain);
		sceneMain.setAxisIsDrawn(false);
		sceneMain.setGridIsDrawn(false);
		sceneMain.setRadius(0);
		sceneMain.showAll();
		  // press 'f' to display frame selection hints
		sceneMain.setShortcut('f', Scene.KeyboardAction.DRAW_FRAME_SELECTION_HINT);
		
		canvasCamera = createGraphics(500, 500, P3D);
		sceneCamera = new Scene(this, (PGraphics3D) canvasCamera, 500, 0);
		sceneCamera.setAxisIsDrawn(false);
		sceneCamera.setGridIsDrawn(false);
		sceneCamera.setRadius(0);

		canvasMain.hint(DISABLE_DEPTH_TEST);
		canvasCamera.hint(DISABLE_DEPTH_TEST);
		
		
		
		
	}
	
	public void draw(){
		handleMouse();
		
		canvasMain.beginDraw();
		canvasMain.lights();
		canvasMain.background(200);
		sceneMain.beginDraw();
		canvasMain.noStroke();
		
		canvasMain.fill(color1[0],color1[1], color1[2]);
		for (int k = 0; k < model1.getFaceCount(); k++) {
			PVector[] faceVertices =model1.getFaceVertices(k);
			canvasMain.beginShape(TRIANGLES);
			for (int i = 0; i < faceVertices.length; i++){
				canvasMain.vertex(faceVertices[i].x, faceVertices[i].y, faceVertices[i].z);
			}
			canvasMain.endShape();
		}
		
		canvasMain.fill(color2[0],color2[1], color2[2]);
		for (int k = 0; k < model2.getFaceCount(); k++) {
			PVector[] faceVertices =model2.getFaceVertices(k);
			canvasMain.beginShape(TRIANGLES);
			for (int i = 0; i < faceVertices.length; i++){
				canvasMain.vertex(faceVertices[i].x, faceVertices[i].y, faceVertices[i].z);
			}
			canvasMain.endShape();
		}
		
//		canvasMain.fill(color3[0],color3[1], color3[2]);
//		for (int k = 0; k < model3.getFaceCount(); k++) {
//			PVector[] faceVertices =model3.getFaceVertices(k);
//			canvasMain.beginShape(TRIANGLES);
//			for (int i = 0; i < faceVertices.length; i++){
//				canvasMain.vertex(faceVertices[i].x, faceVertices[i].y, faceVertices[i].z);
//			}
//			canvasMain.endShape();
//		}
		
		sceneMain.endDraw();
		canvasMain.endDraw();
		image(canvasMain, 0, 0);
		
		// dibujar lo visto por la camara
		canvasCamera.beginDraw();
		canvasCamera.lights();
		canvasCamera.background(150);
		sceneCamera.beginDraw();
		canvasCamera.noStroke();
		canvasCamera.fill(0, 0, 255);
		
		for (int k = 0; k < model1.getFaceCount(); k++) {
			PVector[] faceVerticesWorld =model1.getFaceVertices(k);
			float [] p1={sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[0]).x,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[0]).y,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[0]).z};
			float [] p2={sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[1]).x,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[1]).y,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[1]).z};
			float [] p3={sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[2]).x,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[2]).y,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[2]).z};
			FaceTriangle faceTriangle=new FaceTriangle(p1,p2,p3,color1);
			faces.add(faceTriangle);
		}
		
		
		for (int k = 0; k < model2.getFaceCount(); k++) {
			PVector[] faceVerticesWorld =model2.getFaceVertices(k);
			float [] p1={sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[0]).x,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[0]).y,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[0]).z};
			float [] p2={sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[1]).x,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[1]).y,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[1]).z};
			float [] p3={sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[2]).x,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[2]).y,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[2]).z};
			FaceTriangle faceTriangle=new FaceTriangle(p1,p2,p3,color2);
			faces.add(faceTriangle);
		}
		
//		for (int k = 0; k < model3.getFaceCount(); k++) {
//			PVector[] faceVerticesWorld =model3.getFaceVertices(k);
//			float [] p1={sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[0]).x,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[0]).y,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[0]).z};
//			float [] p2={sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[1]).x,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[1]).y,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[1]).z};
//			float [] p3={sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[2]).x,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[2]).y,sceneMain.camera().cameraCoordinatesOf(faceVerticesWorld[2]).z};
//			FaceTriangle faceTriangle=new FaceTriangle(p1,p2,p3,color3);
//			faces.add(faceTriangle);
//		}
		
		Collections.sort(faces);// arregla el arreglo faces en f(zMin) de menor a mayor
		
//		int pos=0, intercambiar=0;// variable de recursividad
//		System.out.println("Tama–o total: "+faces.size());
//		for(int k=0; k<faces.size();k++){
////			System.out.println("entr— en k");
//			for(int i=k; i<faces.size();i++){
////				System.out.println("entr— en i");
////				System.out.println("entr—");
//				if(verficacion(faces.get(k),faces.get(i))==true){// verfica si es necesario intercambiar las posiciones
//						System.out.println("intercambiar la posici—n "+k+" con la posici—n "+i);
//						FaceTriangle aux=faces.get(k);
//						faces.set(k,faces.get(i));
//						faces.set(i,aux);
//						i=faces.size();
//				}
////				System.out.println("Posici—n: "+k);
//			}
//
//		}
		
		for(int k=0; k<faces.size();k++){// dibuja las caras desde la m‡s profunda
			canvasCamera.fill(faces.get(k).color[0],faces.get(k).color[1],faces.get(k).color[2]);
			canvasCamera.beginShape(TRIANGLES);
				canvasCamera.vertex(faces.get(k).p1[0],faces.get(k).p1[1],faces.get(k).p1[2]);
				canvasCamera.vertex(faces.get(k).p2[0],faces.get(k).p2[1],faces.get(k).p2[2]);
				canvasCamera.vertex(faces.get(k).p3[0],faces.get(k).p3[1],faces.get(k).p3[2]);
			canvasCamera.endShape();
		}
		
		sceneCamera.endDraw();
		canvasCamera.endDraw();
		image(canvasCamera, 500, 0);
		faces.clear();
	}// end draw
	
	public boolean verficacion(FaceTriangle s, FaceTriangle sp){// true: intercambia posici—n: false: queda igual
		boolean resultado=false;
		int aux=0; // aumenta en 1 cuando es verdadera alguna de las condiciones
		
		// COND 1: Las superficies se traslapan  en x y/o en y
		if((s.getxMax()>sp.getxMin() && s.getxMax()<sp.getxMax()) || (s.getxMin()>sp.getxMin() && s.getxMin()<sp.getxMax()) || 
		   (sp.getxMax()>s.getxMin() && sp.getxMax()<s.getxMax()) || (sp.getxMin()>s.getxMin() && sp.getxMin()<s.getxMax()) ||
		   (s.getyMax()>sp.getyMin() && s.getyMax()<sp.getyMax()) || (s.getyMin()>sp.getyMin() && s.getyMin()<sp.getyMax()) || 
		   (sp.getyMax()>s.getyMin() && sp.getyMax()<s.getyMax()) || (sp.getyMin()>s.getyMin() && sp.getyMin()<s.getyMax()) ){
			aux++;
		}
		
		
		// se determina la ecuaci—n del plano para cada uno de los triangulos
		float[] eqs=s.EcuacionPlano();
		float[] eqsp=sp.EcuacionPlano();	
		
		// COND 2:  s no est‡ por completo detr‡s de ps
		// se determina si los puntos de s estan delante de sp ()con la ecuacion de sp
		// s’ hay uno o m‡s, se aumenta aux2-> aux++

		int aux2=0;// cuenta los puntos del tringulo s que estan por delante de sp
		float zsp=0;// es el valor en z de la proyeccion de los vertices de s en sp
		zsp=(-eqsp[3]-eqsp[1]*s.p1[1]-eqsp[0]*s.p1[0])/(eqsp[2]);
		if(zsp<s.p1[2])aux2++;// s estar’a por delante de sp
		zsp=(-eqsp[3]-eqsp[1]*s.p2[1]-eqsp[0]*s.p2[0])/(eqsp[2]);
		if(zsp<s.p2[2])aux2++;// s estar’a por delante de sp
		zsp=(-eqsp[3]-eqsp[1]*s.p3[1]-eqsp[0]*s.p3[0])/(eqsp[2]);
		if(zsp<s.p3[2])aux2++;// s estar’a por delante de sp
		if(aux2>0)aux++;
		
		//COND 3:  sp no est‡ por completo en frente de s
		// se determina si los puntos de sp estan detr‡s de s, con la ecuacion de s
		// s’ hay uno o m‡s, se aumenta aux3-> aux++
		int aux3=0;//cuenta los puntos del tringulo sp que estan por detr‡s de sp
		float zs=0;// es el valor en z de la proyeccion de los vertices de s en sp
		zs=(-eqs[3]-eqs[1]*sp.p1[1]-eqs[0]*sp.p1[0])/(eqs[2]);
		if(zs>sp.p1[2])aux3++;// s estar’a por delante de sp
		zs=(-eqs[3]-eqs[1]*sp.p2[1]-eqs[0]*sp.p2[0])/(eqs[2]);
		if(zs>sp.p2[2])aux3++;// s estar’a por delante de sp
		zs=(-eqs[3]-eqs[1]*sp.p3[1]-eqs[0]*sp.p3[0])/(eqs[2]);
		if(zs>sp.p3[2])aux3++;// s estar’a por delante de sp
		if(aux3>0)aux++;
		
		if(aux==3) {resultado=true;}// indica que se cumplieron todas las condiciones para que se intercambien las posiciones
		
		return resultado;
	}
	
	public void handleMouse() {
		  if (mouseX < canvasMain.height) {
			  sceneMain.enableMouseHandling();
//			  sceneMain.enableKeyboardHandling();
			  sceneCamera.disableMouseHandling();
//			  sceneCamera.disableKeyboardHandling();
		  } 
		  else {
			  sceneMain.disableMouseHandling();
//			  sceneMain.enableKeyboardHandling();
			  sceneCamera.enableMouseHandling();
//			  sceneCamera.enableKeyboardHandling();
		  }
	}

}