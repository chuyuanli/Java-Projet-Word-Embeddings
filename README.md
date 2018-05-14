# LANCER LE JEU

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

# REGLES DU JEU:

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

/!\ Veuillez bien vouloir suivre les instructions données par l'ordinateur, si votre saisie ne correspond pas à ce qu'on attend; l'ordinateur va vous redemander. 

Bon jeu :D


# ARCHITECTURE GÉNÉRALE DE L'IMPLÉMENTATION

1. Projet Principal (Package_Greenson)
- Classes abstraites : 
	- Classe Case (3 classes d'enfants) :
		- CaseMagi1
		- CaseMagi2
		- CaseNormale
	- Classe Dé (2 classes d'enfants) :
		- DéMagi
		- DéNormal

- Autres Classes :
	- Joueur (décrire un joueur)
	- Plateau (décrire un plateau qui génère des cases)
	- Jeu (créer une partie de jeu)
	- Lanceur (lancer un jeu)

- Exception :
	- WordNotFoundException : pour générer l'exception quand l'utilisateur entre un mot inconnu dans le corpus; une exception héritée de Exception a été créée. Elle va afficher un message d'erreur et demander l'utilisateur entrer un autre mot

2. Partie Support (Package_word2vec)
- Utilitaire :
	- Utilityw2v : contient tous les méthodes pour calculer la similarité cosinus ou euclidienne

- Classe :
	- ValueComparator implements interface Comparator<T> : redéfinir la méthode compare en fonction de la similarité cosinus ou euclidienne

3. Astuce de Pre-calcul des Normes :
- Pour rendre le déroulement plus fluide, avant le commencement du jeu, nous allons d'abord pre-calculer les normes pour chaque mot : sqrt(∑ a^2). C'est la raison pour laquelle vous allez voir deux lignes indiquant le processus du pre-calcul. Cela prendra quelques secondes, après avoir fini, le jeu commence !












