package geo;
import adt.*;

public class Figura {
	List Puntos;
	Jugador Creador;
	float Area;
	List vertices;
	Tablero Tablero_juego;
	
	public Figura(List puntos,Tablero tablero) {
		this.Puntos=puntos;
		this.Tablero_juego=tablero;
		this.vertices=this.vertices();
		this.Area=this.calc_area();
	}
	
	/**
	 * Devuelve los puntos ordenados de la figura pero con el primero de la lista como un cierto punto
	 * Se usa para seguir la trayectoria de una figura preconstruida
	 * @param el elemento que será el primero de la rotada
	 * @return lista rotada con el primer elemento deseado
	 */
	public List rotacion(int el) {
		//Lista donde se guardará la rotación
		List rotada= new List();
		
		Node tmp=this.Puntos.getFirst();
		//Coloca el puntero en el elemento que se desea sea el primero
		while((int)tmp.getInfo()!=el) {
			tmp=tmp.getNext();
		}
		//Inserta desde ese elemento es adelante
		while (tmp!=null) {
			rotada.insert(tmp.getInfo());
			tmp=tmp.getNext();
		}
		
		tmp=this.Puntos.getFirst();
		//Inserta los que queden al inicio de la lista de puntos hasta el elemento de nuevo
		while((int)tmp.getInfo()!=el) {
			rotada.insert(tmp.getInfo());
			tmp=tmp.getNext();
		}
		
		rotada.copy().print();
		//Se invierte la lista ya que al insertar los elementos quedan al revés de la original
		return rotada.copy();
	}
	
	/**
	 * Devuelve si el punto xy está dentro de la figura para ver si hay que bloquearlo
	 * En caso de ser parte de ella no se bloquea
	 * @param xy el punto a preguntar si esta cerrado
	 * @return un valor true si el punto está dentro
	 */
	public int bloqueo(int xy){
		//inspirado en el algoritmo de Jordan
		if (this.Puntos.find(xy)!=-1){
			//No decide si pertenece al perímetro
			return 1;
		}
		
		int cont=0;
		int vert_aco=-1;
		
		while (xy<60){
			if (this.vertices().find(xy)!=-1){
				if (vert_aco==-1){
					vert_aco=xy;
				}
				else{
					vert_aco=-1;
					
					cont-=((xy-vert_aco)/10)-1;
				}
				
				}
			
			else if (this.Puntos.find(xy)!=-1){
				cont++;
			}
			else{
				vert_aco=-1;
			}

			xy+=10;
		}
		
		if (cont%2==0){
			//Caso de que está fuera de la figura
			return 2;
		}
		else{
			//Caso de que está dentro de la figura
			return 0;
		}
	}
	
	/**
	 * Metodo que devuelve la cantidad de vértices
	 * de una figura usando sus coordenadas para ver donde hay esquinas.
	 * @return la cantidad de vértices
	 */
	public List vertices() {
		//dos nodos para comparar si el cambio entre puntos corresponde a un vertice
		Node tmp=Puntos.getFirst();
		
		//Pendiente imposible para la primera evaluación
		float pendiente=20;
		
		//Lista donde guardar los vértices
		List L1=new List();
		
		//Coordenadas del punto anterior
		int p1=(int)tmp.getInfo();
		
		//Se añade la figura nueva a ese punto
		Tablero_juego.get(p1).add(this);
		
		tmp=tmp.getNext();
		
		//Coordenadas del punto actual
		int p2=(int)tmp.getInfo();
		
		pendiente=Math.abs(p1-p2);
		
		while (tmp!=null) {
			p2=(int)tmp.getInfo();
			//Caso 1 los puntos comparten x
			//Caso 2 los puntos comparten y
			//Caso 3 los puntos siguen la misma diagonal
			if (Math.abs(p1-p2)!=pendiente ){
				L1.insert(p1);
			}
			
			pendiente=Math.abs(p1-p2); //Se actualiza la nueva pendiente y los nodos se avanzan
			p1=p2;
			tmp=tmp.getNext();
			
			//Se añade la figura nueva a ese punto
			Tablero_juego.get(p1).add(this);
		}
		p1=(int)(Puntos.getFirst().getInfo());

		//Evaluacion al final del recorrido para el primer punto
		if (Math.abs(p1-p2)!=pendiente ){
			L1.insert(p2);
		}
		return L1;
	}
	
	public void forma() {
		List L1=this.vertices;
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
			Node tmp=L1.getFirst();
			//Recorrido para secar pendiente de dos segmentos entre puntos
			int pendiente1=(int)tmp.getInfo()-(int)tmp.getNext().getInfo();
			tmp=tmp.getNext();
			int pendiente2=(int)tmp.getInfo()-(int)tmp.getNext().getInfo();
			tmp=tmp.getNext();
			tmp=tmp.getNext();
			
			//Recorrido para sacar pedientes de los segmentos pararelos a los anteriores
			int pendiente3=(int)tmp.getInfo()-(int)tmp.getNext().getInfo();
			tmp=tmp.getNext();
			int pendiente4=(int)tmp.getInfo()-(int)tmp.getNext().getInfo();
			
			//La pendiente de cada segmento debe ser complementaria a la de su paralelo
			if (pendiente1==-pendiente3 && pendiente2==-pendiente4) {
				System.out.println("Hexágono");}
			else {
				System.out.println("Polígono");
			}
		}
		else {
			System.out.println("Polígono");
		}
		
	}
	/**
	 * Método que calcula el área de un figura en unidades lineales cuadradas
	 * Solo se apoya en los vértices para ello y usa lo formula de Gauss de areas
	 * @return el valor del área total
	 */
	public float calc_area() { 
		//Fórmula de Gauss para áreas
		Node tmp=this.vertices.getFirst();
		Node aux=tmp;
		Node tmp2=tmp.getNext();
		float area=0;
		int x1;
		int x2;
		int y1;
		int y2;
		int aco_iz=0;
		int aco_der=0;
		
		while (tmp2!=null) {
			x1=(int)((int)tmp.getInfo()/10);
			x2=(int)((int)tmp2.getInfo()/10);
			y1=(int)tmp.getInfo()%10;
			y2=(int)tmp2.getInfo()%10;
			aco_iz+=x1*y2;
			aco_der+=y1*x2;
			
			tmp=tmp2;
			tmp2=tmp2.getNext();
		}
		tmp2=aux;
		x1=(int)((int)tmp.getInfo()/10);
		x2=(int)((int)tmp2.getInfo()/10);
		y1=(int)tmp.getInfo()%10;
		y2=(int)tmp2.getInfo()%10;
		aco_iz+=x1*y2;
		aco_der+=y1*x2;
		area=Math.abs(aco_der-aco_iz);
		return area/2;
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

	public void setArea(float area) {
		Area = area;
	}

	public List getVertices() {
		return vertices;
	}

	
}
