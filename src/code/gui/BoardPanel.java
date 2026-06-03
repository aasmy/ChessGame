package gui;

import board.Board;
import components.Square;
import pieces.Piece;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

/**
 * Represents the chess board area in the GUI
 */
public class BoardPanel extends JPanel {
    private static final int BOARD_SIZE = 8;
    private static final int LIGHT_SQUARE_COLOR = 0xD6D0C0;
    private static final int DARK_SQUARE_COLOR = 0x2B4654;
    private static final int GRID_GAP = 0;

    private final Board board;
    private final JButton[][] squareButtons;
    private final PieceImageLoader pieceImageLoader;

    /**
     * Creates the chess board panel
     *
     * @param board the chess board
     */
    public BoardPanel(final Board board) {
        validateBoard(board);

        this.board = board;
        this.squareButtons = new JButton[BOARD_SIZE][BOARD_SIZE];
        this.pieceImageLoader = new PieceImageLoader();

        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE, GRID_GAP, GRID_GAP));
        createBoardSquares();
        refreshBoard();
    }

    /**
     * Refreshes the board buttons using the current board state
     */
    public void refreshBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                refreshSquare(row, column);
            }
        }
    }

    /**
     * Creates all board square buttons
     */
    private void createBoardSquares() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                final JButton squareButton = createSquareButton(row, column);
                squareButtons[row][column] = squareButton;
                add(squareButton);
            }
        }
    }

    /**
     * Creates one square button for the given board position
     *
     * @param row the square row
     * @param column the square column
     * @return the created square button
     */
    private JButton createSquareButton(final int row, final int column) {
        final JButton squareButton = new JButton();

        squareButton.setOpaque(true);
        squareButton.setBorderPainted(false);
        squareButton.setFocusPainted(false);
        squareButton.setBackground(getSquareColor(row, column));

        return squareButton;
    }

    /**
     * Refreshes one square button
     *
     * @param row the square row
     * @param column the square column
     */
    private void refreshSquare(final int row, final int column) {
        final JButton squareButton = squareButtons[row][column];
        final Square square = new Square(row, column);
        final Piece piece = board.getPieceAt(square);

        squareButton.setIcon(null);

        if (piece != null) {
            squareButton.setIcon(pieceImageLoader.getPieceIcon(piece));
        }
    }

    /**
     * Gets the correct color for a board square
     *
     * @param row the square row
     * @param column the square column
     * @return the square color
     */
    private Color getSquareColor(final int row, final int column) {
        final boolean isLightSquare = (row + column) % 2 == 0;

        if (isLightSquare) {
            return new Color(LIGHT_SQUARE_COLOR);
        }

        return new Color(DARK_SQUARE_COLOR);
    }

    /**
     * Validates the board before using it
     *
     * @param board the chess board
     */
    private void validateBoard(final Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
    }
}