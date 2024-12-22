/**
 * Classe responsable de l'attribution optimale des ressources aux colons.
 * Cette classe combine une approche naive initiale avec une optimisation locale
 * pour minimiser les conflits entre les colons.
 */
package Service;
import Colonie.*;

import java.util.*;


public class AttributionOptimale {
    /**
     * Liste des colons de la colonie.
     */
    private List<Colon> colons;

    /**
     * Map associant les ressources aux colons.
     */
    private Map<Ressource, Colon> mapRessources;

    /**
     * Instance de la colonie gérée par cette classe.
     */
    private Colonie colonies; // Une seule colonie (pour différentier la classe et l'instance)

    /**
     * Constructeur de la classe AttributionOptimale.
     *
     * @param colonies   La colonie concernée par l'attribution.
     * @param colons     Liste des colons de la colonie.
     * @param ressources Map associant les ressources aux colons.
     */
    public AttributionOptimale( Colonie colonies,List<Colon> colons, Map<Ressource, Colon> ressources) {
        this.colons = colons;
        this.mapRessources = ressources;
        this.colonies = colonies;

    }


    /**
     * Effectue une attribution optimisée des ressources aux colons.
     *
     * Cette méthode combine une solution naive initiale avec une optimisation locale
     * pour minimiser le nombre de colons jaloux. L'attribution est d'abord effectuée en
     * fonction des contraintes des colons, puis optimisée par échanges locaux.
     *
     * @param ressources Liste des ressources disponibles pour la colonie.
     * @return Le coût minimal en termes de jalousie.
     * @throws Exception Si le nombre de colons diffère du nombre de ressources.
     */
    public int affectationOptimisee ( List<Ressource> ressources) throws Exception {
        // Le nombre de colons doit être égal au nombre de ressources
        if (colonies.getListeColons().size() != ressources.size()) {
            throw new Exception("Nombre de colons different du nombre de ressources");
        }

        // Étape 1 : Effectuer l'affectation naïve pour obtenir un état initial
        AttributionNaive attNaive = new AttributionNaive(colons,mapRessources,colonies);
        attNaive.affectationNaive();

        // Étape 2 : Créer une copie de la liste des colons pour manipulation
        List<Colon> colonsTries = new ArrayList<>(colonies.getListeColons());

        // Étape 3 : Trier les colons par ordre décroissant du nombre de relations détestables
        colonsTries.sort((c1, c2) -> c2.getEnnemis().size() - c1.getEnnemis().size());
        
        int meilleurCout = Integer.MAX_VALUE;
        Map<Colon, Ressource> meilleureAffectation = new HashMap<>();

        // Étape 4 : Essayer plusieurs affectations initiales
    for (int essai = 0; essai < 10; essai++) {
        // Réinitialiser les ressources disponibles
        List<Ressource> ressourcesTemp = new ArrayList<>(ressources);


        // Affecter les ressources en priorité aux colons ayant le plus de contraintes
            for (Colon colon : colonsTries) {
                Ressource meilleureRessource = null;
                int minConflits = Integer.MAX_VALUE;

            for (Ressource ressourceCandidate : ressourcesTemp) {
                colon.setRessourceAttribuee(ressourceCandidate);
                 
                    // Calculer les conflits qui peuvent se produire
                int conflits = 0;
                for (Colon voisin : colon.getEnnemis()) {
                    if (voisin.getRessourceAttribuee() != null && 
                        colon.getlistepreferences().indexOf(voisin.getRessourceAttribuee()) < colon.getlistepreferences().indexOf(ressourceCandidate)) {
                        conflits++;
                    }
                }
                    if (conflits < minConflits) {
                        minConflits = conflits;
                        meilleureRessource = ressourceCandidate;
                    }
                }
                
                // Affecter la meilleure ressource trouvée
            if (meilleureRessource != null) {
                colon.setRessourceAttribuee(meilleureRessource);
                ressourcesTemp.remove(meilleureRessource);
            }
            }

            // Étape 5 : Optimisation locale par échanges de ressources
            boolean amelioration;
            do {
                amelioration = false;
                int coutActuel = colonies.nombreColonsJaloux();
                
                // Essayer tous les échanges possibles entre paires de colons
                for (int i = 0; i < colonsTries.size(); i++) {
                    for (int j = i + 1; j < colonsTries.size(); j++) {
                        Colon colon1 = colonsTries.get(i);
                        Colon colon2 = colonsTries.get(j);
                        
                        // Tester si l'échange améliore le coût
                        colonies.echangerRessources(colon1, colon2);
                        int nouveauCout = colonies.nombreColonsJaloux();
                        
                        if (nouveauCout < coutActuel) {
                            coutActuel = nouveauCout;
                            amelioration = true;
                        } else {
                            // sinon
                            colonies.echangerRessources(colon1, colon2);
                        }
                    }
                }
            } while (amelioration);
            
            // Si on trouve une meilleure affectation on met à jour la solution
            int coutFinal = colonies.nombreColonsJaloux();
            if (coutFinal < meilleurCout) {
                meilleurCout = coutFinal;
                meilleureAffectation.clear();
                for (Colon c : colonsTries) {
                    meilleureAffectation.put(c, c.getRessourceAttribuee());
                }
            }
        }

        // Étape 6 : garder la meilleure affectation
    for (Map.Entry<Colon, Ressource> entry : meilleureAffectation.entrySet()) {
        entry.getKey().setRessourceAttribuee(entry.getValue());
    }
        
        return meilleurCout;


    }


}