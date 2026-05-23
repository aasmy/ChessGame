package movements;

import board.Board;
import components.Square;
import handlers.ExistingPieceAtStartHandler;
import handlers.Handler;
import handlers.NoPiecesBetweenHandler;
import handlers.NotSameColorAtDestinationHandler;
import handlers.ValidBoardHandler;
import handlers.ValidDestinationHandler;
import handlers.ValidStartLocationHandler;
import handlers.VerticalMoveHandler;

/**
 * Validates vertical chess moves
 */
public class VerticalMoveStrategy implements MoveStrategy {
    /**
     * Checks whether a move is valid vertically
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the move is valid vertically
     */
    @Override
    public boolean validateMove(final Board board,
                                final Square from,
                                final Square to)
    {
        final Handler validationChain = buildValidationChain();

        return validationChain.canHandle(board, from, to);
    }

    private Handler buildValidationChain() {
        final Handler validBoard = new ValidBoardHandler();

        validBoard
                .setNext(new ValidStartLocationHandler())
                .setNext(new ValidDestinationHandler())
                .setNext(new ExistingPieceAtStartHandler())
                .setNext(new NotSameColorAtDestinationHandler())
                .setNext(new VerticalMoveHandler())
                .setNext(new NoPiecesBetweenHandler());

        return validBoard;
    }
}