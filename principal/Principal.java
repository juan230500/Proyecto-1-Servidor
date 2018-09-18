package principal;
import geo.*;
import adt.*;

public class Principal {
	public static void main(String[] args) {
		Tablero T1=new Tablero(4);
		Linea L1=new Linea(10,20,T1);
		Linea L2=new Linea(20,31,T1);
		Linea L3=new Linea(31,22,T1);
		Linea L4=new Linea(22,12,T1);
		Linea L5=new Linea(12,01,T1);
		Linea L6=new Linea(01,10,T1);
		/*Linea L7=new Linea(01,02,T1);
		Linea L8=new Linea(02,11,T1);
		Linea L9=new Linea(11,01,T1);*/
		Linea L0=new Linea(33,33,T1);
		List Li=new List();
		T1.show2();
		T1.recorrido(10, Li, L0, 0);
		
		
		
		//Se pueden imprimir solo los vértices de todas las figuras
		//Se prueba la identificación de figuras
		Node tmp=T1.getFiguras().getFirst();
		
		while (tmp!=null) {
		((Figura)tmp.getInfo()).vertices().print();
		((Figura)tmp.getInfo()).forma();
		tmp=tmp.getNext();
		}
		
	
		
		
		
	}
}
