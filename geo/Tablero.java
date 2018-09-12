package geo;
import adt.*;

public class Tablero {
	List Puntos;
	int dim;
	
	public Tablero(int dim) {
		this.dim=dim;
		
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
		 System.out.println(Pact.getXY());
		 List L_rest=Pact.get_rest(ig);
		 /*L_rest.print();
		 System.out.print("\n");*/
		 
		 if (L_rest.getSize()>1+cont) {
			 recorrido(ubi,aco.copy(),ig,cont+1);
		 }
		 
		 Linea Lact=(Linea)L_rest.get(cont);
		 
		 if (-1!=Pact.getPrecedente().find(Lact)){
			 System.out.print("#");
			 Pact.getPrecedente().extract_o(Lact);
			 return null;
		 }
		 aco.insert(Pact.getXY());
		 
		 while(L_rest.getSize()>0) {
			 
			 Pact=Lact.conecta(Pact);
			 System.out.println(Pact.getXY());
			 L_rest=(Pact.get_rest(Lact));
			 
			 int tmp=aco.find(Pact.getXY());
			 if (tmp!=-1){ //Caso de área cerrada
				aco.recortar(tmp);
				aco.print();
				System.out.print("cierra");
				Pact.getPrecedente().insert(Lact);
				return aco;
			 }
			 aco.insert(Pact.getXY());
			 
			 
			 if (L_rest.getSize()>1) {
				 System.out.print("$");
				 recorrido(Pact.getXY(),aco.copy(),Lact,0);
				 return null;
			 }
			 
			 if (L_rest.getSize()!=0)
			 Lact=(Linea)L_rest.get(0);}
		
		aco.print();
		return aco;
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
}
