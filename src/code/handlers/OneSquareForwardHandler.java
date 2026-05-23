package handlers;

import board.Board;
import components.Color;
import components.Square;
import pieces.Piece;

/**
 * Validates that a pawn moves one square forward
 */
public class OneSquareForwardHandler extends BaseHandler
{
    private static final int WHITE_FORWARD_STEP = -1;
    private static final int BLACK_FORWARD_STEP = 1;
    private static final int NO_COLUMN_CHANGE = 0;

    /**
     * Checks whether the move goes one square forward
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     *
     * @return true if the move goes one square forward
     */
    @Override
    protected boolean isValid(final Board board,
                              final Square from,
                              final Square to)
    {
        final Piece piece = board.getPieceAt(from);
        final int rowDifference = to.getRow() - from.getRow();
        final int columnDifference = to.getColumn() - from.getColumn();

        return columnDifference == NO_COLUMN_CHANGE
                && rowDifference == getForwardStep(piece);
    }

    private int getForwardStep(final Piece piece) {
        if (piece.getOwner().getColor() == Color.WHITE) {
            return WHITE_FORWARD_STEP;
        }

        return BLACK_FORWARD_STEP;
    }
}