package Colonie_spatiale;
import java.util.*;

public class Colon {
    private String nom;
    private List <Preference> preferences;
    private List <Colon> ennemis;

    public Colon(String nom) {
        this.nom = nom;
        this.preferences = new ArrayList<>();
        this.ennemis = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }




}
