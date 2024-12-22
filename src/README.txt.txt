# Projet de Gestion des Ressources de la Colonie

## Description
Ce programme permet d'allouer des ressources � des colons dans une colonie en utilisant un **algorithme d'attribution na�f et optimal**. L'objectif est de minimiser la jalousie entre les colons en affectant des ressources selon leurs pr�f�rences. L'algorithme na�f attribue chaque colon � la premi�re ressource disponible de sa liste de pr�f�rences.

---

## Instructions d'Ex�cution du Programme

### Pr�requis
Avant de commencer, assurez-vous que Java est install� sur votre machine. Vous pouvez v�rifier cela en ex�cutant la commande suivante dans votre terminal :
```bash
java -version

�tapes d'Ex�cution

Ouvrir le Dossier du Projet : Ouvrez le dossier contenant les fichiers du projet sur votre ordinateur.
Ouvrir un Terminal : Ouvrez un terminal dans le dossier racine du projet.
Compiler le Code Source : Ex�cutez la commande suivante pour compiler les fichiers .java :
bash
Copier le code
javac ./src/Affichages*.java ./src/Colonie*.java ./src/DataAccess*.java ./src/ExceptionColonie*.java ./src/Main*.java ./src/Menus*.java ./src/Service*.java -d bin
Ex�cuter le Programme : Une fois la compilation termin�e, ex�cutez le programme avec :
bash
Copier le code
java -cp bin Main.Main
Ex�cuter avec Fichier : Pour lire un fichier sp�cifique et acc�der au menu 3, utilisez la commande suivante en rempla�ant //chemin//fichier par le chemin r�el du fichier :
bash
Copier le code
java -cp bin Main.Main //chemin//fichier
Fonctionnement de l'Algorithme Na�f
Pr�f�rences des colons : Chaque colon a une liste de ressources pr�f�r�es.
Attribution des ressources : Chaque colon se voit attribuer la premi�re ressource disponible dans sa liste de pr�f�rences.
V�rification de la disponibilit� : Si une ressource est d�j� attribu�e, elle est ignor�e.
Fin de l'attribution : L'attribution continue jusqu'� ce que tous les colons aient re�u une ressource.
Cet algorithme est na�f car il ne cherche pas � optimiser l'attribution, mais fournit une solution rapide.

Fonctionnement de l'Algorithme Optimis�
L'algorithme d'attribution optimis�e vise � minimiser les conflits (jalousie) en tenant compte des pr�f�rences des colons et de leurs relations conflictuelles.

�tapes
Attribution Initiale : Affectation na�ve des ressources.
Tri des Colons par Conflits : Les colons sont tri�s en fonction du nombre de conflits (ennemis).
Essai d'Attributions : L'algorithme tente diff�rentes affectations pour minimiser les conflits.
Optimisation par �changes : Des �changes de ressources sont effectu�s pour r�duire les conflits locaux.
Solution Optimale : La meilleure attribution, r�duisant la jalousie, est choisie.
Fonctionnalit�s
Gestion des Colons et Ressources : Cr�ation des colons avec leurs pr�f�rences et initialisation des ressources.
Attribution des Ressources :
Na�ve : Attribue les ressources sans tenir compte des conflits.
Optimale : R�duit les conflits entre les colons.
Relations entre Colons : Ajout de relations d'ennemis influen�ant les pr�f�rences.
Menus Interactifs :
Apr�s avoir initialis� une colonie avec ses colons et les ressources disponibles, si un fichier est charg�, l'utilisateur est directement dirig� vers le Menu 3. Dans ce menu, il a les options suivantes :

(1) R�solution automatique : L'utilisateur peut lancer l'algorithme d'attribution optimale des ressources, qui cherche � minimiser le nombre de colons jaloux en r�affectant les ressources de mani�re plus efficace.
(2) Sauvegarde de la solution actuelle : L'utilisateur peut sauvegarder l'�tat actuel de l'attribution des ressources dans un fichier texte, permettant de conserver les r�sultats pour une utilisation future.
(3) Fin : Terminer l'application.
Si aucun fichier n'est charg�, l'utilisateur est dirig� vers le Menu 1 o� il a le choix entre :

(1) Ajouter une relation entre deux colons.
(2) Ajouter les pr�f�rences d'un colon.
(3) Passer au Menu 2.
Ensuite, un r�capitulatif s'affiche � l'utilisateur avec les colons et leurs pr�f�rences. � ce stade, les colons n'ont pas encore de ressources attribu�es.

Une fois pass� au Menu 2, les ressources ont maintenant �t� attribu�es avec l'algorithme na�f. L'utilisateur peut choisir dans le Menu 2 :

(1) �changer les ressources entre colons.
(2) Afficher le nombre de colons jaloux pour voir l'attribution des ressources avec l'algorithme na�f et le nombre de colons jaloux.
(3) Fin pour terminer l'application.Gestion des Exceptions
ExceptionColon : G�re les erreurs li�es aux colons.
PreferencesInvalidException : G�re les erreurs de pr�f�rences invalides.
NomsNonDistinctsException : G�re les erreurs de noms non distincts.
InvalidInputTypeException : G�re les erreurs d'entr�e utilisateur non valides.
Tests Unitaires
Des tests unitaires sont utilis�s pour valider le bon fonctionnement des principales m�thodes d'attribution, de gestion des colons et des relations, avec JUnit pour assurer la qualit� du code.