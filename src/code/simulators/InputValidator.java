package simulators;

import board.Board;
import components.Player;
import components.Square;
import pieces.Piece;

/**
 * Validates player move choices before execution
 */
public class InputValidator {
    /**
     * Checks whether the current player can make the requested move
     *
     * @param board the board where the move is checked
     * @param currentPlayer the player trying to move
     * @param from the selected piece square
     * @param to the destination square
     * @return true if the selected move is valid
     */
    public boolean isValidMove(final Board board,
                               final Player currentPlayer,
                               final Square from,
                               final Square to) {
        validateInputs(board, currentPlayer, from, to);

        return !from.equals(to)
                && hasCurrentPlayerPiece(board, currentPlayer, from)
                && canSelectedPieceMove(board, from, to)
                && !leavesCurrentPlayerInCheck(board, currentPlayer, from, to);
    }


    /**
     * Checks whether the move would leave the current player king in check
     *
     * @param board the chess board
     * @param currentPlayer the player making the move
     * @param from the starting square
     * @param to the destination square
     * @return true if the move leaves the current player in check
     */
    private boolean leavesCurrentPlayerInCheck(final Board board,
                                               final Player currentPlayer,
                                               final Square from,
                                               final Square to) {
        final Piece movingPiece = board.getPieceAt(from);
        final Piece capturedPiece = board.getPieceAt(to);

        board.movePiece(from, to);

        final boolean kingInCheck = GameStateChecker.isCheck(
                board,
                currentPlayer.getColor()
        );

        board.setPieceAt(movingPiece, from);

        if (capturedPiece == null) {
            board.removePieceAt(to);
        } else {
            board.setPieceAt(capturedPiece, to);
        }

        return kingInCheck;
    }

    /**
     * Checks whether the selected square contains a piece owned by the current player
     *
     * @param board the board where the piece is checked
     * @param currentPlayer the player trying to move
     * @param from the selected piece square
     * @return true if the player owns the selected piece
     */
    private boolean hasCurrentPlayerPiece(final Board board,
                                          final Player currentPlayer,
                                          final Square from) {
        if (board.isEmpty(from)) {
            return false;
        }

        final Piece selectedPiece = board.getPieceAt(from);

        return selectedPiece.getOwner().equals(currentPlayer);
    }

    /**
     * Checks whether the selected piece accepts the requested destination
     *
     * @param board the board where the move is checked
     * @param from the selected piece square
     * @param to the destination square
     * @return true if the selected piece can move to the destination
     */
    private boolean canSelectedPieceMove(final Board board,
                                         final Square from,
                                         final Square to) {
        final Piece selectedPiece = board.getPieceAt(from);

        return selectedPiece.canMove(board, to);
    }

    /**
     * Checks that the move validation request has the required objects
     *
     * @param board the board where the move is checked
     * @param currentPlayer the player trying to move
     * @param from the selected piece square
     * @param to the destination square
     */
    private void validateInputs(final Board board,
                                final Player currentPlayer,
                                final Square from,
                                final Square to) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }

        if (currentPlayer == null) {
            throw new IllegalArgumentException("Current player cannot be null");
        }

        if (from == null) {
            throw new IllegalArgumentException("Starting square cannot be null");
        }

        if (to == null) {
            throw new IllegalArgumentException("Destination square cannot be null");
        }
    }
}