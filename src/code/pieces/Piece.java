package pieces;

import board.Board;
import components.Player;
import components.Square;
import movements.MoveStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a general chess piece
 */
public abstract class Piece {
    private final Player owner;
    private final List<MoveStrategy> moveStrategies;
    private Square location;
    private boolean hasMoved;

    /**
     * Creates a chess piece with an owner and a starting location
     *
     * @param owner the player who owns this piece
     * @param location the current piece location
     */
    public Piece(final Player owner, final Square location) {
        validateOwner(owner);
        validateLocation(location);

        this.owner = owner;
        this.location = location;
        moveStrategies = new ArrayList<>();
        hasMoved = false;
    }

    /**
     * Returns the owner of this piece
     *
     * @return the piece owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Returns the current location of this piece
     *
     * @return the current location
     */
    public Square getLocation() {
        return location;
    }

    /**
     * Updates the current location of this piece
     *
     * @param location the new location
     */
    public void setLocation(final Square location) {
        validateLocation(location);

        this.location = location;
    }

    /**
     * Checks whether this piece has moved before
     *
     * @return true if the piece has moved
     */
    public boolean hasMoved() {
        return hasMoved;
    }

    /**
     * Marks this piece as moved
     */
    public void markAsMoved() {
        hasMoved = true;
    }

    /**
     * Returns the movement strategies of this piece
     *
     * @return the movement strategies
     */
    public List<MoveStrategy> getMoveStrategies() {
        return Collections.unmodifiableList(moveStrategies);
    }

    /**
     * Adds a movement strategy to this piece
     *
     * @param moveStrategy the movement strategy to add
     */
    public void addMoveStrategy(final MoveStrategy moveStrategy) {
        if (moveStrategy == null) {
            throw new IllegalArgumentException("Move strategy cannot be null");
        }

        moveStrategies.add(moveStrategy);
    }

    /**
     * Checks whether this piece can move to the given square
     *
     * @param board the board where the move is checked
     * @param to the destination square
     * @return true if at least one movement strategy accepts the move
     */
    public boolean canMove(final Board board, final Square to) {
        validateBoard(board);
        validateLocation(to);

        for (final MoveStrategy moveStrategy : moveStrategies) {
            if (moveStrategy.validateMove(board, location, to)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the piece name
     *
     * @return the piece name
     */
    public abstract String getName();

    /**
     * Returns the piece symbol
     *
     * @return the piece symbol
     */
    public abstract String getSymbol();

    private void validateOwner(final Player owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }
    }

    private void validateLocation(final Square location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
    }

    private void validateBoard(final Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
    }
}