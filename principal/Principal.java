package principal;
import geo.*;
import adt.*;

public class Principal {
	public static void main(String[] args) {
		Tablero T1=new Tablero(3);
		Linea L1=new Linea(00,11,T1);
		Linea L2=new Linea(11,12,T1);
		Linea L3=new Linea(00,10,T1);
		Linea L4=new Linea(11,22,T1);
		T1.show2();
		
		
		Fragmento F1=new Fragmento(00,11);
	}
}
