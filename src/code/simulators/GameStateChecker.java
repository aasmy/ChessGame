package simulators;

import board.Board;
import components.Color;
import components.Square;
import pieces.Piece;

import java.util.List;

/**
 * Checks simple chess game states
 */
public final class GameStateChecker {
    private GameStateChecker() {
    }

    /**
     * Checks whether the king with the given color is currently in check
     *
     * @param board the chess board
     * @param kingColor the king color to check
     * @return true if the king is in check
     */
    public static boolean isCheck(final Board board, final Color kingColor) {
        validateInputs(board, kingColor);

        final Piece king = board.getKing(kingColor);
        final Square kingLocation = king.getLocation();
        final Color opponentColor = kingColor.getOppositeColor();
        final List<Piece> opponentPieces = board.getPiecesByColor(opponentColor);

        for (final Piece opponentPiece : opponentPieces) {
            if (opponentPiece.canMove(board, kingLocation)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Validates the checker input values
     *
     * @param board the chess board
     * @param kingColor the king color
     */
    private static void validateInputs(final Board board, final Color kingColor) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }

        if (kingColor == null) {
            throw new IllegalArgumentException("King color cannot be null");
        }
    }
}