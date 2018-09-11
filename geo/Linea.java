package geo;
import adt.List;

public class Linea {
	Punto Inicio;
	Punto Fin;
	
	public Linea(int xy1,int xy2,Tablero T1, List Frags) {
		Punto Ext1=T1.get(xy1);
		Punto Ext2=T1.get(xy2);
		this.Inicio=Ext1;
		this.Fin=Ext2;
		
		List Frags1=Ext1.getFragmentos();
		List Frags2=Ext2.getFragmentos();
		
		int t1=Frags1.getSize();
		int t2=Frags2.getSize();
		

		if (t1==0 && t2==0) { //CASO 0-0
			Fragmento F1=new Fragmento(xy1,xy2,Frags);
			Ext1.add(F1);
			Ext2.add(F1);
		}
		
		else if(t1==1 && t2==0) { //CASO 1-0
			Fragmento tmp= (Fragmento)Frags1.get(0);
			if (tmp.iscola(xy1)==1) {
				tmp.add(true, xy2);
				Ext2.add(tmp);
				
			}
			else if (tmp.iscola(xy1)==2) {
				tmp.add(false, xy2);
				Ext2.add(tmp);
			}
			else {
				Fragmento F1=new Fragmento(xy1,xy2,Frags);
				Ext1.add(F1);
				Ext2.add(F1);
				F1.cnt(tmp, xy1,false);
			}
			
		}
		
		else if(t2==1 && t1==0) { //CASO 0-1
			Fragmento tmp= (Fragmento)Frags2.get(0);
			if (tmp.iscola(xy2)==1) {
				tmp.add(true, xy1);
				Ext1.add(tmp);
				
			}
			else if (tmp.iscola(xy2)==2) {
				tmp.add(false, xy1);
				Ext1.add(tmp);
			}
			else {
				Fragmento F1=new Fragmento(xy1,xy2,Frags);
				Ext1.add(F1);
				Ext2.add(F1);
				F1.cnt(tmp, xy2,false);
			}
			
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
