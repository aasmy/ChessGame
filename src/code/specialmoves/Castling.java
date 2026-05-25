package specialmoves;

import board.Board;
import components.Square;
import pieces.Piece;

/**
 * Executes the rook movement part of castling
 */
public class Castling implements SpecialMove {
    private static final int KING_SIDE_ROOK_COLUMN = 7;
    private static final int QUEEN_SIDE_ROOK_COLUMN = 0;
    private static final int KING_SIDE_ROOK_TARGET_COLUMN = 5;
    private static final int QUEEN_SIDE_ROOK_TARGET_COLUMN = 3;

    /**
     * Moves the rook to its castling square
     *
     * @param board the chess board
     * @param from the king starting square
     * @param to the king destination square
     */
    @Override
    public void execute(final Board board,
                        final Square from,
                        final Square to) {
        validateInputs(board, from, to);

        final Square rookFrom = getRookStartSquare(from, to);
        final Square rookTo = getRookTargetSquare(from, to);
        final Piece rook = board.getPieceAt(rookFrom);

        board.movePiece(rookFrom, rookTo);
        rook.markAsMoved();
    }

    /**
     * Returns the rook starting square for the castling side
     *
     * @param kingFrom the king starting square
     * @param kingTo the king destination square
     * @return the rook starting square
     */
    private Square getRookStartSquare(final Square kingFrom,
                                      final Square kingTo) {
        if (kingTo.getColumn() > kingFrom.getColumn()) {
            return new Square(kingFrom.getRow(), KING_SIDE_ROOK_COLUMN);
        }

        return new Square(kingFrom.getRow(), QUEEN_SIDE_ROOK_COLUMN);
    }

    /**
     * Returns the rook target square for the castling side
     *
     * @param kingFrom the king starting square
     * @param kingTo the king destination square
     * @return the rook target square
     */
    private Square getRookTargetSquare(final Square kingFrom,
                                       final Square kingTo) {
        if (kingTo.getColumn() > kingFrom.getColumn()) {
            return new Square(kingFrom.getRow(), KING_SIDE_ROOK_TARGET_COLUMN);
        }

        return new Square(kingFrom.getRow(), QUEEN_SIDE_ROOK_TARGET_COLUMN);
    }

    /**
     * Checks whether the castling execution inputs are safe to use
     *
     * @param board the chess board
     * @param from the king starting square
     * @param to the king destination square
     */
    private void validateInputs(final Board board,
                                final Square from,
                                final Square to) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }

        if (from == null) {
            throw new IllegalArgumentException("Starting square cannot be null");
        }

        if (to == null) {
            throw new IllegalArgumentException("Destination square cannot be null");
        }
    }
}