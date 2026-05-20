package board;

import components.Color;
import components.Player;
import components.Square;
import pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a general chess board
 */
public abstract class Board {
    private static final int MINIMUM_ROW_INDEX = 0;
    private static final int MINIMUM_COLUMN_INDEX = 0;
    private static final int MINIMUM_ROW_COUNT = 1;
    private static final int MINIMUM_COLUMN_COUNT = 1;

    private final int rows;
    private final int columns;
    private final Piece[][] pieces;

    /**
     * Creates a board with the given dimensions
     *
     * @param rows the number of board rows
     * @param columns the number of board columns
     */
    public Board(final int rows, final int columns) {
        validateDimensions(rows, columns);

        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    /**
     * Returns the number of board rows
     *
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of board columns
     *
     * @return the number of columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Checks whether a square is inside the board
     *
     * @param square the square to check
     * @return true if the square is inside the board
     */
    public boolean isInsideBoard(final Square square) {
        if (square == null) {
            return false;
        }

        return square.getRow() >= MINIMUM_ROW_INDEX
                && square.getRow() < rows
                && square.getColumn() >= MINIMUM_COLUMN_INDEX
                && square.getColumn() < columns;
    }

    /**
     * Returns the piece at the given square
     *
     * @param square the square to check
     * @return the piece at the given square
     */
    public Piece getPieceAt(final Square square) {
        validateSquare(square);

        return pieces[square.getRow()][square.getColumn()];
    }

    /**
     * Places a piece at the given square
     *
     * @param piece the piece to place
     * @param square the square where the piece will be placed
     */
    public void setPieceAt(final Piece piece, final Square square) {
        validateSquare(square);

        pieces[square.getRow()][square.getColumn()] = piece;

        if (piece != null) {
            piece.setLocation(square);
        }
    }

    /**
     * Removes a piece from the given square
     *
     * @param square the square to clear
     */
    public void removePieceAt(final Square square) {
        validateSquare(square);

        pieces[square.getRow()][square.getColumn()] = null;
    }

    /**
     * Moves a piece from one square to another
     *
     * @param from the starting square
     * @param to the destination square
     */
    public void movePiece(final Square from, final Square to) {
        validateSquare(from);
        validateSquare(to);

        final Piece piece = getPieceAt(from);

        if (piece == null) {
            throw new IllegalArgumentException("There is no piece at the starting square");
        }

        setPieceAt(piece, to);
        removePieceAt(from);
        piece.markAsMoved();
    }

    /**
     * Checks whether a square is empty
     *
     * @param square the square to check
     * @return true if the square has no piece
     */
    public boolean isEmpty(final Square square) {
        return getPieceAt(square) == null;
    }

    /**
     * Returns all pieces owned by a given color
     *
     * @param color the color to search for
     * @return the pieces owned by the given color
     */
    public List<Piece> getPiecesByColor(final Color color) {
        validateColor(color);

        final List<Piece> matchingPieces = new ArrayList<>();

        for (int row = MINIMUM_ROW_INDEX; row < rows; row++) {
            collectPiecesInRow(color, row, matchingPieces);
        }

        return matchingPieces;
    }

    /**
     * Returns the king owned by the given color
     *
     * @param color the color of the king owner
     * @return the king piece
     */
    public Piece getKing(final Color color) {
        validateColor(color);

        for (final Piece piece : getPiecesByColor(color)) {
            if ("King".equals(piece.getName())) {
                return piece;
            }
        }

        throw new IllegalStateException("King was not found");
    }

    /**
     * Initializes the board with the starting pieces
     *
     * @param whitePlayer the white player
     * @param blackPlayer the black player
     */
    public abstract void initializeBoardWithPieces(Player whitePlayer, Player blackPlayer);

    private void collectPiecesInRow(final Color color,
                                    final int row,
                                    final List<Piece> matchingPieces) {
        for (int column = MINIMUM_COLUMN_INDEX; column < columns; column++) {
            final Piece piece = pieces[row][column];

            if (piece != null && piece.getOwner().getColor() == color) {
                matchingPieces.add(piece);
            }
        }
    }

    private void validateDimensions(final int rows, final int columns) {
        if (rows < MINIMUM_ROW_COUNT || columns < MINIMUM_COLUMN_COUNT) {
            throw new IllegalArgumentException("Board dimensions must be positive");
        }
    }

    private void validateSquare(final Square square) {
        if (!isInsideBoard(square)) {
            throw new IllegalArgumentException("Square is outside the board");
        }
    }

    private void validateColor(final Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }
    }
}