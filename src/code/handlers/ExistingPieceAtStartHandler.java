package handlers;

import board.Board;
import components.Square;

/**
 * Validates that the starting square has a piece
 */
public class ExistingPieceAtStartHandler extends BaseHandler {
    /**
     * Checks whether there is a piece at the starting square
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the starting square has a piece
     */
    @Override
    protected boolean isValid(final Board board,
                              final Square from,
                              final Square to)
    {
        return board.getPieceAt(from) != null;
    }
}