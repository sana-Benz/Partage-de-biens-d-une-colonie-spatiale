package Colonie_spatiale;
import java.util.*;

public class Colon {
    private char nom;
    private ArrayList <Preference> preferences;
    private ArrayList <Colon> ennemis;
    //private Colonie colonie;

    public Colon(char nom) {
        this.nom = nom;
        this.preferences = new ArrayList<>();
        this.ennemis = new ArrayList<>();
        //this.colonie=colonie;
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




}
