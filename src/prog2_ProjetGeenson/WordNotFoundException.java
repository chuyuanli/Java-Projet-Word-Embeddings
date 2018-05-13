package prog2_ProjetGeenson;
/**
 * C'est une classe d'exception héritée de Exception
 * @author lichuyuan
 * @version 1.0
 */
public class WordNotFoundException extends Exception {
	/**
	 * Héritage de Exception
	 */
	public WordNotFoundException() {}
	
	/**
	 * Surcharge de l'exception avec un message affiché
	 * @param s Le message affiché quand l'exception est appelée
	 */
	public WordNotFoundException(String s) {
		System.out.println(s);
	}
	
}
