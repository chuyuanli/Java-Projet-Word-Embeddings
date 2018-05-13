package prog2_ProjetGeenson;
import java.util.Random;
/**
 * Classe DeMagi hérite de De
 * @author lichuyuan
 */
public class DeMagi extends De {
	/**
	 * Il construit un dé magique
	 */
	public DeMagi() {
		this.type = "De_magique";
	}

	/**
	 * Méthode redéfinie : lancer un dé magique aléatoirement de 0 a 5.
	 * si tombe sur le point 0 : face piège "restez ou vous etes"
	 */
	public int lancerDe() {
		Random rd = new Random();
		int de = rd.nextInt(6);
		return de;
	}

}
