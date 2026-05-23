package handlers;

import board.Board;
import components.Square;

/**
 * Validates that a move is vertical
 */
public class VerticalMoveHandler extends BaseHandler {
    private static final int NO_ROW_CHANGE = 0;
    private static final int NO_COLUMN_CHANGE = 0;

    /**
     * Checks whether the move stays on the same column and changes row
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the move is vertical
     */
    @Override
    protected boolean isValid(final Board board, final Square from, final Square to) {
        final int rowDifference = to.getRow() - from.getRow();
        final int columnDifference = to.getColumn() - from.getColumn();

        return columnDifference == NO_COLUMN_CHANGE &&
               rowDifference != NO_ROW_CHANGE;
    }
}