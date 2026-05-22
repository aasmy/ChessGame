package handlers;

import board.Board;
import components.Square;

/**
 * Provides common chain behavior for move validation handlers
 */
public abstract class BaseHandler implements Handler {
    private Handler nextHandler;

    /**
     * Sets the next handler in the chain
     *
     * @param nextHandler the next handler to execute
     * @return the next handler
     */
    @Override
    public Handler setNext(final Handler nextHandler) {
        this.nextHandler = nextHandler;

        return nextHandler;
    }

    /**
     * Checks whether the current validation chain accepts the move
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the validation chain accepts the move
     */
    @Override
    public boolean canHandle(final Board board, final Square from, final Square to) {
        if (!isValid(board, from, to)) {
            return false;
        }

        if (nextHandler == null) {
            return true;
        }

        return nextHandler.canHandle(board, from, to);
    }

    /**
     * Checks whether this handler accepts the move
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if this handler accepts the move
     */
    protected abstract boolean isValid(Board board, Square from, Square to);
}