package app;

import gui.ChessFrame;

import javax.swing.SwingUtilities;

/**
 * Starts the chess game application
 */
public final class Main {
    private Main() {
    }

    /**
     * Runs the application
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final ChessFrame chessFrame = new ChessFrame();
            chessFrame.setVisible(true);
        });
    }
}