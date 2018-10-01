package geo;
import adt.*;

public class Figura {
	List Puntos;
	Jugador Creador;
	float Area;
	List vertices;
	Tablero Tablero_juego;
	int segs;
	
	public Figura(List puntos,Tablero tablero,int s) {
		this.Puntos=puntos;
		this.Tablero_juego=tablero;
		this.vertices=this.vertices();
		this.Area=this.calc_area();
		this.segs=s;
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
		
		//Se invierte la lista ya que al insertar los elementos quedan al revés de la original
		return rotada.copy();
	}
	
	/**
	 * Devuelve si el punto xy está dentro de la figura para ver si hay que bloquearlo
	 * En caso de ser parte de ella no se bloquea
	 * @param xy el punto a preguntar si esta cerrado
	 * @return un valor true si el punto está dentro
	 */
	public int bloqueo(int xy,boolean esp){
		//inspirado en el algoritmo de Ray casting
		//Un booleano esp para los casos donde se requiere ver si un punto muy cercano a la figura por la izquierda está dentro
		if (this.Puntos.find(xy)!=-1 && !esp){
			//No decide si pertenece al perímetro
			return 1;
		}
		int cont=0;
		while (xy<60){
			int posv=this.vertices.find(xy);
			
			if (posv!=-1) {
				int tam=vertices.getSize();
				//Se pregunta por los contiguos
				int con1=(int)vertices.get((posv+1)%tam);
				int con2=(int)vertices.get((posv-1)%tam);
				System.out.println("contiguos "+con1+" "+con2);
				//Se extraen las Y de los contiguos y se guarda la Y actual en tam que ya no se usará
				int con1y=con1%10;
				int con2y=con2%10;
				int aux=xy%10;
				//Se les resta la Y actual para que estén referenciadas a esta
				con1y-=aux;
				con2y-=aux;
				//Si ambas son negativas o ambas positivas ese vértice no deberia contarse
				//porque están ambas debajo o encima del rayo en y
				//Tambien si alguna es 0 porque estaría a la misma altura del punto actual
				
				if (con1y*con2y==0) {
					System.out.println("vertice cerrado en "+xy);
					
					xy+=10;
					//Camina hasta el siguiente vértice que debe compartir ese lado paralelo a Y
					vertices.print();
					while (this.vertices.find(xy)==-1) {
						xy+=10;
						System.out.println(xy);
						
					}
					//Se debe evaluar con respecto a si el siguiente vértice "cambia de altura" para saber si suma como pared
					int con1aux=((int)vertices.get((posv+1)%tam))%10;
					int con2aux=((int)vertices.get((posv-1)%tam))%10;
					if ((con1+con2)*(con1aux+con2aux)<0) {
						System.out.println("vertice final cerrado en "+xy);
						cont++;
					}
					else {
						
						System.out.println("vertice final abierto en "+xy);
					}
					
				}
				//Si da negativo el ángulo es agudo hacia arriba o abajo y se suma en el total
				else if (con1y*con2y<0){
					cont++;
					System.out.println("vertice abierto en "+xy);
				}
				xy+=10;
				
			}
			//Suma por ser pared definitiva
			else if (this.Puntos.find(xy)!=-1){
				cont++;
			}
			else{
				xy+=10;
			}
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
	 * método que funciona como extensión el método bloqueo() 
	 * y sirve para, por medio de casos, ver si el punto medio de una linea 
	 * esta dentro a afuera de una figura
	 * @param inc punto inicial de la linea
	 * @param fin punto fianl de la linea
	 */
	public int bloqueoL(int inc, int fin) {
			boolean op;
			int bloqueof;
			if (Puntos.find(inc)!=-1 && Puntos.find(fin)!=-1) {
				return 1;
			}
			//Adaptacion del incio y fin a los casos esperados
			if (inc-fin==1) {
				System.out.println("sin cambios");
			}
			else if(inc-fin==-1) {
				System.out.println("siempre de abajo a arriba");
				int tmp=inc;
				inc=fin;
				fin=tmp;
			}
			else if (inc>fin) {
					System.out.println("siempre de izquierda a derecha");
					int tmp=inc;
					inc=fin;
					fin=tmp;
			}
			
			//Se establece el tamaño de la figura para buscar puntos contiguos
			int tam=Puntos.getSize();
			//Se busca la posicion del punto de inicio de la linea
			int posp=this.Puntos.find(inc);
			//Se extraen los contiguos
			int contig1=(int)Puntos.get(posp+1);
			int contig2=(int)Puntos.get(posp-1);
			//Se extraen sus coordenadas en Y porque estas definirian uno de los 3 casos:
			//que se toque un pared en Y, una pared en X o una esquina
			int yinc=inc%10;
			int xinc=(int)inc/10;
			int y1=contig1%10-yinc;
			int y2=contig2%10-yinc;
			System.out.println(y1+"\t"+y2);
			System.out.println(contig1+"\t"+contig2);

			int det1=y1*y2;
			if (det1==0) {
				
				if (inc-contig1==-10 || inc-contig2==-10) {
					System.out.println("C");
					//Bloque para buscar un nuevo vértice y revisar si comparte lado con la linea actual
					while (y1+y2==0) {
						
						posp=this.Puntos.find(inc);
						//Por fin encontro la esquina
						if (posp!=-1) {
							tam=vertices.getSize();
							contig1=(int)vertices.get((tam+posp+1)%tam);
							contig2=(int)vertices.get((tam+posp-1)%tam);
							y1=contig1%10-yinc;
							y2=contig2%10-yinc;
						}
						
						inc-=10;
						System.out.println("$$$");
					}
					
					System.out.println(yinc+"V"+contig1+"\t"+contig2);
					//Se prepara para comparar con el Y del punto final
					int yfin=fin%10-yinc;
					//Se define cual contiguo esta paralelo a Y y cual no para saber si el valor de adentro es compartido o no
					
					System.out.println("$$$"+y1);
					if (y1==0) {
						//Caso en el que estan uno arriba del otro
						System.out.println(y2+"#"+y1+"%"+yfin);
						if (y2*yfin>=0) {
							op=true;
						}
						else {
							op=false;
						}
					}
					else {
						//Caso en el que estan uno arriba del otro
						if (y1*yfin>=0) {
							op=true;
						}
						else {
							op=false;
						}
					}
					
					//System.out.println("$$$"+inc);
					if (op) {
						bloqueof=2-this.bloqueo(inc, true);
					}
					else {
						bloqueof=this.bloqueo(inc, true);
					}
					
					System.out.println("retorna "+bloqueof);
					return bloqueof;
				}
				else {
					int yfin=fin%10-yinc;
					int x1=(int)contig1/10-xinc;
					int x2=(int)contig2/10-xinc;
					int det2=x1+x2;
					
					if (det2==0) {
						System.out.println("A12");
						if ((y1+y2)*yfin<=0) {
							bloqueof=this.bloqueo(inc+10, true);
							}
						else {
							bloqueof=2-this.bloqueo(inc+10, true);
						}
						System.out.println("retorna "+bloqueof);
						return bloqueof;
					}
					else {
						System.out.println("A11");
						bloqueof=this.bloqueo(inc+10, true);
						System.out.println("retorna "+bloqueof);
						return bloqueof;
					}
					
				}
			}
			else if (det1>=0) {
				
				int yfin=fin%10-yinc;
				int x1=(int)contig1/10-xinc;
				int x2=(int)contig2/10-xinc;
				int det2=x1+x2;
				
				
				if (det2==0) {
					System.out.println("A22");

					if ((y1+y2)*yfin<=0) {
						bloqueof=this.bloqueo(inc+10, true);
						}
					else {
						bloqueof=2-this.bloqueo(inc+10, true);
					}
					System.out.println("retorna "+bloqueof);
					return bloqueof;
					}
				else {
					System.out.println("A21");
					bloqueof=this.bloqueo(inc+10, true);
					System.out.println("retorna "+bloqueof);
					return bloqueof;
				}
			}
			else {
				int yfin=fin%10-yinc;
				if (Math.abs(inc-fin)>=9) {
					System.out.println("B1");
					bloqueof=2-this.bloqueo(inc, true);
					System.out.println("retorna "+bloqueof);
					return bloqueof;
				}
				else {
					int x1=(int)contig1/10-xinc;
					int x2=(int)contig2/10-xinc;
					if (yfin==y1) {
						if (x1>0) {
							System.out.println("B22 por x1");
							bloqueof=this.bloqueo(inc, true);
							System.out.println("retorna "+bloqueof);
							return bloqueof;
						}
						else {
							System.out.println("B21 por x1");
							bloqueof=2-this.bloqueo(inc, true);
							System.out.println("retorna "+bloqueof);
							return bloqueof;
						}
					}
					else {
						if (x2>0) {
							System.out.println("B22 por x2");
							bloqueof=this.bloqueo(inc, true);
							System.out.println("retorna "+bloqueof);
							return bloqueof;
						}
						else {
							System.out.println("B21 por x2");
							bloqueof=2-this.bloqueo(inc, true);
							System.out.println("retorna "+bloqueof);
							return bloqueof;
						}
					}
				}
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
		
		//Pendiente para la primera evaluación
		float pendiente;
		
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
		pendiente=Math.abs(p1-p2);
		p2=(int)(Puntos.getFirst().getNext().getInfo());
		if (Math.abs(p1-p2)!=pendiente ){
			L1.insert(p1);
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

	public void setPuntos(List puntos) {
		Puntos = puntos;
	}

	public int getSegs() {
		return segs;
	}

	public void setSegs(int segs) {
		this.segs = segs;
	}

	
}
