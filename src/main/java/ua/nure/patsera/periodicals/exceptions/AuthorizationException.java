package ua.nure.patsera.periodicals.exceptions;

/**
 * Created by Дарина on 11.09.2017.
 */
public class AuthorizationException extends Exception {
    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Exception cause) {
        super(message, cause);
    }

}
