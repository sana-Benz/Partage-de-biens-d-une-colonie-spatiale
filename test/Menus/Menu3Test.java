package Menus;

import Affichages.RecapColonie;
import Colonie.*;
import DataAccess.FichierColonie;
import ExceptionColonie.ExceptionColon;
import Service.AttributionOptimale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Menu3Test {

    private Colonie colonie;
    private Menu3 menu3;

    @BeforeEach
    void setUp() throws ExceptionColon {
        // Initialisation de la colonie avec 3 colons et 3 ressources
        colonie = new Colonie(3);
        List<String> nomsColons = List.of("Colon1", "Colon2", "Colon3");
        List<String> nomsRessources = List.of("Ressource1", "Ressource2", "Ressource3");
        colonie.initialiserColons(nomsColons);
        colonie.initialiserRessources(nomsRessources);

        // Ajout d'ennemis et de préférences
        colonie.getColon("Colon1").ajoutennemi(colonie.getColon("Colon2"));
        colonie.getColon("Colon2").ajoutennemi(colonie.getColon("Colon1"));
        colonie.getColon("Colon1").ajoutpreference(new Ressource("Ressource1"));
        colonie.getColon("Colon2").ajoutpreference(new Ressource("Ressource2"));
        colonie.getColon("Colon3").ajoutpreference(new Ressource("Ressource3"));

        menu3 = new Menu3(colonie);
    }

    @Test
    void testResolutionAutomatique() {
        // Simuler l'entrée utilisateur pour la résolution automatique
        String input = "1\n3\n"; // 1 pour la résolution automatique, 3 pour quitter
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Appel de la méthode afficherMenu3
        assertDoesNotThrow(() -> menu3.afficherMenu3(scanner));

        // Vérifier que l'attribution optimisée a été effectuée
        // (On peut vérifier l'état de la colonie après l'appel)
    }

    @Test
    void testSauvegardeSolution() {
        // Simuler l'entrée utilisateur pour sauvegarder la solution
        String input = "2\nmon_fichier.txt\n3\n"; // 2 pour sauvegarder, nom du fichier, 3 pour quitter
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Appel de la méthode afficherMenu3
        assertDoesNotThrow(() -> menu3.afficherMenu3(scanner));

        // Vérifier que le fichier a été créé et que les données sont correctes
        // (Vous pouvez vérifier le système de fichiers ou rediriger la sortie)
    }

    @Test
    void testChoixInvalide() {
        // Simuler l'entrée utilisateur pour un choix invalide
        String input = "4\n3\n"; // 4 est un choix invalide
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Appel de la méthode afficherMenu3
        assertDoesNotThrow(() -> menu3.afficherMenu3(scanner));
        // Vérifier que le message d'erreur est affiché
    }
}