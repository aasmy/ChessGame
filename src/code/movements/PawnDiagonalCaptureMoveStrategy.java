package movements;

import board.Board;
import components.Square;
import handlers.EnemyOnDestinationHandler;
import handlers.ExistingPieceAtStartHandler;
import handlers.Handler;
import handlers.PawnDiagonalMoveHandler;
import handlers.ValidBoardHandler;
import handlers.ValidDestinationHandler;
import handlers.ValidStartLocationHandler;

/**
 * Validates diagonal pawn capture moves
 */
public class PawnDiagonalCaptureMoveStrategy implements MoveStrategy {
    /**
     * Checks whether a pawn can capture diagonally
     *
     * @param board the board where the move is checked
     * @param from the starting square
     * @param to the destination square
     * @return true if the pawn can capture diagonally
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
                .setNext(new EnemyOnDestinationHandler())
                .setNext(new PawnDiagonalMoveHandler());

        return validBoard;
    }
}