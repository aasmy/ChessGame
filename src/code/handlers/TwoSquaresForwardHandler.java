package handlers;

import board.Board;
import components.Color;
import components.Square;
import pieces.Piece;

/**
 * Validates that a pawn moves two squares forward at the start
 */
public class TwoSquaresForwardHandler extends BaseHandler {
    private static final int WHITE_FORWARD_DISTANCE = -2;
    private static final int BLACK_FORWARD_DISTANCE = 2;
    private static final int NO_COLUMN_CHANGE = 0;

    /**
     * Checks whether the pawn moves two squares forward before moving
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the pawn can move two squares forward
     */
    @Override
    protected boolean isValid(final Board board,
                              final Square from,
                              final Square to)
    {
        final Piece piece = board.getPieceAt(from);
        final int rowDifference = to.getRow() - from.getRow();
        final int columnDifference = to.getColumn() - from.getColumn();

        return !piece.hasMoved() &&
                columnDifference == NO_COLUMN_CHANGE &&
                rowDifference == getForwardDistance(piece);
    }

    private int getForwardDistance(final Piece piece)
    {
        if (piece.getOwner().getColor() == Color.WHITE)
        {
            return WHITE_FORWARD_DISTANCE;
        }

        return BLACK_FORWARD_DISTANCE;
    }
}