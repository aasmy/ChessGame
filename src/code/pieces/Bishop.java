package pieces;

import components.Player;
import components.Square;
import movements.DiagonalMoveStrategy;

/**
 * Represents a bishop chess piece
 */
public class Bishop extends Piece {
    private static final String NAME = "Bishop";
    private static final String SYMBOL = "B";

    /**
     * Creates a bishop piece with an owner and a starting location
     *
     * @param owner the player who owns this piece
     * @param location the starting location of this piece
     */
    public Bishop(final Player owner,
                  final Square location) {
        super(owner, location);
        addMoveStrategy(new DiagonalMoveStrategy());

    }

    /**
     * Returns the bishop piece name
     *
     * @return the piece name
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Returns the bishop piece symbol
     *
     * @return the piece symbol
     */
    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}