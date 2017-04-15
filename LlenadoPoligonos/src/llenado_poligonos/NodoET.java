package llenado_poligonos;

public class NodoET {
	
	int yMax = 0, xMin = 0, yMin = 0;
	double invM = 0, valXMin = 0;
	
	public NodoET(int yMax, int xMin, double invM, int yMin){
		this.yMax = yMax;
		this.xMin = xMin;
		this.invM = invM;
		this.yMin = yMin;
		valXMin = xMin;
	}
	
	public int get_yMax(){
		return yMax;
	}
	
//	public int get_xMin(){
//		return xMin;
//	}
	
	public double get_invM(){
		return invM;
	}
	
	public void set_xMin(int xMin){
		this.xMin = xMin;
	}
	
	public void set_valXMin(double valXMin){
		this.valXMin = valXMin;
	}

}


