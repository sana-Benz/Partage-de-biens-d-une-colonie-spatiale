package Colonie_spatiale.CreationColonie;

import Colonie_spatiale.ExceptionColon;


import java.util.Scanner;

public class Menu1 {
    private int n;
    int choix;
    Colonie colonie = null;

    public Menu1(int n){
        this.n = n;
    }

    public int getN(){
        return n;
    }

    public static Colonie getColonie(){
        return colonie;
    }


    public void afficherMenu1() {


        // Initialisation de la colonie
        try {
            colonie = new Colonie(n);
        } catch (ExceptionColon e) {
            System.err.println("Erreur lors de la création de la colonie : " + e.getMessage());
            return; // Stoppe l'exécution si la colonie ne peut pas être créée
        }

        Scanner scanner1 = new Scanner(System.in);

        boolean incomplet = true;

        do {
            // Affiche le menu
            System.out.println("\nVeuillez entrer votre choix :");
            System.out.println("1. Ajouter une relation entre deux colons");
            System.out.println("2. Ajouter les préférences d'un colon");
            System.out.println("3. Fin");

            // Lecture du choix de l'utilisateur
            choix = scanner1.nextInt();
            scanner1.nextLine(); // Consomme le saut de ligne

            switch (choix) {
                case 1:
                    // Ajouter une relation entre deux colons
                    System.out.println("Entrez les deux colons (par exemple, A B) :");
                    String input = scanner1.nextLine();
                    String[] parts = input.split(" ");

                    if (parts.length >= 2) {
                        char nom1 = parts[0].charAt(0);
                        char nom2 = parts[1].charAt(0);

                        Colon colon1 = colonie.getColon(nom1);
                        Colon colon2 = colonie.getColon(nom2);

                        if (colon1 != null && colon2 != null) {
                            colon1.ajoutennemi(colon2);
                            colon2.ajoutennemi(colon1);
                            System.out.println("Relation ajoutée entre " + nom1 + " et " + nom2);
                        } else {
                            System.out.println("L'un des colons spécifiés n'existe pas.");
                        }
                    } else {
                        System.out.println("Erreur : Veuillez entrer deux colons.");
                    }
                    break;

                case 2:
                    // Ajouter les préférences d'un colon
                    System.out.println("Entrez toutes les préférences d'un colon en ordre décroissant (par exemple, A 1 2 3) :");
                    String input1 = scanner1.nextLine();
                    String[] les_parts = input1.split(" ");

                    char nomColon = les_parts[0].charAt(0);
                    Colon colon = colonie.getColon(nomColon);

                    if (colon == null) {
                        System.out.println("Erreur : Le colon n'existe pas.");
                        break;
                    }

                    for (int i = 1; i < les_parts.length; i++) {
                        Ressource opt = new Ressource(les_parts[i].charAt(0));
                        colon.ajoutpreference(opt);
                    }
                    colon.AfficherListePref();
                    break;

                case 3:
                    // Vérifier si toutes les informations sont complètes
                    incomplet = false;
                    for (Colon c : colonie.getlistecolons()) {
                        if (c.getlistepreferences().isEmpty()) {
                            System.out.println("La liste des préférences est vide pour le colon " + c.getNom());
                            incomplet = true;
                        } else if (c.getlistepreferences().size() < n) {
                            System.out.println("La liste des préférences est incomplète pour le colon " + c.getNom());
                            incomplet = true;
                        }
                    }

                    if (incomplet) {
                        System.out.println("Des informations sont manquantes. Veuillez les saisir.");
                    }
                    break;

                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        } while (choix != 3 || incomplet);

        // Fermer le scanner
        scanner1.close();
    }

    // il faut ajouter à la fin un affichage qui résume la colonie
    //resumé

}
