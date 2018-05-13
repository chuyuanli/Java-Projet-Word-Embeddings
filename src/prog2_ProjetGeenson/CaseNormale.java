package prog2_ProjetGeenson;
/**
 * Classe CaseNormale hérite de Case. C'est une classe qui n'a pas de particularité
 * @author lichuyuan
 *
 */
public class CaseNormale extends Case {
	/**
	 * Il construit une case normale
	 * @param i le numéro de case
	 */
	public CaseNormale(int i) {
		this.nb = i;
		this.nom = "case_normale";
	}
	
	/**
	 * Redéfinition de la méthode getMessage dans la classe mère
	 * @return un message indique que c'est une case normale
	 */
	public String getMessage() {
		return "Case normale.";
	}


}
