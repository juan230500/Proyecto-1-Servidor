package geo;
import adt.List;
import adt.Node;

public class Punto {
	int XY;
	boolean bloqueo;
	List Lineas;
	List Precedente;
	
	
	public Punto(int xy) {
		XY=xy;
		bloqueo=false;
		Lineas=new List();
		Precedente=new List();
	}
	/**
	 * Agrega una linea dentro de la lsita de lineas del punto
	 * @param L1 linea a gregar
	 */
	public void add(Linea L1) {
		Lineas.insert(L1);
	}
	
	/**
	 * Devuelve un punto nuevo generado a partir de cualquier otro punto
	 * esta nueva instancia puede tener la xy deseada
	 * @param xy del nuevo punto
	 * @return el nuevo punto
	 */
	public Punto copy(int xy) {
		Punto P1=new Punto(xy);
		return P1;
	}
	
	/**
	  * Función que servirá para realizar recorridos
	  * al decirle a un recorrido que ignore su linea de origen
	  * y se concentre en las que le falta recorrer en el nuevo punto
	  * 
	  * @author juan
	  * 
	  * @param ig Linea a ignorar
	  * @return lt Lista con todas las líneas del punto
	  * 		menos la linea a ignorar
	  */
	 public List get_rest(Linea ig) {
		 List lt=new List();
		 Node tmp=this.Lineas.getFirst();
		 while (tmp!=null) {
			 if ((Linea)tmp.getInfo()!=ig) {
				 lt.insert(tmp.getInfo());
			 }
			 tmp=tmp.getNext();
		 }
		 return lt;
	 }

	public int getXY() {
		return XY;
	}

	public boolean isBloqueo() {
		return bloqueo;
	}

	public void setBloqueo(boolean bloqueo) {
		this.bloqueo = bloqueo;
	}

	public List getLineas() {
		return Lineas;
	}
	public List getPrecedente() {
		return Precedente;
	}
	

}
