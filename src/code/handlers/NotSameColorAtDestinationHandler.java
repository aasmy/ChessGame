package handlers;

import board.Board;
import components.Square;
import pieces.Piece;

/**
 * Validates that the destination does not contain a friendly piece
 */
public class NotSameColorAtDestinationHandler extends BaseHandler {
    /**
     * Checks whether the destination is empty or has an enemy piece
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the destination does not contain a friendly piece
     */
    @Override
    protected boolean isValid(final Board board,
                              final Square from,
                              final Square to)
    {
        final Piece currentPiece = board.getPieceAt(from);
        final Piece destinationPiece = board.getPieceAt(to);

        return destinationPiece == null ||
               destinationPiece.getOwner().getColor() !=
                       currentPiece.getOwner().getColor();
    }
}