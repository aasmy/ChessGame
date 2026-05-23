package handlers;

import board.Board;
import components.Square;

/**
 * Validates that a move is horizontal
 */
public class HorizontalMoveHandler extends BaseHandler {
    private static final int NO_ROW_CHANGE = 0;
    private static final int NO_COLUMN_CHANGE = 0;

    /**
     * Checks whether the move stays on the same row and changes column
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the move is horizontal
     */
    @Override
    protected boolean isValid(final Board board, final Square from, final Square to) {
        final int rowDifference = to.getRow() - from.getRow();
        final int columnDifference = to.getColumn() - from.getColumn();

        return rowDifference == NO_ROW_CHANGE &&
               columnDifference != NO_COLUMN_CHANGE;
    }
}