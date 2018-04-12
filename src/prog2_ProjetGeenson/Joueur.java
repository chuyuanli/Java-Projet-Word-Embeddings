package prog2_ProjetGeenson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import javax.sound.midi.VoiceStatus;

import pack.word2vec.Utilityw2v;

public class Joueur {
	protected String nom;
	protected static int countJ = 1;
	protected int nbJ;
	protected Plateau plateau; //tous les joueurs jouent dans le meme plateau
	protected De de; //chaque joueur possede un de
	protected int position; //position ad hoc dans le plateau, commence par plateau[0]
	protected boolean pass; //si joueur veut choisir l'option pass
	protected int countPass; //en total 5 passes seront autorisees
	protected final int nbTry; //nb max d'essai pour un mot, par default=3
	protected Map<String, Double> normes = Utilityw2v.normeAll();//pareil pour tous les joueurs
	protected boolean cos; //si le joueur choisit la similarite cos, par default = true
	protected int n; //prendre les n premiers mots calcules comme reponses, par default = 10
	
	
	public Joueur(String pnom, Plateau plateau, De unDe, boolean lePass, int maxTry, boolean cos, int n) {
		this.nom = pnom;
		this.plateau = plateau;
		this.de = unDe;
		this.nbJ = countJ;
		countJ ++;
		this.pass = lePass;
		if (this.pass) this.countPass = 0;
		if (maxTry <= 0) throw new IllegalAccessError("Max nb d'essai inferieur de 0 !");
		this.nbTry = maxTry;
		this.cos = cos;
		this.n = n;
	}
	
	//surcharge les constructeurs
	public Joueur(String pnom, Plateau plateau, De unDe, boolean lePass) {
		this(pnom, plateau, unDe, lePass, 3, true, 10);
	}	
	
	public Joueur(String pnom, Plateau plateau, De unDe) {
		this(pnom, plateau, unDe, false);
	}
	
	public Joueur(String pnom, Plateau plateau) {
		this(pnom, plateau, new DeNormal());
	}
	
	
	//afficher les informations de joueur
	public String toString() {
		int count = 0;
		if (this.pass) count = 5;
		return nbJ + "." + this.nom + " Position case: " + this.position + " De : " +this.de.type + " Pass:" + 
				this.countPass + "/" + count ;
	}
	
	public void giveDe(De unDe) {
		this.de = unDe;
	}

	
	//lancer de et avancer/rester
	public void move() {
		int moveTo = de.lancerDe();
		System.out.println("Lancer dé ! De = " + moveTo);
		//si c'est un de magique et il tombe sur la face piege, alors on pause un tour
		if(moveTo == 0) { 
			System.out.println("Face piege! Restez ou vous etes!");
		}
		else if(this.position + moveTo < plateau.nbCase-1) {
			this.position += moveTo;
		}
		//si le point de plus position actuelle est egale ou superieur que le nb case, on gagne
		else if(this.position + moveTo >= plateau.nbCase-1) {
			this.position = plateau.nbCase-1;
		}
	}
	
	
	//processus d'un tour pour deviner un mot (package les 4 methodes suivantes)
	public void guessWord() throws WordNotFoundException {
		String motX = motADeviner();	
		boolean correcte = false;
		boolean pass = false;
		int nbGuess = 1;	
		while (nbGuess <= this.nbTry && !correcte && !pass) {
			System.out.println("Essai No." + nbGuess + " :");
			if(this.pass) pass = demandePass(); //si le joueur choisit l'option pass, lui demande s'il veut l'utiliser avant chaque essai
			if (!pass) {
				ArrayList<String> dixMots = reponses();
				if(bingo(motX, dixMots)) {
					System.out.println("Bravo ! Vous avez bien devine le mot : " + motX +"\nVous continuez à jouer !");
					guessWord(); //si le joueur a bien devine, il rejoue
				}
				else System.out.println("Le mot à deviner n'est pas dans la liste. Veuillez reessayer...");
				nbGuess ++;
			}
			else if(pass && this.countPass < 5) {
				this.countPass ++;
				System.out.println("Vous avez utilise " + countPass + " passes.");
				guessWord();
			}
			else if(pass && this.countPass >= 5) {
				System.out.println("Vous ne pouvez pas passer ce mot. Veuillez saisir des indices :");
				pass = false;
			}
		}
		if(nbGuess == this.nbTry+1) System.out.println("Oops... Vous n'avez plus d'essai.\nTour au joueur suivant !");
	}
	
	
	//methode qui fournit un mot et demande(print) le joueur a deviner
	public String motADeviner() {
		System.out.println("\nMot à deviner est pret !");
		return Utilityw2v.giveWord();
	}
	
	
	//methode qui lit la reponse de joueur et fournit 10 mots les plus similaires(print out)
	//return un array de 10 mots
	public ArrayList<String> reponses(){
		//initialiser les 3 indices
		String ind1 = null;
		String ind2 = null;
		String ind3 = null;
		boolean motInconu = false;
		String[] parts = new String[0];
		String res = "";
		//traiter les reponses du joueur, 2 boucles while pour assurer le nb d'arguments et les mots saisis sont bien dans w2v
		do {
			do { //ici on ajoute un boucle pour evider ArrayIndexOutOfRange Exception
			System.out.println("Veuillez entrer les 3 indices : ");
			Scanner sc = new Scanner(System.in);
			res = sc.nextLine();
			parts = res.split(" ");
			}while(parts.length != 3);	
		
			ind1 = parts[0];
			ind2 = parts[1];
			ind3 = parts[2];
		
			if(!Utilityw2v.voc().contains(ind1) || ! Utilityw2v.voc().contains(ind2) || ! Utilityw2v.voc().contains(ind3)) {
				//preciser lequel mot n'existe pas dans w2v
				if(!Utilityw2v.voc().contains(ind1)) System.out.println("Le mot \'" + ind1 + "\' que vous avez saisi n'existe pas dans w2v...");
				else if (! Utilityw2v.voc().contains(ind2)) System.out.println("Le mot \'" + ind2 + "\' que vous avez saisi n'existe pas dans w2v...");
				else System.out.println("Le mot \'" + ind3 + "\' que vous avez saisi n'existe pas dans w2v...");
				motInconu = true;
				}
			else {motInconu = false;}		
		}while (motInconu); //assurer que le joueur entre 3 indices et que ces 3 indices existent dans w2v
		
		//si tous les 3 indices existent dans w2v, calculer la moyenne
		double[] moyenne = Utilityw2v.moyenne(ind1, ind2, ind3);
		//calculer sum
		//double[] sum = Utilityw2v.addition("calcul", "clavier", "écran");
		//sortir les N premiers mots
		ArrayList<String> topNmots = Utilityw2v.topNmots(moyenne, normes, n, cos);
		return topNmots;	
	}
	
	
	//methode qui indique si le mot est bien dans les reponses proposes
	public boolean bingo(String mot, ArrayList<String> answers) {
		return (answers.contains(mot));
	}
	
	//methode qui demande le joueur avant chaque essai s'il veut passer ce tour
	public boolean demandePass() {
		String res = "";
		do {
		System.out.println("Vous voulez passer ce mot ? (O/N)");
		Scanner sc = new Scanner(System.in);
		res = sc.next();
		}while (!res.toLowerCase().equals("o") && !res.toLowerCase().equals("n"));
		return (res.toLowerCase().equals("o"));
	}
	
	

}
