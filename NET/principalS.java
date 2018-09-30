package NET;

import com.fasterxml.jackson.core.JsonProcessingException;

import adt.List;
import geo.Linea;
import geo.Tablero;

public class principalS {

	public static void main(String[] args) throws JsonProcessingException, InterruptedException {
		// TODO Auto-generated method stub
		 Tablero T1=new Tablero(6);
	     Linea L0=new Linea(33,33,T1);
	      List Li=new List();


	     
		Server S1=new Server(9987);
		
		Envio a1=S1.escuchar();
		String ip1=a1.getIp();
		System.out.println(ip1);
		Envio a2=S1.escuchar();
		String ip2=a2.getIp();
		System.out.println(ip2);
		System.out.println("---");
		if (a1.isInicio() && a2.isInicio()) {
			System.out.println("jueguen");
			
		}
		
		Cliente C1= new Cliente(ip1,9999);
		Cliente C2= new Cliente(ip2,9999);
		int[] a= {2,3};
		//Thread.sleep(2000);
		C1.enviarpc(a, null, "dibujo verdadero",true);
		C2.enviarpc(null, null, "dibujo falso",false);
		
		int c=0;
		C1= new Cliente(ip1,9998);
		C2= new Cliente(ip2,9998);
		while (c!=3) {
			
			while(true) {
				System.out.println("Escuchando al primero");
				a1=S1.escuchar();
				System.out.println("Jugador 1 hizo"+a1.Shipout());
				
				if (a1.getXy1()!=50)
					break;
				
				C1.enviarpc(a, null, "repita",true);
				}
			C1.enviarpc(a, null, "espere",false);
			C2.enviarpc(a, null, "El otro dibujó "+a1.getXy1(),true);
			System.out.println("---");
			
			while(true) {
				System.out.println("Escuchando al segundo");
				a2=S1.escuchar();
				System.out.println("Jugador 2 hizo"+a2.Shipout());
				if (a1.getXy1()!=50)
					break;
				C2.enviarpc(a, null, "repita",true);
			}
			C2.enviarpc(a, null, "espere",false);
			C1.enviarpc(a, null, "El otro dibujó "+a1.getXy1(),true);
			System.out.println("---");

		}

	}

}
