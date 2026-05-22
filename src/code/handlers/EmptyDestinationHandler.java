package handlers;

import board.Board;
import components.Square;

/**
 * Validates that the destination square is empty
 */
public class EmptyDestinationHandler extends BaseHandler {
    /**
     * Checks whether the destination square has no piece
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the destination square is empty
     */
    @Override
    protected boolean isValid(final Board board,
                              final Square from,
                              final Square to)
    {
        return board.isEmpty(to);
    }
}