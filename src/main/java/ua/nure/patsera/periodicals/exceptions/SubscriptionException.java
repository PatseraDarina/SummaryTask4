package ua.nure.patsera.periodicals.exceptions;

/**
 * Created by Дарина on 22.09.2017.
 */
public class SubscriptionException extends Exception {
    public SubscriptionException(String message) {
        super(message);
    }

    public SubscriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
