import board.StandardChessBoard;
import components.Color;
import components.Player;
import components.Square;
import pieces.Piece;
import specialmoves.Castling;

/**
 * Runs simple manual checks for castling execution
 */
public class CastlingManualTest {
    /**
     * Runs the manual castling checks
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        final Player whitePlayer = new Player("White Player", Color.WHITE);
        final Player blackPlayer = new Player("Black Player", Color.BLACK);
        final StandardChessBoard board = new StandardChessBoard();
        final Castling castling = new Castling();

        board.initializeBoardWithPieces(whitePlayer, blackPlayer);

        board.removePieceAt(new Square(7, 5));
        board.removePieceAt(new Square(7, 6));

        castling.execute(board, new Square(7, 4), new Square(7, 6));

        final Piece rook = board.getPieceAt(new Square(7, 5));

        System.out.println("Castling rook square: " + rook.getName());
        System.out.println("Castling rook moved: " + rook.hasMoved());
    }
}