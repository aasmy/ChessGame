package handlers;

import board.Board;
import components.Color;
import components.Square;
import pieces.Piece;

/**
 * Validates that a pawn moves one square forward diagonally
 */
public class PawnDiagonalMoveHandler extends BaseHandler {
    private static final int WHITE_FORWARD_STEP = -1;
    private static final int BLACK_FORWARD_STEP = 1;
    private static final int DIAGONAL_COLUMN_STEP = 1;

    /**
     * Checks whether the pawn moves one square forward diagonally
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the pawn moves diagonally forward
     */
    @Override
    protected boolean isValid(final Board board,
                              final Square from,
                              final Square to)
    {
        final Piece piece = board.getPieceAt(from);
        final int rowDifference = to.getRow() - from.getRow();
        final int columnDifference = Math.abs(to.getColumn() - from.getColumn());

        return rowDifference == getForwardStep(piece) &&
                columnDifference == DIAGONAL_COLUMN_STEP;
    }

    private int getForwardStep(final Piece piece) {
        if (piece.getOwner().getColor() == Color.WHITE) {
            return WHITE_FORWARD_STEP;
        }

        return BLACK_FORWARD_STEP;
    }
}