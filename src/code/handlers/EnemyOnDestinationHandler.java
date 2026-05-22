package handlers;

import board.Board;
import components.Square;
import pieces.Piece;

/**
 * Validates that the destination square has an enemy piece
 */
public class EnemyOnDestinationHandler extends BaseHandler {
    /**
     * Checks whether the destination square contains an enemy piece
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the destination square contains an enemy piece
     */
    @Override
    protected boolean isValid(final Board board,
                              final Square from,
                              final Square to)
    {
        final Piece currentPiece = board.getPieceAt(from);
        final Piece destinationPiece = board.getPieceAt(to);

        return destinationPiece != null
                && destinationPiece.getOwner().getColor() != currentPiece.getOwner().getColor();
    }
}