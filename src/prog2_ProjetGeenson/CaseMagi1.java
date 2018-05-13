package prog2_ProjetGeenson;
/**
 * Classe CaseMagi1 hérite de Case. C'est une classe qui fait relancer le dé
 * @author lichuyuan
 *
 */
public class CaseMagi1 extends Case{
	/**
	 * Il construit une case magique de type1 (relancer le dé)
	 * @param i le numéro de case
	 */
	public CaseMagi1(int i) {
		this.nb = i;
		this.nom = "case_magi1";
	}
	
	/**
	 * Redéfinition de la méthode getMessage dans la classe mère
	 * @return un message qui demande à relancer le dé
	 */
	public String getMessage() {
		return "Relancer le de";
	}


}
