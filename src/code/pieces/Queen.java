package pieces;

import components.Player;
import components.Square;
import movements.DiagonalMoveStrategy;
import movements.HorizontalMoveStrategy;
import movements.VerticalMoveStrategy;

/**
 * Represents a queen chess piece
 */
public class Queen extends Piece {
    private static final String NAME = "Queen";
    private static final String SYMBOL = "Q";

    /**
     * Creates a queen piece with an owner and a starting location
     *
     * @param owner the player who owns this piece
     * @param location the starting location of this piece
     */
    public Queen(final Player owner,
                 final Square location) {
        super(owner, location);
        addMoveStrategy(new HorizontalMoveStrategy());
        addMoveStrategy(new VerticalMoveStrategy());
        addMoveStrategy(new DiagonalMoveStrategy());
    }

    /**
     * Returns the queen piece name
     *
     * @return the piece name
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Returns the queen piece symbol
     *
     * @return the piece symbol
     */
    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}