package app;

import simulators.ChessGame;

/**
 * Starts the chess game application
 */
public class Main {
    /**
     * Runs the application
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        final ChessGame chessGame = new ChessGame();

        chessGame.start();
    }
}