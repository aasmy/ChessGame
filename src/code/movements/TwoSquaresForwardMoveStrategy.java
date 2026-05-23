package movements;

import board.Board;
import components.Square;
import handlers.EmptyDestinationHandler;
import handlers.ExistingPieceAtStartHandler;
import handlers.Handler;
import handlers.NoPiecesBetweenHandler;
import handlers.TwoSquaresForwardHandler;
import handlers.ValidBoardHandler;
import handlers.ValidDestinationHandler;
import handlers.ValidStartLocationHandler;

/**
 * Validates two square forward pawn moves
 */
public class TwoSquaresForwardMoveStrategy implements MoveStrategy {
    /**
     * Checks whether a pawn can move two squares forward
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the pawn can move two squares forward
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
                .setNext(new EmptyDestinationHandler())
                .setNext(new TwoSquaresForwardHandler())
                .setNext(new NoPiecesBetweenHandler());

        return validBoard;
    }
}