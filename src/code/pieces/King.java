package pieces;

import components.Player;
import components.Square;
import movements.KingMoveStrategy;
import movements.CastlingMoveStrategy;

/**
 * Represents a king chess piece
 */
public class King extends Piece {
    private static final String NAME = "King";
    private static final String SYMBOL = "K";

    /**
     * Creates a king piece with an owner and a starting location
     *
     * @param owner the player who owns this piece
     * @param location the starting location of this piece
     */
    public King(final Player owner,
                final Square location) {
        super(owner, location);
        addMoveStrategy(new KingMoveStrategy());
        addMoveStrategy(new CastlingMoveStrategy());
    }

    /**
     * Returns the king piece name
     *
     * @return the piece name
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Returns the king piece symbol
     *
     * @return the piece symbol
     */
    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}