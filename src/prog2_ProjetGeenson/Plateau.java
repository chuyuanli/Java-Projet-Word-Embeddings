package prog2_ProjetGeenson;

import java.util.ArrayList;
import java.util.Collections;

public class Plateau {
	protected Case[] plateau;
	protected int nbCase;
	
	//version nb de case choisit par joueur
	public Plateau(int newNbCase) {
		if(newNbCase < 24) throw new IllegalArgumentException("Erreur: nb de case inferieur de 24 !");
		this.nbCase = newNbCase;
		this.plateau = new Case[nbCase];
		//d'abord initialiser toutes les cases comme case normale
		for(int i=0; i<nbCase; i++) plateau[i] = new CaseNormale(i);
		//ensuite apeler methode generate magique case
		generateMagiCase();	
	}
	
	//surcharge constructeur, version standard, nb de case=24
	public Plateau() {
		this(24);
	}
	
	//methode pour choisir aleatoirement choisir 6 cases magiques dont 3 de type magi1 et 3 de type magi2
	public void generateMagiCase() {
		ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<nbCase; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        //var. pointeur pour indiquer l'indice dans arraylist
        int pointeur;
        //prend les 3 premieres numeros comme case magi1 (relancer le de)
        for (pointeur=0; pointeur<3; pointeur++) {
        		int nbCaseMagi1 = list.get(pointeur);
            plateau[nbCaseMagi1] = new CaseMagi1(nbCaseMagi1);
        }
        pointeur ++;
        //prend les 3 numeros suivants comme case magi2(reculer trois cases), verifier le nb de case est bien > 3
        //var. count pour verifier si on bien distribue 3 case de type magi2
        int count = 1;
        while(count <= 3) {
        		int nbCaseMagi2 = list.get(pointeur);
        		if(nbCaseMagi2 >= 3) {
        			plateau[nbCaseMagi2] = new CaseMagi2(nbCaseMagi2);
        			count ++;
        		}
        		pointeur ++;
        }
	}
	
	//print les cases magiques
	public void caseSpecial() {
		for(int i=0; i<nbCase;i++) {
			if (!plateau[i].nom.equals("case_normale")) System.out.println("case "+ i + ": " + plateau[i].getMessage());
		}
	}

}
