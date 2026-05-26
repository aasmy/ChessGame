import board.StandardChessBoard;
import components.Color;
import components.PieceType;
import components.Player;
import components.Square;
import pieces.Piece;
import pieces.PieceFactory;
import specialmoves.Promotion;

/**
 * Runs simple manual checks for pawn promotion
 */
public class PromotionManualTest {
    /**
     * Runs the manual promotion checks
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        final Player whitePlayer = new Player("White Player", Color.WHITE);
        final Player blackPlayer = new Player("Black Player", Color.BLACK);
        final StandardChessBoard board = new StandardChessBoard();
        final Square promotionSquare = new Square(0, 0);
        final Promotion promotion = new Promotion();

        board.initializeBoardWithPieces(whitePlayer, blackPlayer);
        board.setPieceAt(
                PieceFactory.createPiece(PieceType.PAWN, whitePlayer, promotionSquare),
                promotionSquare
        );

        promotion.promote(board, promotionSquare, PieceType.QUEEN);

        final Piece promotedPiece = board.getPieceAt(promotionSquare);

        System.out.println("Promotion piece name: " + promotedPiece.getName());
        System.out.println("Promotion owner color: " + promotedPiece.getOwner().getColor());
    }
}