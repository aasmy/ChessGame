package pieces;

import components.Player;
import components.Square;

/**
 * Represents a knight chess piece
 */
public class Knight extends Piece {
    private static final String NAME = "Knight";
    private static final String SYMBOL = "N";

    /**
     * Creates a knight piece with an owner and a starting location
     *
     * @param owner the player who owns this piece
     * @param location the starting location of this piece
     */
    public Knight(final Player owner,
                  final Square location) {
        super(owner, location);
    }

    /**
     * Returns the knight piece name
     *
     * @return the piece name
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Returns the knight piece symbol
     *
     * @return the piece symbol
     */
    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}