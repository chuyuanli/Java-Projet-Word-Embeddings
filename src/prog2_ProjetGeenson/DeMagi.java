package prog2_ProjetGeenson;

import java.util.Random;

public class DeMagi extends De {
	
	public DeMagi() {
		this.type = "De_magique";
	}

	@Override
	//lancer un de magique aleatoirement de 0 a 5, si point=0, face piege "restez ou vous etes"
	public int lancerDe() {
		Random rd = new Random();
		int de = rd.nextInt(6);
		return de;
	}

}
