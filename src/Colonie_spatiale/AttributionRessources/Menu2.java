package Colonie_spatiale.AttributionRessources;

import Colonie_spatiale.CreationColonie.Colon;

import java.util.Scanner;

import Colonie_spatiale.CreationColonie.Colonie;
import Colonie_spatiale.Main;
import Colonie_spatiale.CreationColonie.Menu1;



public class Menu2 {
    private int choix ;




    public void afficherMenu2() {
        Colonie colonie = Menu1.getColonie();
        colonie.affectationNaive();

        do {
            //affiche le 2eme menu

            System.out.println("Veuillez entrer votre choix pour le deuxieme menu");
            System.out.println("1 Echanger les ressources de deux colons");
            System.out.println("2 Afficher le nombre de colons jaloux ");
            System.out.println("3 Fin ");
            Scanner scanner1 = new Scanner(System.in);
            choix = scanner1.nextInt();
            scanner1.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("Entrez les deux colons pour lesquels vous voulez échanger les ressources (par exemple, A B) :");
                    String input = scanner1.nextLine();
                    String[] parts = input.split(" ");

                    if (parts.length >= 2) {
                        char nom1 = parts[0].charAt(0);
                        char nom2 = parts[1].charAt(0);
                        Colon colon1 = colonie.getColon(nom1);
                        Colon colon2 = colonie.getColon(nom2);
                        if (colon1 == null || colon2 == null) {
                            System.out.println("un des colons n'existe pas");
                            break;
                        }
                        colonie.echangerRessources(colon1, colon2);
                    } else {
                        System.out.println("Erreur de lecture de colons, veuillez entrer deux caractères.");
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
                    System.out.println("choix invalide, veuillez réessayer");
                    break;
            }
            scanner1.close();
        } while (choix != 3);

    }
}

