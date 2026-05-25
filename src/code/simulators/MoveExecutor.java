package simulators;

import board.Board;
import components.Square;
import pieces.King;
import pieces.Piece;

/**
 * Executes valid chess moves on the board
 */
public class MoveExecutor {
    private static final int CASTLING_DISTANCE = 2;
    private static final int KING_SIDE_ROOK_COLUMN = 7;
    private static final int QUEEN_SIDE_ROOK_COLUMN = 0;
    private static final int KING_SIDE_ROOK_TARGET_COLUMN = 5;
    private static final int QUEEN_SIDE_ROOK_TARGET_COLUMN = 3;

    /**
     * Moves a piece from one square to another
     *
     * @param board the board where the move is executed
     * @param from the starting square
     * @param to the destination square
     */
    public void executeMove(final Board board, final Square from, final Square to) {
        validateExecutionRequest(board, from, to);

        final Piece piece = board.getPieceAt(from);
        final boolean isCastling = isCastlingMove(piece, from, to);

        board.movePiece(from, to);
        piece.markAsMoved();

        if (isCastling) {
            moveCastlingRook(board, from, to);
        }
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

    /**
     * Checks whether the move is a castling move
     *
     * @param piece the moving piece
     * @param from the starting square
     * @param to the destination square
     * @return true if the move is castling
     */
    private boolean isCastlingMove(final Piece piece,
                                   final Square from,
                                   final Square to) {
        final int columnDistance = Math.abs(from.getColumn() - to.getColumn());

        return piece instanceof King && columnDistance == CASTLING_DISTANCE;
    }

    /**
     * Moves the rook during castling
     *
     * @param board the chess board
     * @param kingFrom the king starting square
     * @param kingTo the king destination square
     */
    private void moveCastlingRook(final Board board,
                                  final Square kingFrom,
                                  final Square kingTo) {
        final int row = kingFrom.getRow();
        final int rookFromColumn;
        final int rookToColumn;

        if (kingTo.getColumn() > kingFrom.getColumn()) {
            rookFromColumn = KING_SIDE_ROOK_COLUMN;
            rookToColumn = KING_SIDE_ROOK_TARGET_COLUMN;
        } else {
            rookFromColumn = QUEEN_SIDE_ROOK_COLUMN;
            rookToColumn = QUEEN_SIDE_ROOK_TARGET_COLUMN;
        }

        final Square rookFrom = new Square(row, rookFromColumn);
        final Square rookTo = new Square(row, rookToColumn);
        final Piece rook = board.getPieceAt(rookFrom);

        board.movePiece(rookFrom, rookTo);
        rook.markAsMoved();
    }
}