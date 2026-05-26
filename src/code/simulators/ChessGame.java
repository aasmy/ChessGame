package simulators;

import board.StandardChessBoard;
import components.Color;
import components.PieceType;
import components.Player;
import components.Square;
import exceptions.InvalidInputException;
import exceptions.InvalidMoveException;
import pieces.Pawn;
import pieces.Piece;
import specialmoves.Promotion;

import java.util.Scanner;

/**
 * Controls the main chess game flow
 */
public class ChessGame {

    private static final int WHITE_PROMOTION_ROW = 0;
    private static final int BLACK_PROMOTION_ROW = 7;

    private final Player whitePlayer;
    private final Player blackPlayer;
    private final StandardChessBoard board;
    private final InputParser inputParser;
    private final InputValidator inputValidator;
    private final MoveExecutor moveExecutor;
    private Player currentPlayer;
    private boolean isRunning;
    private final Scanner scanner;

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
        scanner = new Scanner(System.in);

        board.initializeBoardWithPieces(whitePlayer, blackPlayer);
    }

    /**
     * Starts a simple console loop for playing moves
     */
    public void start() {

        printBoard();
        System.out.println("Enter moves like e2 e4, type resign to resign, or quit to exit");
        while (isRunning) {
            printMovePrompt();
            final String moveText = scanner.nextLine();

            if (isQuitCommand(moveText)) {
                isRunning = false;
            } else if (isResignCommand(moveText)) {
                handleResign();
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
     */
    public void playMove(final String moveText) {
        final Square[] squares = inputParser.parseMove(moveText);
        final Square from = squares[0];
        final Square to = squares[1];

        if (!inputValidator.isValidMove(board, currentPlayer, from, to)) {
            throw new InvalidMoveException("Invalid move");
        }

        moveExecutor.executeMove(board, from, to);
        handlePromotionIfNeeded(to);
        switchTurn();
    }



    /**
     * Prints a check or checkmate message if the current player king is under attack
     */
    private void printCheckMessageIfNeeded() {
        final Color checkedColor = currentPlayer.getColor();

        if (GameStateChecker.isCheckmate(board, checkedColor)) {
            System.out.println("Checkmate");
            isRunning = false;
        } else if (GameStateChecker.isCheck(board, checkedColor)) {
            System.out.println("Check");
        } else if (GameStateChecker.isStalemate(board, checkedColor)) {
            System.out.println("Stalemate");
            isRunning = false;
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
     * Handles one move entered from the console
     *
     * @param moveText the move text entered by the user
     */
    private void handleMoveInput(final String moveText) {
        try {
            playMove(moveText);
            System.out.println("Move played");
            printCheckMessageIfNeeded();
            printBoard();
        } catch (final InvalidMoveException exception) {
            System.out.println(exception.getMessage());
        } catch (final InvalidInputException exception) {
            System.out.println(exception.getMessage());
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
        return "quit".equalsIgnoreCase(moveText.trim());
    }

    /**
     * Checks whether the player wants to resign
     *
     * @param moveText the entered move text
     * @return true if the input is a resign command
     */
    private boolean isResignCommand(final String moveText) {
        return "resign".equalsIgnoreCase(moveText.trim());
    }

    /**
     * Ends the game when the current player resigns
     */
    private void handleResign() {
        System.out.println(currentPlayer.getName() + " resigned");
        isRunning = false;
    }

    /**
     * Promotes a pawn if it reaches the final row
     *
     * @param location the pawn location after moving
     */
    private void handlePromotionIfNeeded(final Square location) {
        final Piece piece = board.getPieceAt(location);

        if (!(piece instanceof Pawn) || !isPromotionRow(location)) {
            return;
        }

        final PieceType promotionType = askForPromotionType();

        new Promotion().promote(board, location, promotionType);
    }

    /**
     * Checks whether a square is a promotion row
     *
     * @param location the square to check
     * @return true if the square is a promotion row
     */
    private boolean isPromotionRow(final Square location) {
        final int row = location.getRow();

        return row == WHITE_PROMOTION_ROW || row == BLACK_PROMOTION_ROW;
    }

    /**
     * Asks the current player to choose a promotion piece
     *
     * @return the selected piece type
     */
    private PieceType askForPromotionType() {
        while (true) {
            System.out.print("Promote to queen, rook, bishop, or knight: ");

            final String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "queen":
                    return PieceType.QUEEN;
                case "rook":
                    return PieceType.ROOK;
                case "bishop":
                    return PieceType.BISHOP;
                case "knight":
                    return PieceType.KNIGHT;
                default:
                    System.out.println("Invalid promotion choice");
            }
        }
    }
}