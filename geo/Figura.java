package geo;
import adt.*;

public class Figura {
	List Puntos;
	Jugador Creador;
	float Area;
	
	public Figura(List puntos) {
		this.Puntos=puntos;
		
	}
	
	/**
	 * Metodo que devuelve la cantidad de vértices
	 * de una figura usando sus coordenadas para ver donde hay esquinas.
	 * @return la cantidad de vértices
	 */
	public List vertices() {
		//dos nodos para comparar si el cambio entre puntos corresponde a un vertice
		Node tmp=Puntos.getFirst();
		Node tmp2=tmp.getNext(); 
		
		//Pendiente imposible para la primera evaluación
		float pendiente=20;
		
		//Lista donde guardar los vértices
		List L1=new List();
		
		while (tmp2!=null) {
			int p1=(int)tmp.getInfo();
			int p2=(int)tmp2.getInfo(); //Los puntos se extran como dos cifras xy
			
			if (Math.abs(p1-p2)==pendiente ){ //Caso de no vertice
				//Caso 1 los puntos comparten x
				//Caso 2 los puntos comparten y
				//Caso 3 los puntos siguen la misma diagonal
			}
			else { //caso de vertice
				L1.insert(p1);
			}
			
			pendiente=Math.abs(p1-p2); //Se actualiza la nueva pendiente y los nodos se avanzan
			tmp=tmp2;
			tmp2=tmp2.getNext();
		}
		
		//Evaluacion al final del recorrido para el primer punto
		int p1=(int)tmp.getInfo();
		int p2=(int)Puntos.getFirst().getInfo();
		if (Math.abs(p1-p2)!=pendiente ){
			L1.insert(p1);
		}
		
		return L1;
	}
	
	public void forma() {
		List L1=this.vertices();
		//Caso de cuadrilátero
		int tam=L1.getSize();
		if (tam==4) {
			int pendiente=Math.abs((int)L1.get(0)-(int)L1.get(1));
			//Estas dos pendientes solo son posibles en un cuadrilátero rombo
			if (pendiente%9==0 || pendiente%11==0) {
				System.out.println("Rombo");
				return;
			}
			
			//Se necesita otra pendiente para comparar si es rectángulo o cuadrado
			int pendiente2=Math.abs((int)L1.get(2)-(int)L1.get(1));
			
			//El resultado de restar (distancias) entre los puntos para que sea cadrado
			// Debe ser divisible entre 9
			
			// Ej: se tienen los puntos 00,20,22,... |0-20|=20 y |20-2|=2 
			//entonces 2-20=-18 y es multiplo de 9
			if((pendiente-pendiente2)%9==0) {
				System.out.println("Cuadrado");
			}
			else {
				System.out.println("Rectángulo");
			}
			
		}
		
		else if(tam==3) {
			System.out.println("Triángulo");
		}
		else if(tam==6 && this.getPuntos().getSize()==6 ) {
			System.out.println("Hexágono");
		}
		else {
			System.out.println("Polígono");
		}
		
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
