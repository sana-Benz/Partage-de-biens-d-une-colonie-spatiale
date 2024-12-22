/**
 * La classe {@code Ressource} représente une ressource qui peut être attribuée à un colon dans une colonie.
 * Chaque ressource a un nom et un état de disponibilité.
 */
package Colonie;

/**
 * Représente une ressource dans une colonie spatiale.
 */
public class Ressource {
    private String nom;
    private boolean disponible;

    /**
     * Constructeur pour initialiser une ressource avec un nom donné.
     *
     * @param nom le nom de la ressource
     */
    public Ressource(String nom) {
        this.nom = nom;
        this.disponible = true;
    }

    /**
     * Retourne le nom de la ressource.
     *
     * @return le nom de la ressource
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de la ressource.
     *
     * @param nom le nouveau nom de la ressource
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne une représentation textuelle de la ressource.
     *
     * @return le nom de la ressource
     */
    @Override
    public String toString() {
        return nom;
    }

    /**
     * Compare cette ressource à un autre objet.
     * Deux ressources sont égales si leurs noms sont identiques.
     *
     * @param o l'objet à comparer
     * @return {@code true} si les deux ressources ont le même nom, sinon {@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Ressource ressource = (Ressource) o;
        return nom.equals(ressource.nom);
    }

    /**
     * Retourne le code de hachage de la ressource.
     *
     * @return le code de hachage basé sur le nom de la ressource
     */
    @Override
    public int hashCode() { return nom.hashCode(); }
}