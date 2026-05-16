package components;

import java.util.Objects;

/**
 * Represents one square location on the chess board.
 */
public class Square {
    private final int row;
    private final int column;

    /**
     * Creates a square using row and column indexes.
     *
     * @param row the row index
     * @param column the column index
     */
    public Square(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the row index.
     *
     * @return the row index
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column index.
     *
     * @return the column index
     */
    public int getColumn() {
        return column;
    }

    /**
     * Compares this square with another object.
     *
     * @param object the object to compare with
     * @return true if both squares have the same row and column
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Square)) {
            return false;
        }

        final Square square;
        square = (Square) object;

        return row == square.row && column == square.column;
    }

    /**
     * Returns the hash code for this square.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    /**
     * Returns a readable text representation of this square.
     *
     * @return the square as text
     */
    @Override
    public String toString() {
        return "Square{"
                + "row=" + row
                + ", column=" + column
                + '}';
    }
}