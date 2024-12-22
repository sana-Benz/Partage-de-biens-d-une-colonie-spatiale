/**
 * Exception levée lorsque des préférences invalides sont fournies.
 */
package ExceptionColonie;

public class PreferencesInvalidException  extends Exception{
    /**
     * Constructeur de l'exception avec un message d'erreur.
     *
     * @param message le message d'erreur décrivant la cause de l'exception
     */
    public PreferencesInvalidException(String message){
        super(message);
    }
    
}
