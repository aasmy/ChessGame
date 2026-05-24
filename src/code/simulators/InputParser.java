package simulators;

import components.Square;

/**
 * Converts user text input into board squares
 */
public class InputParser {
    private static final int EXPECTED_MOVE_PARTS = 2;
    private static final int SQUARE_TEXT_LENGTH = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int FIRST_FILE = 'a';
    private static final int LAST_FILE = 'h';
    private static final int FIRST_RANK = '1';
    private static final int LAST_RANK = '8';
    private static final int BOARD_SIZE = 8;

    /**
     * Parses a move text into starting and destination squares
     *
     * @param moveText the move text written by the user
     * @return an array containing the starting square and destination square
     */
    public Square[] parseMove(final String moveText) {
        validateMoveText(moveText);

        final String[] parts = moveText.trim().toLowerCase().split("\\s+");

        if (parts.length != EXPECTED_MOVE_PARTS) {
            throw new IllegalArgumentException("Move must contain two squares");
        }

        return new Square[] {
                parseSquare(parts[0]),
                parseSquare(parts[1])
        };
    }

    /**
     * Parses a square text into a board square
     *
     * @param squareText the square text written by the user
     * @return the parsed square
     */
    public Square parseSquare(final String squareText) {
        validateSquareText(squareText);

        final char file = squareText.charAt(FILE_INDEX);
        final char rank = squareText.charAt(RANK_INDEX);

        final int column = file - FIRST_FILE;
        final int row = BOARD_SIZE - Character.getNumericValue(rank);

        return new Square(row, column);
    }

    /**
     * Validates the full move text before parsing it
     *
     * @param moveText the move text written by the user
     */
    private void validateMoveText(final String moveText) {
        if (moveText == null || moveText.trim().isEmpty()) {
            throw new IllegalArgumentException("Move text cannot be empty");
        }
    }

    /**
     * Validates one square text before parsing it
     *
     * @param squareText the square text written by the user
     */
    private void validateSquareText(final String squareText) {
        if (squareText == null || squareText.length() != SQUARE_TEXT_LENGTH) {
            throw new IllegalArgumentException("Square must have file and rank");
        }

        final char file = squareText.charAt(FILE_INDEX);
        final char rank = squareText.charAt(RANK_INDEX);

        if (file < FIRST_FILE || file > LAST_FILE) {
            throw new IllegalArgumentException("Square file must be between a and h");
        }

        if (rank < FIRST_RANK || rank > LAST_RANK) {
            throw new IllegalArgumentException("Square rank must be between 1 and 8");
        }
    }
}