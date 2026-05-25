package movements;

import board.Board;
import components.Square;
import pieces.King;
import pieces.Piece;
import pieces.Rook;

/**
 * Validates a king castling move
 */
public class CastlingMoveStrategy implements MoveStrategy {
    private static final int CASTLING_COLUMN_DISTANCE = 2;
    private static final int QUEEN_SIDE_ROOK_COLUMN = 0;
    private static final int KING_SIDE_ROOK_COLUMN = 7;
    private static final int LEFT_STEP = -1;
    private static final int RIGHT_STEP = 1;

    /**
     * Checks whether the castling move is valid
     *
     * @param board the chess board
     * @param from the starting square
     * @param to the destination square
     * @return true if castling is valid
     */
    @Override
    public boolean validateMove(final Board board,
                                final Square from,
                                final Square to) {
        if (!hasValidInputs(board, from, to)) {
            return false;
        }

        final Piece king = board.getPieceAt(from);

        if (!(king instanceof King) || king.hasMoved()) {
            return false;
        }

        if (!isCastlingShape(from, to)) {
            return false;
        }

        final Square rookSquare = getRookSquare(board, from, to);
        final Piece rook = board.getPieceAt(rookSquare);

        return rook instanceof Rook
                && !rook.hasMoved()
                && isPathClear(board, from, rookSquare);
    }

    /**
     * Checks whether the input values are valid enough for castling validation
     *
     * @param board the chess board
     * @param from the starting square
     * @param to the destination square
     * @return true if inputs can be checked
     */
    private boolean hasValidInputs(final Board board,
                                   final Square from,
                                   final Square to) {
        return board != null
                && from != null
                && to != null
                && board.isInsideBoard(from)
                && board.isInsideBoard(to)
                && board.getPieceAt(from) != null
                && board.getPieceAt(to) == null;
    }

    /**
     * Checks whether the king moves two columns on the same row
     *
     * @param from the starting square
     * @param to the destination square
     * @return true if the move has a castling shape
     */
    private boolean isCastlingShape(final Square from, final Square to) {
        final int rowDifference = Math.abs(from.getRow() - to.getRow());
        final int columnDifference = Math.abs(from.getColumn() - to.getColumn());

        return rowDifference == 0 && columnDifference == CASTLING_COLUMN_DISTANCE;
    }

    /**
     * Returns the rook square used for the castling side
     *
     * @param board the chess board
     * @param from the king starting square
     * @param to the king destination square
     * @return the rook square
     */
    private Square getRookSquare(final Board board,
                                 final Square from,
                                 final Square to) {
        final int rookColumn;

        if (to.getColumn() > from.getColumn()) {
            rookColumn = KING_SIDE_ROOK_COLUMN;
        } else {
            rookColumn = QUEEN_SIDE_ROOK_COLUMN;
        }

        return new Square(from.getRow(), rookColumn);
    }

    /**
     * Checks whether there are no pieces between the king and rook
     *
     * @param board the chess board
     * @param kingSquare the king square
     * @param rookSquare the rook square
     * @return true if the path is clear
     */
    private boolean isPathClear(final Board board,
                                final Square kingSquare,
                                final Square rookSquare) {
        final int step = getColumnStep(kingSquare, rookSquare);
        int column = kingSquare.getColumn() + step;

        while (column != rookSquare.getColumn()) {
            final Square square = new Square(kingSquare.getRow(), column);

            if (!board.isEmpty(square)) {
                return false;
            }

            column += step;
        }

        return true;
    }

    /**
     * Returns the column step from the king toward the rook
     *
     * @param kingSquare the king square
     * @param rookSquare the rook square
     * @return the column step
     */
    private int getColumnStep(final Square kingSquare, final Square rookSquare) {
        if (rookSquare.getColumn() > kingSquare.getColumn()) {
            return RIGHT_STEP;
        }

        return LEFT_STEP;
    }
}