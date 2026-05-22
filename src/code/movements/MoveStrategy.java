package movements;

import board.Board;
import components.Square;

/**
 * Represents a movement rule that can validate a chess move
 */
public interface MoveStrategy {
    /**
     * Checks whether a move from one square to another is valid
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the move is valid
     */
    boolean validateMove(Board board, Square from, Square to);
}