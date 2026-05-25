package exceptions;

/**
 * Represents an invalid chess move error
 */
public class InvalidMoveException extends RuntimeException {
    /**
     * Creates an invalid move exception with a message
     *
     * @param message the exception message
     */
    public InvalidMoveException(final String message) {
        super(message);
    }
}