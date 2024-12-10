package Colonie_spatiale.CreationColonie;

import Colonie_spatiale.ExceptionColon;

import java.util.*;

public class Colon {
    private char nom;
    private ArrayList <Ressource> preferences;
    private ArrayList <Colon> ennemis;
    private Ressource ressourceAttribuee ;

    public Colon(char nom) {
        this.nom = nom;
        this.preferences = new ArrayList<>();
        this.ennemis = new ArrayList<>();
        this.ressourceAttribuee=null;
    }

    public char getNom() {
        return nom;
    }
    public void ajoutennemi(Colon c) {
            ennemis.add(c); 
        } 
    public void ajoutpreference(Ressource p){
        preferences.add(p);

    }
    public ArrayList<Ressource> getlistepreferences(){
        return preferences;
    }

    public void ajoutEnnemi (Colon c) throws ExceptionColon {
        if (ennemis.size() > get(n)-1) {
            throw new ExceptionColon("");
        }
        ennemis.add(c);
    public void AfficherListePref(){
        System.out.println("préference du colon"+nom+": "+this.preferences);
    }

    public void setRessourceAttribuée(Ressource r){
        ressourceAttribuee = r;
    }
    public Ressource getRessourceAttribuée(){
        return ressourceAttribuee;
    }

    public ArrayList<Colon> getEnnemis(){
        return ennemis;
    }

    /*public boolean prefereObjet(Ressource ressource) {
        // Comparaison des ressources en fonction de leur ordre de préférence
        boolean result= this.preferences.indexOf(ressource) < this.preferences.indexOf(this.ressourceAttribuée);
        System.out.println("Le colon préfère " + ressource + " ? " + result);
        return result;
    }*/
    public boolean prefereObjet(Ressource ressource) {
        if (this.ressourceAttribuee == null || ressource == null) {
            System.out.println("Erreur : ressourceAttribuée ou ressource est null");
            return false;
        }
        if (!this.preferences.contains(ressource)) {
            System.out.println("Erreur : la ressource " + ressource + " n'est pas trouvée dans les préférences de " + nom);
            System.out.println("préference du colon"+nom+": "+this.preferences);
            return false;
        }
        if (!this.preferences.contains(this.ressourceAttribuee)) {
            System.out.println("Erreur : la ressource attribuée " + this.ressourceAttribuee + " n'est pas trouvée dans les préférences de " + nom);
            return false;
        }
        boolean result = this.preferences.indexOf(ressource) < this.preferences.indexOf(this.ressourceAttribuee);
        System.out.println("Le colon " + nom + " préfère " + ressource + " à " + this.ressourceAttribuee + " ? " + result);
        return result;
    }


}
