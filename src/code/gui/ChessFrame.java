package gui;

import javax.swing.JFrame;

/**
 * Represents the main GUI window for the chess game
 */
public class ChessFrame extends JFrame {
    private static final String WINDOW_TITLE = "Chess Game";
    private static final int WINDOW_WIDTH = 720;
    private static final int WINDOW_HEIGHT = 760;

    /**
     * Creates the main chess window
     */
    public ChessFrame() {
        setTitle(WINDOW_TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}