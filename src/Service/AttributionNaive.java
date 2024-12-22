/**
 * Classe AttributionNaive qui implémente une méthode d'attribution naive des ressources
 * aux colons en fonction de leurs préférences.
 */
package Service;
import Colonie.*;
import java.util.*;

/**
 * Classe responsable de l'attribution naive des ressources aux colons.
 */
public class AttributionNaive {
    /**
     * Liste des colons.
     */
    private List<Colon> colons;

    /**
     * Map des ressources et des colons auxquels elles sont attribuées.
     */
    private Map<Ressource, Colon> ressources;

    /**
     * Instance de la colonie.
     */
    private Colonie colonie;

    /**
     * Constructeur de la classe AttributionNaive.
     *
     * @param colons    Liste des colons.
     * @param ressources Map des ressources et des colons auxquels elles sont attribuées.
     * @param colonie   Instance de la colonie.
     */
    public AttributionNaive(List<Colon> colons, Map<Ressource, Colon> ressources, Colonie colonie) {
        this.colons = colons;
        this.ressources = ressources;
        this.colonie= colonie;
    }


    /**
     * Méthode d'attribution naive des ressources aux colons en fonction de leurs préférences.
     * Cette méthode attribue la première ressource disponible dans les préférences de chaque colon.
     */
    public void affectationNaive() {
        for (Colon c : colons) {
            for (Ressource p : c.getlistepreferences()) {
                Ressource ressource = colonie.getRessourceParNom(p.getNom());
                if (ressource != null && ressources.get(ressource) == null) {
                    c.setRessourceAttribuee(ressource);
                    ressources.put(ressource, c);
                    break;
                }
            }
        }
    }
}
