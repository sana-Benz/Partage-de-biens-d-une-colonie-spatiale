/**
 * La classe {@code Colon} représente un colon avec un nom, des préférences de ressources,
 * une liste d'ennemis et une ressource attribuée.
 * Elle permet également de gérer les préférences, les relations d'inimitiés (ennemis)
 * et d'identifier si un colon préfère une autre ressource à celle qui lui est attribuée.
 */
package Colonie;

import ExceptionColonie.ExceptionColon;
import java.util.*;

public class Colon {
    private String nom;
    private ArrayList<Ressource> preferences;
    private ArrayList<Colon> ennemis;
    private Ressource ressourceAttribuee;
    boolean jaloux;

    /**
     * Constructeur pour initialiser un colon avec un nom donné.
     *
     * @param nom le nom du colon
     */
    public Colon(String nom) {
        this.nom = nom;
        this.preferences = new ArrayList<>();
        this.ennemis = new ArrayList<>();
        this.ressourceAttribuee = null;
        this.jaloux = false;
    }

    /**
     * Retourne le nom du colon.
     *
     * @return le nom du colon
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit l'état de jalousie du colon.
     */
    public void setJaloux() {
        this.jaloux = true;
    }

    /**
     * Ajoute un ennemi à la liste des ennemis du colon.
     *
     * @param c le colon à ajouter en tant qu'ennemi
     * @throws ExceptionColon si le colon est déjà dans la liste des ennemis
     */
    public void ajoutennemi(Colon c) throws ExceptionColon {
        if (ennemis.contains(c)) {
            throw new ExceptionColon("La relation 'deteste' entre '" + this.nom + "' et '" + c.getNom() + "' existe déjà.");
        }
        ennemis.add(c);
    }

    /**
     * Ajoute une ressource aux préférences du colon si elle n'existe pas déjà.
     *
     * @param p la ressource à ajouter
     */
    public void ajoutpreference(Ressource p) {
        if (!preferences.contains(p)) {
            preferences.add(p);
        } else {
            System.out.println("Erreur : La ressource " + p.getNom() + " est déjà dans les préférences.");
        }
    }

    /**
     * Retourne la liste des préférences du colon.
     *
     * @return une liste des ressources préférées
     */
    public ArrayList<Ressource> getlistepreferences() {
        return preferences;
    }

    /**
     * Affiche la liste des préférences du colon.
     */
    public void AfficherListePref() {
        System.out.print("Preferences du colon " + nom + ": ");
        for (Ressource r : preferences) {
            System.out.print(r.getNom() + " ");
        }
        System.out.println();
    }

    /**
     * Définit la ressource attribuée au colon.
     *
     * @param r la ressource à attribuer
     */
    public void setRessourceAttribuee(Ressource r) {
        ressourceAttribuee = r;
    }

    /**
     * Retourne la ressource attribuée au colon.
     *
     * @return la ressource attribuée ou {@code null} si aucune ressource n'est attribuée
     */
    public Ressource getRessourceAttribuee() {
        return ressourceAttribuee;
    }

    /**
     * Retourne la liste des ennemis du colon.
     *
     * @return une liste des colons ennemis
     */
    public ArrayList<Colon> getEnnemis() {
        return ennemis;
    }

    /**
     * Vérifie si le colon préfère une autre ressource à celle qui lui est actuellement attribuée.
     *
     * @param ressource la ressource à comparer
     * @return {@code true} si la ressource est préférée, {@code false} sinon
     */
    public boolean prefereObjet(Ressource ressource) {
        if (this.ressourceAttribuee == null || ressource == null) {
            return false;
        }
        if (!this.preferences.contains(ressource)) {
            System.out.println(
                    "Erreur : la ressource " + ressource + " n'est pas trouvee dans les preferences de " + nom);
            System.out.println("preference du colon" + nom + ": " + this.preferences);
            return false;
        }
        if (!this.preferences.contains(this.ressourceAttribuee)) {
            System.out.println("Erreur : la ressource attribuee " + this.ressourceAttribuee
                    + " n'est pas trouvee dans les preferences de " + nom);
            return false;
        }
        return this.preferences.indexOf(ressource) < this.preferences.indexOf(this.ressourceAttribuee);
    }

    /**
     * Retourne une représentation textuelle du colon (son nom).
     *
     * @return le nom du colon
     */
    @Override
    public String toString() {
        return nom; // Retourne uniquement le nom du colon
    }


}