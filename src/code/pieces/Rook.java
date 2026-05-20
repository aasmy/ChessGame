package pieces;

import components.Player;
import components.Square;

/**
 * Represents a rook chess piece
 */
public class Rook extends Piece {
    private static final String NAME = "Rook";
    private static final String SYMBOL = "R";

    /**
     * Creates a rook piece with an owner and a starting location
     *
     * @param owner the player who owns this piece
     * @param location the starting location of this piece
     */
    public Rook(final Player owner,
                final Square location) {
        super(owner, location);
    }

    /**
     * Returns the rook piece name
     *
     * @return the piece name
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Returns the rook piece symbol
     *
     * @return the piece symbol
     */
    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}