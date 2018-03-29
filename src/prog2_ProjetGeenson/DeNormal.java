package prog2_ProjetGeenson;

import java.util.Random;

public class DeNormal extends De {
	
	public DeNormal() {
		this.type = "De_normal";
	}

	@Override
	//lancer un de normal aleatoirement de 1 a 6
	public int lancerDe() {
		Random rd = new Random();
		int de = rd.nextInt(6) + 1;
		return de;
	}
}
