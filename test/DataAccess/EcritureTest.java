package DataAccess;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import ExceptionColonie.ExceptionColon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import Colonie.Colon;
import Colonie.Colonie;
import Colonie.Ressource;

public class EcritureTest {
    private Colonie colonie;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        try{
            colonie = new Colonie(3);
        } catch (ExceptionColon e) {
            throw new RuntimeException(e);
        }

        scanner = new Scanner(System.in);
    }

    @Test
    public void testSaveAttributionSuccess() throws IOException {
        Colon colon1 = new Colon("Colon1");
        Colon colon2 = new Colon("Colon2");
        Colon colon3 = new Colon("Colon3");
        Ressource ressource1 = new Ressource("Ressource1");
        Ressource ressource2 = new Ressource("Ressource2");
        Ressource ressource3 = new Ressource("Ressource3");

        colon1.setRessourceAttribuee(ressource1);
        colon2.setRessourceAttribuee(ressource2);
        colon3.setRessourceAttribuee(ressource3);

        try {
            colonie.ajoutColon(colon1);
            colonie.ajoutColon(colon2);
            colonie.ajoutColon(colon3);
        } catch (ExceptionColon e) {
            throw new RuntimeException(e);
        }

        // Simulez l'entrée utilisateur avec une réponse "oui"
        Scanner mockScanner = new Scanner("oui\n");
        String nomFichier = "testSaveAttributionSuccess.txt";

        // Appel de la méthode avec le scanner simulé
        FichierColonie.saveAttribution(nomFichier, colonie, mockScanner);

        // Vérifiez si le fichier a été créé
        File fichier = new File(nomFichier);
        assertTrue(fichier.exists(), "Le fichier doit exister après la sauvegarde.");

        // Vérifiez le contenu du fichier
        String content = new String(Files.readAllBytes(Paths.get(nomFichier)));
        System.out.println("Contenu généré :");
        System.out.println(content);

        // Normalisez le contenu pour gérer les différences de sauts de ligne
        String normalizedContent = content.replaceAll("\r\n", "\n").trim();
        String expectedContent = "cout = 0\nColon1:Ressource1\nColon2:Ressource2\nColon3:Ressource3";

        assertEquals(expectedContent, normalizedContent, "Le contenu du fichier doit correspondre au contenu attendu.");

        // Nettoyez après le test
        fichier.delete();
    }


    @Test
    public void testSaveAttributionException() {
        Colon colon1 = new Colon("Colon1");
        Colon colon2 = new Colon("Colon2");
        Colon colon3 = new Colon("Colon3");

        try {
            colonie.ajoutColon(colon1);
            colonie.ajoutColon(colon2);
            colonie.ajoutColon(colon3);
        } catch (ExceptionColon e) {
            throw new RuntimeException(e);
        }

        String nomFichier = "testSaveAttributionException.txt";
        assertThrows(IOException.class, () -> FichierColonie.saveAttribution(nomFichier, colonie, scanner));
    }


}
