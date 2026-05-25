package specialmoves;

import board.Board;
import components.PieceType;
import components.Square;
import pieces.Piece;
import pieces.PieceFactory;

/**
 * Executes pawn promotion by replacing the pawn with a selected piece
 */
public class Promotion {
    /**
     * Promotes a pawn into the selected piece type
     *
     * @param board the chess board
     * @param pawnSquare the square that contains the pawn
     * @param promotionType the selected promotion piece type
     */
    public void promote(final Board board,
                        final Square pawnSquare,
                        final PieceType promotionType) {
        validateInputs(board, pawnSquare, promotionType);

        final Piece pawn = board.getPieceAt(pawnSquare);
        final Piece promotedPiece = PieceFactory.createPiece(
                promotionType,
                pawn.getOwner(),
                pawnSquare
        );

        board.setPieceAt(promotedPiece, pawnSquare);
    }

    /**
     * Checks whether promotion inputs are safe to use
     *
     * @param board the chess board
     * @param pawnSquare the square that contains the pawn
     * @param promotionType the selected promotion piece type
     */
    private void validateInputs(final Board board,
                                final Square pawnSquare,
                                final PieceType promotionType) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }

        if (pawnSquare == null) {
            throw new IllegalArgumentException("Pawn square cannot be null");
        }

        if (promotionType == null) {
            throw new IllegalArgumentException("Promotion type cannot be null");
        }

        if (!board.isInsideBoard(pawnSquare)) {
            throw new IllegalArgumentException("Pawn square is outside the board");
        }

        if (board.isEmpty(pawnSquare)) {
            throw new IllegalArgumentException("Pawn square does not contain a piece");
        }
    }
}