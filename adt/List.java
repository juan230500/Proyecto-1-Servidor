package adt;

public class List {

	  protected Node first;
	  private int size;

	  public List() {
		//La lsita se inicia con el primer elemento en null
	    first = null;
	  }
	  
	  /**
	   * Inserta un elemento al inicio de una lista enlazada
	   * 
	   * @param o Objecto genérico a insertar por medio de un nuevo nodo
	   */
	  public void insert(Object o) {
	    Node tmp = new Node(o, null);
	    tmp.setNext(first);
	    first = tmp;
	    this.size++;
	  }
	  
	  /**
	   * Elimina el primer elemento de una lista enlazada
	   * @return elobjeto eliminado
	   */
	  public Object extract() {
	    Object out = null;

	    if (size!=0) {
	      out = first.getInfo();
	      first = first.getNext();
	    }
	    this.size--;

	    return out;
	  }
	  
	  /**
	   * Elimina un elemento específico de la lista con un cierto contenido
	   * @param o objeto que debe conterner el nodo a eliminar
	   */
	  public void extract_o(Object o) {
		    Node tmp=this.first;
		    while(tmp!=null) {//Se sigue buscando el elemento hasta llegar al final de la lista
		    	 if (tmp.getInfo()==o) {//Si el temporal al incio contiene el objeto buscado
				    	this.first=(this.first.getNext()); //Se desenlaza el primer nodo
				    	tmp=this.first;
				    	this.size--;
				    	return;
				    }
		    	 else if (tmp.getNext()!=null) { //Si la lista no contiene un solo elemento
		    		if (tmp.getNext().getInfo()==o) {//Verifica que el nodo delantero contenga el objeto
				    	tmp.setNext(tmp.getNext().getNext()); //Se desenlaza el nodo con el objeto
				    	this.size--;
				    	return;}
			    }
		    	//Si ni el incial ni el delantero contienen el objeto 
		    	//y la lista no se acaba, se avanza el temporal
			    tmp=tmp.getNext(); 
			    
		    }
	  }
	  
	  /**
	   * Devuelve el objeto contenido en cierta posición de la lista
	   * @param n posición del elemento
	   * @return el objeto en esa posición
	   */
	  public Object get(int n) {
		  Node tmp=first;
		  for (int i=0;i<n;i++) {
			  tmp=tmp.getNext();
		  }
		  return tmp.getInfo();
	  }
	  
	  /**
	   * Genera una nueva instancia de la lista
	   * a partir de reocorrer todos sus nodos
	   * @return la nueva instancia
	   */
	  public List copy() {
		  List l1=new List();
		  Node tmp=this.first;
		  //Con la nueva lista instanciada y el tmp apuntando al primero de la lista molde
		  //Se inicia el proceso de insertar cada nodo del molde en la nueva lista
		  while (tmp!=null) {
			  l1.insert(tmp.getInfo());
			  tmp=tmp.getNext();
		  }
		  //Se retorna la nueva lista con los nodos insertados al incio, osea al revés
		  return l1;
	  }
	  
	  /**
	   * Busca un objeto entre los elementos de la lista
	   * @param o el objeto buscado
	   * @return la posición del objeto
	   */
	  public int find(Object o) {
		  Node tmp=this.first;
		  int c=0;
		  while (tmp!=null) {
			  if (tmp.getInfo()==o) {
				  return c;
			  }
			  tmp=tmp.getNext();
			  c++;
		  }
		  return -1;
	  }
	  
	  /**
	   * Busca una posición en la lista y elimina todos los elementos
	   * desde esa posición para atrás
	   * @param pos posición limite del recorrido
	   */
	  public void recortar(int pos) {
		  Node tmp=first;
		  for (int i=0;i<pos;i++) {
			  tmp=tmp.getNext();
		  }
		  //las posicion a recortar el igual a los nodos que se conservan más 1
		  this.size=pos+1;
		  tmp.setNext(null);
	  }
	  
	  /**
	   * Imprime un elemento de la lista
	   * @param n la posición del elemento
	   */
	  public void print(int n) {
	    if (size!=0) {
	      Node tmp = first;
	      for (int i = 0; i < n; i++) {
	        tmp = tmp.getNext();
	        if (tmp == null)
	          return;
	      }
	      System.out.println(tmp.getInfo());
	    }
	  }
	  
	  /**
	   * Imprime todos los elementos de la lista
	   */
	  public void print() {
		 System.out.print("[");
	    if (size!=0) {
	      Node tmp = first;
	      while (tmp != null) {
	        System.out.print(tmp.getInfo()+",");
	        tmp = tmp.getNext();
	      }
	      
	    }
	    System.out.print("]");
	  }
	  
	 
	  
	  public Node getFirst() {
			return first;
		}
	  
	  public int getSize() {
			return size;
		}
	  
	  public void setFirst(Node first) {
			this.first=first;
		}
	  
	  
	  
	  
	}


