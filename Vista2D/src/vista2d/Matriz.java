package vista2d;

public class Matriz {
	
	float[][] transformacion = new float[3][3];
	
	public Matriz(float valorX, float valorY, int ind){
		if(ind == 0){// Traslación
			transformacion[0][0] = 1;
			transformacion[0][1] = 0;
			transformacion[0][2] = valorX;
			transformacion[1][0] = 0;
			transformacion[1][1] = 1;
			transformacion[1][2] = valorY;
			transformacion[2][0] = 0;
			transformacion[2][1] = 0;
			transformacion[2][2] = 1;
		}else if(ind == 1){// Escalamiento
			transformacion[0][0] = valorX;
			transformacion[0][1] = 0;
			transformacion[0][2] = 0;
			transformacion[1][0] = 0;
			transformacion[1][1] = valorY;
			transformacion[1][2] = 0;
			transformacion[2][0] = 0;
			transformacion[2][1] = 0;
			transformacion[2][2] = 1;
		}else System.out.println("Error en la matriz");
	}
	
	public Matriz(float Beta){// Rotacion
		transformacion[0][0] = (float)Math.cos(Beta);
		transformacion[0][1] = (float)-Math.sin(Beta);
		transformacion[0][2] = 0;
		transformacion[1][0] = (float)Math.sin(Beta);
		transformacion[1][1] = (float)Math.cos(Beta);
		transformacion[1][2] = 0;
		transformacion[2][0] = 0;
		transformacion[2][1] = 0;
		transformacion[2][2] = 1;
	}
	
	public void changeMat(float Beta){
		transformacion[0][0] = (float)Math.cos(Beta);
		transformacion[0][1] = (float)-Math.sin(Beta);
		transformacion[1][0] = (float)Math.sin(Beta);
		transformacion[1][1] = (float)Math.cos(Beta);
	}
	
	public void changeMat(float valorX, float valorY, int ind){
		if(ind == 0){
			transformacion[0][2] = valorX;
			transformacion[1][2] = valorY;
		}else if(ind == 1){
			transformacion[0][0] = valorX;
			transformacion[1][1] = valorY;
		}else System.out.println("Error en cambio de matriz");
	}
	
	public float[][] getMatriz(){
		return transformacion;
	}

}
