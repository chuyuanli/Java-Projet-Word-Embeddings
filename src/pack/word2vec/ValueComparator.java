package pack.word2vec;

import java.util.Comparator;
import java.util.Map;

class ValueComparator implements Comparator<String> {
    Map<String, Double> base;
    boolean cos;

    public ValueComparator(Map<String, Double> base, boolean cos) {
        this.base = base;
        this.cos = cos;
    }

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

