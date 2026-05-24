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
    private boolean isRunning;

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
        isRunning = true;

        board.initializeBoardWithPieces(whitePlayer, blackPlayer);
    }

    /**
     * Starts a simple console loop for playing moves
     */
    public void start() {
        final Scanner scanner = new Scanner(System.in);

        printBoard();
        System.out.println("Enter moves like e2 e4, or type quit to exit");

        while (isRunning) {
            printMovePrompt();
            final String moveText = scanner.nextLine();

            if (isQuitCommand(moveText)) {
                isRunning = false;
            } else {
                handleMoveInput(moveText);
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
        printCheckMessageIfNeeded();
        switchTurn();

        return true;
    }

    /**
     * Prints a check or checkmate message if the opponent king is under attack
     */
    private void printCheckMessageIfNeeded() {
        final Color opponentColor = currentPlayer.getColor().getOppositeColor();

        if (GameStateChecker.isCheckmate(board, opponentColor)) {
            System.out.println("Checkmate");
            isRunning = false;
        } else if (GameStateChecker.isCheck(board, opponentColor)) {
            System.out.println("Check");
        }
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

    /**
     * Prints the result of a move attempt
     *
     * @param movePlayed true if the move was played successfully
     */
    private void printMoveResult(final boolean movePlayed) {
        if (movePlayed) {
            System.out.println("Move played");
        } else {
            System.out.println("Invalid move");
        }
    }

    /**
     * Handles one move entered from the console
     *
     * @param moveText the move text entered by the user
     */
    private void handleMoveInput(final String moveText) {
        try {
            final boolean movePlayed = playMove(moveText);

            printMoveResult(movePlayed);

            if (movePlayed) {
                printBoard();
            }
        } catch (final IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Checks whether the user wants to quit the game
     *
     * @param moveText the move text entered by the user
     * @return true if the user wants to quit
     */
    private boolean isQuitCommand(final String moveText) {
        return moveText.equalsIgnoreCase("quit");
    }
}