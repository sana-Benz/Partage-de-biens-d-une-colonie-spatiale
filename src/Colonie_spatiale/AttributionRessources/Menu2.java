package Colonie_spatiale.AttributionRessources;

import Colonie_spatiale.CreationColonie.Colon;
import Colonie_spatiale.CreationColonie.Colonie;
import java.util.*;

import java.util.Scanner;

public class Menu2 {
    private int choix;

    private Colonie colonie; // Référence à la colonie partagée

    public Menu2(Colonie colonie) {
        this.colonie = colonie; // Réutilisation de l'instance
    }

    public void afficherMenu2(Scanner scanner1) {
        colonie.affectationNaive();

        do {
            // Affiche le 2ème menu
            System.out.println("Veuillez entrer votre choix pour le deuxieme menu");
            System.out.println("1 Echanger les ressources de deux colons");
            System.out.println("2 Afficher le nombre de colons jaloux ");
            System.out.println("3 Fin ");

            choix = scanner1.nextInt();
            scanner1.nextLine(); // Consomme le saut de ligne

            switch (choix) {
                case 1:
                    System.out.println(
                            "Entrez les deux colons pour lesquels vous voulez echanger les ressources (par exemple, A B) :");
                    String input = scanner1.nextLine();
                    String[] parts = input.split(" ");

                    if (parts.length >= 2) {
                        char nom1 = parts[0].charAt(0);
                        char nom2 = parts[1].charAt(0);
                        Colon colon1 = colonie.getColon(String.valueOf(nom1));
                        Colon colon2 = colonie.getColon(String.valueOf(nom2));
                        if (colon1 == null || colon2 == null) {
                            System.out.println("Un des colons n'existe pas");
                            break;
                        }
                        colonie.echangerRessources(colon1, colon2);
                    } else {
                        System.out.println("Erreur de lecture de colons, veuillez entrer deux caracteres.");
                    }
                    colonie.affichageaffection();
                    break;

                case 2:
                    System.out.println("Voici le nombre de colons jaloux : ");
                    System.out.println(colonie.nombreColonsJaloux());
                    colonie.affichageaffection();
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
