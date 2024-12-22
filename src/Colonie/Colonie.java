/**
 * La classe {@code Colonie} gère un ensemble de colons et de ressources.
 * Elle permet d'initialiser les colons et les ressources, de gérer leur relation,
 * et de fournir des méthodes pour les affectations et les interactions entre eux.
 */
package Colonie;

import ExceptionColonie.ExceptionColon;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Gère les colons et les ressources d'une colonie spatiale.
 */
public class Colonie {
    public int n;
    private List<Colon> colons;
    private Map<Ressource, Colon> ressources;

    /**
     * Constructeur pour initialiser une colonie avec un nombre maximum de colons.
     *
     * @param n le nombre de colons dans la colonie
     * @throws ExceptionColon si un problème survient lors de l'initialisation
     */
    public Colonie(int n) throws ExceptionColon {
        this.n = n;
        this.colons = new ArrayList<>();
        this.ressources = new HashMap<>();
    }

    /**
     * Initialise la liste des colons à partir d'une liste de noms.
     *
     * @param nomsColons une liste contenant les noms des colons
     */
    public void initialiserColons(List<String> nomsColons) {
        for (String nom : nomsColons) {
            colons.add(new Colon(nom)); }
    }

    /**
     * Initialise la liste des ressources à partir d'une liste de noms.
     *
     * @param nomsRessources une liste contenant les noms des ressources
     */
    public void initialiserRessources(List<String> nomsRessources) {
        for (String nom : nomsRessources) {
            Ressource r = new Ressource(nom);
            ressources.put(r, null); }
    }

    /**
     * Retourne le nombre maximum de colons.
     *
     * @return le nombre maximum de colons
     */
    public int getn() {
        return n;
    }

    /**
     * Retourne la liste des colons.
     *
     * @return une liste des colons
     */
    public List<Colon> getListeColons() {
        return colons;
    }

    /**
     * Retourne la liste des ressources.
     *
     * @return une liste des ressources
     */
    public List<Ressource> getListeRessources(){
        List<Ressource> keysList = new ArrayList<>();
        for (Ressource key : ressources.keySet()) {
            keysList.add(key);
        }
        return keysList;
    }

    /**
     * Modifie l'association des ressources aux colons.
     *
     * @param ressources une map associant des ressources à des colons
     */
    public void setRessources(Map<Ressource, Colon> ressources) {
        this.ressources = ressources;
    }

    /**
     * Retourne un colon par son nom.
     *
     * @param nom le nom du colon
     * @return le colon correspondant ou {@code null} s'il n'existe pas
     */
    public Colon getColon(String nom) {
        for (Colon x : colons) {
            if (x.getNom().equals(nom)) {
                return x;
            }
        }
        return null;
    }

    /**
     * Ajoute un colon à la colonie.
     *
     * @param c le colon à ajouter
     * @throws ExceptionColon si le nombre maximum de colons est atteint
     */
    public void ajoutColon(Colon c) throws ExceptionColon {
        if (colons.size() >= n) { // Empêche d'ajouter plus que n
            throw new ExceptionColon("Le nombre de colons ne peut pas dépasser la limite.");
        }
        colons.add(c);
    }

    /**
     * Retourne la liste des colons.
     *
     * @return une liste des colons
     */
    public List<Colon> getlistecolons() {
        return colons;
    }

    /**
     * Retourne l'association actuelle des ressources aux colons.
     *
     * @return une map associant des ressources à des colons
     */
    public Map<Ressource, Colon> getRessources() {
        return ressources;
    }

    /**
     * Retourne le nombre de colons jaloux dans la colonie.
     *
     * @return le nombre de colons jaloux
     */
    public int nombreColonsJaloux() {
        int nombreJaloux = 0;

        for (Colon colon : this.colons) {
            for (Colon ennemi : colon.getEnnemis()) {
                Ressource ressourceEnnemi = ennemi.getRessourceAttribuee();

                if (ressourceEnnemi != null && colon.prefereObjet(ressourceEnnemi)) {
                    colon.setJaloux();
                    nombreJaloux++;
                }
            }
        }
        return nombreJaloux;
    }

    /**
     * Retourne une ressource par son nom.
     *
     * @param nom le nom de la ressource
     * @return la ressource correspondante ou {@code null} si elle n'existe pas
     */
    public Ressource getRessourceParNom(String nom) {
        for (Ressource r : ressources.keySet()) {
            if (r.getNom().equals(nom)) {
                return r;
            }
        }
        return null;
    }



    /**
     * Permute les ressources attribuées entre deux colons.
     *
     * @param colon1 le premier colon
     * @param colon2 le second colon
     */
    public void echangerRessources(Colon colon1, Colon colon2) {
        Ressource ressource1 = colon1.getRessourceAttribuee();
        Ressource ressource2 = colon2.getRessourceAttribuee();
        if (ressource1 != null && ressource2 != null) {
            colon1.setRessourceAttribuee(ressource2);
            colon2.setRessourceAttribuee(ressource1);
            ressources.put(ressource1, colon2);
            ressources.put(ressource2, colon1);
        } else {
            System.out.println("L'un des colons n'a pas de ressource attribuee, impossible d'echanger.");
        }
    }

    /**
     * Trie les colons selon un tri alphanumérique naturel.
     */
    public void trierColonsParNom() {
        colons.sort(Comparator.comparing(colon -> colon.getNom(), (s1, s2) -> {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher m1 = pattern.matcher(s1);
            Matcher m2 = pattern.matcher(s2);

            int pos1 = 0, pos2 = 0;

            while (m1.find(pos1) && m2.find(pos2)) {
                int compareText = s1.substring(pos1, m1.start()).compareTo(s2.substring(pos2, m2.start()));
                if (compareText != 0) return compareText;

                int num1 = Integer.parseInt(m1.group());
                int num2 = Integer.parseInt(m2.group());
                if (num1 != num2) return Integer.compare(num1, num2);

                pos1 = m1.end();
                pos2 = m2.end();
            }
            return s1.substring(pos1).compareTo(s2.substring(pos2));
        }));
    }

    /**
     * Vérifie si toutes les ressources ont été attribuées.
     *
     * @return {@code true} si toutes les ressources sont attribuées, sinon {@code false}
     */
    public boolean toutesRessourcesAttribuees() {
        for (Colon colon : colons) {
            if (colon.getRessourceAttribuee() == null) {
                return false;
            }
        }
        return true;
    }

}