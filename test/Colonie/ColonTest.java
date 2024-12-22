package Colonie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColonTest {

    private Colon colon;
    private Ressource bois;
    private Ressource pierre;

    @BeforeEach
    void setUp() {
        colon = new Colon("Alice");
        bois = new Ressource("Bois");
        pierre = new Ressource("Pierre");
    }

    @Test
    void testGetNom() {
        assertEquals("Alice", colon.getNom(), "Le nom du colon est incorrect.");
    }

    @Test
    void testAjoutPreference() {
        colon.ajoutpreference(bois);
        colon.ajoutpreference(pierre);
        List<Ressource> preferences = colon.getlistepreferences();

        assertEquals(2, preferences.size(), "Le nombre de préférences est incorrect.");
        assertTrue(preferences.contains(bois), "La ressource Bois devrait être dans les préférences.");
        assertTrue(preferences.contains(pierre), "La ressource Pierre devrait être dans les préférences.");
    }

    @Test
    void testAjoutEnnemi() {
        Colon ennemi = new Colon("Bob");
        try {
            colon.ajoutennemi(ennemi);
        }catch(Exception e) {
            System.out.println(e);
        }

        List<Colon> ennemis = colon.getEnnemis();
        assertEquals(1, ennemis.size(), "Le nombre d'ennemis est incorrect.");
        assertTrue(ennemis.contains(ennemi), "L'ennemi Bob devrait être dans la liste des ennemis.");
    }

    @Test
    void testSetRessourceAttribuee() {
        colon.setRessourceAttribuee(bois);
        assertEquals(bois, colon.getRessourceAttribuee(), "La ressource attribuée est incorrecte.");
    }

    @Test
    void testPrefereObjet() {
        colon.ajoutpreference(bois);
        colon.ajoutpreference(pierre);
        colon.setRessourceAttribuee(pierre);

        assertTrue(colon.prefereObjet(bois), "Alice devrait préférer Bois à Pierre.");
        assertFalse(colon.prefereObjet(pierre), "Alice ne devrait pas préférer Pierre à Pierre.");
    }

    @Test
    void testPrefereObjetAvecRessourceNonAttribuee() {
        colon.ajoutpreference(bois);
        colon.ajoutpreference(pierre);

        assertFalse(colon.prefereObjet(bois), "Sans ressource attribuée, Alice ne peut préférer Bois.");
    }

    @Test
    void testSetJaloux() {
        colon.setJaloux();
        assertTrue(colon.jaloux, "Le statut jaloux du colon devrait être vrai après l'appel de setJaloux.");
    }

    @Test
    void testAfficherListePref() {
        colon.ajoutpreference(bois);
        colon.ajoutpreference(pierre);
        colon.AfficherListePref();

        assertEquals(List.of(bois, pierre), colon.getlistepreferences(), "La liste des préférences affichée est incorrecte.");
    }

    @Test
    void testToString() {
        assertEquals("Alice", colon.toString(), "La méthode toString devrait retourner le nom du colon.");
    }
}
