package adt;

public class List {

	  protected Node first;
	  private int size;

	  public List() {
	    first = null;
	  }

	  public void insert(Object o) {

	    Node tmp = new Node(o, null);

	    tmp.setNext(first);

	    first = tmp;
	    this.size++;
	  }

	  public Object extract() {
	    Object out = null;

	    if (!isEmpty()) {
	      out = first.getInfo();
	      first = first.getNext();
	    }
	    this.size--;

	    return out;
	  }

	  public void print(int n) {
	    if (!isEmpty()) {
	      Node tmp = first;

	      for (int i = 0; i < n; i++) {
	        tmp = tmp.getNext();
	        if (tmp == null)
	          return;
	      }
	      System.out.println(tmp.getInfo());
	    }
	  }

	  public void print() {
		 System.out.print("[");
	    if (!isEmpty()) {
	      Node tmp = first;
	      
	      while (tmp != null) {
	        System.out.print(tmp.getInfo()+",");
	        tmp = tmp.getNext();
	      }
	      
	    }
	    System.out.print("]");
	  }

	  public boolean isEmpty() {
	    if (first == null)
	      return true;
	    else
	      return false;
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
	  
	  
	  public Object get(int n) {
		  Node tmp=first;
		  for (int i=0;i<n;i++) {
			  tmp=tmp.getNext();
		  }
		  return tmp.getInfo();
	  }
	  
	}


