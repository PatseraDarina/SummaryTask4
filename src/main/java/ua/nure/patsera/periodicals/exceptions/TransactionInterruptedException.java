package ua.nure.patsera.periodicals.exceptions;

public class TransactionInterruptedException extends Exception {
    public TransactionInterruptedException(String message) {
        super(message);
    }

    public TransactionInterruptedException(String message, Exception cause) {
        super(message, cause);
    }
}
