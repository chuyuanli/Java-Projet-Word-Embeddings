1. LANCER LE JEU

Au moment de lancer le jeu depuis le Terminal, il faut impérativement spécifier le chemin de votre fichier word2vec avec la commande suivante:

   	  - "w2v=<chemin>"

Vous pouvez configurer 7 options, en entrant en argument les commandes ci-dessous, séparées par UN espace. Dans chaque cas il est crucial de respecter
exactement la graphie (minsucule/majuscule, espace/pas espace):

	   - Nombre de joueurs: "nbJoueur=<n>" (/!\ Minimum 2)
	   - Nombre d'essais: "nbTry=<n>" 
	   - Nombre de voisins renvoyés par le jeu: "kRespond=<n>"
	   - Nombre de cases: "nbCase=<n>" (/!\ Minimum 24)
	   - Présence d'un dé magique: "deMagi=true" ou non: "deMagi=false"
	   - Methode cosinus: "cos=true" ou Distance euclidienne: "cos=false"
	   - Droit au pass:"pass=true" ou non: "pass=false"

Dans chaque cas, n doit être un entier naturel.
Une fois le jeu lancé, il vous sera demandé d'entrer le nom des joueurs séparés par un espace. Assurez-vous de rentrer le bon nombre de noms.
Puis les configurations sont affichées.
Le jeu peut alors commencer !

2. REGLES DU JEU:

Ce jeu est un jeu de vocabulaire. Nous nous situons sur un plateau classique, avec des cases dont stockent des mots à deviner. Le but du jeu est d'arriver le premier à la dernière case ou de la dépasser.


	 - Après avoir lancé le dé, vous devez faire deviner un mot généré aléatoirement.
	 - Entrez 3 mots (indices) que vous pensez proches sémantiquement de ce dernier. 
	 - Les indices doivent être différents entre eux et différents du mot à deviner.
	 - L'ordinateur donne une liste de k mots les plus proches, trouvés sur la base d'un calcul de similarité.
	 - Par défaut 3 essais par joueur pour un tour.
	 - Si vous le souhaitez, choisissez pass=true dès le début pour avoir au maximum 5 fois "passer ce mot".
	 - Si le mot à deviner fait partie des k propositions de l'ordinateur, on a réussi et on peut relancer le dé.
	 - Quand un joueur gagne, on vous demande si vous voulez commencer encore une partie de jeu.
   
/!\ Il existe des cases-piège placées aléatoirement sur le plateau. Trois d'entre elles font 'reculrer de 3 cases', les trois autres permettent de 'relancer le dé'.
   
/!\ Il existe aussi un dé magique qui contient des faces numérotées de 0 à 5.

/!\ Veuillez bien vouloir suivre les instructions données par l'ordinateur, si votre saisi ne correspond pas à ce qu'on attend; l'ordinateur ne cesserait pas de vous redemander. 

