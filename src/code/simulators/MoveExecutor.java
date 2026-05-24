package simulators;

import board.Board;
import components.Square;
import pieces.Piece;

/**
 * Executes valid chess moves on the board
 */
public class MoveExecutor {
    /**
     * Moves a piece from one square to another
     *
     * @param board the board where the move is executed
     * @param from the starting square
     * @param to the destination square
     */
    public void executeMove(final Board board,
                            final Square from,
                            final Square to) {
        validateExecutionRequest(board, from, to);

        final Piece piece = board.getPieceAt(from);

        board.movePiece(from, to);
        piece.markAsMoved();
    }

    /**
     * Checks basic execution requirements before changing the board state
     *
     * These checks protect the executor from null values, invalid squares,
     * and empty starting squares. They do not replace movement validation
     *
     * @param board the board where the move is executed
     * @param from the starting square
     * @param to the destination square
     */
    private void validateExecutionRequest(final Board board,
                                          final Square from,
                                          final Square to)
    {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }

        if (from == null) {
            throw new IllegalArgumentException("Starting square cannot be null");
        }

        if (to == null) {
            throw new IllegalArgumentException("Destination square cannot be null");
        }

        if (!board.isInsideBoard(from)) {
            throw new IllegalArgumentException("Starting square is outside the board");
        }

        if (!board.isInsideBoard(to)) {
            throw new IllegalArgumentException("Destination square is outside the board");
        }

        if (board.isEmpty(from)) {
            throw new IllegalArgumentException("Starting square does not contain a piece");
        }
    }
}