package DataAccess;

import Colonie.*;
import ExceptionColonie.ExceptionColon;

import java.io.*;
import java.util.*;

public class FichierColonie {

    public static Colonie chargerDepuisFichier(String cheminFichier, int n) throws ExceptionColon, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));
        String ligne;
        List<String> nomsColons = new ArrayList<>();
        List<String> nomsRessources = new ArrayList<>();
        Map<String, List<String>> preferences = new HashMap<>();
        Map<String, List<String>> deteste = new HashMap<>();

        // lecture du fichier : l'ordre est colons, ressources, deteste, preferences
        String phase = "colons";
        while ((ligne = reader.readLine()) != null) {
            ligne = ligne.trim();
            if (ligne.isEmpty()) continue;

            if (!ligne.endsWith(".")) {
                throw new ExceptionColon("Ligne invalide (doit se terminer par un point) : " + ligne);
            }

            ligne = ligne.substring(0, ligne.length() - 1); // Retirer le point final
            if (ligne.startsWith("colon(")) {
                if (!phase.equals("colons")) throw new ExceptionColon("Erreur d'ordre dans le fichier : 'colon' attendu.");
                String colonNom = ligne.substring(6, ligne.length() - 1); // Extraire le nom entre parenthèses
                nomsColons.add(colonNom);

            } else if (ligne.startsWith("ressource(")) {
                phase = "ressources";
                String ressourceNom = ligne.substring(10, ligne.length() - 1);
                nomsRessources.add(ressourceNom);

            } else if (ligne.startsWith("deteste(")) {
                phase = "deteste";
                String[] params = ligne.substring(8, ligne.length() - 1).split(",");
                if (params.length != 2) throw new ExceptionColon("Format invalide pour 'deteste' : " + ligne);
                deteste.computeIfAbsent(params[0], k -> new ArrayList<>()).add(params[1]);

            } else if (ligne.startsWith("preferences(")) {
                phase = "preferences";
                String[] params = ligne.substring(12, ligne.length() - 1).split(",");
                if (params.length < 2) throw new ExceptionColon("Format invalide pour 'preferences' : " + ligne);
                String colon = params[0];
                preferences.put(colon, Arrays.asList(params).subList(1, params.length));
            } else {
                throw new ExceptionColon("Ligne invalide : " + ligne);
            }
        }

        // Validation des données
        if (nomsColons.size() != nomsRessources.size()) {
            throw new ExceptionColon("Le nombre de colons doit etre egal au nombre de ressources.");
        }
        for (String colon : deteste.keySet()) {
            if (!nomsColons.contains(colon)) {
                throw new ExceptionColon("Le colon '" + colon + "' mentionne dans 'deteste' n'existe pas.");
            }
            for (String ennemi : deteste.get(colon)) {
                if (!nomsColons.contains(ennemi)) {
                    throw new ExceptionColon("L'ennemi '" + ennemi + "' mentionne pour '" + colon + "' n'existe pas.");
                }
            }
        }
        for (String colon : preferences.keySet()) {
            if (!nomsColons.contains(colon)) {
                throw new ExceptionColon("Le colon '" + colon + "' mentionne dans 'preferences' n'existe pas.");
            }
            for (String ressource : preferences.get(colon)) {
                if (!nomsRessources.contains(ressource)) {
                    throw new ExceptionColon("La ressource '" + ressource + "' mentionnee pour '" + colon + "' n'existe pas.");
                }
            }
            if (preferences.get(colon).size() != nomsRessources.size()) {
                throw new ExceptionColon("Les preferences pour le colon '" + colon + "' sont incompletes.");
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
        //ajouter les préférences pour les colons
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


    public static void saveAttribution() throws ExceptionColon, IOException {

        System.out.println("en train de faire savestate");


    }




    }
