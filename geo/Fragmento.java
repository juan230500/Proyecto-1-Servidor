package geo;
import adt.List;

public class Fragmento {
	private List Medios;
	private List Conex;
	private List Cp;
	private List Conexi;
	private List Cpi;
	
	public Fragmento(int xy1,int xy2,List Frags) {
		Conex=new List();
		Cp=new List();
		Conexi=new List();
		Cpi=new List();
		Medios=new List();
		Medios.insert(xy1);
		Medios.insert(xy2);
		Frags.insert(this);

	}
	
	public void add(boolean primero,int xy) {
		if (primero) {
			Medios.insert(xy);
		}
		else {
			Medios.insertf(xy);
		}
	}
	
	public int iscola(int xy) {
		if ((int)Medios.getFirst().getInfo()==xy) {
			return 1;
		}
		else if((int)Medios.getLast().getInfo()==xy) {
			return 2;
		}
		else {
			return -1;
		}
	}
	
	public void cnt(Fragmento F1,int pos,boolean call) {
		Conex.insert(F1);
		Cp.insert(pos);
		Conexi.insert(F1.getConex());
		Cpi.insert(F1.getConex());
		if (call) {
			return;
		}
		F1.cnt(this, pos, true);
	}

	public List getMedios() {
		return Medios;
	}

	public List getConex() {
		return Conex;
	}

	public List getCp() {
		return Cp;
	}

	public List getConexi() {
		return Conexi;
	}

	public List getCpi() {
		return Cpi;
	}
	
	
}
