package principal;
import geo.*;
import adt.*;

public class Principal {
	public static void main(String[] args) {
		Tablero T1=new Tablero(4);
		Linea L1=new Linea(00,11,T1);
		Linea L2=new Linea(11,10,T1);
		Linea L3=new Linea(10,00,T1);
		Linea L4=new Linea(22,00,T1);
		Linea L0=new Linea(22,11,T1);
		Linea L6=new Linea(22,33,T1);
		List Li=new List();
		T1.show2();
		T1.recorrido(22, Li, L4, 0);
		
		
	}
}
