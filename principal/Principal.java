package principal;
import geo.*;
import adt.*;

public class Principal {
	public static void main(String[] args) {
		Tablero T1=new Tablero(4);
		List Frags=new List();
		Linea L1=new Linea(00,11,T1,Frags);
		Linea L2=new Linea(11,12,T1,Frags);
		Linea L3=new Linea(00,10,T1,Frags);
		Linea L4=new Linea(11,22,T1,Frags);
		Linea L5=new Linea(33,22,T1,Frags);
		Linea L6=new Linea(22,21,T1,Frags);
		T1.show2();
		Frags.showF();
		Fragmento F1=new Fragmento(00,11,Frags);
	}
}
