package Colonie_spatiale;
import java.util.*;

public class Colon {
    private char nom;
    private ArrayList <Preference> preferences;
    private ArrayList <Colon> ennemis;
    private Ressource ressourceAttribuée ;

    public Colon(char nom) {
        this.nom = nom;
        this.preferences = new ArrayList<>();
        this.ennemis = new ArrayList<>();
        this.ressourceAttribuée=null;
    }

    public char getNom() {
        return nom;
    }
    public void ajoutennemi(Colon c) {
            ennemis.add(c); 
        } 
    public void ajoutpreference(Preference p){
        preferences.add(p);

    }
    public ArrayList<Preference> getlistepreferences(){
        return preferences;
    }
    public void setRessourceAttribuée(Ressource r){
        ressourceAttribuée = r;
    }
    public Ressource getRessourceAttribuée(){
        return ressourceAttribuée;
    }

    public ArrayList<Colon> getEnnemis(){
        return ennemis;
    }

    public boolean prefereObjet(Ressource ressource) {
        // Comparaison des ressources en fonction de leur ordre de préférence
        boolean result= this.preferences.indexOf(ressource) < this.preferences.indexOf(this.ressourceAttribuée);
        System.out.println("Le colon préfère " + ressource + " ? " + result);
        return result;
    }


}
