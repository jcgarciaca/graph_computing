package superficies;

import processing.core.PVector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;


public class FaceTriangle implements Comparable {
	
	float [] p1;
	float [] p2;
	float [] p3;
	int [] color;
	float xMin, xMax, yMin, yMax, zMin, zMax;
	
	public FaceTriangle(float [] p1,float [] p2,float [] p3,int [] color){
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
		this.color=color;
	}

	public float getxMin(){
		xMin=p1[0];
		if(xMin>p2[0])xMin=p2[0];
		if(xMin>p3[0])xMin=p3[0];
		return xMin;
	 }
	
	public float getxMax(){
		xMax=p1[0];
		if(xMax<p2[0])xMax=p2[0];
		if(xMax<p3[0])xMax=p3[0];
		return xMax;
	 }
	public float getyMin(){
		yMin=p1[1];
		if(yMin>p2[1])yMin=p2[1];
		if(yMin>p3[1])yMin=p3[1];
		return yMin;
	 }
	
	public float getyMax(){
		yMax=p1[1];
		if(yMax<p2[1])yMax=p2[1];
		if(yMax<p3[1])yMax=p3[1];
		return yMax;
	 }
	public float getzMin(){
		zMin=p1[2];
		if(zMin>p2[2])zMin=p2[2];
		if(zMin>p3[2])zMin=p3[2];
		return zMin;
	 }
	
	public float getzMax(){
		zMax=p1[2];
		if(zMax<p2[2])zMax=p2[2];
		if(zMax<p3[2])zMax=p3[2];
		return zMax;
	 }
	
	public float [] EcuacionPlano(){
		float [] abcd={0,0,0,0};
		float[] p1p2={p2[0]-p1[0],p2[1]-p1[1],p2[2]-p1[2]};
		float[] p1p3={p3[0]-p1[0],p3[1]-p1[1],p3[2]-p1[2]};
		float[]n={p1p2[1]*p1p3[2]-p1p3[1]*p1p2[2],-(p1p2[0]*p1p3[2]-p1p3[0]*p1p2[2]),p1p2[0]*p1p3[1]-p1p3[0]*p1p2[1]};
		float d=-n[0]*p1[0]-n[1]*p1[1]-n[2]*p1[2];
		abcd[0]=n[0];
		abcd[1]=n[1];
		abcd[2]=n[2];
		abcd[3]=d;
		return abcd;
	}
	
	public float[] pX(){// A X B = C
		float [] C={0,0,0};
		
		return C;
	}
	public int compareTo(Object arg0) {// ordena de menor a mayor
		// TODO Auto-generated method stub
		FaceTriangle faceTriangle = (FaceTriangle)arg0;
       if(this.zMin < faceTriangle.getzMin())
           return -1;
       else if(this.zMin == faceTriangle.getzMin())
           return 0;
       else
           return 1;
	}
	
}
