package newtonraphson;

import java.util.ArrayList;

public class NewtonRaphson {
	
	ArrayList<Double> raices = new ArrayList<Double>();
	
	public NewtonRaphson(){
		
	}
	
	public void solve(double vx, double vy, double vz){
		
		double x = 0;
		double fx = 0;
		double dfx = 0, auxdfx = 0;		
		
		double dx = (float) 0.0001;
		double Ex = (float) 0.001;
		double Ey = (float) 0.002;
		
		x = 1;
		
		boolean aux1 = true;
		boolean aux2 = true;
		int aux3 = 0, gradoEcuaccion = 4;
		int iteracionesMax = 0, iteracionesTotales = 0;//100
		while(aux2){
			aux1 = true;
			iteracionesMax = 0;
			while(aux1){
				iteracionesTotales++;
				iteracionesMax++;
				if(raices.size()>0){
					fx=(Math.pow((Math.pow(((640*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) + (310*x)/(Math.pow(x, 2) + 1)), 2) + 
							Math.pow(((1280*x)/(Math.pow(x, 2) + 1) - (155*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) - 
							(1571840*x)/(Math.pow(x, 2) + 1) + Math.pow(vz, 2) - 810621);

					for(int i=0; i<raices.size();i++){
						fx=fx/(x-raices.get(i));
					}
					
					double x1=x+dx/2;
					double x2=x-dx/2;
					double fx1=(Math.pow((Math.pow(((640*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) + (310*x1)/(Math.pow(x1, 2) + 1)), 2) + 
							Math.pow(((1280*x1)/(Math.pow(x1, 2) + 1) - (155*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) - 
							(1571840*x1)/(Math.pow(x1, 2) + 1) + Math.pow(vz, 2) - 810621);
					for(int i=0; i<raices.size();i++){
						fx1=fx1/(x1-raices.get(i));
					}
					
					double fx2=(Math.pow((Math.pow(((640*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) + (310*x2)/(Math.pow(x2, 2) + 1)), 2) + 
							Math.pow(((1280*x2)/(Math.pow(x2, 2) + 1) - (155*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) - 
							(1571840*x2)/(Math.pow(x2, 2) + 1) + Math.pow(vz, 2) - 810621);
					for(int i=0; i<raices.size();i++){
						fx2=fx2/(x2-raices.get(i));
					}
					
					dfx=(fx1-fx2)/(dx);
					if(dfx==0){
						dfx=-auxdfx;
					}
					auxdfx=dfx;					
					
					double xnext=x-(fx)/(dfx);
					
					if(Math.abs(fx)<Ey && Math.abs(xnext-x)<Ex || iteracionesMax==100){
						raices.add(xnext);
						aux1=false;
					}else{
						x=xnext;
					}
					
				} else{
					fx=(Math.pow((Math.pow(((640*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) + (310*x)/(Math.pow(x, 2) + 1)), 2) + 
							Math.pow(((1280*x)/(Math.pow(x, 2) + 1) - (155*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) - 
							(1571840*x)/(Math.pow(x, 2) + 1) + Math.pow(vz, 2) - 810621);

					double  x1=x+dx/2;
					double x2=x-dx/2;

					double fx1=(Math.pow((Math.pow(((640*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) + (310*x1)/(Math.pow(x1, 2) + 1)), 2) + 
							Math.pow(((1280*x1)/(Math.pow(x1, 2) + 1) - (155*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) - 
							(1571840*x1)/(Math.pow(x1, 2) + 1) + Math.pow(vz, 2) - 810621);
					
					double fx2=(Math.pow((Math.pow(((640*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) + (310*x2)/(Math.pow(x2, 2) + 1)), 2) + 
							Math.pow(((1280*x2)/(Math.pow(x2, 2) + 1) - (155*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) - 
							(1571840*x2)/(Math.pow(x2, 2) + 1) + Math.pow(vz, 2) - 810621);
					
					dfx=(fx1-fx2)/(dx);
					if(dfx==0){
						dfx=-auxdfx;
					}
					auxdfx=dfx;
					
					double xnext=x-(fx)/(dfx);
										
					if(Math.abs(fx)<Ey && Math.abs(xnext-x)<Ex || iteracionesMax==100){
						raices.add(xnext);
						aux1=false;
					}else{
						x=xnext;
					}
				}
				
				
			}
			aux3++;
			if(aux3==gradoEcuaccion)aux2=false;
			
		}
		
		for(int i=0; i<raices.size();i++){
			System.out.println();
			System.out.println("raiz "+i+": "+((raices.get(i))));
		}
		System.out.println("iteraciones totales "+iteracionesTotales);
		
	}

}
