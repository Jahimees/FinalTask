package by.epam.ft.exception;

/**
 * This exception occurs when HR enters text
 * information in fields that are intended for numeric information only
 */
public class InvalidEnterInformationException extends NumberFormatException {
    public InvalidEnterInformationException(String message){
        super(message);
    }
}
