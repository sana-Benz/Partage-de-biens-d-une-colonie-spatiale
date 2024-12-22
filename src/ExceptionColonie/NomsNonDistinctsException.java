/**
 * Exception levée lorsque des noms non distincts sont détectés dans une liste ou une collection.
 */
package ExceptionColonie;

public class NomsNonDistinctsException extends Exception {
    /**
     * Constructeur de l'exception avec un message d'erreur.
     *
     * @param message le message d'erreur décrivant la cause de l'exception
     */
    public NomsNonDistinctsException(String message) {
        super(message);
    }
}
