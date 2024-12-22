package DataAccess;

import static org.junit.jupiter.api.Assertions.*;
import DataAccess.FichierColonie;
import Colonie.Colonie;
import ExceptionColonie.ExceptionColon;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

class LectureTest {
    private final Path testDirectory = Path.of("./test/DataAccess/fichiersTest/");

    // Helper method to get test file path
    private Path getTestFilePath(String fileName) {
        return testDirectory.resolve(fileName);
    }

    @Test
    public void testColonMissingPeriod() {
        Path testFile = testDirectory.resolve("testColonMissingPeriod.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
        }, "Le programme doit détecter les lignes sans point final.");
    }


    @Test
    public void testInvalidOrder() {
        Path testFile = testDirectory.resolve("testInvalidOrder.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
        }, "Le programme doit détecter un ordre invalide des sections.");
    }

    @Test
    public void testUnequalColonAndResources() {
        Path testFile =testDirectory.resolve("testUnequalColonAndResources.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 2);
        }, "Le programme doit détecter un déséquilibre entre colons et ressources.");
    }

    @Test
    public void testInvalidDetesteFormat() {
        Path testFile = testDirectory.resolve("testInvalidDetesteFormat.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
        }, "Le programme doit détecter un format invalide pour deteste.");
    }

    @Test
    public void testPreferencesIncomplete() {
        Path testFile = testDirectory.resolve("testPreferencesIncomplete.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
        }, "Le programme doit détecter des préférences incomplètes.");
    }

    @Test
    public void testDetesteReferencesUnknownColon() {
        Path testFile = testDirectory.resolve("testDetesteReferencesUnknownColon.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
        }, "Le programme doit détecter un colon inconnu référencé dans deteste.");
    }

    @Test
    public void testPreferencesReferenceUnknownResource() {
        Path testFile = testDirectory.resolve("testPreferencesReferenceUnknownResource.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
        }, "Le programme doit détecter une ressource inconnue référencée dans preferences.");
    }

    @Test
    public void testValidFile() {
        Path testFile = testDirectory.resolve("testValidFile.txt");
        assertDoesNotThrow(() -> {
            Colonie colonie = FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
            assertNotNull(colonie, "La colonie doit être créée correctement à partir d'un fichier valide.");
        });
    }
    @Test
    public void testEmptyFile() {
        Path testFile = testDirectory.resolve("testEmptyFile.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
        }, "Le programme doit détecter un fichier vide.");
    }

    @Test
    public void testDuplicateColon() {
        Path testFile = testDirectory.resolve("testDuplicateColon.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
        }, "Le programme doit détecter les colons déclarés plusieurs fois.");
    }

    @Test
    public void testDuplicateResource() {
        Path testFile = testDirectory.resolve("testDuplicateResource.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
        }, "Le programme doit détecter les ressources déclarées plusieurs fois.");
    }

    @Test
    public void testDuplicateDeteste() {
        Path testFile = testDirectory.resolve("testDuplicateDeteste.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
        }, "Le programme doit détecter les relations 'deteste' déclarées plusieurs fois.");
    }

    @Test
    public void testDuplicatePreferences() {
        Path testFile = testDirectory.resolve("testDuplicatePreferences.txt");
        assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 1);
        }, "Le programme doit détecter les préférences déclarées plusieurs fois pour un colon.");
    }

    @Test
    public void testColonSansPreferences() {
        Path testFile = testDirectory.resolve("testColonSansPreferences.txt");
        try {
            Files.writeString(testFile,
                    "colon(A).\n" +
                            "colon(B).\n" +
                            "ressource(R1).\n" +
                            "ressource(R2).\n" +
                            "preferences(A,R1,R2)."); // Pas de préférences pour le colon B
        } catch (IOException e) {
            fail("Erreur lors de l'écriture du fichier de test.");
        }

        ExceptionColon exception = assertThrows(ExceptionColon.class, () -> {
            FichierColonie.chargerDepuisFichier(testFile.toString(), 2);
        }, "Le programme doit détecter lorsqu'un colon n'a pas de liste de préférences définie.");

        assertTrue(exception.getMessage().contains("Le colon 'B' n'a pas de liste de préférences définie."),
                "L'exception doit indiquer quel colon manque de préférences.");
    }



}
