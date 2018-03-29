package prog2_ProjetGeenson;

import pack.word2vec.Utilityw2v;

public class Lanceur {
	
	public Lanceur() {
		
		
		
	}
	
	//main
	public static void main(String[] args) {
		//traiter les arguments
		String chemin = (args[0].split("="))[1];
		String nbJ = args[1]; //need prepare a list of Joueurs and passe it to Lanceur()
		String nbTry = args[2];
		String deMagi = args[3];
		
		//preparer le utilitaire w2v
		Utilityw2v.readFile(chemin);
		
		
		//commencer le jeu
		//Lanceur l = new Lanceur();
	}

}
