package Colonie_spatiale;

import Colonie_spatiale.CreationColonie.Colon;
import Colonie_spatiale.CreationColonie.Colonie;
import Colonie_spatiale.CreationColonie.Menu1;

import java.util.Scanner;

public class Main {
    public static int n; // Déclaration statique pour utilisation globale

    public static void main(String[] args) {
        System.out.println("Entrez la taille de la colonie spatiale");
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        if (n < 0) {
            System.out.println("La taille de la colonie ne peut pas être négative");
        }

        Menu1 menu = new Menu1(n);
    }
}
