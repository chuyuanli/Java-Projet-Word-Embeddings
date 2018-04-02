package prog2_ProjetGeenson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import pack.word2vec.Utilityw2v;

public class Lanceur {
		
	public static void main(String[] args) throws IOException {
		//PARTIE 1. TRAITER LES ARGUMENTS
		//String chemin = (args[0].split("="))[1];
//		String nbJ = args[1]; //need prepare a list of Joueurs and passe it to Lanceur()
//		String nbTry = args[2];
//		String deMagi = args[3];
		for (String i: args) System.out.println(i);
		
		//arguments obligatoires
		String w2v;
		int nbJoueur;
		String nomJoueur;
		//arguments optionnels
		int nbTry;//default=3
		int kRepond;//par default=10
		boolean deMagi;//false
		boolean cos;//true
		boolean pass;//false
		
		
		
//		String chemin = args[0];
//		System.out.println(chemin+"\n");
//			
//		//PARTIE 2.
//		//preparer le utilitaire w2v
//		Utilityw2v.readFile(chemin);	
//		//calculer et preparer les normes
//		System.out.println("Pre-calculer les normes...");
//		Map<String, Double> normes = Utilityw2v.normeAll();
//		System.out.println("Finit pre-calculer les normes.\n");
//		
//
//		int nbPlayer = 2;
//		//PARTIE 3. LANCER LE JEU
//		System.out.println("------ Bienvenu au jeu Geenson -------");
//		
//		Jeu jj = new Jeu(nbPlayer);
//		jj.afficher();
//		
//		while(!jj.gameOver()) {
//			System.out.println("Appuyer sur enter pour continuer");
//			Scanner scanner = new Scanner(System.in);
//			String s = scanner.nextLine(); 
//			while (s.length()!=0) {
//				System.out.println("Veuillez taper que sur Enter pour continuer");
//				s = scanner.nextLine();	
//			}
//			jj.unTour();
//		}
//		if (jj.gameOver()) jj.rePlay();	
	
	}//end of main

}

//NOTES:
//quand calculer cosinus, moyenne ou addition donnent le meme resultat, car cosinus mesure the angle
//mais quand calculer euclidienne, moyenne et sum donnent difference

//tester: (fille - mere + fils) ~ pere: le mot "pere" est bien dans les 10 premiers mots
//double[] result = Utilityw2v.sous_Add("fille", "maman", "fils");
//Utilityw2v.topNmots(result, normes, 10);

/*
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
*/