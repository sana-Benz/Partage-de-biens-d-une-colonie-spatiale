package DataAccess;

import Colonie.*;
import ExceptionColonie.ExceptionColon;

import java.io.*;
import java.util.*;
import java.nio.file.Files;

public class FichierColonie {

    public static Colonie chargerDepuisFichier(String cheminFichier, int n) throws ExceptionColon, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));
        String ligne;
        List<String> nomsColons = new ArrayList<>();
        List<String> nomsRessources = new ArrayList<>();
        Map<String, List<String>> preferences = new HashMap<>();
        Map<String, List<String>> deteste = new HashMap<>();

        // Lecture du fichier : l'ordre est colons, ressources, deteste, preferences
        String phase = "colons";
        int ligneNum = 0;
        boolean hasValidData = false;
        while ((ligne = reader.readLine()) != null) {
            ligneNum++;
            ligne = ligne.trim();
            if (ligne.isEmpty()) continue;
            hasValidData = true;

            // Vérification du nombre exact de parenthèses
            long openParentheses = ligne.chars().filter(c -> c == '(').count();
            long closeParentheses = ligne.chars().filter(c -> c == ')').count();
            if (openParentheses != 1 || closeParentheses != 1) {
                throw new ExceptionColon("Ligne invalide " + ligneNum + " : chaque ligne doit contenir exactement 1 paire de parenthèses. Ligne : " + ligne);
            }

            if (!ligne.endsWith(".")) {
                throw new ExceptionColon("Ligne invalide " + ligneNum + " (doit se terminer par un point) : " + ligne);
            }

            ligne = ligne.substring(0, ligne.length() - 1); // Retirer le point final
            if (ligne.startsWith("colon(")) {
                if (!phase.equals("colons")) throw new ExceptionColon("Erreur d'ordre dans le fichier : 'colon' attendu. Ligne : " + ligneNum);
                String colonNom = ligne.substring(6, ligne.length() - 1); // Extraire le nom entre parenthèses
                if (nomsColons.contains(colonNom)) {
                    throw new ExceptionColon("Le colon '" + colonNom + "' est déclaré plusieurs fois. Ligne : " + ligneNum);
                }
                nomsColons.add(colonNom);

            } else if (ligne.startsWith("ressource(")) {
                phase = "ressources";
                String ressourceNom = ligne.substring(10, ligne.length() - 1);
                if (nomsRessources.contains(ressourceNom)) {
                    throw new ExceptionColon("La ressource '" + ressourceNom + "' est déclarée plusieurs fois. Ligne : " + ligneNum);
                }
                nomsRessources.add(ressourceNom);

            } else if (ligne.startsWith("deteste(")) {
                phase = "deteste";
                String[] params = ligne.substring(8, ligne.length() - 1).split(",");
                if (params.length != 2) throw new ExceptionColon("Format invalide pour 'deteste' : Ligne " + ligneNum + ", Ligne : " + ligne);

                // Validation : un colon ne peut pas détester une ressource
                if (nomsRessources.contains(params[1])) {
                    throw new ExceptionColon("Un colon ne peut pas détester une ressource. Ligne : " + ligneNum + ", Ligne : " + ligne);
                }
                if (deteste.containsKey(params[0]) && deteste.get(params[0]).contains(params[1])) {
                    throw new ExceptionColon("La relation 'deteste' entre '" + params[0] + "' et '" + params[1] + "' est déclarée plusieurs fois. Ligne : " + ligneNum);
                }
                deteste.computeIfAbsent(params[0], k -> new ArrayList<>()).add(params[1]);

            } else if (ligne.startsWith("preferences(")) {
                phase = "preferences";
                String[] params = ligne.substring(12, ligne.length() - 1).split(",");
                if (params.length < 2) throw new ExceptionColon("Format invalide pour 'preferences' : Ligne " + ligneNum + ", Ligne : " + ligne);
                String colon = params[0];
                if (preferences.containsKey(colon) && !preferences.get(colon).isEmpty()) {
                    throw new ExceptionColon("Les préférences pour le colon '" + colon + "' sont déclarées plusieurs fois. Ligne : " + ligneNum);
                }
                preferences.put(colon, Arrays.asList(params).subList(1, params.length));

            } else {
                throw new ExceptionColon("Ligne invalide : Ligne " + ligneNum + ", Ligne : " + ligne);
            }
        }
        if (!hasValidData) {
            throw new ExceptionColon("Le fichier est vide.");
        }

        // Validation des données
        if (nomsColons.size() != nomsRessources.size()) {
            throw new ExceptionColon("Le nombre de colons doit être égal au nombre de ressources.");
        }

        for (String colon : deteste.keySet()) {
            if (!nomsColons.contains(colon)) {
                throw new ExceptionColon("Le colon '" + colon + "' mentionné dans 'deteste' n'existe pas.");
            }
            for (String ennemi : deteste.get(colon)) {
                if (!nomsColons.contains(ennemi)) {
                    throw new ExceptionColon("L'ennemi '" + ennemi + "' mentionné pour '" + colon + "' n'existe pas.");
                }
            }
        }

        // Validation des préférences
        for (String colon : preferences.keySet()) {
            if (!nomsColons.contains(colon)) {
                throw new ExceptionColon("Le colon '" + colon + "' mentionné dans 'preferences' n'existe pas.");
            }
        }

        // Validation pour tous les colons
        for (String colon : nomsColons) {
            if (!preferences.containsKey(colon)) {
                throw new ExceptionColon("Le colon '" + colon + "' n'a pas de liste de préférences définie.");
            }
            for (String ressource : preferences.get(colon)) {
                if (!nomsRessources.contains(ressource)) {
                    throw new ExceptionColon("La ressource '" + ressource + "' mentionnée pour '" + colon + "' n'existe pas.");
                }
            }
            if (preferences.get(colon).size() != nomsRessources.size()) {
                throw new ExceptionColon("Les préférences pour le colon '" + colon + "' sont incomplètes.");
            }
        }


        // Construction de la colonie
        Colonie colonie = new Colonie(n);
        colonie.initialiserColons(nomsColons);
        colonie.initialiserRessources(nomsRessources);

        for (Map.Entry<String, List<String>> entry : deteste.entrySet()) {
            Colon colon = colonie.getColon(entry.getKey());
            for (String ennemi : entry.getValue()) {
                Colon ennemiColon = colonie.getColon(ennemi);

                // Ajouter l'ennemi pour les deux colons
                colon.ajoutennemi(ennemiColon);
                ennemiColon.ajoutennemi(colon);
            }
        }

        // Ajouter les préférences pour les colons
        for (Map.Entry<String, List<String>> entry : preferences.entrySet()) {
            Colon colon = colonie.getColon(entry.getKey());
            for (String ressource : entry.getValue()) {
                Ressource pref = new Ressource(ressource);
                colon.ajoutpreference(pref);
            }
        }

        reader.close();
        return colonie;
    }





    public static void saveAttribution(String nomFichier, Colonie colonie, Scanner scanner) throws IOException {
        // Vérifiez si toutes les ressources sont attribuées
        if (!colonie.toutesRessourcesAttribuees()) {
            throw new IOException("Erreur : Les ressources n'ont pas ete attribuees a tous les colons. Veuillez effectuer une attribution avant de sauvegarder.");
        }

        // Ajoutez l'extension .txt si nécessaire
        if (!nomFichier.endsWith(".txt")) {
            nomFichier += ".txt";
        }

        // Obtenez le chemin complet dans le répertoire courant
        File fichier = new File(nomFichier);

        // Vérifiez si le fichier existe déjà
        while (fichier.exists()) {
            System.out.println("Un fichier avec le nom '" + nomFichier + "' existe deje.");
            System.out.println("Voulez-vous l'ecraser ? (oui/non) : ");
            String reponse = scanner.nextLine().trim().toLowerCase();

            if (reponse.equals("oui")) {
                break; // Continuer pour écraser le fichier
            } else if (reponse.equals("non")) {
                System.out.println("Veuillez entrer un autre nom de fichier :");
                nomFichier = scanner.nextLine().trim();
                if (!nomFichier.endsWith(".txt")) {
                    nomFichier += ".txt";
                }
                fichier = new File(nomFichier);
            } else {
                System.out.println("Reponse invalide. Veuillez repondre par 'oui' ou 'non'.");
            }
        }

        // Écriture dans le fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            colonie.trierColonsParNom();
            int cout = colonie.nombreColonsJaloux();
            writer.write("cout = " + cout + "\n");

            for (Colon colon : colonie.getListeColons()) {
                Ressource ressource = colon.getRessourceAttribuee();

                if (ressource != null) {
                    writer.write(colon.getNom() + ":" + ressource.getNom());
                } else {
                    writer.write(colon.getNom() + ":aucune ressource attribuee");
                }
                writer.newLine();
            }
            System.out.println("Les affectations ont ete sauvegardees dans le fichier : " + fichier.getAbsolutePath());
        }
    }









}
