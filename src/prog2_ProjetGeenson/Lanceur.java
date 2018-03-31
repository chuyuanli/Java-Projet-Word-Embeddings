package prog2_ProjetGeenson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import pack.word2vec.Utilityw2v;

public class Lanceur {
	
	public Lanceur() {
		
		
		
	}
	
	//main
	public static void main(String[] args) throws IOException {
		//traiter les arguments
		//String chemin = (args[0].split("="))[1];
//		String nbJ = args[1]; //need prepare a list of Joueurs and passe it to Lanceur()
//		String nbTry = args[2];
//		String deMagi = args[3];
		
		
		String chemin = args[0];
		System.out.println(chemin+"\n");
				
		//preparer le utilitaire w2v
		Utilityw2v.readFile(chemin);
//		System.out.println(Utilityw2v.vocTaille());	
//		String s1 = "ordinateur";
//		double[] s1_vecs = Utilityw2v.getVecs(s1);
//		double norme = Utilityw2v.norme(s1_vecs);
//		System.out.println("Ordinateur norme = " + norme + "\n");
		
		
		
		Map<String, Double> normes = Utilityw2v.normeAll();
		System.out.println("Finit pre-calculer les normes.\n");
		
		String motX = Utilityw2v.giveWord();

		ArrayList<String> top10mots = Utilityw2v.top10mots("calcul", "clavier", "écran", normes);
		
		if(Utilityw2v.goodGuess(motX, top10mots)) System.out.println("Bingo !");
		else System.out.println("Oops..");
		

		//commencer le jeu
		//Lanceur l = new Lanceur();
	}

}


