package prog2_ProjetGeenson;
import java.util.Scanner;
/**
 * C'est une classe qui crée une partie de jeu.
 * @author lichuyuan
 * @version 1.0
 */
public class Jeu {
	protected Plateau vuePlat;
	protected Joueur[] joueurs;
	/**
	 * Il construit un nouveau jeu avec un groupe de joueurs choisis
	 * @param players une liste de joueurs de type Joueur
	 */
	public Jeu(Joueur[] players) {
		System.out.println("------ Commencons le jeu ! --------\n");
		this.vuePlat = new Plateau();
		this.joueurs = players;
	}
	
	/**
	 * Afficher la situation de chaque joueurs dans cette partie de jeu
	 */
	public void afficher() {
		for(Joueur i : joueurs) {
			System.out.println(i);
		}
	}	
	
	/**
	 * Il permet de faire un tour de jeu pour chaque joueur
	 * @throws WordNotFoundException exception hérite de Exception pour indiquer le mot saisi n'est pas compris dans le jeu
	 */
	public void unTour() throws WordNotFoundException {
		for (Joueur jj: joueurs) {
			System.out.println("\n*** Tour de joueur "+jj.nom + " ***");
			jj.move();
			jj.guessWord();
		}
		System.out.println("\n*** Résumé ***");
		afficher();
	}
	 
	/**
	 * Méthode pour indiquer la fin du jeu
	 * @return true si le jeu est terminé, false sinon
	 */
	public boolean gameOver() {
		return winner() != null;
	}
	
	/**
	 * Indiquer le gagnant; sinon retourner null
	 * @return le joueur qui a gagné le jeu
	 */
	public Joueur winner() {
		Joueur joueur = null;
		for(Joueur j: joueurs) {
			if (j.position == vuePlat.nbCase-1) {
				System.out.println(j.nom + " a gagne !!!");
				return j;
			}
		}
		return joueur;
	}

	/**
	 * Demander rejouer quand une partie serait fini.
	 */
	public void rePlay() {
		String res = "";
		do {
			System.out.println("Jeu fini ! Voulez-vous jouer une autre partie ? (O/N)");
			Scanner sc = new Scanner(System.in);
			res = sc.next();
		}while (!res.toLowerCase().equals("o") && !res.toLowerCase().equals("n"));
		if(res.toLowerCase().equals("o")) {
			Lanceur l = new Lanceur();
		}
		else System.out.println("Au revoir !");	
	}

}
