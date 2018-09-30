package NET;

import com.fasterxml.jackson.core.JsonProcessingException;

import adt.List;
import geo.Linea;
import geo.Tablero;

public class principalS {

	public static void main(String[] args) throws JsonProcessingException {
		// TODO Auto-generated method stub
		 Tablero T1=new Tablero(6);
	     Linea L0=new Linea(33,33,T1);
	      List Li=new List();


	     
		Server S1=new Server(9987);
		
		Envio a1=S1.escuchar();
		String ip1=a1.getIp();
		System.out.println(a1.Shipout());
		/*Envio a2=S1.escuchar();
		String ip2=a2.getIp();
		System.out.println(ip2);*/
		System.out.println("---");
		if (a1.isInicio()) {
			System.out.println("jueguen");
			
		}
		
		Cliente C1= new Cliente(ip1,9999);
		//Cliente C2= new Cliente(ip2,9999);
		int[] a= {2,3};
		C1.enviarpc(a, a, "puto",true);
		/*C2.enviarpc(null, null, "",true);
		
		int c=0;
		while (c!=3) {
		a1=S1.escuchar();
		System.out.println(a1.Shipout());
		a2=S1.escuchar();
		System.out.println(a1.Shipout());
		System.out.println("---");
	
		}*/

	}

}
