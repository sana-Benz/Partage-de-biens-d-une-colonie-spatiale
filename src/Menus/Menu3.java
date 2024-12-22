/**
 * Classe représentant le troisième menu interactif pour la gestion de la colonie spatiale.
 * Ce menu permet de résoudre automatiquement les affectations, de sauvegarder les solutions, et de quitter le menu.
 */
package Menus;

import Affichages.RecapColonie;
import Colonie.Colon;
import Colonie.Colonie;
import DataAccess.FichierColonie;
import Colonie.Ressource;
import Service.AttributionOptimale;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu3 {
    /**
     * Choix de l'utilisateur dans le menu.
     */
    private int choix;
    /**
     * Instance de la colonie spatiale.
     */
    private Colonie colonie; // Référence à la colonie
    /**
     * Carte des ressources et leurs colons associés.
     */
    Map<Ressource, Colon> ressources;

    /**
     * Constructeur de la classe Menu3.
     *
     * @param colonie l'instance de la colonie spatiale à manipuler
     */
    public Menu3(Colonie colonie) {
        this.colonie = colonie; // Réutilisation de l'instance
    }

    /**
     * Affiche et gère les options du troisième menu.
     *
     * @param scanner1 le scanner pour la lecture des entrées utilisateur
     */
    public void afficherMenu3(Scanner scanner1) {
        RecapColonie affichage = new RecapColonie(colonie);

        do{
            // Affiche le 3ème menu
            System.out.println("Veuillez entrer votre choix ");
            System.out.println("1 Resolution automatique");
            System.out.println("2 Sauvegarde de la solution actuelle ");
            System.out.println("3 Fin ");

            try {
                System.out.print("Votre choix : ");
                choix = Integer.parseInt(scanner1.nextLine()); // Utiliser nextLine et parser
            } catch (NumberFormatException e) {
                System.out.println("L'entree doit etre un entier. Veuillez reessayer.");
                continue; // Recommencer la boucle
            }

            switch (choix) {
                case 1:
                    List<Ressource> ressources = colonie.getRessources().keySet().stream().toList();
                    AttributionOptimale attributionOpt = new AttributionOptimale(colonie,colonie.getListeColons() , colonie.getRessources());
                    try{
                        attributionOpt.affectationOptimisee(colonie.getListeRessources());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    affichage.affichageaffection();
                    break;
                case 2:
                    try {
                        System.out.println("\nVeuillez entrer le nom du fichier pour sauvegarder les affectations :");
                        System.out.println("Vous trouverez le fichier dans le repertoire courant ");
                        String nomFichier = scanner1.nextLine().trim();
                        FichierColonie.saveAttribution(nomFichier,colonie,scanner1);
                    } catch (Exception e) {
                        System.out.println("Erreur inattendue : " + e.getMessage());
                    }
                    break;
                case 3:
                    break;

                default:
                    System.out.println("Choix invalide, veuillez reessayer");
                    break;

            }


        }while(choix != 3);

    }
}
