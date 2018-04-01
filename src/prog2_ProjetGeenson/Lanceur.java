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
		
	public static void main(String[] args) throws IOException {
		//PARTIE 1. TRAITER LES ARGUMENTS
		//String chemin = (args[0].split("="))[1];
//		String nbJ = args[1]; //need prepare a list of Joueurs and passe it to Lanceur()
//		String nbTry = args[2];
//		String deMagi = args[3];
		
		
		String chemin = args[0];
		System.out.println(chemin+"\n");
			
		//PARTIE 2.
		//preparer le utilitaire w2v
		Utilityw2v.readFile(chemin);
		//System.out.println(Utilityw2v.vocTaille());	
	
		//calculer les normes
		System.out.println("Pre-calculer les normes...");
		Map<String, Double> normes = Utilityw2v.normeAll();
		System.out.println("Finit pre-calculer les normes.\n");
		//choisir aleatoirement un mot a deviner
		String motX = Utilityw2v.giveWord();
		double[] motX_vecs = Utilityw2v.getVecs(motX);
		//fixer les parametres
		int n = 10;
		boolean cos = true;
		//calculer moyen
		double[] moyenne = Utilityw2v.moyenne("calcul", "clavier", "écran");
		//calculer sum
		double[] sum = Utilityw2v.addition("calcul", "clavier", "écran");
		//sortir les N premiers mots
		ArrayList<String> topNmots = Utilityw2v.topNmots(sum, normes, n, cos);
		//tester le resultat
		if(Utilityw2v.goodGuess("ordinateur", topNmots)) System.out.println("Bingo !");
		else System.out.println("Oops..");
		
		//tester: (fille - mere + fils) ~ pere: le mot "pere" est bien dans les 10 premiers mots
//		double[] result = Utilityw2v.sous_Add("fille", "maman", "fils");
//		Utilityw2v.topNmots(result, normes, 10);
		
		//PARTIE 3. LANCER LE JEU
	}

}

//NOTES:
//quand calculer cosinus, moyenne ou addition donnent le meme resultat, car cosinus mesure the angle
//mais quand calculer euclidienne, moyenne et sum donnent difference

