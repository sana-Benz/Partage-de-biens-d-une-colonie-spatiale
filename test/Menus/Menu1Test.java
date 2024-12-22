package Menus;

import Colonie.*;
import ExceptionColonie.ExceptionColon;
import ExceptionColonie.PreferencesInvalidException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Menu1Test {

    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        // Aucun besoin d'initialisation particulière ici
    }

    @Test
    void testAfficherMenu1_SuccessfulSetup() throws ExceptionColon, PreferencesInvalidException {
        String simulatedUserInput = String.join("\n",
                "Alice",         // Nom du colon 1
                "Bob",           // Nom du colon 2
                "Bois",          // Nom de la ressource 1
                "Eau",           // Nom de la ressource 2
                "1",             // Choix : Ajouter une relation
                "Alice Bob",     // Relation entre Alice et Bob
                "2",             // Choix : Ajouter les préférences d'Alice
                "Alice Bois Eau",// Préférences d'Alice
                "2",             // Choix : Ajouter les préférences de Bob
                "Bob Eau Bois",  // Préférences de Bob
                "3"              // Choix : Fin du menu
        );

        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        Scanner scanner = new Scanner(System.in);
        Menu1 menu = new Menu1(2);
        menu.afficherMenu1(scanner);

        // Assertions pour vérifier les résultats
        Colonie colonie = Menu1.getColonie();
        assertNotNull(colonie, "La colonie ne devrait pas être null après l'exécution du menu.");
        assertEquals(2, colonie.getListeColons().size(), "Le nombre de colons est incorrect.");
        assertNotNull(colonie.getColon("Alice"), "Alice devrait exister dans la colonie.");
        assertNotNull(colonie.getColon("Bob"), "Bob devrait exister dans la colonie.");

        // Vérifier la relation entre Alice et Bob
        assertTrue(colonie.getColon("Alice").getEnnemis().contains(colonie.getColon("Bob")),
                "Bob devrait être un ennemi de Alice.");
        assertTrue(colonie.getColon("Bob").getEnnemis().contains(colonie.getColon("Alice")),
                "Alice devrait être un ennemi de Bob.");

        // Vérifier les préférences
        List<Ressource> preferencesAlice = colonie.getColon("Alice").getlistepreferences();
        assertEquals(2, preferencesAlice.size(), "Alice devrait avoir 2 préférences.");
        assertTrue(preferencesAlice.stream().anyMatch(r -> r.getNom().equals("Bois")),
                "Alice devrait préférer Bois.");
        assertTrue(preferencesAlice.stream().anyMatch(r -> r.getNom().equals("Eau")),
                "Alice devrait préférer Eau.");

        List<Ressource> preferencesBob = colonie.getColon("Bob").getlistepreferences();
        assertEquals(2, preferencesBob.size(), "Bob devrait avoir 2 préférences.");
        assertTrue(preferencesBob.stream().anyMatch(r -> r.getNom().equals("Eau")),
                "Bob devrait préférer Eau.");
        assertTrue(preferencesBob.stream().anyMatch(r -> r.getNom().equals("Bois")),
                "Bob devrait préférer Bois.");
    }

    @Test
    void testAfficherMenu1_InvalidInputHandling() throws ExceptionColon, PreferencesInvalidException {
        // Simuler des choix invalides suivis d'un choix valide
        String simulatedUserInput = String.join("\n",
                "Alice",          // Nom du colon 1
                "Bob",            // Nom du colon 2
                "Bois",           // Nom de la ressource 1
                "Eau",            // Nom de la ressource 2
                "abc",            // Entrée non valide pour choix
                "4",              // Choix hors options
                "1",              // Choix valide : Ajouter une relation
                "Alice Bob",      // Relation entre Alice et Bob
                "2",              // Choix : Ajouter les préférences d'Alice
                "Alice Bois Eau", // Préférences d'Alice
                "2",              // Choix : Ajouter les préférences de Bob
                "Bob Eau Bois",   // Préférences de Bob
                "3"               // Choix : Fin du menu
        );

        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        Scanner scanner = new Scanner(System.in);
        Menu1 menu = new Menu1(2);

        // Assurer que la méthode s'exécute sans lever d'exception
        assertDoesNotThrow(() -> menu.afficherMenu1(scanner));

        // Vérifier que la colonie est correctement initialisée malgré les entrées invalides
        Colonie colonie = Menu1.getColonie();
        assertNotNull(colonie, "La colonie ne devrait pas être null après l'exécution du menu.");
        assertEquals(2, colonie.getListeColons().size(), "Le nombre de colons est incorrect.");
        assertNotNull(colonie.getColon("Alice"), "Alice devrait exister dans la colonie.");
        assertNotNull(colonie.getColon("Bob"), "Bob devrait exister dans la colonie.");

        // Vérifier la relation entre Alice et Bob
        assertTrue(colonie.getColon("Alice").getEnnemis().contains(colonie.getColon("Bob")),
                "Bob devrait être un ennemi de Alice.");
        assertTrue(colonie.getColon("Bob").getEnnemis().contains(colonie.getColon("Alice")),
                "Alice devrait être un ennemi de Bob.");

        // Vérifier les préférences
        List<Ressource> preferencesAlice = colonie.getColon("Alice").getlistepreferences();
        assertEquals(2, preferencesAlice.size(), "Alice devrait avoir 2 préférences.");
        assertTrue(preferencesAlice.stream().anyMatch(r -> r.getNom().equals("Bois")),
                "Alice devrait préférer Bois.");
        assertTrue(preferencesAlice.stream().anyMatch(r -> r.getNom().equals("Eau")),
                "Alice devrait préférer Eau.");

        List<Ressource> preferencesBob = colonie.getColon("Bob").getlistepreferences();
        assertEquals(2, preferencesBob.size(), "Bob devrait avoir 2 préférences.");
        assertTrue(preferencesBob.stream().anyMatch(r -> r.getNom().equals("Eau")),
                "Bob devrait préférer Eau.");
        assertTrue(preferencesBob.stream().anyMatch(r -> r.getNom().equals("Bois")),
                "Bob devrait préférer Bois.");
    }

    @Test
    void testAfficherMenu1_DuplicateColonNames() throws ExceptionColon, PreferencesInvalidException {
        // Simuler des noms avec des doublons au début, puis des noms corrects
        String simulatedUserInput = String.join("\n",
                "Alice",          // Première tentative - Colon 1
                "Alice",          // Première tentative - Colon 2 (duplicata)
                "Alice",          // Seconde tentative - Colon 1
                "Bob",            // Seconde tentative - Colon 2 (correct)
                "Bois",           // Nom de la ressource 1
                "Eau",            // Nom de la ressource 2
                "1",              // Choix : Ajouter une relation
                "Alice Bob",      // Relation entre Alice et Bob
                "2",              // Choix : Ajouter les préférences d'Alice
                "Alice Bois Eau", // Préférences d'Alice
                "2",              // Choix : Ajouter les préférences de Bob
                "Bob Eau Bois",   // Préférences de Bob
                "3"               // Choix : Fin du menu
        );

        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        Scanner scanner = new Scanner(System.in);
        Menu1 menu = new Menu1(2);

        // Assurer que la méthode s'exécute sans lever d'exception
        assertDoesNotThrow(() -> menu.afficherMenu1(scanner));

        // Vérifier que la colonie est correctement initialisée malgré les noms dupliqués
        Colonie colonie = Menu1.getColonie();
        assertNotNull(colonie, "La colonie ne devrait pas être null après l'exécution du menu.");
        assertEquals(2, colonie.getListeColons().size(), "Le nombre de colons est incorrect.");
        assertNotNull(colonie.getColon("Alice"), "Alice devrait exister dans la colonie.");
        assertNotNull(colonie.getColon("Bob"), "Bob devrait exister dans la colonie.");

        // Vérifier la relation entre Alice et Bob
        assertTrue(colonie.getColon("Alice").getEnnemis().contains(colonie.getColon("Bob")),
                "Bob devrait être un ennemi de Alice.");
        assertTrue(colonie.getColon("Bob").getEnnemis().contains(colonie.getColon("Alice")),
                "Alice devrait être un ennemi de Bob.");

        // Vérifier les préférences
        List<Ressource> preferencesAlice = colonie.getColon("Alice").getlistepreferences();
        assertEquals(2, preferencesAlice.size(), "Alice devrait avoir 2 préférences.");
        assertTrue(preferencesAlice.stream().anyMatch(r -> r.getNom().equals("Bois")),
                "Alice devrait préférer Bois.");
        assertTrue(preferencesAlice.stream().anyMatch(r -> r.getNom().equals("Eau")),
                "Alice devrait préférer Eau.");

        List<Ressource> preferencesBob = colonie.getColon("Bob").getlistepreferences();
        assertEquals(2, preferencesBob.size(), "Bob devrait avoir 2 préférences.");
        assertTrue(preferencesBob.stream().anyMatch(r -> r.getNom().equals("Eau")),
                "Bob devrait préférer Eau.");
        assertTrue(preferencesBob.stream().anyMatch(r -> r.getNom().equals("Bois")),
                "Bob devrait préférer Bois.");
    }

    // Réinitialiser System.in après chaque test
    @AfterEach
    public void tearDown() {
        System.setIn(originalIn);
    }
}
