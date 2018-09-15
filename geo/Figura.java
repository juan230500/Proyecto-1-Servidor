package geo;
import adt.*;

public class Figura {
	List Puntos;
	Jugador Creador;
	float Area;
	
	/**
	 * Metodo que devuelve la cantidad de vértices
	 * de una figura usando sus coordenadas para ver donde hay esquinas.
	 * @return la cantidad de vértices
	 */
	public int vertices() {
		Node tmp=Puntos.getFirst();
		Node tmp2=tmp.getNext(); //dos nodos para comparar si el cambio entre puntos corresponde a un vertice
		float pendiente=20;
		while (tmp2!=null) {
			int p1=((Punto)(tmp.getInfo())).getXY();
			int p2=((Punto)(tmp2.getInfo())).getXY(); //Los puntos se extran como dos cifras xy
			
			if (Math.abs(p1-p2)==pendiente ){ //Caso de no vertice
				//Caso 1 los puntos comparten x
				//Caso 2 los puntos comparten y
				//Caso 3 los puntos siguen la misma diagonal
			}
			else { //caso de vertice
				System.out.println(p1);
			}
			
			pendiente=Math.abs(p1-p2); //Se actualiza la nueva pendiente y los nodos se avanzan
			tmp=tmp2;
			tmp2=tmp2.getNext();
		}
		return 1;
	}
	
	public float calc_area(List Puntos) {
		return 0;
	}
	
	public void combinar (Figura F1) {
		
	}

	public List getPuntos() {
		return Puntos;
	}

	public Jugador getCreador() {
		return Creador;
	}

	public float getArea() {
		return Area;
	}

	
}
