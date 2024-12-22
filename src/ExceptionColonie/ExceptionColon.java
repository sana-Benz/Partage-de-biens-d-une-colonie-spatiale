/**
 * Exception levée lorsqu'une erreur liée à un colon se produit.
 */
package ExceptionColonie;

public class ExceptionColon extends Exception {

    /**
     * Constructeur de l'exception avec un message d'erreur.
     *
     * @param message le message d'erreur décrivant la cause de l'exception
     */
    public ExceptionColon(String message) {
        super(message);
    }
}
