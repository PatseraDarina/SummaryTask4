package ua.nure.patsera.periodicals.exceptions;

/**
 * Created by Дарина on 11.09.2017.
 */
public class RegistrationException extends Exception {
    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
