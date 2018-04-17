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
		
	public static void main(String[] args) throws IOException, WordNotFoundException {
		//PARTIE 1. TRAITER LES ARGUMENTS
		System.out.println("\n------ Bienvenu au jeu Geenson -------\n");
		String w2v = "";		
		//arguments optionnels, les valeurs par default
		int nbJoueur = 2;
		int nbTry = 3;
		int kRepond = 10;
		int nbCase = 24;
		boolean deMagi = false;
		boolean cos = true;
		boolean pass = false;
		Plateau plateau = new Plateau();
		De use_de = new DeNormal();
		Joueur[] joueurs = new Joueur[nbJoueur];
		
		for (int i=0; i<args.length; i++) {
			String[] arg = args[i].split("=");
			if (arg[0].equals("w2v")) w2v = arg[1];
			else if(arg[0].equals("nbJoueur")) {
				try {
				nbJoueur = Integer.parseInt(arg[1]);
				}catch(NumberFormatException e) {
					e.getMessage();
				};
				if(nbJoueur <= 2) throw new IllegalArgumentException("Nb joueurs inferieur de 2 !");
			}
			else if(arg[0].equals("nbTry")) {
				try {
				nbTry = Integer.parseInt(arg[1]);
				}catch(NumberFormatException e) {e.getMessage();};	
			}
			else if(arg[0].equals("kRespond")){
				try {
				kRepond = Integer.parseInt(arg[1]);
				}catch(NumberFormatException e) {e.getMessage();};	
			}
			else if(arg[0].equals("nbCase")){
				try {
				nbCase = Integer.parseInt(arg[1]);
				}catch(NumberFormatException e) {e.getMessage();};
				plateau = new Plateau(nbCase); //instancier le plateau
			}
			else if(arg[0].equals("deMagi")) {
				deMagi = Boolean.parseBoolean(arg[1]);//pour boolean, pas besoin try..catch, ortho fault va considerer comme false
			}	
			else if(arg[0].equals("cos")) cos = Boolean.parseBoolean(arg[1]);
			else if(arg[0].equals("pass")) pass = Boolean.parseBoolean(arg[1]);
		}
		
		//demander les noms de joueurs
		String[] noms = new String[0];
		do{
			System.out.println("Vous avez choisi "+ nbJoueur + " joueurs.\n" +
					"Veuillez saisir les noms des joueurs, separez par espace");
			Scanner sc = new Scanner(System.in);
			noms = sc.nextLine().split(" ");
		}while (noms.length != nbJoueur);
		
		//afficher les parametres que les joueurs choisissent
		System.out.println("\n*** PARAMETRES DU JEU ***");
		String de = deMagi ? "De magique":"De normal";
		String p = pass ? "Pass autorise (5 fois max)" : "Pas de pass";
		String metri = cos ? "Similarite cosinus" : "Distance euclidienne";
		System.out.println("NB JOUEURS : " + nbJoueur + "\nDE UTILISE :" + de + "\nNB TRY : " + nbTry + "\nPASS : "
				+ p + "\nNB K : " + kRepond + "\nMETRIQUE  : " + metri + "\nNB CASE : " + nbCase + "\n");				
		//preparer le utilitaire w2v
		Utilityw2v.readFile(w2v);	
		//calculer et preparer les normes
		System.out.println("Pre-calculer les normes...");
		Map<String, Double> normes = Utilityw2v.normeAll();
		System.out.println("Finit pre-calculer les normes.\n");
		
		System.out.println("*** FIN DE PARAMETRES ***\n");
		
		//instancier chaque joueurs
		for(int i=0; i<nbJoueur; i++) {
			joueurs[i] = new Joueur(noms[i], plateau, deMagi? new DeMagi() : new DeNormal(), pass, nbTry, cos, kRepond);
		}

		//PARTIE 2. LANCER LE JEU
		Jeu jeu = new Jeu(joueurs);
		jeu.afficher();
		
		while(!jeu.gameOver()) {
			System.out.println("Appuyer sur enter pour continuer");
			Scanner scanner = new Scanner(System.in);
			String s = scanner.nextLine(); 
			while (s.length()!=0) {
				System.out.println("Veuillez taper que sur Enter pour continuer");
				s = scanner.nextLine();	
			}
			jeu.unTour();
		}
		if (jeu.gameOver()) jeu.rePlay();			
	
	}//end of main
	
}//end of class Lanceur



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