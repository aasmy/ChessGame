package pieces;

import components.Player;
import components.Square;

/**
 * Represents a pawn chess piece
 */
public class Pawn extends Piece {
    private static final String NAME = "Pawn";
    private static final String SYMBOL = "P";

    /**
     * Creates a pawn piece with an owner and a starting location
     *
     * @param owner the player who owns this piece
     * @param location the starting location of this piece
     */
    public Pawn(final Player owner,
                final Square location) {
        super(owner, location);
    }

    /**
     * Returns the pawn piece name
     *
     * @return the piece name
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Returns the pawn piece symbol
     *
     * @return the piece symbol
     */
    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}