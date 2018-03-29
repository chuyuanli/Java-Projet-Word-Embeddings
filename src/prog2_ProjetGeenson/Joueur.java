package prog2_ProjetGeenson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Joueur {
	protected String nom;
	protected static int countJ = 1;
	protected int nbJ;
	protected Plateau plateau; //tous les joueurs jouent dans le meme plateau
	protected De de; //chaque joueur possede un de
	protected int position; //position ad hoc dans le plateau, commence par plateau[0]
	protected boolean pass; //si joueur veut choisir l'option pass
	protected int countPass; //en total 5 fois de passes seront autorisees
	protected final int nbTry; //nb max d'essai pour un mot, par default=3
	
	
	public Joueur(String pnom, Plateau plateau, De unDe, boolean lePass, int maxTry) {
		this.nom = pnom;
		this.plateau = plateau;
		this.nbJ = countJ;
		countJ ++;
		this.pass = lePass;
		if (this.pass) this.countPass = 0;
		if (maxTry <= 0) throw new IllegalAccessError("Max nb d'essai inferieur de 0 !");
		this.nbTry = maxTry;
		
	}
	
	//surcharge les constructeurs
	public Joueur(String pnom, Plateau plateau, De unDe, boolean lePass) {
		this(pnom, plateau, unDe, lePass, 3);
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
		return nbJ + ". " + this.nom + " Case: " + this.position + " " + de.type + " Pass:" + 
				this.countPass + "/" + count;
	}
	
	
	//lancer le de et avancer/rester
	public void move() {
		int moveTo = de.lancerDe();
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
	public void guessWord() {
		String motX = motADeviner();	
		//boucler 3/nbTry fois pour deviner un mot
		boolean correcte = false;
		boolean pass = false;
		int nbGuess = 1;	
		while (nbGuess <= this.nbTry && !correcte && !pass) {
			System.out.println("Essai No." + nbGuess + " :");
			pass = demandePass();
			if (!pass) {
				String[] dixMots = reponses();
				if(bingo(motX, dixMots)) {
					System.out.println("Bravo ! Mot X = " + motX);
					correcte = true;
				}
				nbGuess ++;
			}
		}
		System.out.println("Oops... Vous n'avez plus d'essai.\nTour au joueur suivant...");
	}
	
	
	//methode qui fournit un mot et demande(print) le joueur a deviner
	public String motADeviner() {
		
		//return a word
		return null;
	}
	
	
	//methode qui lit la reponse de joueur et fournit 10 mots les plus similaires(print out)
	//return un array de 10 mots
	public String[] reponses() {
		System.out.println("Veuillez entrer les 3 indices : ");
		
		String[] dixMots = new String[10];

		return dixMots;
	}
	
	
	//methode qui indique si le mot est bien dans les reponses proposes
	public boolean bingo(String mot, String[] answers) {
		return (Arrays.asList(answers).contains(mot));
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
