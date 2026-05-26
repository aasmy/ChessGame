import board.StandardChessBoard;
import components.Color;
import components.Player;
import components.Square;
import simulators.GameStateChecker;
import simulators.MoveExecutor;

/**
 * Runs simple manual checks for game state detection
 */
public class GameStateCheckerManualTest {
    /**
     * Runs the manual game state checks
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        testCheckDetection();
        testCheckmateDetection();
    }

    /**
     * Checks that a simple queen attack is detected as check
     */
    private static void testCheckDetection() {
        final Player whitePlayer = new Player("White Player", Color.WHITE);
        final Player blackPlayer = new Player("Black Player", Color.BLACK);
        final StandardChessBoard board = new StandardChessBoard();
        final MoveExecutor executor = new MoveExecutor();

        board.initializeBoardWithPieces(whitePlayer, blackPlayer);

        executor.executeMove(board, new Square(6, 4), new Square(4, 4));
        executor.executeMove(board, new Square(1, 5), new Square(2, 5));
        executor.executeMove(board, new Square(7, 3), new Square(3, 7));

        System.out.println("Check detection test: "
                + GameStateChecker.isCheck(board, Color.BLACK));
    }

    /**
     * Checks that Fool's Mate is detected as checkmate
     */
    private static void testCheckmateDetection() {
        final Player whitePlayer = new Player("White Player", Color.WHITE);
        final Player blackPlayer = new Player("Black Player", Color.BLACK);
        final StandardChessBoard board = new StandardChessBoard();
        final MoveExecutor executor = new MoveExecutor();

        board.initializeBoardWithPieces(whitePlayer, blackPlayer);

        executor.executeMove(board, new Square(6, 5), new Square(5, 5));
        executor.executeMove(board, new Square(1, 4), new Square(3, 4));
        executor.executeMove(board, new Square(6, 6), new Square(4, 6));
        executor.executeMove(board, new Square(0, 3), new Square(4, 7));

        System.out.println("Checkmate detection test: "
                + GameStateChecker.isCheckmate(board, Color.WHITE));
    }
}