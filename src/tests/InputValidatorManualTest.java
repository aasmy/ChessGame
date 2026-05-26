import board.StandardChessBoard;
import components.Color;
import components.Player;
import components.Square;
import simulators.InputValidator;

/**
 * Runs simple manual checks for move input validation
 */
public class InputValidatorManualTest {
    /**
     * Runs the manual validator checks
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        final Player whitePlayer = new Player("White Player", Color.WHITE);
        final Player blackPlayer = new Player("Black Player", Color.BLACK);
        final StandardChessBoard board = new StandardChessBoard();
        final InputValidator validator = new InputValidator();

        board.initializeBoardWithPieces(whitePlayer, blackPlayer);

        testCurrentPlayerMove(board, validator, whitePlayer);
        testOpponentPieceMove(board, validator, whitePlayer);
        testSameSquareMove(board, validator, whitePlayer);
    }

    /**
     * Checks that the current player can move one of their own pieces
     *
     * @param board the chess board
     * @param validator the input validator
     * @param whitePlayer the white player
     */
    private static void testCurrentPlayerMove(final StandardChessBoard board,
                                              final InputValidator validator,
                                              final Player whitePlayer) {
        final Square from = new Square(6, 4);
        final Square to = new Square(4, 4);

        System.out.println("Current player move test: "
                + validator.isValidMove(board, whitePlayer, from, to));
    }

    /**
     * Checks that a player cannot move an opponent piece
     *
     * @param board the chess board
     * @param validator the input validator
     * @param whitePlayer the white player
     */
    private static void testOpponentPieceMove(final StandardChessBoard board,
                                              final InputValidator validator,
                                              final Player whitePlayer) {
        final Square from = new Square(1, 4);
        final Square to = new Square(3, 4);

        System.out.println("Opponent piece move test: "
                + validator.isValidMove(board, whitePlayer, from, to));
    }

    /**
     * Checks that moving to the same square is rejected
     *
     * @param board the chess board
     * @param validator the input validator
     * @param whitePlayer the white player
     */
    private static void testSameSquareMove(final StandardChessBoard board,
                                           final InputValidator validator,
                                           final Player whitePlayer) {
        final Square from = new Square(6, 4);
        final Square to = new Square(6, 4);

        System.out.println("Same square move test: "
                + validator.isValidMove(board, whitePlayer, from, to));
    }
}