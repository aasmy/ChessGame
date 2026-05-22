package handlers;

import board.Board;
import components.Square;

/**
 * Validates that the starting square is inside the board
 */
public class ValidStartLocationHandler extends BaseHandler {
    /**
     * Checks whether the starting square is inside the board
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the starting square is inside the board
     */
    @Override
    protected boolean isValid(final Board board,
                              final Square from,
                              final Square to)
    {
        return board.isInsideBoard(from);
    }
}