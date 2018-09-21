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
	  * @param pri el primer elemento, toda trayectoria debe cerrar en él
	  * @param act el siguiente punto de la nueva linea que da una dirección inicial al recorrido
	  * @param ig la línea a ignorar, generalmente es la linea que se acaba de crear
	  * @param aco la lista de puntos que irá guadando el recorrido
	  * @return un booleano en caso de recursividad para bloquear algunas recursiones
	  */
	 public boolean recorrer2(int pri,int act,Linea ig, List aco) {
		 Punto Pact=this.get(act);
		 List L_rest=Pact.get_rest(ig);
		 Linea Lact;
		 
		 while (L_rest.getSize()>0) {
			 aco.insert(Pact.getXY());
			 
			 if (L_rest.getSize()>1) {
				 List Fig=Pact.getFiguras();
				 //Caso de tocar una figura preconstruida
				 if (Fig.getSize()>0) {
					 ((Figura)Fig.get(0)).getVertices().print();
					 System.out.println("Se toca la figura");
					 return false;
				 }
				 //Caso de bifurcación simple 
				 else {
					 System.out.println("Se toca la bifucación");
					 return false;
				 }
			 }
			 
			 if (Pact.getXY()==pri) {
				 aco.print();
				 System.out.println("cierra");
				 
				 Figura F1=new Figura(aco,this);
				 this.Figuras.insert(F1);
				 
				 return false;
			 }
			 Lact=(Linea)L_rest.get(0);
			 Pact=Lact.conecta(Pact);
			 L_rest=Pact.get_rest(Lact);
		 }
		 aco.print();
		 System.out.println("no cierra");
		 return true;
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
