package prog2_ProjetGeenson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import pack.word2vec.Utilityw2v;
/**
 * C'est une classe qui décrit un joueur. 
 * @author lichuyuan
 * @version 1.0
 */
public class Joueur {
	protected String nom;
	protected static int countJ = 1;
	protected int nbJ;
	protected Plateau plateau;
	protected De de;
	protected int position; //position ad hoc dans le plateau, commence par plateau[0]
	protected boolean pass;
	protected int countPass;
	protected final int nbTry;
	protected Map<String, Double> normes = Utilityw2v.normeAll();//pareil pour tous les joueurs
	protected boolean cos;
	protected int n;
	private String motAdeviner=null;
	
	/**
	 * Il construit un joueur avec son nom, le plateau qu'il se situe, le dé qu'il possède, 
	 * la permission de passe, le nombre maximal d'eesai, le calcul cosinus ou euclidien,
	 * et le nombre de mots qu'il prend en compte pour calculer la similarité.
	 * @param pnom le nom de joueur
	 * @param plateau le plateau qu'il se situe; tous les joueurs jouent dans le meme plateau
	 * @param unDe un dé qu'un joueur possède 
	 * @param lePass si le joueur a l'option pour passer un mot (en total 5 passes seront autorisees)
	 * @param maxTry nb max d'essai pour un mot, par default=3
	 * @param cos si le joueur choisit la similarite cos, par default = true
	 * @param n prendre les n premiers mots calcules comme reponses, par default = 10
	 */
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
	
	/**
	 * Surcharge le constructeur. Il construit le nom de joueur, le plateau qu'il se situe, le dé qu'il possède et l'option de passe;
	 * par défaut, le nombre d'essai = 3, le nombre de mots pris en compte = 10
	 * @param pnom le nom de joueur
	 * @param plateau tous les joueurs jouent dans le meme plateau
	 * @param unDe un dé qu'un joueur possède 
	 * @param lePass si le joueur a l'option pour passer un mot (en total 5 passes seront autorisees)
	 */
	public Joueur(String pnom, Plateau plateau, De unDe, boolean lePass) {
		this(pnom, plateau, unDe, lePass, 3, true, 10);
	}	
	
	/**
	 * Surcharge le constructeur. Il construit le nom de joueur, le plateau qu'il se situe, le dé qu'il possède;
	 * par défaut, le nombre d'essai = 3, le nombre de mots pris en compte = 10, pas d'option passe
	 * @param pnom le nom de joueur
	 * @param plateau tous les joueurs jouent dans le meme plateau
	 * @param unDe un dé qu'un joueur possède 
	 */
	public Joueur(String pnom, Plateau plateau, De unDe) {
		this(pnom, plateau, unDe, false);
	}
	
	/**
	 * Surcharge le constructeur. Il construit le nom de joueur, le plateau qu'il se situe;
	 * par défaut, le nombre d'essai = 3, le nombre de mots pris en compte = 10, pas d'option passe, dé normal
	 * @param pnom le nom de joueur
	 * @param plateau tous les joueurs jouent dans le meme plateau
	 */
	public Joueur(String pnom, Plateau plateau) {
		this(pnom, plateau, new DeNormal());
	}
	
	
	/**
	 * Afficher les informations de joueur 
	 */
	public String toString() {
		int count = 0;
		if (this.pass) count = 5;
		return nbJ + "." + this.nom + " Position case: " + this.position + " De : " +this.de.type + " Pass:" + 
				this.countPass + "/" + count ;
	}
	
	/**
	 * Donner un dé
	 * @param unDe
	 */
	public void giveDe(De unDe) {
		this.de = unDe;
	}

	/**
	 * Lancer le dé et avancer ou rester
	 */
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
	
	
	/**
	 * Processus d'un tour pour deviner un mot
	 * @throws WordNotFoundException
	 */
	public void guessWord() throws WordNotFoundException {
		this.motAdeviner= motADeviner();	
		boolean correcte = false;
		boolean pass = false;
		int nbGuess = 1;	
		while (nbGuess <= this.nbTry && !correcte && !pass) {
			System.out.println("Essai No." + nbGuess + " :");
			if(this.pass) pass = demandePass(); //si le joueur choisit l'option pass, lui demande s'il veut l'utiliser avant chaque essai
			if (!pass) {
				ArrayList<String> dixMots = reponses();
				if(bingo(motAdeviner, dixMots)) {
					System.out.println("Bravo ! Vous avez bien devine le mot : " + motAdeviner+"\nVous continuez à jouer !");
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
	
	/**
	 * Méthode qui fournit un mot et demander(print) le joueur à deviner
	 * @return le mot à deviner
	 */
	public String motADeviner() {
		System.out.println("\nMot à deviner est pret !");
		return Utilityw2v.giveWord();
	}
	
	/**
	 * Méthode qui lit la réponse de joueur et fournit 10 mots les plus similaires (print out) 
	 * @return un array de 10 mots
	 */
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
			System.out.println("Veuillez entrer les 3 indices différents entre eux, et différents du mot à deviner : ");
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
			
			
		}while (motInconu || ind1.equals(ind2)||ind1.equals(ind3)||ind3.equals(ind2)||ind1.equals(motAdeviner)||ind2.equals(motAdeviner)||ind3.equals(motAdeviner)); //assurer que le joueur entre 3 indices et que ces 3 indices existent dans w2v
		
		//si tous les 3 indices existent dans w2v, calculer la moyenne
		double[] moyenne = Utilityw2v.moyenne(ind1, ind2, ind3);
		//calculer sum
		//double[] sum = Utilityw2v.addition("calcul", "clavier", "écran");
		//sortir les N premiers mots
		ArrayList<String> topNmots = Utilityw2v.topNmots(moyenne, normes, n, cos);
		return topNmots;	
	}
	
	/**
	 * Méthode qui indique si le mot à deviner est bien dans les réponses proposées
	 * @param mot le mot à deviner
	 * @param answers les n mots les plus proches des indices d'après le calcul
	 * @return true si le mot à deviner est bien dans la liste; sinon false
	 */
	public boolean bingo(String mot, ArrayList<String> answers) {
		return (answers.contains(mot));
	}
	
	/**
	 * Méthode qui demande le joueur avant chaque essai s'il veut passer ce tour 
	 * @return true si le joueur veut passer ce mot; sinon false
	 */
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
