package prog2_ProjetGeenson;
/**
 * Classe CaseMagi2 hérite de Case. C'est une classe qui fait reculer 3 cases
 * @author lichuyuan
 *
 */
public class CaseMagi2 extends Case {
	/**
	 * Il construit une case magique de type2 (reculer trois cases)
	 * @param i le numéro de case
	 */
	public CaseMagi2(int i) {
		this.nb = i;
		this.nom = "case_magi2";
	}

	/**
	 * Redéfinition de la méthode getMessage dans la classe mère
	 * @return un message qui demande à reculer trois cases
	 */
	public String getMessage() {
		return "Reculer de trois cases";
	}


}
