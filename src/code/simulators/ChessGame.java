package simulators;

import board.StandardChessBoard;
import components.Color;
import components.Player;
import components.Square;
import java.util.Scanner;

/**
 * Controls the main chess game flow
 */
public class ChessGame {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final StandardChessBoard board;
    private final InputParser inputParser;
    private final InputValidator inputValidator;
    private final MoveExecutor moveExecutor;
    private Player currentPlayer;

    /**
     * Creates a chess game with default players and a standard board
     */
    public ChessGame() {
        whitePlayer = new Player("White Player", Color.WHITE);
        blackPlayer = new Player("Black Player", Color.BLACK);
        board = new StandardChessBoard();
        inputParser = new InputParser();
        inputValidator = new InputValidator();
        moveExecutor = new MoveExecutor();
        currentPlayer = whitePlayer;

        board.initializeBoardWithPieces(whitePlayer, blackPlayer);
    }

    /**
     * Starts a simple console loop for playing moves
     */
    public void start() {
        final Scanner scanner = new Scanner(System.in);

        printBoard();

        while (true) {
            printMovePrompt();
            final String moveText = scanner.nextLine();

            if (moveText.equalsIgnoreCase("quit")) {
                System.out.println("Game ended");
                break;
            }

            try {
                final boolean movePlayed = playMove(moveText);

                if (!movePlayed) {
                    System.out.println("Invalid move");
                } else {
                    printBoard();
                }
            } catch (final IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    /**
     * Prints the current game board
     */
    public void printBoard() {
        board.printBoard();
    }

    /**
     * Tries to play one move written in chess square format
     *
     * @param moveText the move text written by the user
     * @return true if the move was played
     */
    public boolean playMove(final String moveText) {
        final Square[] squares = inputParser.parseMove(moveText);
        final Square from = squares[0];
        final Square to = squares[1];

        if (!inputValidator.isValidMove(board, currentPlayer, from, to)) {
            return false;
        }

        moveExecutor.executeMove(board, from, to);
        switchTurn();

        return true;
    }

    /**
     * Switches the active player turn
     */
    private void switchTurn() {
        if (currentPlayer.equals(whitePlayer)) {
            currentPlayer = blackPlayer;
        } else {
            currentPlayer = whitePlayer;
        }
    }

    /**
     * Prints the current player's move prompt
     */
    private void printMovePrompt() {
        System.out.print(currentPlayer.getName() + " move: ");
    }
}