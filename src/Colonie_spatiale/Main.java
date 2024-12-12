package Colonie_spatiale;

import Colonie_spatiale.AttributionRessources.Menu2;
import Colonie_spatiale.CreationColonie.Colonie;
import Colonie_spatiale.CreationColonie.FichierColonie;
import Colonie_spatiale.CreationColonie.Menu1;
import Colonie_spatiale.InvalidInputTypeException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static int n=0; // Déclaration statique pour utilisation globale

    public static void main(String[] args) throws ExceptionColon {
        Scanner scanner = new Scanner(System.in); // Création du scanner une seule fois

<<<<<<< HEAD
        boolean validInput = false; // Variable pour vérifier si l'entrée est valide

        while (!validInput) { // Boucle jusqu'à ce qu'une entrée valide soit fournie
            try{
                System.out.println("Entrez la taille de la colonie spatiale");//******************************** */
                n = scanner.nextInt();
                scanner.nextLine();  // Consomme le saut de ligne après nextInt()

                if (n < 0) {
                    System.out.println("La taille de la colonie ne peut pas être négative");
                } else {
                    validInput = true; // L'entrée est valide, sortir de la boucle
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("L'entrée doit être un entier. Veuillez réessayer.");
                scanner.nextLine(); // Consomme l'entrée invalide
            }
=======
        Colonie colonie = null;

        // Vérifier si un fichier est passé en argument
        if (args.length > 0) {
            String cheminFichier = args[0];
            System.out.println("Chargement des données depuis le fichier : " + cheminFichier);

            try {
                colonie = FichierColonie.chargerDepuisFichier(cheminFichier, n);
                System.out.println("Colonie chargée depuis le fichier !");
            } catch (IOException e) {
                System.err.println("Erreur d'entree/sortie : " + e.getMessage());
                return;
            } catch (ExceptionColon e) {
                System.err.println("Erreur : " + e.getMessage());
                return;
            }


        } else {
            System.out.println("Aucun fichier fourni. Construction manuelle de la colonie.");
            System.out.println("Entrez la taille de la colonie spatiale");// ******************************** */
            n = scanner.nextInt();
            scanner.nextLine(); // Consomme le saut de ligne après nextInt()

            if (n < 0) {
                System.out.println("La taille de la colonie ne peut pas etre negative");
                scanner.close(); // Fermer le scanner à la fin
                return;
            }

            Menu1 menu1 = new Menu1(n);
            menu1.afficherMenu1(scanner); // Passe le scanner à Menu1 pour éviter d'en créer un nouveau

            colonie = menu1.getColonie();
>>>>>>> 9e9431c18e9be6ed7e37f59b009bb2f918c3d929
        }

         //afficher le recap de colonie avant le menu 2
        System.out.println("\n=== Recapitulatif de l'etat de la colonie ===");
        RecapColonie recap = new RecapColonie(colonie);
        recap.afficherEtatColonie();


        Menu2 menu2 = new Menu2(colonie);
        menu2.afficherMenu2(scanner); // Passe le scanner à Menu2 également
<<<<<<< HEAD
        System.out.println("L'entrée doit être un entier. Veuillez réessayer.");
        scanner.nextLine(); // Consomme l'entrée invalide
   
        scanner.close();  // Fermeture du scanner ici
    
}}
=======

        scanner.close(); // Fermeture du scanner ici
    }
}
>>>>>>> 9e9431c18e9be6ed7e37f59b009bb2f918c3d929
