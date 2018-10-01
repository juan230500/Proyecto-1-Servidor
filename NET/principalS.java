package NET;

import com.fasterxml.jackson.core.JsonProcessingException;

import adt.*;
import geo.*;

public class principalS {

	public static void main(String[] args) throws JsonProcessingException, InterruptedException {
		// TODO Auto-generated method stub
		Cola C=new Cola();
		Tablero T1=new Tablero(6);
	     Linea L0=new Linea(33,33,T1);
	      List Li=new List();


	    //Servidor con puerto pricipal para todas las conexiones entrantes
		Server S1=new Server(9987);
		
		//Se escucha a los dos primeros que manden Inicio=true
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
		
		//Se usan las ip y un primer puerto para dar inicio a los jugadores
		
		
		int val;
		System.out.println("$$$");
		LinkedQueue Cola=new LinkedQueue();
		while(true) {
			
			Cliente C1= new Cliente(ip1,9999);
			Cliente C2= new Cliente(ip2,9999);
			
			C1.enviarpc(null, null, "Oponente "+a2.getUser(),true,0);
			C2.enviarpc(null, null, "Oponente "+a1.getUser(),false,0);
			
			int c=0;
			//Se usan puertos distintos para responder durante el juego
			C1= new Cliente(ip1,9998);
			C2= new Cliente(ip2,9998);
			int[] a=new int[2];
			int[] b=new int[2];
			while (c!=3) {
				
				while(true) {
					System.out.println("Escuchando al primero");
					a1=S1.escuchar();
					if (a1.isInicio()) {
						System.out.println("cola"+a1.Shipout());
						Cola.enqueue(a1.getIp());
						continue;
					}
					System.out.println("Jugador 1 hizo"+a1.Shipout());
					val=T1.gen(a1.getXy1(), a1.getXy2());
					if (val!=0)
						break;
					C1.enviarpc(null, null, "repita",true,0);
					}
				
				if (val==2) {
					a[0]=(int)a1.getXy1()/10;
					b[0]=a1.getXy1()%10;
					a[1]=(int)a1.getXy2()/10;
					b[1]=a1.getXy2()%10;
					
					C1.enviarpc(a, b, "Linea",false,0);
					C2.enviarpc(a, b, "Linea",true,0);
					}
				else {
					List Li1=new List();
					Li1=T1.getFtmp().getPuntos();
					int[] posx=Li1.toarrayX();
					int[] posy=Li1.toarrayY();
					
					C1.enviarpc(posx, posy, "Dibujo",false,T1.getFtmp().getSegs()*2);
					C2.enviarpc(posx, posy, "Dibujo",true,0);
					T1.setFtmp(null);
				}
				
				System.out.println("---");
				
				while(true) {
					System.out.println("Escuchando al segundo");
					a2=S1.escuchar();
					if (a2.isInicio()) {
						System.out.println("cola"+a2.Shipout());
						Cola.enqueue(a2.getIp());
						continue;
					}
					System.out.println("Jugador 2 hizo"+a2.Shipout());
					val=T1.gen(a2.getXy1(), a2.getXy2());
					if (val!=0)
						break;
					C2.enviarpc(a, null, "repita",true,0);
				}
				
				if (val==2) {
					a[0]=(int)a2.getXy1()/10;
					b[0]=a2.getXy1()%10;
					a[1]=(int)a2.getXy2()/10;
					b[1]=a2.getXy2()%10;
					
					C2.enviarpc(a, b, "Linea",false,0);
					C1.enviarpc(a, b, "Linea",true,0);
					System.out.println("---");
				}
				else {
					List Li1=new List();
					Li1=T1.getFtmp().getPuntos();
					int[] posx=Li1.toarrayX();
					int[] posy=Li1.toarrayY();
					
					C2.enviarpc(posx, posy, "Dibujo",false,T1.getFtmp().getSegs()*2);
					C1.enviarpc(posx, posy, "Dibujo",true,0);
					T1.setFtmp(null);
				}
	
			}
			
			ip1=(String)(Cola.dequeue());
			ip2=(String)(Cola.dequeue());
			
			
		}

	}

}
