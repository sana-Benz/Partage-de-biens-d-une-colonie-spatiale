package Colonie;

import ExceptionColonie.ExceptionColon;
import Service.AttributionNaive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ColonieTest {

    private Colonie colonie;

    @BeforeEach
    public void setUp() throws ExceptionColon {
        // Initialisation d'une colonie avec un maximum de 5 colons
        colonie = new Colonie(5);

        // Initialisation des ressources
        List<String> nomsRessources = Arrays.asList("Bois", "Eau", "Pierre");
        colonie.initialiserRessources(nomsRessources);
    }

    @Test
    public void testInitialisationColonie() {
        assertEquals(5, colonie.getn(), "La taille maximale de la colonie est incorrecte.");
        assertTrue(colonie.getListeColons().isEmpty(), "La liste des colons devrait être vide au départ.");
        assertFalse(colonie.getRessources().isEmpty(), "La liste des ressources ne devrait pas être vide après initialisation.");
        assertEquals(3, colonie.getRessources().size(), "Le nombre de ressources initialisées est incorrect.");
    }

    @Test
    public void testAjoutColon() throws ExceptionColon {
        Colon colon = new Colon("Alice");
        colonie.ajoutColon(colon);
        assertEquals(1, colonie.getListeColons().size(), "Le nombre de colons après ajout est incorrect.");
        assertEquals("Alice", colonie.getListeColons().get(0).getNom(), "Le nom du colon ajouté est incorrect.");
    }

    @Test
    public void testAjoutColonDepasseLimite() {
        Exception exception = assertThrows(ExceptionColon.class, () -> {
            for (int i = 1; i <= colonie.getn() + 1; i++) {
                colonie.ajoutColon(new Colon("Colon" + i));
            }
        }, "Une exception devrait être levée lorsqu'on dépasse la limite de colons.");
        assertEquals("Le nombre de colons ne peut pas dépasser la limite.", exception.getMessage(), "Le message de l'exception ne correspond pas.");
    }

    @Test
    public void testInitialiserColons() throws ExceptionColon {
        List<String> nomsColons = Arrays.asList("Alice", "Bob", "Charlie");
        colonie.initialiserColons(nomsColons);
        assertEquals(3, colonie.getListeColons().size(), "Le nombre de colons initialisés est incorrect.");
        assertEquals("Alice", colonie.getListeColons().get(0).getNom(), "Le premier colon initialisé est incorrect.");
        assertEquals("Bob", colonie.getListeColons().get(1).getNom(), "Le deuxième colon initialisé est incorrect.");
        assertEquals("Charlie", colonie.getListeColons().get(2).getNom(), "Le troisième colon initialisé est incorrect.");
    }

    @Test
    public void testInitialiserRessources() {
        // Les ressources sont déjà initialisées dans setUp()
        assertEquals(3, colonie.getRessources().size(), "Le nombre de ressources initialisées est incorrect.");
        assertTrue(colonie.getRessources().containsKey(new Ressource("Bois")), "La ressource 'Bois' devrait être présente.");
        assertTrue(colonie.getRessources().containsKey(new Ressource("Eau")), "La ressource 'Eau' devrait être présente.");
        assertTrue(colonie.getRessources().containsKey(new Ressource("Pierre")), "La ressource 'Pierre' devrait être présente.");
    }

    @Test
    public void testEchangerRessources() throws ExceptionColon {
        Colon alice = new Colon("Alice");
        Colon bob = new Colon("Bob");
        Ressource bois = colonie.getRessourceParNom("Bois");
        Ressource eau = colonie.getRessourceParNom("Eau");

        colonie.ajoutColon(alice);
        colonie.ajoutColon(bob);

        // Attribution initiale des ressources
        alice.setRessourceAttribuee(bois);
        bob.setRessourceAttribuee(eau);

        // Mise à jour de la map des ressources dans la colonie
        colonie.getRessources().put(bois, alice);
        colonie.getRessources().put(eau, bob);

        // Effectuer l'échange des ressources
        colonie.echangerRessources(alice, bob);

        // Vérifications après l'échange
        assertEquals(eau, alice.getRessourceAttribuee(), "L'échange de ressources pour Alice est incorrect.");
        assertEquals(bois, bob.getRessourceAttribuee(), "L'échange de ressources pour Bob est incorrect.");
    }

    @Test
    public void testNombreColonsJaloux() throws ExceptionColon {
        Colon alice = new Colon("Alice");
        Colon bob = new Colon("Bob");

        // Ajouter les colons via ajoutColon
        colonie.ajoutColon(alice);
        colonie.ajoutColon(bob);

        // Récupérer les ressources de la colonie
        Ressource bois = colonie.getRessourceParNom("Bois");
        Ressource eau = colonie.getRessourceParNom("Eau");
        Ressource pierre = colonie.getRessourceParNom("Pierre");
        assertNotNull(bois, "La ressource 'Bois' devrait être présente dans la colonie.");
        assertNotNull(eau, "La ressource 'Eau' devrait être présente dans la colonie.");
        assertNotNull(pierre, "La ressource 'Pierre' devrait être présente dans la colonie.");

        // Configurer les préférences et les ennemis
        alice.ajoutpreference(bois);  // Alice préfère Bois
        alice.ajoutpreference(eau);   // Puis Eau
        bob.ajoutpreference(bois);    // Bob préfère Bois
        alice.ajoutennemi(bob);        // Alice a Bob comme ennemi

        // Appeler l'algorithme d'affectation des ressources
        AttributionNaive assigner = new AttributionNaive(colonie.getListeColons(), colonie.getRessources(), colonie);
        assigner.affectationNaive();

        // Manuellement changer la ressource attribuée à Alice pour déclencher la jalousie
        colonie.getRessources().put(eau, alice);
        colonie.getRessources().put(bois, bob);
        alice.setRessourceAttribuee(eau);
        bob.setRessourceAttribuee(bois);




        // Appeler la méthode à tester
        int nombreJaloux = colonie.nombreColonsJaloux();

        // Vérifier le résultat attendu
        assertEquals(1, nombreJaloux, "Le nombre de colons jaloux est incorrect.");
    }

    @Test
    public void testToutesRessourcesAttribuees() throws ExceptionColon {
        Colon alice = new Colon("Alice");
        Colon bob = new Colon("Bob");
        Ressource bois = colonie.getRessourceParNom("Bois");
        Ressource eau = colonie.getRessourceParNom("Eau");

        colonie.ajoutColon(alice);
        colonie.ajoutColon(bob);

        // Attribution des ressources
        alice.setRessourceAttribuee(bois);
        colonie.getRessources().put(bois, alice);
        bob.setRessourceAttribuee(eau);
        colonie.getRessources().put(eau, bob);

        // Vérifier que toutes les ressources sont attribuées
        assertTrue(colonie.toutesRessourcesAttribuees(), "Toutes les ressources devraient être attribuées.");
    }

    @Test
    public void testToutesRessourcesAttribueesFalse() throws ExceptionColon {
        Colon alice = new Colon("Alice");
        colonie.ajoutColon(alice);

        // Alice n'a pas de ressource attribuée
        assertFalse(colonie.toutesRessourcesAttribuees(), "Toutes les ressources ne sont pas attribuées, donc devrait retourner false.");
    }
}

