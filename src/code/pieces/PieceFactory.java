package pieces;

import components.PieceType;
import components.Player;
import components.Square;

/**
 * Creates chess pieces based on their types
 */
public final class PieceFactory {
    private PieceFactory() {
    }

    /**
     * Creates a chess piece with the given type, owner, and location
     *
     * @param pieceType the type of piece to create
     * @param owner the player who owns the piece
     * @param location the starting location of the piece
     * @return the created chess piece
     */
    public static Piece createPiece(final PieceType pieceType,
                                    final Player owner,
                                    final Square location) {
        if (pieceType == null) {
            throw new IllegalArgumentException("Piece type cannot be null");
        }

        return switch (pieceType) {
            case KING -> new King(owner, location);
            case QUEEN -> new Queen(owner, location);
            case ROOK -> new Rook(owner, location);
            case BISHOP -> new Bishop(owner, location);
            case KNIGHT -> new Knight(owner, location);
            case PAWN -> new Pawn(owner, location);
        };
    }
}