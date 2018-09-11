package adt;

public class List {

	  protected Node first;
	  private int size;
	  private Node last; //para manejar mejor los fragmentos

	  public List() {
	    first = null;
	    last=null;
	    size=0;
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
	    
	    if (size==0) { //el last no cambia si se agrega al inicio
	    	last=first;
	    }
	    
	    this.size++;
	  }
	  
	  public void insertf(Object o) {
		    Node tmp = new Node(o, null);
		    if (size!=0) {
		    	last.setNext(tmp);
		    	last=tmp;
		    }
		    else {
		    	tmp.setNext(first);
			    first = tmp;
			    last=first;
		    }
		    this.size++;
	  }
	  
	  
	  /**
	   * Elimina el primmer elemento de una lista enlazada
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
		  while (tmp!=null) {
			  l1.insert(tmp.getInfo());
			  tmp=tmp.getNext();
		  }
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

	public Node getLast() {
		return last;
	}

	public void setLast(Node last) {
		this.last = last;
	}

	  
	}


