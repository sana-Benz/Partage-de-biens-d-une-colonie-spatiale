package Colonie_spatiale.CreationColonie;

import Colonie_spatiale.ExceptionColon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        for (Map.Entry<Ressource, Colon> entry : ressources.entrySet()) {

            if (entry.getValue() != null) { // Vérifie si la valeur n'est pas null

                System.out.println(entry.getValue().getNom() + " : " + entry.getKey().getNom());
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

}
