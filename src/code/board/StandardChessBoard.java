package board;

import components.PieceType;
import components.Player;
import components.Square;
import pieces.PieceFactory;

/**
 * Represents a standard eight by eight chess board
 */
public class StandardChessBoard extends Board {
    private static final int BOARD_SIZE = 8;

    private static final int BLACK_MAJOR_PIECES_ROW = 0;
    private static final int BLACK_PAWNS_ROW = 1;
    private static final int WHITE_PAWNS_ROW = BOARD_SIZE - 2;
    private static final int WHITE_MAJOR_PIECES_ROW = BOARD_SIZE - 1;

    private static final int FIRST_ROW = 0;
    private static final int FIRST_COLUMN = 0;
    private static final PieceType[] MAJOR_PIECES_ORDER = {
            PieceType.ROOK,
            PieceType.KNIGHT,
            PieceType.BISHOP,
            PieceType.QUEEN,
            PieceType.KING,
            PieceType.BISHOP,
            PieceType.KNIGHT,
            PieceType.ROOK
    };

    /**
     * Creates a standard chess board
     */
    public StandardChessBoard() {
        super(BOARD_SIZE, BOARD_SIZE);
    }

    /**
     * Initializes the board with standard chess starting pieces
     *
     * @param whitePlayer the white player
     * @param blackPlayer the black player
     */
    @Override
    public void initializeBoardWithPieces(final Player whitePlayer,
                                          final Player blackPlayer)
    {
        initializeMajorPieces(blackPlayer, BLACK_MAJOR_PIECES_ROW);
        initializePawns(blackPlayer, BLACK_PAWNS_ROW);

        initializePawns(whitePlayer, WHITE_PAWNS_ROW);
        initializeMajorPieces(whitePlayer, WHITE_MAJOR_PIECES_ROW);
    }

    /**
     * Prints the current board state
     */
    public void printBoard() {
        for (int row = FIRST_ROW; row < getRows(); row++) {
            printRow(row);
        }
    }

    private void printRow(final int row) {
        for (int column = FIRST_COLUMN; column < getColumns(); column++) {
            final Square location = new Square(row, column);

            if (isEmpty(location)) {
                System.out.print("- ");
            } else {
                System.out.print(getPieceAt(location).getSymbol() + " ");
            }
        }

        System.out.println();
    }

    private void initializeMajorPieces(final Player player, final int row)
    {
        for (int column = FIRST_COLUMN; column < MAJOR_PIECES_ORDER.length; column++)
        {
            final Square location = new Square(row, column);
            final PieceType pieceType = MAJOR_PIECES_ORDER[column];

            setPieceAt(PieceFactory.createPiece(pieceType, player, location), location);
        }
    }

    private void initializePawns(final Player player, final int row)
    {
        for (int column = FIRST_COLUMN; column < getColumns(); column++)
        {
            final Square location = new Square(row, column);
            setPieceAt(PieceFactory.createPiece(PieceType.PAWN, player, location), location);
        }
    }
}