package prog2_ProjetGeenson;

import java.util.Scanner;

public class Jeu {
	protected Plateau vuePlat;
	protected Joueur[] joueurs;
	
	public Jeu(Joueur[] players) {
		System.out.println("------ Commencons le jeu ! --------\n");
		this.vuePlat = new Plateau();
		this.joueurs = players;
	}

	public void afficher() {
		for(Joueur i : joueurs) {
			System.out.println(i);
		}
	}	
	
	//methode pour faire un tour
	public void unTour() throws WordNotFoundException {
		for (Joueur jj: joueurs) {
			jj.move();
			jj.guessWord();
		}
		//afficher();
	}
	 
	//pour indiquer la fin du jeu
	public boolean gameOver() {
		return winner() != null;
	}
	
	//indiquer le gagnant
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

	//demander rejouer
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
