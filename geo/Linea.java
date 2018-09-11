package geo;
import adt.List;

public class Linea {
	Punto Inicio;
	Punto Fin;
	
	public Linea(int xy1,int xy2,Tablero T1) {
		Punto Ext1=T1.get(xy1);
		Punto Ext2=T1.get(xy2);
		this.Inicio=Ext1;
		this.Fin=Ext2;
		
		List Frags1=Ext1.getFragmentos();
		List Frags2=Ext2.getFragmentos();
		

		if (Frags1.getSize()==0 && Frags2.getSize()==0) {
			Fragmento F1=new Fragmento(xy1,xy2);
			Ext1.add(F1);
			Ext2.add(F1);
		}
		
		else if(Frags1.getSize()==1 && Frags2.getSize()==0) {
			Fragmento tmp= (Fragmento)Frags1.get(0);
			if (tmp.iscola(xy1)==1) {
				tmp.add(true, xy2);
				tmp.getMedios().print();
				Ext2.add(tmp);
				
			}
			else if (tmp.iscola(xy1)==2) {
				tmp.add(false, xy2);
				tmp.getMedios().print();
				Ext2.add(tmp);
			}
			else {
				Fragmento F1=new Fragmento(xy1,xy2);
				Ext1.add(F1);
				Ext2.add(F1);
				F1.getMedios().print();
			}
			
		}
		
		else if(Frags2.getSize()==1 && Frags1.getSize()==0) {
			Fragmento tmp= (Fragmento)Frags2.get(0);
			if (tmp.iscola(xy2)==1) {
				tmp.add(true, xy1);
				tmp.getMedios().print();
				Ext1.add(tmp);
				
			}
			else if (tmp.iscola(xy2)==2) {
				tmp.add(false, xy1);
				tmp.getMedios().print();
				Ext1.add(tmp);
			}
			else {
				Fragmento F1=new Fragmento(xy1,xy2);
				Ext1.add(F1);
				Ext2.add(F1);
				F1.getMedios().print();
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
