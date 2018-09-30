package adt;

public class Cola {
	private Node inicio;
	private Node termino;
	
	public Cola(){
	inicio=null;
	termino=null;
	
	}
	/**
	 * Inserta un nuevo elemento en la cola 
	 * de forma que es el nuevo incio no se modifica
	 * Y el elemento nuevo pasa a ser el nuevo último
	 * @param dato objeto a insertar
	 */
	public void insertar(int dato){
		Node i=new Node(dato,null);
		if(inicio==null & termino==null){
			inicio=i;
			termino=i;
		}
		termino.setNext(i);
		termino=termino.getNext();
	}
	
	/**
	 * Se elimina el primer elemento de la cola
	 * Osea el primero que entró en ella
	 * @return el valor eliminado
	 */
	public Object extraer(){
		Object dato=inicio.getInfo();;
		inicio=inicio.getNext();
		return dato;
		}
	
	/**
	 * Recorre la lista y devuelve su cantidad de elementos (tamaño)
	 * @return numero de  elementos
	 */
	public int contar(){
		int contador=0;
		Node c=this.inicio;
		while(c!=null){
			contador++;
			c=c.getNext();
		}
		return contador;
		}
}

