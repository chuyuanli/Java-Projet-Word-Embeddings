package prog2_ProjetGeenson;

/**
 * C'est une classe abstraite de Case avec une méthode abstraite getMessage.
 * @author lichuyuan
 * @version 1.0
 */
public abstract class Case {
	protected String nom;
	protected int nb;
	
	/**
	 * C'est une méthode abstraite qui revient un String pour indiquer quel est la fonctin de case.
	 * @return le message donné par une case particulère
	 */
	public abstract String getMessage();

}
