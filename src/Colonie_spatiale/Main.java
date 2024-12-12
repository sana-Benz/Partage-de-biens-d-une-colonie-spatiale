package Colonie_spatiale;

import Colonie_spatiale.AttributionRessources.Menu2;
import Colonie_spatiale.CreationColonie.Colonie;
import Colonie_spatiale.CreationColonie.Menu1;
import Colonie_spatiale.InvalidInputTypeException;

import java.util.Scanner;

public class Main {
    public static int n; // Déclaration statique pour utilisation globale 

    public static void main(String[] args) throws ExceptionColon {
        Scanner scanner = new Scanner(System.in);  // Création du scanner une seule fois

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
        }

        Menu1 menu1 = new Menu1(n);
        menu1.afficherMenu1(scanner); // Passe le scanner à Menu1 pour éviter d'en créer un nouveau

        Colonie colonie = menu1.getColonie();
        Menu2 menu2 = new Menu2(colonie);
        menu2.afficherMenu2(scanner); // Passe le scanner à Menu2 également
        System.out.println("L'entrée doit être un entier. Veuillez réessayer.");
        scanner.nextLine(); // Consomme l'entrée invalide
   
        scanner.close();  // Fermeture du scanner ici
    
}}
