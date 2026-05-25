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
     * Checks whether the king with the given color is in checkmate
     *
     * @param board the chess board
     * @param kingColor the king color to check
     * @return true if the king is in checkmate
     */
    public static boolean isCheckmate(final Board board, final Color kingColor) {
        validateInputs(board, kingColor);

        if (!isCheck(board, kingColor)) {
            return false;
        }

        final List<Piece> playerPieces = board.getPiecesByColor(kingColor);

        for (final Piece piece : playerPieces) {
            if (hasAnyLegalMove(board, piece)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether the piece has at least one legal move
     *
     * @param board the chess board
     * @param piece the piece to test
     * @return true if the piece has a legal move
     */
    private static boolean hasAnyLegalMove(final Board board, final Piece piece) {
        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                final Square target = new Square(row, column);

                if (piece.canMove(board, target)
                        && !leavesKingInCheck(board, piece, target)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks whether moving the piece would leave its king in check
     *
     * @param board the chess board
     * @param piece the piece to move
     * @param target the target square
     * @return true if the move leaves the king in check
     */
    private static boolean leavesKingInCheck(final Board board,
                                             final Piece piece,
                                             final Square target) {
        final Square originalLocation = piece.getLocation();
        final Piece capturedPiece = board.getPieceAt(target);

        board.movePiece(originalLocation, target);

        final boolean kingInCheck = isCheck(board, piece.getOwner().getColor());

        board.setPieceAt(piece, originalLocation);

        if (capturedPiece == null) {
            board.removePieceAt(target);
        } else {
            board.setPieceAt(capturedPiece, target);
        }

        return kingInCheck;
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

    /**
     * Checks whether the player with the given color is in stalemate
     *
     * @param board the chess board
     * @param playerColor the player color to check
     * @return true if the player is in stalemate
     */
    public static boolean isStalemate(final Board board, final Color playerColor) {
        validateInputs(board, playerColor);

        if (isCheck(board, playerColor)) {
            return false;
        }

        final List<Piece> playerPieces = board.getPiecesByColor(playerColor);

        for (final Piece piece : playerPieces) {
            if (hasAnyLegalMove(board, piece)) {
                return false;
            }
        }

        return true;
    }
}