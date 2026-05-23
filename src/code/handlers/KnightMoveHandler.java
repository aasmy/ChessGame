package handlers;

import board.Board;
import components.Square;

/**
 * Validates that a move follows the knight shape
 */
public class KnightMoveHandler extends BaseHandler {
    private static final int LONG_MOVE_DISTANCE = 2;
    private static final int SHORT_MOVE_DISTANCE = 1;

    /**
     * Checks whether the move follows an L shape
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the move follows the knight shape
     */
    @Override
    protected boolean isValid(final Board board, final Square from, final Square to) {
        final int rowDifference = Math.abs(to.getRow() - from.getRow());
        final int columnDifference = Math.abs(to.getColumn() - from.getColumn());

        return
                (rowDifference == LONG_MOVE_DISTANCE &&
                columnDifference == SHORT_MOVE_DISTANCE) ||

                (rowDifference == SHORT_MOVE_DISTANCE &&
                columnDifference == LONG_MOVE_DISTANCE);
    }
}