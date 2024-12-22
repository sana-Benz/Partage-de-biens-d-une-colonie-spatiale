/**
 * Classe représentant le deuxième menu interactif pour la gestion de la colonie spatiale.
 * Ce menu permet d'échanger les ressources entre deux colons, d'afficher le nombre de colons jaloux,
 * et de quitter le menu.
 */
package Menus;

import Affichages.RecapColonie;
import Colonie.*;
import Service.AttributionNaive;
import Service.AttributionOptimale;

import java.util.List;
import java.util.Scanner;


public class Menu2 {
    /**
     * Choix de l'utilisateur dans le menu.
     */
    private int choix;

    /**
     * Instance de la colonie spatiale partagée entre les différents menus.
     */
    private Colonie colonie; // Référence à la colonie partagée

    /**
     * Constructeur de la classe Menu2.
     *
     * @param colonie l'instance de la colonie spatiale à manipuler
     */
    public Menu2(Colonie colonie) {
        this.colonie = colonie; // Réutilisation de l'instance
    }

    /**
     * Affiche et gère les options du deuxième menu.
     *
     * @param scanner1 le scanner pour la lecture des entrées utilisateur
     * @throws Exception si une erreur survient pendant l'exécution
     */
    public void afficherMenu2(Scanner scanner1) throws Exception {
        AttributionNaive attNaive = new AttributionNaive(colonie.getlistecolons(),colonie.getRessources(),colonie);
        attNaive.affectationNaive();
        RecapColonie affichage = new RecapColonie(colonie);

        do {
            System.out.println("Veuillez entrer votre choix pour le deuxieme menu");
            System.out.println("1 Echanger les ressources de deux colons");
            System.out.println("2 Afficher le nombre de colons jaloux ");
            System.out.println("3 Fin ");

            try {
                System.out.print("Votre choix : ");
                String input = scanner1.nextLine().trim(); // Lit l'entrée utilisateur

                if (!input.matches("\\d+")) { // Vérifie si l'entrée est un entier
                    System.out.println("Erreur : Veuillez entrer un numero valide.");
                    continue;
                }

                choix = Integer.parseInt(input); // Convertit en entier
            } catch (Exception e) {
                System.out.println("Erreur inattendue : " + e.getMessage());
                break;
            }



            switch (choix) {
                case 1:
                    System.out.println(
                            "Entrez les deux colons pour lesquels vous voulez echanger les ressources (par exemple, A B) :");
                    String input = scanner1.nextLine();
                    String[] parts = input.split(" ");

                    if (parts.length >= 2) {
                        String nom1 = parts[0];
                        String nom2 = parts[1];
                        Colon colon1 = colonie.getColon(nom1);
                        Colon colon2 = colonie.getColon(nom2);

                        if (colon1 == null || colon2 == null) {
                            System.out.println("Un des colons n'existe pas");
                            break;
                        }
                        colonie.echangerRessources(colon1, colon2);
                    } else {
                        System.out.println("Erreur de lecture de colons, veuillez entrer deux caracteres.");
                    }
                    affichage.affichageaffection();
                    break;

                case 2:
                    affichage.affichageaffection();
                    break;
                case 3:
                    break;

                default:
                    System.out.println("Choix invalide, veuillez reessayer");
                    break;
            }
        } while (choix != 3);
    }
}
