package geo;
import adt.*;

public class Tablero {
	List Puntos;
	int dim;
	List Figuras;
	
	public Tablero(int dim) {
		this.dim=dim;
		this.Figuras=new List();
		
		Punto P1=new Punto(00);
		List L1=new List();
		for (int i=dim-1;i!=-1;i--) {
			List L2=new List();
			for (int j=dim-1;j!=-1;j--) {
				L2.insert(P1.copy(i*10+j));
			}
			L1.insert(L2.copy());
		}
		this.Puntos=L1;
	}
	
	/**
	 * Esta función toma un entero de dos cifras xy
	 * y devuelve el punto ese par ordenado (x,y)
	 * 
	 * @param ub ubicación de la forma xy de un punto
	 * @return el punto en la ubicación deseada
	 */
	public Punto get(int ub) {
		int x=(int)(ub/10);
		int y=dim-1-ub%10;
		return (Punto)((List)Puntos.get(x)).get(y);
	}
	
	public void show() {
		for (int i=0;i<dim;i++) {
			for (int j=0;j<dim;j++) {
				System.out.print(this.get(i*10+j)+"\t");
			}
			System.out.print("\n");
		}
	}
	public void show2() {
		for (int i=0;i<dim;i++) {
			for (int j=0;j<dim;j++) {
				this.get(i*10+j).getLineas().print();
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
	
	
	/**
	  * Este método es el principal de la clase matriz_puntos
	  * Busca realizar un recorrido por todos los puntos posibles
	  * para identificar áreas cerradas siguiendo trayectorias lineales.
	  * Realiza recursividad de pila en caso de bifurcaciones
	  * 
	  * @param ubi ubicación inicial del recorrido
	  * @param aco Puntos acomulados en rocorridos pasados
	  * @param ig Linea a ignorar en caso de recursión
	  * @param cont Contador para ver que elemento tomar al inicio
	  * @return lista con los puntos acomulados que formen un area cerrada
	  */
	 public List recorrido(int ubi,List aco, Linea ig,int cont) {
		 Punto Pact=this.get(ubi);
		 List L_rest=Pact.get_rest(ig);
		 
		 if (L_rest.getSize()>1+cont) { //Caso de múltiples incios
			// El nuevo recorrido empezará con su cont en una unidad más
			// Con lo cual tomará otro primer elemento para su camino lineal
			//Hasta el punto que se cubran todos los caminos lineales que hay en el inicio
			 recorrido(ubi,aco.copy().copy(),ig,cont+1);
		 }

		 
		 //Linea que define el primer recorrido incial
		 Linea Lact=(Linea)L_rest.get(cont);
		 
		 if (-1!=Pact.getPrecedente().find(Lact)){ //Caso de repetir recorrido por linea que ya se entró
			 System.out.print("#");
			 Pact.getPrecedente().extract_o(Lact); //La linea precedente se elimina después de ser usado
			 return null; //Se detiene la ejecución de esa linea
		 }
		 
		 //Se añade el primer punto
		 aco.insert(Pact.getXY());
		 
		 while(L_rest.getSize()>0) { //ciclo para recorrido lineal
			 
			 //Se actualiza el punto a la siguiente conexión de la linea actual
			 Pact=Lact.conecta(Pact);
			 //Se buscan las lineas que no sean la que se acaba de pasar
			 L_rest=(Pact.get_rest(Lact));
			 
			 //Se pregunta si el nuevo punto ya fue recorrido, osea si está en aco
			 int tmp=aco.find(Pact.getXY());
			 if (tmp!=-1){ //Caso de área cerrada
				 
				//Se recorta en caso de tocar el acomulado en algun lugar diferente del inicio
				aco.recortar(tmp);
				aco.print();
				System.out.print("cierra\n");
				
				//Se coloca un precedente para no volver a salir desde la linea por la que se entró
				Pact.getPrecedente().insert(Lact);
				
				//Inserta el area en forma de lista de coordenadas
				if (Figuras.find(aco)==-1) {
					Figura F1=new Figura(aco);
					this.Figuras.insert(F1);
				}
				
				return null;
			 }
			 
			 
			 //Se pregunta si el camino lienal llega a una bifuración
			 if (L_rest.getSize()>1) {
				 System.out.print("$");
				 //Se reinicia como si fuera un caso de múltiples inicios
				 //Con el mismo acomulado y se ignora la linea actual
				 recorrido(Pact.getXY(),aco.copy().copy(),Lact,0);
				 return null;
			 }
			 
			 //Si no hay bifurcación se agrega el punto
			 aco.insert(Pact.getXY());
			 
			 //Si hay lineas para continuar el recorrido se toma la primera (recorrido linea)
			 if (L_rest.getSize()!=0)
			 Lact=(Linea)L_rest.get(0);}
		 
		//No se encontraron areas entonces solo se detiene la ejecución
		return null;
	}
	
	public void dibujar (Linea L1) {
		
	}
	
	public void dibujar(Figura F1) {
		
	}

	public int getDim() {
		return dim;
	}


	public List getPuntos() {
		return Puntos;
	}

	public List getFiguras() {
		return Figuras;
	}
}
