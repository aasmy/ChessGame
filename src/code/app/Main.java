package app;

import board.StandardChessBoard;
import components.Color;
import components.Player;
import components.Square;
import pieces.Piece;

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

        final Square from = new Square(7, 1);
        final Square to = new Square(5, 2);
        final Piece piece = board.getPieceAt(from);

        System.out.println(piece.getName() + " can move: " + piece.canMove(board, to));

        System.out.println("ChessGame project started");
    }
}