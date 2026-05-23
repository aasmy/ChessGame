package handlers;

import board.Board;
import components.Square;

/**
 * Validates that a move is diagonal
 */
public class DiagonalMoveHandler extends BaseHandler {
    /**
     * Checks whether row and column changes are equal in size
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the move is diagonal
     */
    @Override
    protected boolean isValid(final Board board, final Square from, final Square to) {
        final int rowDifference = Math.abs(to.getRow() - from.getRow());
        final int columnDifference = Math.abs(to.getColumn() - from.getColumn());

        return rowDifference == columnDifference;
    }
}