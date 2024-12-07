package Colonie_spatiale.CreationColonie;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ressource ressource = (Ressource) o;
        return nom == ressource.nom;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(nom);
    }
}
