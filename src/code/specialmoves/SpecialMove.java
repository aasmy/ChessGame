package specialmoves;

import board.Board;
import components.Square;

/**
 * Represents a special chess move execution
 */
public interface SpecialMove {
    /**
     * Executes the special move on the board
     *
     * @param board the chess board
     * @param from the starting square
     * @param to the destination square
     */
    void execute(Board board, Square from, Square to);
}