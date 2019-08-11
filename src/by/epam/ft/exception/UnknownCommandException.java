package by.epam.ft.exception;

/**
 * This exception occurs when an incorrect command is received in the request
 */
public class UnknownCommandException extends IllegalArgumentException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
