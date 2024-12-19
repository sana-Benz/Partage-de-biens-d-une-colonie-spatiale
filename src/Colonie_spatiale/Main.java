package Colonie_spatiale;

import Colonie_spatiale.AttributionRessources.Menu2;
import Colonie_spatiale.CreationColonie.Colon;
import Colonie_spatiale.CreationColonie.Colonie;
import Colonie_spatiale.CreationColonie.FichierColonie;
import Colonie_spatiale.CreationColonie.Menu1;
import Colonie_spatiale.CreationColonie.Ressource;
import Colonie_spatiale.InvalidInputTypeException;
import Colonie_spatiale.AttributionRessources.AttributionOptimale;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static int n=0; // Déclaration statique pour utilisation globale

    public static void main(String[] args) throws ExceptionColon {
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
            menu1.afficherMenu1(scanner); // Passe le scanner à Menu1 pour éviter d'en créer un nouveau

            colonie = menu1.getColonie();
        }

         //afficher le recap de colonie avant le menu 2
        System.out.println("\n=== Recapitulatif de l'etat de la colonie ===");
        //RecapColonie recap = new RecapColonie(colonie);
        //recap.afficherEtatColonie();

        // Exécuter l'algorithme d'attribution optimale
        //List<Colon> colons = colonie.getlistecolons(); // Assurez-vous que cette méthode existe
        //Map<Ressource, Colon> ressources = colonie.getRessources(); // Assurez-vous que cette méthode existe

        //AttributionOptimale attribution = new AttributionOptimale(colons, ressources);
        //attribution.affectationOptimisee(); // Appel de la méthode pour effectuer l'attribution
        //attribution.afficherAffectations(); // Afficher les affectations

        Menu2 menu2 = new Menu2(colonie);
        menu2.afficherMenu2(scanner); // Passe le scanner à Menu2 également

        scanner.close(); // Fermeture du scanner ici
    }
}
