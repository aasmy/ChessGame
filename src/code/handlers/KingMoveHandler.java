package handlers;

import board.Board;
import components.Square;

/**
 * Validates that a move follows the king shape
 */
public class KingMoveHandler extends BaseHandler {
    private static final int MAXIMUM_MOVE_DISTANCE = 1;
    private static final int NO_POSITION_CHANGE = 0;

    /**
     * Checks whether the move is one square in any direction
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the move follows the king shape
     */
    @Override
    protected boolean isValid(final Board board, final Square from, final Square to) {
        final int rowDifference = Math.abs(to.getRow() - from.getRow());
        final int columnDifference = Math.abs(to.getColumn() - from.getColumn());

        return (rowDifference != NO_POSITION_CHANGE ||
                columnDifference != NO_POSITION_CHANGE) &&
                rowDifference <= MAXIMUM_MOVE_DISTANCE &&
                columnDifference <= MAXIMUM_MOVE_DISTANCE;
    }
}