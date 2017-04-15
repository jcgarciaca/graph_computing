package newtonraphson;

import java.util.ArrayList;

public class Principal {
	
//	public static void main(String[] args) {
//		NewtonRaphson cinematica = new NewtonRaphson();
//		cinematica.solve(78, 111, 1122);
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double vx=78, vy=111, vz=1122;
		
//		Raices raices= new Raices();
//		raices.NewtonRapson(vx, vy, vz);
//		for(int i=0; i<4;i++){
//		System.out.println("raiz "+i+" = "+raices.raices.get(i));
//		}
		
		ArrayList<Double> raices = new ArrayList<Double>();
		double x=0;
		double fx=0;
		double dfx=0, auxdfx=0;
		
		
		double dx=(float) 0.0001;
		double Ex=(float) 0.001;
		double Ey=(float) 0.002;
		
		x=1;
		
		boolean aux1=true;
		boolean aux2=true;
		int aux3=0, gradoEcuaccion=4;
		int iteracionesMax=0, iteracionesTotales=0;//100
		while(aux2){
//			System.out.println("entra  aux2");
			aux1=true;
//			x=-5;
			iteracionesMax=0;
			while(aux1){
				iteracionesTotales++;
				iteracionesMax++;
//				System.out.println(iteracionesMax);
				if(raices.size()>0)
				{
					System.out.println("entra a if(raices.size()>0)");
//					fx=x*x+x-2;
//					fx= (x*x*x - 9*x*x + 25*x*(1+(Math.sin(x)*Math.sin(x))/(25)) +(x)/(((1)/(Math.cos(x)))*((1)/(Math.cos(x))))-24);
					
					fx=(Math.pow((Math.pow(((640*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) + (310*x)/(Math.pow(x, 2) + 1)), 2) + 
							Math.pow(((1280*x)/(Math.pow(x, 2) + 1) - (155*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) - 
							(1571840*x)/(Math.pow(x, 2) + 1) + Math.pow(vz, 2) - 810621);

					for(int i=0; i<raices.size();i++){
						System.out.println("Raiz por la cual se divide es: "+raices.get(i));
						fx=fx/(x-raices.get(i));
					}
					System.out.println("fx = "+fx);
					
					double x1=x+dx/2;
					System.out.println("x1 = "+x1);
					double x2=x-dx/2;
					System.out.println("x2 = "+x2);
					
//					double fx1=x1*x1+x1-2;
//					double fx1= (x1*x1*x1 - 9*x1*x1 + 25*x1*(1+(Math.sin(x1)*Math.sin(x1))/(25)) +(x1)/(((1)/(Math.cos(x1)))*((1)/(Math.cos(x1))))-24);
					double fx1=(Math.pow((Math.pow(((640*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) + (310*x1)/(Math.pow(x1, 2) + 1)), 2) + 
							Math.pow(((1280*x1)/(Math.pow(x1, 2) + 1) - (155*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) - 
							(1571840*x1)/(Math.pow(x1, 2) + 1) + Math.pow(vz, 2) - 810621);
					for(int i=0; i<raices.size();i++){
						fx1=fx1/(x1-raices.get(i));
					}
					System.out.println("fx1 = "+fx1);
					
//					double fx2=x2*x2+x2-2;
//					double fx2= (x2*x2*x2 - 9*x2*x2 + 25*x2*(1+(Math.sin(x2)*Math.sin(x2))/(25)) +(x2)/(((1)/(Math.cos(x2)))*((1)/(Math.cos(x2))))-24);
					double fx2=(Math.pow((Math.pow(((640*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) + (310*x2)/(Math.pow(x2, 2) + 1)), 2) + 
							Math.pow(((1280*x2)/(Math.pow(x2, 2) + 1) - (155*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) - 
							(1571840*x2)/(Math.pow(x2, 2) + 1) + Math.pow(vz, 2) - 810621);
					for(int i=0; i<raices.size();i++){
						fx2=fx2/(x2-raices.get(i));
					}
					System.out.println("fx2 = "+fx2);
					
					dfx=(fx1-fx2)/(dx);
					if(dfx==0){
						dfx=-auxdfx;
					}
					auxdfx=dfx;
					System.out.println("dfx = "+dfx);
					
					
					double xnext=x-(fx)/(dfx);
					
					System.out.println("xnext = "+xnext);
					
					if(Math.abs(fx)<Ey && Math.abs(xnext-x)<Ex || iteracionesMax==100){
						raices.add(xnext);
						System.out.println("raiz = "+xnext);
						aux1=false;
					}else{
						x=xnext;
					}
					
				} else{
					System.out.println("entra a ==0)");
					
//					fx=x*x+x-2;
//					fx= x*x*x - 9*x*x + 25*x*(1+(Math.sin(x)*Math.sin(x))/(25)) +(x)/(((1)/(Math.cos(x)))*((1)/(Math.cos(x))))-24 ;
					fx=(Math.pow((Math.pow(((640*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) + (310*x)/(Math.pow(x, 2) + 1)), 2) + 
							Math.pow(((1280*x)/(Math.pow(x, 2) + 1) - (155*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x, 2) - 1))/(Math.pow(x, 2) + 1) - 
							(1571840*x)/(Math.pow(x, 2) + 1) + Math.pow(vz, 2) - 810621);
					System.out.println("fx = "+fx);
					double  x1=x+dx/2;
					System.out.println("x1 = "+x1);
					double x2=x-dx/2;
					System.out.println("x2 = "+x2);
//					double fx1=x1*x1+x1-2;
//					double fx2=x2*x2+x2-2;
					
//					double fx1= x1*x1*x1 - 9*x1*x1 + 25*x1*(1+(Math.sin(x1)*Math.sin(x1))/(25)) +(x1)/(((1)/(Math.cos(x1)))*((1)/(Math.cos(x1))))-24 ;
//					double fx2= x2*x2*x2 - 9*x2*x2 + 25*x2*(1+(Math.sin(x2)*Math.sin(x2))/(25)) +(x2)/(((1)/(Math.cos(x2)))*((1)/(Math.cos(x2))))-24 ;
					double fx1=(Math.pow((Math.pow(((640*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) + (310*x1)/(Math.pow(x1, 2) + 1)), 2) + 
							Math.pow(((1280*x1)/(Math.pow(x1, 2) + 1) - (155*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x1, 2) - 1))/(Math.pow(x1, 2) + 1) - 
							(1571840*x1)/(Math.pow(x1, 2) + 1) + Math.pow(vz, 2) - 810621);
					System.out.println("fx1 = "+fx1);
					double fx2=(Math.pow((Math.pow(((640*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) + (310*x2)/(Math.pow(x2, 2) + 1)), 2) + 
							Math.pow(((1280*x2)/(Math.pow(x2, 2) + 1) - (155*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) + 614), 2) - 
							Math.pow(vx, 2) - Math.pow(vy, 2) - Math.pow(vz, 2) + 22500), 2)/90000 + (190340*(Math.pow(x2, 2) - 1))/(Math.pow(x2, 2) + 1) - 
							(1571840*x2)/(Math.pow(x2, 2) + 1) + Math.pow(vz, 2) - 810621);
					System.out.println("fx2 = "+fx2);
					dfx=(fx1-fx2)/(dx);
					if(dfx==0){
						dfx=-auxdfx;
					}
					auxdfx=dfx;
					System.out.println("dfx = "+dfx);
					
					double xnext=x-(fx)/(dfx);
					System.out.println("xnext = "+xnext);
					
					if(Math.abs(fx)<Ey && Math.abs(xnext-x)<Ex || iteracionesMax==100){
						raices.add(xnext);
						System.out.println("xnext Def = "+xnext);
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
