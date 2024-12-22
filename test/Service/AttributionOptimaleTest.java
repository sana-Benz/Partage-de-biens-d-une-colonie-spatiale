package Service;

import Colonie.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AttributionOptimaleTest {

    private Colonie colonie;
    private List<Colon> colons;
    private Map<Ressource, Colon> ressources;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialiser une colonie avec 3 colons et 3 ressources
        colonie = new Colonie(3);

        // Ajouter des colons
        List<String> nomsColons = Arrays.asList("Alice", "Bob", "Charlie");
        colonie.initialiserColons(nomsColons);
        colons = colonie.getListeColons();

        // Ajouter des ressources
        List<String> nomsRessources = Arrays.asList("Bois", "Eau", "Pierre");
        colonie.initialiserRessources(nomsRessources);
        ressources = colonie.getRessources();

        // Définir des préférences pour chaque colon
        colons.get(0).ajoutpreference(new Ressource("Eau")); // Alice préfère Eau
        colons.get(0).ajoutpreference(new Ressource("Bois"));
        colons.get(0).ajoutpreference(new Ressource("Pierre"));

        colons.get(1).ajoutpreference(new Ressource("Bois")); // Bob préfère Bois
        colons.get(1).ajoutpreference(new Ressource("Eau"));
        colons.get(1).ajoutpreference(new Ressource("Pierre"));

        colons.get(2).ajoutpreference(new Ressource("Pierre")); // Charlie préfère Pierre
        colons.get(2).ajoutpreference(new Ressource("Bois"));
        colons.get(2).ajoutpreference(new Ressource("Eau"));

        // Ajouter des relations d'ennemis
        colons.get(0).ajoutennemi(colons.get(1)); // Alice déteste Bob
        colons.get(1).ajoutennemi(colons.get(0)); // Bob déteste Alice
        colons.get(1).ajoutennemi(colons.get(2)); // Bob déteste Charlie
        colons.get(2).ajoutennemi(colons.get(1)); // Charlie déteste Bob
    }

    @Test
    public void testAffectationOptimisee() throws Exception {
        AttributionOptimale attribution = new AttributionOptimale(colonie, colons, ressources);

        // Exécuter l'algorithme d'affectation optimisée
        int nombreColonsJaloux = attribution.affectationOptimisee(new ArrayList<>(ressources.keySet()));

        // Vérifier que le nombre de colons jaloux est minimal
        assertEquals(0, nombreColonsJaloux, "Le nombre de colons jaloux devrait être minimal.");

        // Vérifier les affectations finales
        Map<Ressource, Colon> ressourcesAttribuees = colonie.getRessources();
        assertNotNull(ressourcesAttribuees.get(new Ressource("Eau")), "La ressource 'Eau' devrait être attribuée.");
        assertNotNull(ressourcesAttribuees.get(new Ressource("Bois")), "La ressource 'Bois' devrait être attribuée.");
        assertNotNull(ressourcesAttribuees.get(new Ressource("Pierre")), "La ressource 'Pierre' devrait être attribuée.");

        // Vérifier que chaque colon a une ressource attribuée
        for (Colon colon : colons) {
            assertNotNull(colon.getRessourceAttribuee(), "Chaque colon devrait avoir une ressource attribuée.");
        }
    }

    @Test
    public void testConflitEntreColons() throws Exception {
        AttributionOptimale attribution = new AttributionOptimale(colonie, colons, ressources);

        // Exécuter l'algorithme d'affectation optimisée
        int nombreColonsJaloux = attribution.affectationOptimisee(new ArrayList<>(ressources.keySet()));

        // Vérifier que les ennemis n'ont pas de ressources préférées en commun si possible
        for (Colon colon : colons) {
            Ressource ressourceAttribuee = colon.getRessourceAttribuee();
            for (Colon ennemi : colon.getEnnemis()) {
                if (ressourceAttribuee != null && ennemi.getlistepreferences().contains(ressourceAttribuee)) {
                    assertNotEquals(ressourceAttribuee, ennemi.getRessourceAttribuee(),
                            "Les ennemis ne devraient pas partager une ressource préférée.");
                }
            }
        }
    }

    @Test
    public void testAffectationAvecConflitInitial() throws Exception {
        // Ajouter un conflit initial
        colons.get(0).setRessourceAttribuee(new Ressource("Eau")); // Alice reçoit Eau
        ressources.put(new Ressource("Eau"), colons.get(0));

        AttributionOptimale attribution = new AttributionOptimale(colonie, colons, ressources);

        // Exécuter l'algorithme d'affectation optimisée
        int nombreColonsJaloux = attribution.affectationOptimisee(new ArrayList<>(ressources.keySet()));

        // Vérifier que le conflit a été résolu
        assertEquals(0, nombreColonsJaloux, "Le nombre de colons jaloux devrait être résolu après optimisation.");
    }
}
