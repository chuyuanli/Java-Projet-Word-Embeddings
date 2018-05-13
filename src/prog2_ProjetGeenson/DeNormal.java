package prog2_ProjetGeenson;
import java.util.Random;
/**
 * Classe DeNormal hérite de De
 * @author lichuyuan
 */
public class DeNormal extends De {
	/**
	 * Il construit un dé normal
	 */
	public DeNormal() {
		this.type = "De_normal";
	}

	
	/**
	 * Méthode redéfinie : lancer un dé magique aléatoirement de 1 a 6
	 */
	public int lancerDe() {
		Random rd = new Random();
		int de = rd.nextInt(6) + 1;
		return de;
	}
}
