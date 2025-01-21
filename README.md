# Projet de Gestion des Ressources de la Colonie


## Description
Ce programme permet d'allouer des ressources à des colons dans une colonie spatiale fictive.  
L'objectif ultime est de **minimiser la jalousie entre les colons** afin de réduire les conflits.  
Un colon est jaloux si l'un de ses ennemis reçoit une ressource qu'il aurait préférée.

Pour résoudre ce problème, deux approches ont été développées :
1. **Approche naïve** : Attribue à chaque colon la première ressource disponible dans sa liste de préférences.
2. **Approche optimisée** : Une attribution naïve est d'abord réalisée. Les colons sont ensuite triés en fonction du nombre de conflits .L'algorithme tente différentes affectations pour minimiser les conflits et des échanges de ressources sont ensuite effectués pour réduire les conflits locaux. A la fin, La meilleure attribution, minimisant la jalousie, est choisie.


---


## Instructions d'Exécution du Programme


### Prérequis
Avant de commencer, assurez-vous que Java est installé sur votre machine. Vous pouvez vérifier cela en exécutant la commande suivante dans votre terminal :
```bash
java -version```


### Étapes d'Exécution


1) **Ouvrir le Dossier du Projet** : Ouvrez le dossier contenant les fichiers du projet sur votre ordinateur.
2) **Ouvrir un Terminal** : Ouvrez un terminal dans le dossier racine du projet.

3) **Compiler le Code Source** : Exécutez la commande suivante pour compiler les fichiers .java :
bash
```javac ./src/Affichages*.java ./src/Colonie*.java ./src/DataAccess*.java ./src/ExceptionColonie*.java ./src/Main*.java ./src/Menus*.java ./src/Service*.java -d bin```


4.1) **Exécuter le programme** : Une fois la compilation terminée, exécutez le programme avec :
bash
java -cp bin Main.Main


4.2) **Exécuter avec un fichier** : Pour lire un fichier spécifique et accéder au menu 3, utilisez la commande suivante en remplaçant //cheminFichierConfigurationColonie par le chemin réel du fichier :
bash
java -cp bin Main.Main //cheminFichierConfigurationColonie

Le fichier de configuration doit avoir le même format que le fichier colonie.txt .



## Fonctionnement des Menus

Le programme propose deux modes de fonctionnement, selon que vous spécifiez ou non un chemin de fichier lors de l'exécution.

### 1. **En cas de non spécification de chemin de fichier**

Le programme démarre avec une **construction manuelle** de la colonie. Vous aurez accès au **Menu 1** et au **Menu 2** :

#### Menu 1 : Construction Manuelle de la Colonie
- **Ajouter une relation entre deux colons** : Définissez les relations d'ennemis entre les colons.
- **Ajouter les préférences des colons** : Spécifiez les ressources préférées de chaque colon.
- **Passer au Menu 2** : Passez à l'attribution des ressources.

#### Menu 2 : Gestion Après Attribution
- **Échanger les ressources entre colons** : Effectuez des modifications dans l'attribution.
- **Afficher le nombre de colons jaloux** : Affichez les colons insatisfaits par l'attribution actuelle.
- **Fin de l'application** : Quittez le programme.

---

### 2. **En cas de spécification d’un fichier de configuration**

Si un fichier de configuration est spécifié lors de l'exécution, le programme procède à une **construction automatique** de la colonie. Vous aurez accès directement au **Menu 3** :

#### Menu 3 : Après Chargement du Fichier
- **Construction automatique de la colonie** : Configure automatiquement les colons, leurs préférences, et leurs relations en fonction du fichier.
- **Résolution automatique avec l'algorithme optimisé** : Minimise les conflits grâce à l'attribution optimisée.
- **Sauvegarde de l'état actuel** : Enregistrez la colonie et l'attribution dans un fichier.
- **Fin de l'application** : Quittez le programme.

---

### Résumé des Modes

| **Mode**                          | **Actions Principales**                                                                                                                                 |
|-----------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Sans chemin de fichier**        | Construction manuelle : ajouter des relations, définir les préférences, ajuster l'attribution via les menus 1 et 2.                                     |
| **Avec fichier de configuration** | Construction automatique : charger les données depuis un fichier, appliquer l'algorithme optimisé, sauvegarder l'état via le menu 3.                   |




## Gestion des Exceptions
**ExceptionColon** : Gère les erreurs liées aux colons.
**PreferencesInvalidException** : Gère les erreurs de préférences invalides.
**NomsNonDistinctsException** : Gère les erreurs de noms non distincts.
**InvalidInputTypeException** : Gère les erreurs d'entrée utilisateur non valides.

## Tests Unitaires
Des tests unitaires sont utilisés pour valider le bon fonctionnement des principales méthodes d'attribution, de gestion des colons et des relations, avec **JUnit** pour assurer la qualité du code.
