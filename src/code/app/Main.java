package app;

import board.StandardChessBoard;
import components.Color;
import components.Player;

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
        final Player whitePlayer = new Player("White Player", Color.WHITE);
        final Player blackPlayer = new Player("Black Player", Color.BLACK);
        final StandardChessBoard board = new StandardChessBoard();

        board.initializeBoardWithPieces(whitePlayer, blackPlayer);
        board.printBoard();

        System.out.println("ChessGame project started");
    }
}