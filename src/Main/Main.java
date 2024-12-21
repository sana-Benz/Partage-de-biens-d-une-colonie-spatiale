package Main;

import Menus.Menu2;
import Colonie.Colonie;
import Affichages.RecapColonie;
import DataAccess.FichierColonie;
import Menus.Menu1;
import ExceptionColonie.ExceptionColon;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static int n=0; // Déclaration statique pour utilisation globale

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); // Création du scanner une seule fois

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
            menu1.afficherMenu1(scanner);
            colonie=menu1.getColonie();
            if (colonie == null) {
                System.out.println("Erreur : La colonie n'a pas ete correctement initialisee.");
                return;
            }

        }

        //afficher le recap de colonie avant le menu 2
        System.out.println("\n=== Recapitulatif de l'etat de la colonie ===");
        RecapColonie recap = new RecapColonie(colonie);
        recap.afficherEtatColonie();


        Menu2 menu2 = new Menu2(colonie);
        menu2.afficherMenu2(scanner); // Passe le scanner à Menu2 également

        scanner.close(); // Fermeture du scanner ici
    }
}
