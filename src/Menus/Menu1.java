/**
 * Classe représentant le premier menu pour l'initialisation de la colonie spatiale.
 * Permet de définir les noms des colons et des ressources, ainsi que leurs relations et préférences.
 */
package Menus;

import Colonie.*;
import ExceptionColonie.ExceptionColon;
import ExceptionColonie.PreferencesInvalidException;
import ExceptionColonie.NomsNonDistinctsException;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Menu1 {
    /**
     * Nombre total de colons ou de ressources dans la colonie.
     */
    private int n;
    /**
     * Choix de l'utilisateur dans le menu.
     */
    int choix;
    /**
     * Instance statique de la colonie partagée dans ce menu.
     */
    private static Colonie colonie; // Champ statique

    /**
     * Constructeur de la classe Menu1.
     *
     * @param n Le nombre de colons et de ressources.
     * @throws ExceptionColon Si la création de la colonie échoue.
     */
    public Menu1(int n) throws ExceptionColon {
        colonie = new Colonie(n); // Initialisation de la colonie
        this.n = n;
    }

    /**
     * Retourne la taille de la colonie.
     *
     * @return La taille de la colonie.
     */
    public int getN() {
        return n;
    }

    /**
     * Retourne l'instance de la colonie.
     *
     * @return L'instance de la colonie.
     */
    public static Colonie getColonie() {
        return colonie;
    }

    /**
     * Affiche et gère les interactions du premier menu pour l'initialisation de la colonie.
     *
     * @param scanner1 Scanner pour lire les entrées utilisateur.
     * @throws PreferencesInvalidException Si les préférences saisies sont invalides.
     */
    public void afficherMenu1(Scanner scanner1) throws PreferencesInvalidException {

        try {
            colonie = new Colonie(n);
        } catch (ExceptionColon e) {
            System.err.println("Erreur lors de la creation de la colonie : " + e.getMessage());
            return; // Stoppe l'exécution si la colonie ne peut pas être créée
        }

        List<String> nomsColons = new ArrayList<>();

        boolean nomsValides = false;

        // Demande des noms des colons jusqu'à ce qu'ils soient distincts
        while (!nomsValides) {
            System.out.println("Veuillez entrer les noms des " + n + " colons (séparés par des espaces ou sur plusieurs lignes) :");
            nomsColons.clear(); // Réinitialiser la liste des noms des colons

            for (int i = 0; i < n; i++) {
                System.out.println("Colon " + (i + 1) + " :");
                String nom = scanner1.nextLine().trim(); // Lit et nettoie le nom
                nomsColons.add(nom);
            }

            // Vérification des noms des colons
            HashSet<String> setColons = new HashSet<>(nomsColons);
            if (setColons.size() < nomsColons.size()) {
                System.out.println("Erreur : Les noms des colons doivent être distincts. Veuillez réessayer.");
            } else {
                nomsValides = true; // Les noms sont valides
            }

        }

        colonie.initialiserColons(nomsColons); // Appelez la méthode pour initialiser les colons

        List<String> nomsRessources = new ArrayList<>();
        boolean ressourcesValides = false;

        // Demande des noms des ressources jusqu'à ce qu'ils soient distincts
        while (!ressourcesValides) {
            System.out.println("Veuillez entrer les noms des " + n + " ressources (séparés par des espaces ou sur plusieurs lignes) :");
            nomsRessources.clear(); // Réinitialiser la liste des noms des ressources

            for (int i = 1; i <= n; i++) {
                System.out.println("Ressource " + (i) + " :");
                String nom = scanner1.nextLine().trim(); // Lit et nettoie le nom
                nomsRessources.add(nom);
            }

            // Vérification des noms des ressources
            HashSet<String> setRessources = new HashSet<>(nomsRessources);
            if (setRessources.size() < nomsRessources.size()) {
                System.out.println("Erreur : Les noms des ressources doivent être distincts. Veuillez réessayer.");
                continue; // Redemander les ressources
            }

            // Vérification des noms distincts entre colons et ressources
            boolean distincts = true;
            for (String colonNom : nomsColons) {
                if (setRessources.contains(colonNom)) {
                    System.out.println("Erreur : Le nom '" + colonNom + "' est utilisé à la fois pour un colon et une ressource. Veuillez redemander les noms des ressources.");
                    distincts = false;
                    break;
                }
            }
            if (distincts) {
                ressourcesValides = true; // Les ressources sont valides
            }
        }

        colonie.initialiserRessources(nomsRessources);

        boolean incomplet = true;


        do {
            // Affiche le menu
            System.out.println("\nVeuillez entrer votre choix :");
            System.out.println("1. Ajouter une relation entre deux colons");
            System.out.println("2. Ajouter les preferences d'un colon");
            System.out.println("3. Fin");

            try {
                System.out.print("Votre choix : ");
                choix = Integer.parseInt(scanner1.nextLine()); // Utiliser nextLine et parser
            } catch (NumberFormatException e) {
                System.out.println("L'entree doit etre un entier. Veuillez reessayer.");
                continue; // Recommencer la boucle
            }

            switch (choix) {
                case 1:
                    // Ajouter une relation entre deux colons
                    System.out.println("Entrez les deux colons (par exemple, A B) :");
                    String input = scanner1.nextLine();
                    String[] parts = input.split(" ");

                    if (parts.length >= 2) {
                        String nom1 = parts[0];
                        String nom2 = parts[1];

                        Colon colon1 = colonie.getColon(nom1);
                        Colon colon2 = colonie.getColon(nom2);

                        if (colon1 != null && colon2 != null) {
                            try {
                                colon1.ajoutennemi(colon2);
                                colon2.ajoutennemi(colon1);
                                System.out.println("Relation ajoutée entre " + nom1 + " et " + nom2);
                            } catch (ExceptionColon e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("L'un des colons specifies n'existe pas.");
                        }
                    } else {
                        System.out.println("Erreur : Veuillez entrer deux colons.");
                    }
                    break;

                case 2:
                    // Ajouter les préférences d'un colon
                    boolean preferencesValides = false;

                    while (!preferencesValides) {
                        System.out.println("Entrez toutes les préférences d'un colon en ordre décroissant (par exemple, A 1 2 3) :");
                        String input1 = scanner1.nextLine().trim();
                        String[] les_parts = input1.split("\\s+");

                        // Vérification du nom du colon
                        String nomColon = les_parts[0];
                        Colon colon = colonie.getColon(nomColon);

                        if (colon == null) {
                            System.out.println("Erreur : Le colon '" + nomColon + "' n'existe pas.");
                            break;
                        }

                        // Vérification si les préférences ont déjà été saisies
                        if (!colon.getlistepreferences().isEmpty()) {
                            System.out.println("Erreur : Les préférences pour le colon " + nomColon + " ont déjà été saisies.");
                            break;
                        }

                        // Vérification du nombre de préférences
                        if (les_parts.length - 1 != n) {
                            System.out.println("Erreur : Vous devez entrer exactement " + n + " préférences pour le colon " + nomColon);
                            continue; // Redemander les préférences
                        }

                        // Vérification des doublons et de l'existence des ressources
                        HashSet<String> preferencesSet = new HashSet<>();
                        List<Ressource> preferences = new ArrayList<>();
                        boolean erreurDetectee = false;

                        for (int i = 1; i < les_parts.length; i++) {
                            String ressourceNom = les_parts[i];

                            if (!preferencesSet.add(ressourceNom)) {
                                System.out.println("Erreur : La ressource '" + ressourceNom + "' est entrée plusieurs fois. Veuillez réessayer.");
                                erreurDetectee = true;
                                break;
                            }

                            Ressource ressource = colonie.getRessourceParNom(ressourceNom);
                            if (ressource == null) {
                                System.out.println("Erreur : La ressource '" + ressourceNom + "' n'existe pas.");
                                erreurDetectee = true;
                                break;
                            }

                            preferences.add(ressource);
                        }

                        if (erreurDetectee) {
                            continue; // Redemander les préférences
                        }

                        // Si toutes les validations passent, ajoutez les préférences au colon
                        for (Ressource ressource : preferences) {
                            colon.ajoutpreference(ressource);
                        }
                        colon.AfficherListePref();
                        preferencesValides = true; // Sortie de la boucle
                    }
                    break;


                case 3:
                    // Vérifier si toutes les informations sont complètes
                    incomplet = false;
                    for (Colon c : colonie.getlistecolons()) {
                        if (c.getlistepreferences().isEmpty()) {
                            System.out.println("La liste des preferences est vide pour le colon " + c.getNom());
                            incomplet = true;
                        } else if (c.getlistepreferences().size() < n) {
                            System.out.println("La liste des preferences est incomplete pour le colon " + c.getNom());
                            incomplet = true;
                        }
                    }

                    if (incomplet) {
                        System.out.println("Des informations sont manquantes. Veuillez les saisir.");
                    }
                    break;

                default:
                    System.out.println("Choix invalide, veuillez reessayer.");
                    break;
            }
        } while (choix != 3 || incomplet);
    }

}
