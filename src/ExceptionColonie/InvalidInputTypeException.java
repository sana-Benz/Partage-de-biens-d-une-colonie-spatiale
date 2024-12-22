/**
 * Exception levée lorsque le type d'entrée fourni est invalide.
 */
package ExceptionColonie;


public class InvalidInputTypeException extends Exception {
    /**
     * Constructeur de l'exception avec un message d'erreur.
     *
     * @param message le message d'erreur décrivant la cause de l'exception
     */
    public InvalidInputTypeException(String message){
        super(message);
    }
    
}
