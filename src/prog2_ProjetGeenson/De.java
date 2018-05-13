package prog2_ProjetGeenson;
/**
 * C'est une classe abstraite de Dé avec une méthode abstraite lancerDé.
 * @author lichuyuan
 * @version 1.0
 */
public abstract class De {
	protected String type;
	
	/**
	 * C'est une méthode abstraite pour obtenir le point du dé
	 * @return le nombre du dé
	 */
	public abstract int lancerDe();
	
}
