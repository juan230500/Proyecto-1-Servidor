package geo;
import adt.*;

public class Tablero {
	List Puntos;
	int dim;
	List Figuras;
	Figura Ftmp;
	
	public Tablero(int dim) {
		this.dim=dim;
		this.Figuras=new List();
		this.Ftmp=null;
		
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
	
	public void addL(int xy1,int xy2) {
		Linea L=new Linea(xy1,xy2,this);
		this.get(xy1).add(L);
		this.get(xy2).add(L);
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
	
	 public int gen(int xy1,int xy2) {
		//Verificación para lineas superpuestas
		//Revisa si alguna de las lineas del punto p1 lleva al punto p2
		Punto p1=this.get(xy1);
		Punto p2=this.get(xy2);

		List Litmp=p1.getLineas();
		Node tmp=Litmp.getFirst();
		int val;
		Linea Ltmp;
		while (tmp!=null) {
			Ltmp=(Linea)tmp.getInfo();
			if (Ltmp.conecta(p1)==p2) {
				System.out.println("Superpone "+xy1+" con "+xy2);
				return 0;
			}
			tmp=tmp.getNext();
		}
		
		
		//Verificación para líneas internas
		//Se apoya en el método bloqueoL para identificar lineas internas
		
		List Figs=p1.getFiguras();
		tmp=Figs.getFirst();
		Figura Ftmp;
		while (tmp!=null) {
			Ftmp=(Figura)tmp.getInfo();
			Litmp=Ftmp.getPuntos();
			if (Litmp.find(xy2)!=-1) {
				if (Ftmp.bloqueoL(xy1, xy2)==0) {
					System.out.println("Linea interna");
					return 0;
				}
			}
			tmp=tmp.getNext();
			}
		
		//Verificación para que no se cruzen diagonales
		//Se pregunta a la esquina derecha superior o inferior si ya hay una diagonal allí
		int dif=Math.abs(xy1-xy2);
		
		//Si es diagonal la diferencia debe ser 9 o 11
		if (Math.abs(10-dif)==1) {
			int xy3;
			if (xy1>xy2) {
				xy3=xy1;
			}
			else {
				xy3=xy2;
			}
			if (dif==9) {
				xy3+=1;
			}

			Punto p3=this.get(xy3);
			System.out.println("$"+xy3);
			//El xy mayor es el que debe evaluar si está bloqueado
			if (!p3.isBloqueo_d()) {
				//Se bloquea la otra diagonal posible
				System.out.println("se bloquea: "+(xy3));
				Punto paux=this.get(xy3);
				paux.setBloqueo_d(true);
				
				//Bloque para instanciar correctamente una linea dentro del tablero

			} else {
				System.out.println("ya ha una diagonal ahí: "+xy3);
				return 0;
			}
			
		}
		
		//Verificacion de puntos bloqueados totalmente
		List FIGS=this.getFiguras();
		tmp=FIGS.getFirst();
		while (tmp!=null) {
			System.out.println(tmp);
			Ftmp=(Figura)tmp.getInfo();
			if (Ftmp.bloqueo(xy1, false)!=2 || Ftmp.bloqueo(xy2, false)!=2) {
				System.out.println("Punto bloqueado");
				return 0;
			}
			tmp=tmp.getNext();
		}
		
		//Finalmente si ninguna restricción se cumple se agrega la linea;
		System.out.println("Se crea la linea "+xy1+"-"+xy2);
		Linea L=new Linea(xy1,xy2,this);
		List Li=new List();
		this.get(xy1).add(L);
		this.get(xy2).add(L);
		if (p1.getLineas().getSize()>1 && p2.getLineas().getSize()>1) {
			this.recorrer2(xy1, xy2, L, Li.copy(), 0);
			for (int i=dim-1;i!=-1;i--) {
				for (int j=dim-1;j!=-1;j--) {
					Punto p=this.get(i*10+j);
					p.setPrecedente(new List());
				}
			}
			if (this.Ftmp==null) {
				return 2;
			}
			else {
				this.recorrer3(xy1, xy2, L, Li.copy(), this.Ftmp,true);
				return 1;
			}
		}
		return 2;

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
				 Ftmp=new Figura(aco,this);
				 /*Figura F1=new Figura(aco,this);
				 this.Figuras.insert(F1);*/
				 
				 return true;
			 }
			 
			 if (L_rest.getSize()>1) {
				 List Figtmp=Pact.getFiguras();
				 //Caso de tocar una figura preconstruida
				 seg++;
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
	 
	 public boolean recorrer3(int pri,int act,Linea ig, List aco, Figura Ftmp, boolean FigsR) {
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
				 System.out.println("cierraC con ");
				 Pact.addPrecedente(Lact);
				 Ftmp=new Figura(aco,this);
				 /*Figura F1=new Figura(aco,this);
				 this.Figuras.insert(F1);*/
				 
				 return true;
			 }
			 
			 if (L_rest.getSize()>1) {
				 //Caso de tocar una figura preconstruida
				 aco.insert(act);
				 if (FigsR && Ftmp.getPuntos().find(act)!=-1 && L_rest.getSize()>1) {
					 Linea L1=(Linea)L_rest.get(0);
					 Linea L2=(Linea)L_rest.get(1);
					 int xy1=L1.conecta(Pact).getXY();
					 int xy2=L2.conecta(Pact).getXY();
					 System.out.println(xy1+">>>>>"+xy2);
					 FigsR=false;
					 if (Ftmp.bloqueo(xy2, false)>Ftmp.bloqueo(xy1, false)) {
						 this.recorrer3(pri,xy1, L1, aco.copy().copy(),Ftmp, FigsR);
						 
					 }
					 else {
						 this.recorrer3(pri,xy2, L2, aco.copy().copy(),Ftmp,FigsR);
					 }
					 return true;
					 
					 
					 
				 }
				 //Caso de bifurcación simple 
				 
				 //Bloque para generar nuevos caminos
				 Node tmp=L_rest.getFirst();
				 
				 while(tmp!=null) {
					 Lact=(Linea)tmp.getInfo();
					 if (Pact.getPrecedente().find(Lact)!=-1) {
						 Pact.getPrecedente().extract_o(Lact);
						 System.out.println("Se bloquea por precedencia el camino por "+Lact.conecta(Pact).getXY());
						 return false;
					 }
					 System.out.println("Se sigue el camino por "+Lact.conecta(Pact).getXY());
					 if(this.recorrer3(pri,Lact.conecta(Pact).getXY(), Lact, aco.copy().copy(),Ftmp ,FigsR)) {
						 break;
					 }
					 tmp=tmp.getNext();
				 }
				 return true;
				 
			 }
			
			 
			 
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

	public Figura getFtmp() {
		return Ftmp;
	}
}
