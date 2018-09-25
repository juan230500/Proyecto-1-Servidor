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
	 public boolean recorrer2(int pri,int act,Linea ig, List aco,int seg) {
		 Punto Pact=this.get(act);
		 List L_rest=Pact.get_rest(ig);
		 Linea Lact=ig;
		 List Figs=new List();
		 
		 while (L_rest.getSize()>0) {
			 act=Pact.getXY();
			 aco.print();
			 System.out.println(act);
			 
			 if (aco.find(act)!=-1){
				 aco.print();
				 System.out.println("cierra");
				 Pact.addPrecedente(Lact);
				 
				 return false;
			 }
			 if (act==pri) {
				 aco.insert(act);
				 aco.print();
				 System.out.println("cierraC con "+(++seg)+" segmentos");
				 Pact.addPrecedente(Lact);
				 Figura F1=new Figura(aco,this);
				 this.Figuras.insert(F1);
				 
				 return true;
			 }
			 
			 if (L_rest.getSize()>1) {
				 List Figtmp=Pact.getFiguras();
				 //Caso de tocar una figura preconstruida
				 seg++;
				 if (Figtmp.getSize()>0) {
					 //Al tocar un área se restarán n-1 segmentos por los n puntos compartidos
					 Node tmp=Figtmp.getFirst();
					 while (tmp!=null) {
						 Figs.insert(tmp.getInfo());
						 ((Figura)tmp.getInfo()).getVertices().print();
						 System.out.println("se toca con ");
						 tmp=tmp.getNext();
					 }
					 /*((Figura)Fig.get(0)).getVertices().print();
					 iFigs.insert(act);
					 Figs.insert(((Figura)Fig.get(0)));
					 List rotada=((Figura)Fig.get(0)).rotacion(act);
					 rotada.print();
					 
					 //Bloque para elejir correctamente el camino por la figura preconstruida
					 Node tmp=rotada.getFirst().getNext();
					 L_rest=Pact.get_rest(Lact);
					 Lact=(Linea)L_rest.get(0);
					 for (int i=0;Lact.conecta(Pact).getXY()!=(int)tmp.getInfo();i++) {
						 Lact=(Linea)L_rest.get(i);
					 }
					 Pact=Lact.conecta(Pact);
					 
					 //Cuando se encuentren tres lineas en un punto significa una posible salidad dela figura
					 while (Pact.getLineas().getSize()<3) {
						 System.out.println(Pact.getLineas().getSize());
						 L_rest=Pact.get_rest(Lact);
						 Lact=(Linea)L_rest.get(0);
						 Pact=Lact.conecta(Pact);
						 act=Pact.getXY();
					 }
					 fFigs.insert(act);
					 System.out.println("Sale por "+act);
					 System.out.println("Se toca la figura en "+iFigs.getFirst().getInfo());
					 
					 return false;*/
				 }
				 //Caso de bifurcación simple 
				 
				 //Bloque para generar nuevos caminos
				 Node tmp=L_rest.getFirst();
				 aco.insert(act);
				 while(tmp!=null) {
					 Lact=(Linea)tmp.getInfo();
					 if (Pact.getPrecedente().find(Lact)!=-1) {
						 Pact.getPrecedente().extract_o(Lact);
						 System.out.println("Se bloquea por precedencia el camino por "+Lact.conecta(Pact).getXY());
						 return false;
					 }
					 System.out.println("Se sigue el camino por "+Lact.conecta(Pact).getXY());
					 if(this.recorrer2(pri,Lact.conecta(Pact).getXY(), Lact, aco.copy().copy(),seg)) {
						 break;
					 }
					 tmp=tmp.getNext();
				 }
				 return true;
				 
			 }
			 
			 if (Pact.getFiguras().getSize()==0) {
			 seg++;
			 System.out.println("suma");}
			 
			 
			 Lact=(Linea)L_rest.get(0);
			 Pact=Lact.conecta(Pact);
			 L_rest=Pact.get_rest(Lact);
			 aco.insert(act);
		 }
		 aco.print();
		 System.out.println("no cierra");
		 return false;
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
