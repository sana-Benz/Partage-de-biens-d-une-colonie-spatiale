package Colonie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RessourceTest {

    private Ressource ressource;

    @BeforeEach
    void setUp() {
        ressource = new Ressource("Bois");
    }

    @Test
    void testConstructeur() {
        assertEquals("Bois", ressource.getNom(), "Le nom de la ressource est incorrect.");
        assertTrue(ressource.hashCode() != 0, "Le hashCode ne doit pas être 0 pour une ressource valide.");
    }

    @Test
    void testSetNom() {
        ressource.setNom("Pierre");
        assertEquals("Pierre", ressource.getNom(), "Le setter du nom de la ressource ne fonctionne pas correctement.");
    }

    @Test
    void testToString() {
        assertEquals("la ressource est Bois", ressource.toString(), "La méthode toString est incorrecte.");
    }

    @Test
    void testEqualsMemeObjet() {
        Ressource autreRessource = new Ressource("Bois");
        assertEquals(ressource, autreRessource, "Deux ressources avec le même nom devraient être égales.");
    }

    @Test
    void testEqualsObjetDifferent() {
        Ressource autreRessource = new Ressource("Pierre");
        assertNotEquals(ressource, autreRessource, "Deux ressources avec des noms différents ne devraient pas être égales.");
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(ressource, null, "Une ressource ne devrait jamais être égale à null.");
    }

    @Test
    void testEqualsObjetAutreClasse() {
        String autreObjet = "Bois";
        assertNotEquals(ressource, autreObjet, "Une ressource ne devrait pas être égale à un objet d'une autre classe.");
    }

    @Test
    void testHashCode() {
        Ressource autreRessource = new Ressource("Bois");
        assertEquals(ressource.hashCode(), autreRessource.hashCode(), "Le hashCode pour deux ressources avec le même nom devrait être identique.");
    }
}
