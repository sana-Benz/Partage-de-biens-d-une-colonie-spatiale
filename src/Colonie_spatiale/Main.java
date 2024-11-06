package Colonie_spatiale;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Entrez la taille de la colonie spatiale");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Colonie colonie =null;
        try{
            colonie =new Colonie(n);
        }catch(ExceptionColon e){
            System.err.println(e.getMessage());
        }
        int choix;
        do{
            //affiche le 1er menu
        System.out.println("Veuillez entrer votre choix ");
        System.out.println("1 Ajouter une relation entre deux colons ");
        System.out.println("2 Ajouter les préférences d'un colon ");
        System.out.println("3 Fin ");
        Scanner scanner1 = new Scanner(System.in);
        choix = scanner1.nextInt();
        
        switch(choix){
            case 1:  
                System.out.println("Entrez les deux colons (par exemple, A B) :");
                String input = scanner1.nextLine();
                String[] parts = input.split(" ");
        
                if (parts.length >= 2) {
                    char nom1 = parts[0].charAt(0); 
                    char nom2 = parts[1].charAt(0);

                    Colon colon1=colonie.getColon(nom1);
                    Colon colon2=colonie.getColon(nom1);
                 
                    colon1.ajoutennemi(colon2);
                    colon2.ajoutennemi(colon1);
                } else {  
                    System.out.println("Erreur de lecture de colon  s, veuillez entrer deux caractères.");
                }
                break;

            case 2 :
                System.out.println("Entrez toutes les préférences d'un colon en ordre décroissant (par exemple, A 1 2 3)  :");
                // et n preferences
                String input1 = scanner1.nextLine();
                String[] les_parts = input1.split(" ");
                if ( les_parts.length != n+1){
                    System.out.println("Il faut préciser les préferences de toutes les options possibles");
                    break;
                }
                char nomColon = les_parts[0].charAt(0); 
                Colon colon=colonie.getColon(nomColon);
                if (colon == null){
                    System.out.println("Erreur, le colon n'existe pas");
                    break;
                }
                for (int i=1; i<les_parts.length; i++){
                    Preference opt = new Preference(les_parts[i].charAt(0));
                    colon.ajoutpreference(opt);
                }
                break;
            case 3:
                for(Colon c: colonie.getlistecolons()){

                }
            default: 
                System.out.println("choix invalide, veuillez réessayer");
                break;
    }
   
    }while (choix!=3);
}
}
