package handlers;

import board.Board;
import components.Square;

/**
 * Represents a validation step in a move validation chain
 */
public interface Handler {
    /**
     * Sets the next handler in the chain
     *
     * @param nextHandler the next handler to execute
     * @return the next handler
     */
    Handler setNext(Handler nextHandler);

    /**
     * Checks whether the current validation chain accepts the move
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the validation chain accepts the move
     */
    boolean canHandle(Board board, Square from, Square to);
}