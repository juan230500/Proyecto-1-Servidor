package geo;

public class Linea {
	Punto Inicio;
	Punto Fin;
	String Jugador;
	
	public Linea(int xy1,int xy2,Tablero T1) {
		this.Inicio=T1.get(xy1);
		this.Fin=T1.get(xy2);
		Inicio.add(this);
		Fin.add(this);
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
