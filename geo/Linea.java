package geo;

public class Linea {
	Punto Inicio;
	Punto Fin;
	String Jugador;
	
	public Linea(int xy1,int xy2,Tablero T1) {
		//Verificación para que no se cruzen diagonales
		//Se pregunta a la esquina derecha superior o inferior si ya hay una diagonal allí
		int dif=Math.abs(xy1-xy2);
		
		//Si es diagonal la diferencia debe ser 9 o 11
		if (Math.abs(10-dif)==1) {
			Punto p1=T1.get(xy1);
			Punto p2=T1.get(xy2);
			//El xy mayor es el que debe evaluar si está bloqueado
			if (xy1>xy2) {
				if (!p1.isBloqueo_d()) {
					//Se bloquea la otra diagonal posible
					Punto paux=T1.get(xy1+10-dif);
					paux.setBloqueo_d(true);
					
					//Bloque para instanciar correctamente una linea dentro del tablero
					this.Inicio=T1.get(xy1);
					this.Fin=T1.get(xy2);
					Inicio.add(this);
					Fin.add(this);
				} else {
					System.out.println("ya ha una diagonal ahí: "+xy1);
				}
			} else{
				if (!p2.isBloqueo_d()) {
					//Se bloquea la otra diagonal posible
					System.out.println("se bloquea: "+(xy2+10-dif));
					Punto paux=T1.get(xy2+10-dif);
					paux.setBloqueo_d(true);
					
					//Bloque para instanciar correctamente una linea dentro del tablero
					this.Inicio=T1.get(xy1);
					this.Fin=T1.get(xy2);
					Inicio.add(this);
					Fin.add(this);
				} else {
					System.out.println("ya ha una diagonal ahí: "+xy2);
				}
			}
		}
		else {
			//Bloque para instanciar correctamente una linea dentro del tablero
			this.Inicio=T1.get(xy1);
			this.Fin=T1.get(xy2);
			Inicio.add(this);
			Fin.add(this);
		}
		
		
	}
	
	/**
	 * Dado un extremo de la línea, este método devuelve el otro
	 * @param P1
	 * @return el otro extremo de la linea
	 */
	public Punto conecta(Punto P1) {
		if (P1==Inicio) {
			return Fin;
		}
		else if (P1==Fin) {
			return Inicio;
		}
		else {
			return null;
		}
	}

	public Punto getInicio() {
		return Inicio;
	}

	public Punto getFin() {
		return Fin;
	}


}
