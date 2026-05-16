package movements;

import components.Square;

/**
 * Represents a movement rule that can validate a chess move.
 */
public interface MoveStrategy {
    /**
     * Checks whether a move from one square to another is valid.
     *
     * @param from the starting square
     * @param to the destination square
     *
     * @return true if the move is valid
     */
    boolean validateMove(Square from, Square to);
}