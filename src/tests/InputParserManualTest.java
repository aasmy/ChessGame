import components.Square;
import exceptions.InvalidInputException;
import simulators.InputParser;

/**
 * Runs simple manual checks for input parsing
 */
public class InputParserManualTest {
    /**
     * Runs the manual parser checks
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        final InputParser parser = new InputParser();

        testValidMove(parser);
        testInvalidMoveText(parser);
        testInvalidSquareText(parser);
    }

    /**
     * Checks that a normal move input is parsed correctly
     *
     * @param parser the input parser to test
     */
    private static void testValidMove(final InputParser parser) {
        final Square[] squares = parser.parseMove("e2 e4");

        System.out.println("Valid move test:");
        System.out.println("From row: " + squares[0].getRow());
        System.out.println("From column: " + squares[0].getColumn());
        System.out.println("To row: " + squares[1].getRow());
        System.out.println("To column: " + squares[1].getColumn());
    }

    /**
     * Checks that incomplete move text is rejected
     *
     * @param parser the input parser to test
     */
    private static void testInvalidMoveText(final InputParser parser) {
        try {
            parser.parseMove("e2");
        } catch (final InvalidInputException exception) {
            System.out.println("Invalid move text test: " + exception.getMessage());
        }
    }

    /**
     * Checks that invalid square text is rejected
     *
     * @param parser the input parser to test
     */
    private static void testInvalidSquareText(final InputParser parser) {
        try {
            parser.parseMove("e9 e4");
        } catch (final InvalidInputException exception) {
            System.out.println("Invalid square text test: " + exception.getMessage());
        }
    }
}