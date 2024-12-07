package Colonie_spatiale.AttributionRessources;
import Colonie_spatiale.CreationColonie.Colon;
import Colonie_spatiale.CreationColonie.Ressource;

import java.util.*;


public class AttributionOptimale {
    private List<Colon> colons;
    private Map<Ressource, Colon> ressources;

    public AttributionOptimale(List<Colon> colons, Map<Ressource, Colon> ressources) {
        this.colons = colons;
        this.ressources = ressources;
    }

    public void affectationOptimisee() {
        // Étape 1 : Trier les colons par ordre décroissant du nombre d'ennemis
        List<Colon> colonsTries = new ArrayList<>(colons);
        colonsTries.sort((a, b) -> b.getEnnemis().size() - a.getEnnemis().size());

        // Étape 2 : Affecter les ressources
        for (Colon colon : colonsTries) {
            Ressource meilleureRessource = null;
            int minPopularite = Integer.MAX_VALUE;

            for (Ressource ressource : colon.getlistepreferences()) {
                // Vérifier si la ressource est disponible
                if (ressources.get(ressource) == null) { // La ressource n'est pas encore attribuée
                    // Calculer combien d'ennemis convoitent cette ressource
                    int popularite = 0;
                    for (Colon ennemi : colon.getEnnemis()) {
                        if (ennemi.getlistepreferences().contains(ressource)) {
                            popularite++;
                        }
                    }

                    // Sélectionner la ressource la moins convoitée
                    if (popularite < minPopularite) {
                        meilleureRessource = ressource;
                        minPopularite = popularite;
                    }
                }
            }

            // Affecter la meilleure ressource trouvée au colon
            if (meilleureRessource != null) {
                colon.setRessourceAttribuée(meilleureRessource);
                ressources.put(meilleureRessource, colon);
            }
        }
    }

    public void afficherAffectations() {
        for (Map.Entry<Ressource, Colon> entry : ressources.entrySet()) {
            if (entry.getValue() != null) {
                System.out.println(entry.getValue().getNom() + " -> " + entry.getKey().getNom());
            }
        }
    }
}