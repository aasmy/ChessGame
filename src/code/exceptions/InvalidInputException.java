package exceptions;

/**
 * Represents an invalid user input error
 */
public class InvalidInputException extends RuntimeException {
    /**
     * Creates an invalid input exception with a message
     *
     * @param message the exception message
     */
    public InvalidInputException(final String message) {
        super(message);
    }
}