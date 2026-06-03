package gui;

import board.Board;
import board.StandardChessBoard;
import components.Color;
import components.Player;

import javax.swing.JFrame;

/**
 * Represents the main GUI window for the chess game
 */
public class ChessFrame extends JFrame {
    private static final String WINDOW_TITLE = "Norse Chess";
    private static final int WINDOW_WIDTH = 720;
    private static final int WINDOW_HEIGHT = 760;

    /**
     * Creates the main chess window
     */
    public ChessFrame() {
        final Player whitePlayer = new Player("White", Color.WHITE);
        final Player blackPlayer = new Player("Black", Color.BLACK);
        final Board board = new StandardChessBoard();

        board.initializeBoardWithPieces(whitePlayer, blackPlayer);

        setTitle(WINDOW_TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        add(new BoardPanel(board));
    }
}