package handlers;

import board.Board;
import components.Square;

/**
 * Validates that there are no pieces between two squares
 */
public class NoPiecesBetweenHandler extends BaseHandler {
    private static final int NO_CHANGE = 0;
    private static final int FORWARD_STEP = 1;
    private static final int BACKWARD_STEP = -1;

    /**
     * Checks whether the path between two squares is clear
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if there are no pieces between the squares
     */
    @Override
    protected boolean isValid(final Board board,
                              final Square from,
                              final Square to)
    {
        int currentRow = from.getRow() + getStep(from.getRow(), to.getRow());
        int currentColumn = from.getColumn() + getStep(from.getColumn(), to.getColumn());

        while (currentRow != to.getRow() || currentColumn != to.getColumn())
        {
            final Square currentSquare = new Square(currentRow, currentColumn);

            if (!board.isEmpty(currentSquare))
            {
                return false;
            }

            currentRow += getStep(from.getRow(), to.getRow());
            currentColumn += getStep(from.getColumn(), to.getColumn());
        }

        return true;
    }

    private int getStep(final int fromValue, final int toValue)
    {
        if (toValue > fromValue) {
            return FORWARD_STEP;
        }

        if (toValue < fromValue) {
            return BACKWARD_STEP;
        }

        return NO_CHANGE;
    }
}