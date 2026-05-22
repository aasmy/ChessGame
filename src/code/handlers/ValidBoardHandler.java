package handlers;

import board.Board;
import components.Square;

/**
 * Validates that the board exists
 */
public class ValidBoardHandler extends BaseHandler {
    /**
     * Checks whether the board is valid
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the board exists
     */
    @Override
    protected boolean isValid(final Board board,
                              final Square from,
                              final Square to)
    {
        return board != null;
    }
}