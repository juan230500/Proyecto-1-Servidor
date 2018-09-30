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
		int c1=0;
		int c2=0;
		int c=0;
		while (c!=3) {
		Envio a=S1.escuchar();
		System.out.println(a.Shipout());
		c1=a.getXy1();
		c2=a.getXy2();
		T1.addL(c1, c2);
		System.out.println(c1+"\t"+c2);
		c++;
		}
		T1.recorrer2(c1, c2, L0, Li, 0);
		
	}

}
