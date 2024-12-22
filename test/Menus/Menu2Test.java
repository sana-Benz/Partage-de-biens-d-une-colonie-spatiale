package Menus;

import Colonie.*;
import ExceptionColonie.ExceptionColon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Menu2Test {

    private Colonie colonie;
    private Menu2 menu2;

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

        menu2 = new Menu2(colonie);
    }

    @Test
    void testEchangerRessources() {
        // Simuler l'entrée utilisateur pour échanger les ressources
        String input = "1\nColon1 Colon2\n3\n"; // 1 pour échanger, puis les noms des colons, puis 3 pour quitter
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Appel de la méthode afficherMenu2
        assertDoesNotThrow(() -> menu2.afficherMenu2(scanner));

        // Vérifier que les ressources ont été échangées
        assertNotNull(colonie.getColon("Colon1").getRessourceAttribuee());
        assertNotNull(colonie.getColon("Colon2").getRessourceAttribuee());
    }

    @Test
    void testAfficherNombreColonsJaloux() {
        // Simuler l'entrée utilisateur pour afficher le nombre de colons jaloux
        String input = "2\n3\n"; // 2 pour afficher, 3 pour quitter
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Appel de la méthode afficherMenu2
        assertDoesNotThrow(() -> menu2.afficherMenu2(scanner));

    }

    @Test
    void testChoixInvalide() {
        // Simuler l'entrée utilisateur pour un choix invalide
        String input = "4\n3\n"; // 4 est un choix invalide
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Appel de la méthode afficherMenu2
        assertDoesNotThrow(() -> menu2.afficherMenu2(scanner));
        // Vérifier que le message d'erreur est affiché
    }
}