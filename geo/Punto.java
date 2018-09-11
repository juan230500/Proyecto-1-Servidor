package geo;
import adt.List;

public class Punto {
	int XY;
	boolean bloqueo;
	List Lineas;
	List Fragmentos;
	
	
	public Punto(int xy) {
		XY=xy;
		bloqueo=false;
		Lineas=new List();
		Fragmentos=new List();
	}
	/**
	 * Agrega una linea dentro de la lsita de lineas del punto
	 * @param L1 linea a agregar
	 */
	public void add(Linea L1) {
		Lineas.insert(L1);
	}
	
	public void add(Fragmento F1) {
		Fragmentos.insert(F1);
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
	public List getFragmentos() {
		return Fragmentos;
	}

}
