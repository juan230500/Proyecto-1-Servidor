package principal;
import geo.*;
import adt.*;

public class Principal {
	public static void main(String[] args) {
		/*List L1=new List();
		L1.insert(2);
		L1.insert(5);
		L1.insert(4);
		L1.insert(3);
		L1.print();
		L1.recortar(0,3);
		L1.print();*/
		
		Tablero T1=new Tablero(4);
		Linea L0=new Linea(33,33,T1);
		List Li=new List();
		
		
		Linea L1=new Linea(00,10,T1);
		Linea L2=new Linea(10,20,T1);
		Linea L3=new Linea(20,21,T1);
		Linea L4=new Linea(21,11,T1);
		Linea L5=new Linea(11,01,T1);
		Linea L6=new Linea(11,20,T1);
		T1.recorrido(11,11, Li, L0, 0);
		
		Linea L7=new Linea(01,00,T1);
		T1.recorrido(01,01, Li, L0, 0);
		
		/*Linea L8=new Linea(12,11,T1);*/
		/*Linea L9=new Linea(02,11,T1);*/
		T1.show2();
		//T1.recorrido(10,10, Li, L0, 0);
		//T1.recorrido(11, Li, L0, 0);
		
		
		
		
		//Se pueden imprimir solo los vértices de todas las figuras
		//Se prueba la identificación de figuras
		Node tmp=T1.getFiguras().getFirst();
		
		while (tmp!=null) {
		((Figura)tmp.getInfo()).vertices().print();
		System.out.println(((Figura)tmp.getInfo()).getArea());
		tmp=tmp.getNext();
		}
	
	
		
		
		
	}

}
