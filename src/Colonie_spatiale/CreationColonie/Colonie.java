package Colonie_spatiale.CreationColonie;

import Colonie_spatiale.ExceptionColon;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Colonie {
    public int n;
    private List<Colon> colons;
    private Map<Ressource, Colon> ressources;

    public Colonie(int n) throws ExceptionColon {
        if (n > 26) {
            throw new ExceptionColon("Le nombre de colons ne peut pas depasser 26.");
        }
        this.n = n;
        this.colons = new ArrayList<>();
        this.ressources = new HashMap<>();
    }

    public void initialiserColons(List<String> nomsColons) {
        for (String nom : nomsColons) {
            Colon c = new Colon(nom); colons.add(c); }
    }
    public void initialiserRessources(List<String> nomsRessources) {
        for (String nom : nomsRessources) {
            Ressource r = new Ressource(nom);
            ressources.put(r, null); }
    }

    public int getn() {
        return n;
    }

    public List<Colon> getListeColons (){
        return colons;
    }


    public void setRessources(Map<Ressource, Colon> ressources) {
        this.ressources = ressources;
    }

    public Colon getColon(String nom) {
        for (Colon x : colons) {
            if (x.getNom().equals(nom)) {
                return x;
            }
        }
        return null;
    }

    public void ajoutColon(Colon c) throws ExceptionColon {
        if (colons.size() > n) {
            throw new ExceptionColon("Le nombre de colons ne peut pas depasser le nombre donne.");
        }
        colons.add(c);
    }

    public List<Colon> getlistecolons() {
        return colons;
    }

    public Map<Ressource, Colon> getRessources() {
        return ressources;
    }

    // solution naive
    public void affectationNaive() {
        for (Colon c : colons) {
            for (Ressource p : c.getlistepreferences()) {
                Ressource ressource = getRessourceParNom(p.getNom());
                if (ressource != null && ressources.get(ressource) == null) {
                    c.setRessourceAttribuee(ressource);
                    ressources.put(ressource, c);
                    break;
                }
            }
        }
    }

    public int nombreColonsJaloux() {
        int nombreJaloux = 0;

        for (Colon colon : this.colons) {
            for (Colon ennemi : colon.getEnnemis()) {
                Ressource ressourceEnnemi = ennemi.getRessourceAttribuee();

                if (colon.prefereObjet(ressourceEnnemi)) { // !ressourceColon.equals(ressourceEnnemi)) {
                    System.out.println("Le colon " + colon.getNom() + " est jaloux de l'ennemi " + ennemi.getNom()
                            + " avec ressource " + ressourceEnnemi);
                    colon.setJaloux();
                    nombreJaloux++;

                }
            }
        }

        return nombreJaloux;
    }

    public Ressource getRessourceParNom(String nom) {
        for (Ressource r : ressources.keySet()) {
            if (r.getNom().equals(nom)) {
                return r;
            }
        }
        return null;
    }

    public void affichageaffection() {
        trierColonsParNom(); // Trier les colons par nom

        for (Colon colon : colons) {
            Ressource ressource = colon.getRessourceAttribuee(); // Obtenir la ressource attribuée au colon

            if (ressource != null) { // Vérifie si la ressource est attribuée
                System.out.println(colon.getNom() + " : " + ressource.getNom());
            } else {
                System.out.println(colon.getNom() + " : aucune ressource attribuée");
            }
        }
    }


    public void echangerRessources(Colon colon1, Colon colon2) {

        Ressource ressource1 = colon1.getRessourceAttribuee();
        Ressource ressource2 = colon2.getRessourceAttribuee();

        if (ressource1 != null && ressource2 != null) {

            colon1.setRessourceAttribuee(ressource2);

            colon2.setRessourceAttribuee(ressource1);

            // Met à jour le dictionnaire de ressources

            ressources.put(ressource1, colon2);

            ressources.put(ressource2, colon1);

        } else {

            System.out.println("L'un des colons n'a pas de ressource attribuee, impossible d'echanger.");

        }

    }

    public void trierColonsParNom() {
        colons.sort(Comparator.comparing(colon -> colon.getNom(), (s1, s2) -> {
            // Comparateur pour trier naturellement les noms
            Pattern pattern = Pattern.compile("\\d+");
            Matcher m1 = pattern.matcher(s1);
            Matcher m2 = pattern.matcher(s2);

            int pos1 = 0, pos2 = 0;

            while (m1.find(pos1) && m2.find(pos2)) {
                // Comparaison des parties non numériques
                int compareText = s1.substring(pos1, m1.start()).compareTo(s2.substring(pos2, m2.start()));
                if (compareText != 0) return compareText;

                // Comparaison des parties numériques
                int num1 = Integer.parseInt(m1.group());
                int num2 = Integer.parseInt(m2.group());
                if (num1 != num2) return Integer.compare(num1, num2);

                // Avance aux positions suivantes
                pos1 = m1.end();
                pos2 = m2.end();
            }
            // Comparaison des parties restantes
            return s1.substring(pos1).compareTo(s2.substring(pos2));
        }));
    }


}
