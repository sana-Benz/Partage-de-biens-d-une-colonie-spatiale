package Menus;

import Colonie.Colon;
import Colonie.Colonie;
import Colonie.Ressource;
import Service.AttributionNaive;
import ExceptionColonie.ExceptionColon;
import ExceptionColonie.PreferencesInvalidException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class Menu1Test {

    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        // Réinitialiser les entrées pour chaque test
    }

    @Test
    public void testAfficherMenu1_SuccessfulSetup() throws ExceptionColon, PreferencesInvalidException {
        String simulatedUserInput = String.join("\n",
                "Alice",      // Nom du colon 1
                "Bob",        // Nom du colon 2
                "Bois",       // Nom de la ressource 1
                "Eau",        // Nom de la ressource 2
                "1",          // Ajouter une relation
                "Alice Bob",  // Relation entre Alice et Bob
                "2",          // Ajouter les préférences d'Alice
                "Alice Bois Eau", // Préférences d'Alice
                "2",          // Ajouter les préférences de Bob
                "Bob Eau Bois",   // Préférences de Bob
                "3"           // Fin du menu
        );

        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        Scanner scanner = new Scanner(System.in);
        Menu1 menu = new Menu1(2);
        menu.afficherMenu1(scanner);

        // Assertions pour vérifier les résultats
        Colonie colonie = Menu1.getColonie();
        assertNotNull(colonie);
        assertEquals(2, colonie.getListeColons().size());
        assertNotNull(colonie.getColon("Alice"));
        assertNotNull(colonie.getColon("Bob"));
    }

    @Test
    public void testAfficherMenu1_InvalidInputHandling() throws ExceptionColon, PreferencesInvalidException {
        // Simuler des choix invalides suivis d'un choix valide
        String simulatedUserInput = String.join("\n",
                "Alice",      // Nom du colon 1
                "Bob",        // Nom du colon 2
                "Bois",       // Nom de la ressource 1
                "Eau",        // Nom de la ressource 2
                "abc",        // Entrée non valide
                "4",          // Choix hors options
                "1",          // Choix valide
                "Alice Bob",  // Relation entre Alice et Bob
                "3"           // Fin
        );

        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        Scanner scanner = new Scanner(System.in);
        Menu1 menu = new Menu1(2);
        menu.afficherMenu1(scanner);

        Colonie colonie = Menu1.getColonie();
        assertNotNull(colonie, "La colonie ne devrait pas être null après l'exécution du menu.");
        assertEquals(2, colonie.getListeColons().size(), "Le nombre de colons est incorrect.");
        assertNotNull(colonie.getColon("Alice"), "Alice devrait exister dans la colonie.");
        assertNotNull(colonie.getColon("Bob"), "Bob devrait exister dans la colonie.");
    }

    @Test
    public void testAfficherMenu1_DuplicateColonNames() throws ExceptionColon, PreferencesInvalidException {
        // Simuler des noms avec des doublons au début, puis des noms corrects
        String simulatedUserInput = String.join("\n",
                "Alice",    // Nom du colon 1
                "Alice",    // Nom du colon 2 (duplicata)
                "Bob",      // Nom du colon 2 (correct)
                "Bois",     // Nom de la ressource 1
                "Eau",      // Nom de la ressource 2
                "3"         // Fin
        );

        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        Scanner scanner = new Scanner(System.in);
        Menu1 menu = new Menu1(2);
        menu.afficherMenu1(scanner);

        Colonie colonie = Menu1.getColonie();
        assertNotNull(colonie, "La colonie ne devrait pas être null après l'exécution du menu.");
        assertEquals(2, colonie.getListeColons().size(), "Le nombre de colons est incorrect.");
        assertNotNull(colonie.getColon("Alice"), "Alice devrait exister dans la colonie.");
        assertNotNull(colonie.getColon("Bob"), "Bob devrait exister dans la colonie.");
    }

    // Réinitialiser System.in après chaque test
    @AfterEach
    public void tearDown() {
        System.setIn(originalIn);
    }
}
