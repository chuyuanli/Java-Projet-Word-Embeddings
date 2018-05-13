package pack.word2vec;
import java.util.Comparator;
import java.util.Map;
/**
 * C'est un comparator implémentée de Comparator qui designe la comparaison de similarité
 * @author lichuyuan
 * @version 1.0
 * @see Comparator
 */
class ValueComparator implements Comparator<String> {
    Map<String, Double> base;
    boolean cos;
    /**
     * Il construit une base de comparaison, ainsi qu'une façon de calcul
     * @param base un map de mot et leurs vecteurs
     * @param cos si la similarité sera calculée en cosinus
     */
    public ValueComparator(Map<String, Double> base, boolean cos) {
        this.base = base;
        this.cos = cos;
    }

    /**
     * Méthode qui redéfinit la comparaison
     */
    public int compare(String a, String b) {
    		if(this.cos) {
	        if (base.get(a) >= base.get(b)) return -1;
	        else return 1;
    		}
    		else {//cos=false, utilise euclidien
    			if (base.get(a) >= base.get(b)) return 1;
    	        else return -1;
    		}
    }
   
} 

