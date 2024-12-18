package Colonie_spatiale.AttributionRessources;

import Colonie_spatiale.CreationColonie.Colon;
import Colonie_spatiale.CreationColonie.Colonie;
import Colonie_spatiale.CreationColonie.FichierColonie;
import Colonie_spatiale.CreationColonie.Ressource;
import Colonie_spatiale.ExceptionColon;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Menu3 {
    private int choix;
    private Colonie colonie; // Référence à la colonie
    Map<Ressource, Colon> ressources;

    public Menu3(Colonie colonie) {
        this.colonie = colonie; // Réutilisation de l'instance
    }

    public void afficherMenu3(Scanner scanner1) {

        do{
            // Affiche le 3ème menu
            System.out.println("Veuillez entrer votre choix pour le deuxieme menu");
            System.out.println("1 Résolution automatique");
            System.out.println("2 Sauvegarde de la solution actuelle ");
            System.out.println("3 Fin ");

            choix = scanner1.nextInt();
            scanner1.nextLine(); // Consomme le saut de ligne

            switch (choix) {
                case 1:
                    AttributionOptimale attributionOpt = new AttributionOptimale(colonie.getListeColons() , ressources);
                    System.out.println("l'attribution optimale a été effectuée");
                    colonie.affichageaffection();
                case 2:
                    try {
                        FichierColonie.saveAttribution();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ExceptionColon e) {
                        throw new RuntimeException(e);
                    }
                case 3:
                    break;

                default:
                    System.out.println("Choix invalide, veuillez reessayer");
                    break;

            }


        }while(choix != 3);

    }
}
