# Projet de Gestion des Ressources de la Colonie

## Description
Ce programme permet d'allouer des ressources à des colons dans une colonie en utilisant un **algorithme d'attribution naïf et optimal**. L'objectif est de minimiser la jalousie entre les colons en affectant des ressources selon leurs préférences. L'algorithme naïf attribue chaque colon à la première ressource disponible de sa liste de préférences.

---

## Instructions d'Exécution du Programme

### Prérequis
Avant de commencer, assurez-vous que Java est installé sur votre machine. Vous pouvez vérifier cela en exécutant la commande suivante dans votre terminal :
```bash
java -version

Étapes d'Exécution

Ouvrir le Dossier du Projet : Ouvrez le dossier contenant les fichiers du projet sur votre ordinateur.
Ouvrir un Terminal : Ouvrez un terminal dans le dossier racine du projet.
Compiler le Code Source : Exécutez la commande suivante pour compiler les fichiers .java :
bash
Copier le code
javac ./src/Affichages*.java ./src/Colonie*.java ./src/DataAccess*.java ./src/ExceptionColonie*.java ./src/Main*.java ./src/Menus*.java ./src/Service*.java -d bin
Exécuter le Programme : Une fois la compilation terminée, exécutez le programme avec :
bash
Copier le code
java -cp bin Main.Main
Exécuter avec Fichier : Pour lire un fichier spécifique et accéder au menu 3, utilisez la commande suivante en remplaçant //chemin//fichier par le chemin réel du fichier :
bash
Copier le code
java -cp bin Main.Main //chemin//fichier
Fonctionnement de l'Algorithme Naïf
Préférences des colons : Chaque colon a une liste de ressources préférées.
Attribution des ressources : Chaque colon se voit attribuer la première ressource disponible dans sa liste de préférences.
Vérification de la disponibilité : Si une ressource est déjà attribuée, elle est ignorée.
Fin de l'attribution : L'attribution continue jusqu'à ce que tous les colons aient reçu une ressource.
Cet algorithme est naïf car il ne cherche pas à optimiser l'attribution, mais fournit une solution rapide.

Fonctionnement de l'Algorithme Optimisé
L'algorithme d'attribution optimisée vise à minimiser les conflits (jalousie) en tenant compte des préférences des colons et de leurs relations conflictuelles.

Étapes
Attribution Initiale : Affectation naïve des ressources.
Tri des Colons par Conflits : Les colons sont triés en fonction du nombre de conflits (ennemis).
Essai d'Attributions : L'algorithme tente différentes affectations pour minimiser les conflits.
Optimisation par Échanges : Des échanges de ressources sont effectués pour réduire les conflits locaux.
Solution Optimale : La meilleure attribution, réduisant la jalousie, est choisie.
Fonctionnalités
Gestion des Colons et Ressources : Création des colons avec leurs préférences et initialisation des ressources.
Attribution des Ressources :
Naïve : Attribue les ressources sans tenir compte des conflits.
Optimale : Réduit les conflits entre les colons.
Relations entre Colons : Ajout de relations d'ennemis influençant les préférences.
Menus Interactifs :
Après avoir initialisé une colonie avec ses colons et les ressources disponibles, si un fichier est chargé, l'utilisateur est directement dirigé vers le Menu 3. Dans ce menu, il a les options suivantes :

(1) Résolution automatique : L'utilisateur peut lancer l'algorithme d'attribution optimale des ressources, qui cherche à minimiser le nombre de colons jaloux en réaffectant les ressources de manière plus efficace.
(2) Sauvegarde de la solution actuelle : L'utilisateur peut sauvegarder l'état actuel de l'attribution des ressources dans un fichier texte, permettant de conserver les résultats pour une utilisation future.
(3) Fin : Terminer l'application.
Si aucun fichier n'est chargé, l'utilisateur est dirigé vers le Menu 1 où il a le choix entre :

(1) Ajouter une relation entre deux colons.
(2) Ajouter les préférences d'un colon.
(3) Passer au Menu 2.
Ensuite, un récapitulatif s'affiche à l'utilisateur avec les colons et leurs préférences. À ce stade, les colons n'ont pas encore de ressources attribuées.

Une fois passé au Menu 2, les ressources ont maintenant été attribuées avec l'algorithme naïf. L'utilisateur peut choisir dans le Menu 2 :

(1) Échanger les ressources entre colons.
(2) Afficher le nombre de colons jaloux pour voir l'attribution des ressources avec l'algorithme naïf et le nombre de colons jaloux.
(3) Fin pour terminer l'application.Gestion des Exceptions
ExceptionColon : Gère les erreurs liées aux colons.
PreferencesInvalidException : Gère les erreurs de préférences invalides.
NomsNonDistinctsException : Gère les erreurs de noms non distincts.
InvalidInputTypeException : Gère les erreurs d'entrée utilisateur non valides.
Tests Unitaires
Des tests unitaires sont utilisés pour valider le bon fonctionnement des principales méthodes d'attribution, de gestion des colons et des relations, avec JUnit pour assurer la qualité du code.
