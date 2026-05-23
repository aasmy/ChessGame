package movements;

import board.Board;
import components.Square;
import handlers.ExistingPieceAtStartHandler;
import handlers.Handler;
import handlers.KnightMoveHandler;
import handlers.NotSameColorAtDestinationHandler;
import handlers.ValidBoardHandler;
import handlers.ValidDestinationHandler;
import handlers.ValidStartLocationHandler;

/**
 * Validates knight chess moves
 */
public class KnightMoveStrategy implements MoveStrategy {
    /**
     * Checks whether a move is valid for a knight
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the move is valid for a knight
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

        /*
         * Knights can jump over pieces, so this strategy does not use
         * NoPiecesBetweenHandler
         */
        validBoard
                .setNext(new ValidStartLocationHandler())
                .setNext(new ValidDestinationHandler())
                .setNext(new ExistingPieceAtStartHandler())
                .setNext(new NotSameColorAtDestinationHandler())
                .setNext(new KnightMoveHandler());

        return validBoard;
    }
}