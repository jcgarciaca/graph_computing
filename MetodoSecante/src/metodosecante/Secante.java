package metodosecante;

import java.lang.Math;

public class Secante {
	
	float posX = 798, posY = 372, posZ = 815;
	
	public void solve(){
		int i = 0;
		float m, u3, u3_0 = 0, inicio = 0, h = 0.5f;
		u3 = inicio + h;
		while(Math.abs(f(u3, posX, posY, posZ)) > 1000){
			m = (f(u3, posX, posY, posZ) - f(u3_0, posX, posY, posZ))/(u3 - u3_0);
			u3_0 = u3;
			u3 = u3 - f(u3, posX, posY, posZ)/m;
			if(i >= 100) break;
			i++;
			System.out.println("u3: " + u3);
		}
		System.out.println(i);
		System.out.println("Solucion: " + u3 + " f: " + f(u3, posX, posY, posZ));
	}
	
	public float f(float u3, float x, float y, float z){
//		float func = (float)(u3 - 29.2);
		float func = (float) (
				Math.pow((Math.pow(((640*(Math.pow(u3, 2) - 1))/(Math.pow(u3, 2) + 1) + (310*u3)/(Math.pow(u3, 2) + 1)), 2) + 
						Math.pow(((1280*u3)/(Math.pow(u3, 2) + 1) - (155*(Math.pow(u3, 2) - 1))/(Math.pow(u3, 2) + 1) + 614), 2) - 
						Math.pow(x, 2) - Math.pow(y, 2) - Math.pow(z, 2) + 22500), 2)/90000 + (190340*(Math.pow(u3, 2) - 1))/(Math.pow(u3, 2) + 1) - 
						(1571840*u3)/(Math.pow(u3, 2) + 1) + Math.pow(z, 2) - 810621				
				);
		System.out.println("funcion: " + func);
//		Double d2 = new Double(func);
//		if(d2.isNaN()) f(u3 + 1, x, y, z);
		return func;
		
	}

}
