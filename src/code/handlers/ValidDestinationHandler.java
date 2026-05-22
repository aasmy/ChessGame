package handlers;

import board.Board;
import components.Square;

/**
 * Validates that the destination square is inside the board
 */
public class ValidDestinationHandler extends BaseHandler {
    /**
     * Checks whether the destination square is inside the board
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the destination square is inside the board
     */
    @Override
    protected boolean isValid(final Board board,
                              final Square from,
                              final Square to)
    {
        return board.isInsideBoard(to);
    }
}