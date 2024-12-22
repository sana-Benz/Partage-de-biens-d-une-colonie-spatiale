package Affichages;

import Colonie.Colon;
import Colonie.Colonie;
import Colonie.Ressource;
import ExceptionColonie.ExceptionColon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class RecapColonieTest {

    private Colonie colonie;
    private RecapColonie recapColonie;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @Before
    public void setUp() throws ExceptionColon {
        // Initialiser la Colonie avec un nombre maximum de colons
        colonie = new Colonie(10); // Par exemple, une colonie pouvant contenir jusqu'à 10 colons

        // Initialiser RecapColonie avec la Colonie réelle
        recapColonie = new RecapColonie(colonie);

        // Rediriger la sortie standard pour capturer les affichages
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        // Restaurer la sortie standard originale après chaque test
        System.setOut(originalOut);
    }

    @Test
    public void testAfficherEtatColonie_AffichageCorrect() throws ExceptionColon {
        // Créer des ressources
        Ressource bois = new Ressource("Bois");
        Ressource eau = new Ressource("Eau");

        // Ajouter des ressources à la colonie
        colonie.initialiserRessources(Arrays.asList("Bois", "Eau"));

        // Créer des colons
        Colon alice = new Colon("Alice");
        Colon bob = new Colon("Bob");

        // Définir les préférences de Alice
        alice.ajoutpreference(bois); // Alice préfère le Bois

        // Définir les ennemis
        alice.ajoutennemi(bob); // Bob est l'ennemi de Alice
        bob.ajoutennemi(alice); // Alice est l'ennemi de Bob

        // Assigner des ressources aux colons
        alice.setRessourceAttribuee(bois);
        bob.setRessourceAttribuee(eau);

        // Ajouter les colons à la colonie
        colonie.ajoutColon(alice);
        colonie.ajoutColon(bob);

        // Exécuter la méthode à tester
        recapColonie.afficherEtatColonie();

        // Capturer la sortie
        String output = outputStream.toString();

        // Pour le débogage : afficher la sortie réelle
        System.out.println("Sortie capturée :\n" + output);

        // Vérifications pour Alice
        assertTrue("La sortie ne contient pas 'Alice'", output.contains("Alice"));
        assertTrue("La sortie ne contient pas les préférences de Alice correctement formatées",
                output.contains("Preferences : Bois"));
        assertTrue("La sortie ne contient pas les ennemis de Alice correctement",
                output.contains("Ennemis : Bob"));
        assertTrue("La sortie ne contient pas la ressource attribuée à Alice correctement",
                output.contains("Ressource attribuee : Bois"));

        // Vérifications pour Bob
        assertTrue("La sortie ne contient pas 'Bob'", output.contains("Bob"));
        assertTrue("La sortie ne contient pas les préférences de Bob correctement formatées",
                output.contains("Preferences : Aucune preference definie."));
        assertTrue("La sortie ne contient pas les ennemis de Bob correctement",
                output.contains("Ennemis : Alice"));
        assertTrue("La sortie ne contient pas la ressource attribuée à Bob correctement",
                output.contains("Ressource attribuee : Eau"));
    }

    @Test
    public void testAffichageAffectation_AffichageCorrect() throws ExceptionColon {
        // Créer des ressources
        Ressource bois = new Ressource("Bois");
        Ressource eau = new Ressource("Eau");

        // Ajouter des ressources à la colonie
        colonie.initialiserRessources(Arrays.asList("Bois", "Eau"));

        // Créer des colons
        Colon alice = new Colon("Alice");
        Colon bob = new Colon("Bob");

        // Assigner des ressources aux colons
        alice.setRessourceAttribuee(bois);
        bob.setRessourceAttribuee(null); // Bob n'a pas de ressource attribuée

        // Ajouter les colons à la colonie
        colonie.ajoutColon(alice);
        colonie.ajoutColon(bob);

        // Définir le nombre de colons jaloux
        // Pour ce test, nous devons appeler une méthode pour calculer les colons jaloux
        int nombreJaloux = colonie.nombreColonsJaloux();

        // Exécuter la méthode à tester
        recapColonie.affichageaffection();

        // Capturer la sortie
        String output = outputStream.toString();

        // Pour le débogage : afficher la sortie réelle
        System.out.println("Sortie capturée :\n" + output);

        // Vérifications du nombre de colons jaloux
        assertTrue("La sortie ne contient pas 'Voici le nombre de colons jaloux :'",
                output.contains("Voici le nombre de colons jaloux :"));
        assertTrue("La sortie ne contient pas le nombre correct de colons jaloux",
                output.contains(String.valueOf(nombreJaloux)));

        // Vérifications du tableau d'affectation des ressources
        assertTrue("La sortie ne contient pas le titre du tableau",
                output.contains("Colon                Ressource attribuee"));
        assertTrue("La sortie ne contient pas l'affectation de Alice correctement",
                output.contains("Alice                Bois"));
        assertTrue("La sortie ne contient pas l'affectation de Bob correctement",
                output.contains("Bob                  aucune ressource attribuée"));
    }
}
