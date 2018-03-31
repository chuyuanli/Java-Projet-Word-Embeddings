package pack.word2vec;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Utilityw2v {
	private static String file;
	private static Map<String, double[]> w2v; //stocker <mot, vecs> paire
	private static ArrayList<String> chosenW = new ArrayList();
	
	//Instancier des qu'on lit un ficher w2v
	private Utilityw2v(String input) throws IOException {
		this.file = input;
		w2v = readFile(input);
	}
	
	public static int vocTaille() {
		return w2v.size();
	}
		
	public static Map<String, double[]> readFile(String input) throws IOException {
		FileReader fr = new FileReader(input);
		BufferedReader br = new BufferedReader(fr);
		String line;	
		
		w2v = new HashMap();	//creer un map pour stocker les mots et leurs vecteurs
		while((line = br.readLine()) != null) {		
			if(line.length() > 0) { //sauter la ligne vide 				
				String[] parties = line.split(" "); //separer le mot et les vecteurs
				String mot = parties[0];
				double[] vecteurs = new double[100];	//stocker les vecteurs dans un tableau double[100]			
				for (int i=1; i<parties.length; i++) {
					vecteurs[i-1] = Double.parseDouble(parties[i]);
				}			
				w2v.put(mot, vecteurs); //ajouter la paire (mot, vec) dans map
			}
		}
		return w2v;
	}
	
	
	public static double[] getVecs(String a) {
		if(w2v.containsKey(a)) return w2v.get(a);
		else throw new IllegalArgumentException("Mot "+a+" n'existe pas dans w2v.");
	}
	
	public static String toString(String a) {
		String vecs = "";
		for (double d: getVecs(a)) vecs += String.valueOf(d) + "\t";
		return vecs;
	}
	
	//sum les vecteurs du mot a, b et c, et retourne un nouveau vecteur
	public static double[] addition(String a, String b, String c) {
		double[] vecSum = new double[w2v.get(a).length];
		if (w2v.containsKey(a) && w2v.containsKey(b) && w2v.containsKey(c)) {
			for (int i=0; i<w2v.get(a).length; i++)
				vecSum[i] = w2v.get(a)[i] + w2v.get(b)[i] + w2v.get(c)[i];
			return vecSum;
		}
		else if(!w2v.containsKey(a)) throw new IllegalArgumentException("Mot " + a + "n'existe pas dans w2v.");
		else if(!w2v.containsKey(a)) throw new IllegalArgumentException("Mot " + b + "n'existe pas dans w2v.");
		else throw new IllegalArgumentException("Mot " + c + "n'existe pas dans w2v.");
	}
	
	
	//pareil que addition
	public static double[] soustraction(String a, String b) {
		double[] vecSoustra = new double[w2v.get(a).length];
		if (w2v.containsKey(a) && w2v.containsKey(b)) {
			for (int i=0; i<w2v.get(a).length; i++)
				vecSoustra[i] = w2v.get(a)[i] - w2v.get(b)[i];
			return vecSoustra;
		}
		else if(!w2v.containsKey(a)) throw new IllegalArgumentException("Mot " + a + "n'existe pas dans w2v.");
		else throw new IllegalArgumentException("Mot " + b + "n'existe pas dans w2v.");
	}

	
	//methode qui prend 3 Strings et retourne un espace vectoriel du moyen de ces mots
	public static double[] moyenne(String a, String b, String c) {
		double[] sumVec = addition(a, b, c);
		double[] newVec = new double[sumVec.length];
		for(int i=0;i<sumVec.length;i++) newVec[i] = sumVec[i] / 3;
		return newVec;
	}
	
	//multiplier deux espace vec et retourne un double score
	//l'argument motX est le moyen des 3 indices, a represent un mot dans w2v
	public static double multiplication(double[] motX, double[] a) {
		if(motX.length == a.length) { //verifier que les 2 espaces ont la meme taille
			double multi = 0.0;
			for(int i=0;i<motX.length;i++) multi += motX[i] * a[i];
			return multi;
		}
		else throw new IllegalArgumentException("Taille de vecteurs n'est pas egale.");
	}
	
	//calculer la norme d'un espace vec i.e: || b ||, retourne un double
	public static double norme(double[] vecs) {
		double somme = 0.0; double norme = 0.0;
		for(double vec: vecs) somme += Math.pow(vec, 2); 
		norme = Math.sqrt(somme);	
		return norme;
	}
	
	//pre-calculer le norme pour tous les mots dans w2v
	public static Map<String, Double> normeAll() {
		Map<String, Double> norme_all = new HashMap<>(); //Map n'accepte pas type primitive
		for (String mot: w2v.keySet()) {
			double a_norme = norme(w2v.get(mot));
			norme_all.put(mot, (Double)a_norme); //boxing double a Double	
		}
		return norme_all;
	}
	
	//methode pour calculer le score cosinus entre 2 mots = multi(a, b) / norme(a)*norme(b)
	public static double cosinus(double[] motX_vec, double[] a_vec) {
		double numerateur = multiplication(motX_vec, a_vec);
		double denominateur = norme(motX_vec) * norme(a_vec);		
		return numerateur / denominateur;
	}
	
	//package toutes les methodes cosinus, prend 3 indices et un Map de scores normes pre-calcules comme argument
	//retourne les 10 mots les plus similaires
	public static ArrayList<String> top10mots(String a, String b, String c, Map<String, Double> normes) {
		double[] moyenne_vec = moyenne(a, b, c);
		HashMap<String, Double> scores_cos = new HashMap<>(); //creer un map pour stocker <mot, score_cos>
		//calculer tous la similarite cosinus avec tous les mots dans w2v
		for (String mot: w2v.keySet()) {
			/*
			//possibilite 1, apeler directement cosinus
			double a_score = cosinus(moyenne_vec, w2v.get(mot));
			scores_cos.put(mot, (Double)a_score); 
			*/
			
			//possi 2, prend les normes pre-calculees dans l'argument, evider de repeter les normes de chaque mot
			double numerateur = multiplication(moyenne_vec, w2v.get(mot));
			double denominateur = norme(moyenne_vec) * normes.get(mot);
			Double a_score = (Double)(numerateur/denominateur); //boxing double a Double
			scores_cos.put(mot, a_score);
		}			
		TreeMap<String, Double> sorted_score = trierMap(scores_cos); 
		return nFirst(10, sorted_score); 
	}
	
	
	//creer un nouveau TreeMap et trier les scores
	public static TreeMap<String, Double> trierMap(HashMap<String, Double> untrier) {
		ValueComparator vc = new ValueComparator(untrier); //redefinir methode compare dans la classe ValueComparator
        TreeMap<String, Double> sorted_score = new TreeMap<String, Double>(vc);
        sorted_score.putAll(untrier);//copy tous les Entry de score cosinus dans sorted_w2v, il va trier selon la methode compare
        return sorted_score;
	}
	
	//prendre les n premiers mots dans TreeMap, retourner les 10 premiers mots dans une arrayList
	public static ArrayList<String> nFirst(int n, TreeMap<String, Double> sorted_score) {
		int count = 0;
		TreeMap<String,Double> target = new TreeMap<String,Double>();
		 for (Map.Entry<String,Double> entry:sorted_score.entrySet()) {
			 if (count >= n) break;
		     target.put(entry.getKey(), entry.getValue());
		     count ++;
		  }
		  System.out.println(target);
		  ArrayList<String> mots = new ArrayList<String>(target.keySet());
		  return mots;
	}
	
	
//	public static double euclidien() {
//		return a_score_euclidien;
//	}
	
	
	
	//methode pour choisir aleatoirement un mot et a faire deviner le joueur
	public static String giveWord() {
		Random random = new Random();
		ArrayList<String> keys = new ArrayList<String>(w2v.keySet());
		String motX;
		do {
			motX = keys.get(random.nextInt(keys.size()));
		}while(chosenW.contains(motX)); //evider les mots repretitifs
		chosenW.add(motX);
		System.out.println("Mot a deviner = " + motX);
		return motX;
	}
	
	//methode qui indique si le joueur a bien devine le mot
	public static boolean goodGuess(String motX, ArrayList<String> top10mots) {		
		return top10mots.contains(motX);
	}
	
	
	

}
