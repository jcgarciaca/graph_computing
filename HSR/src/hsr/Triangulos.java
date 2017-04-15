package hsr;

public class Triangulos {
	
	float[] vert1, vert2, vert3;
	int[] color;
	float zMin = 0, xMin = 0, yMin = 0;
	float zMax = 0, xMax = 0, yMax = 0;
	float aV = 0, bV = 0, cV = 0, dV = 0;
	
	public Triangulos(float[] vert1, float[] vert2, float[] vert3, int[] color){
		this.vert1 = vert1;
		this.vert2 = vert2;
		this.vert3 = vert3;
		this.color = color;
	}	
		
	public float getXMin(){
		xMin = vert1[0];
		if(vert2[0] < xMin) xMin = vert2[0];
		if(vert3[0] < xMin) xMin = vert3[0];
		return xMin;
	}
	
	public float getYMin(){
		yMin = vert1[1];
		if(vert2[1] < yMin) yMin = vert2[1];
		if(vert3[1] < yMin) yMin = vert3[1];
		return yMin;
	}
	
	public float getZMin(){
		zMin = vert1[2];
		if(vert2[2] < zMin) zMin = vert2[2];
		if(vert3[2] < zMin) zMin = vert3[2];
		return zMin;
	}
	
	public float getXMax(){
		xMax = vert1[0];
		if(vert2[0] > xMax) xMax = vert2[0];
		if(vert3[0] > xMax) xMax = vert3[0];
		return xMax;
	}
	
	public float getYMax(){
		yMax = vert1[1];
		if(vert2[1] > yMax) yMax = vert2[1];
		if(vert3[1] > yMax) yMax = vert3[1];
		return yMax;
	}
	
	public float getZMax(){
		zMax = vert1[2];
		if(vert2[2] > zMax) zMax = vert2[2];
		if(vert3[2] > zMax) zMax = vert3[2];
		return zMax;
	}
	
	public float [] ecuacionPlano(){
		float [] abcd={0,0,0,0};
		float[] p1p2={vert2[0]-vert1[0],vert2[1]-vert1[1],vert2[2]-vert1[2]};
		float[] p1p3={vert3[0]-vert1[0],vert3[1]-vert1[1],vert3[2]-vert1[2]};
		float[]n={p1p2[1]*p1p3[2]-p1p3[1]*p1p2[2],-(p1p2[0]*p1p3[2]-p1p3[0]*p1p2[2]),p1p2[0]*p1p3[1]-p1p3[0]*p1p2[1]};
		float d=-n[0]*vert1[0]-n[1]*vert1[1]-n[2]*vert1[2];
		abcd[0]=n[0];
		abcd[1]=n[1];
		abcd[2]=n[2];
		abcd[3]=d;
		
		aV = abcd[0];
		bV = abcd[1];
		cV = abcd[2];
		dV = abcd[3];
		return abcd;
	}
}
