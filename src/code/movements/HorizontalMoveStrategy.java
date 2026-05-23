package movements;

import board.Board;
import components.Square;
import handlers.ExistingPieceAtStartHandler;
import handlers.Handler;
import handlers.HorizontalMoveHandler;
import handlers.NoPiecesBetweenHandler;
import handlers.NotSameColorAtDestinationHandler;
import handlers.ValidBoardHandler;
import handlers.ValidDestinationHandler;
import handlers.ValidStartLocationHandler;

/**
 * Validates horizontal chess moves
 */
public class HorizontalMoveStrategy implements MoveStrategy {
    /**
     * Checks whether a move is valid horizontally
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the move is valid horizontally
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
                .setNext(new HorizontalMoveHandler())
                .setNext(new NoPiecesBetweenHandler());

        return validBoard;
    }
}