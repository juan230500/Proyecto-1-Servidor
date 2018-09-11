package geo;
import adt.List;

public class Fragmento {
	private List Medios;
	
	public Fragmento(int xy1,int xy2) {
		List medios=new List();
		Medios=medios;
		Medios.insert(xy1);
		Medios.insert(xy2);

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

	public List getMedios() {
		return Medios;
	}
	
	
}
