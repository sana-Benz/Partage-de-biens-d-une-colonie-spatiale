package Colonie_spatiale;

public class Ressource {
    private char nom;
    private boolean disponible;

    public Ressource(char nom) {
        this.nom = nom;
        this.disponible = true;
    }
    public char getNom() {
        return nom;
    }
    public void setNom(char nom) {
        this.nom = nom;
    }
    public String toString(){
        return "la ressource est "+nom;
    }

}
