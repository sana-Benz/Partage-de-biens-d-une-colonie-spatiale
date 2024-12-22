package Menus;

import Colonie.*;
import ExceptionColonie.ExceptionColon;
import ExceptionColonie.PreferencesInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Menu1Test {

    private Menu1 menu1;

    @BeforeEach
    void setUp() throws ExceptionColon {
        menu1 = new Menu1(3); // Initialisation avec 3 colons
    }

    @Test
    void testGetN() {
        assertEquals(3, menu1.getN());
    }

    @Test
    void testGetColonie() {
        assertNotNull(Menu1.getColonie());
    }

    @Test
    void testAfficherMenu1() throws PreferencesInvalidException {
        // Entrée simulée ajustée : chaque section correspond à un appel à nextLine()
        String input = "Colon1\nColon2\nColon3\nRessource1\nRessource2\nRessource3\n1\nColon1 Colon2\n2\nColon1 1 2 3\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        try {
            menu1.afficherMenu1(scanner);
        } catch (NoSuchElementException e) {
            System.err.println("Exception capturée: " + e.getMessage());
            fail("Une exception NoSuchElementException a été lancée pendant l'exécution de afficherMenu1.");
        }
        // Vérifiez que les colons et les ressources ont été correctement initialisés
        Colonie colonie = Menu1.getColonie();
        assertEquals(3, colonie.getlistecolons().size());
        assertEquals(3, colonie.getListeRessources().size());

        // Vérifiez que les relations et les préférences ont été correctement ajoutées
        Colon colon1 = colonie.getColon("Colon1");
        Colon colon2 = colonie.getColon("Colon2");
        assertNotNull(colon1);
        assertNotNull(colon2);
        assertTrue(colon1.getEnnemis().contains(colon2));
        assertTrue(colon2.getEnnemis().contains(colon1));
        assertEquals(3, colon1.getlistepreferences().size());
    }

    @Test
    void testAfficherMenu1InvalidInput() throws PreferencesInvalidException {
        // Entrée simulée avec noms non distincts
        String input = "Colon1\nColon1\nColon3\nRessource1\nRessource2\nRessource3\n1\nColon1 Colon2\n2\nColon1 1 2 3\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        try {
            menu1.afficherMenu1(scanner);
        } catch (NoSuchElementException e) {
            // Ajout d'un message d'erreur pour diagnostiquer si on dépasse l'entrée
            System.err.println("Exception capturée : " + e.getMessage());
            fail("L'entrée simulée est insuffisante pour tester la méthode.");
        }

        // Vérifiez que les colons et les ressources n'ont pas été correctement
        // initialisés
        Colonie colonie = Menu1.getColonie();
        assertEquals(0, colonie.getlistecolons().size());
        assertEquals(0, colonie.getListeRessources().size());
    }
}
