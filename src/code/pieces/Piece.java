package pieces;

import components.Player;
import components.Square;
import movements.MoveStrategy;

import java.util.ArrayList;
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
    public Piece(final Player owner,
                 final Square location)
    {
        validateOwner(owner);
        validateLocation(location);

        this.owner = owner;
        this.location = location;
        this.moveStrategies = new ArrayList<>();
        this.hasMoved = false;
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
     * @return the piece location
     */
    public Square getLocation() {
        return location;
    }

    /**
     * Updates the current location of this piece
     *
     * @param location the new piece location
     */
    public void setLocation(final Square location) {
        validateLocation(location);

        this.location = location;
    }

    /**
     * Checks whether this piece has moved before
     *
     * @return true if this piece has moved before
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
     * Returns the movement strategies assigned to this piece
     *
     * @return the movement strategies
     */
    public List<MoveStrategy> getMoveStrategies() {
        return new ArrayList<>(moveStrategies);
    }

    /**
     * Adds a movement strategy to this piece
     *
     * @param moveStrategy the movement strategy to add
     */
    protected void addMoveStrategy(final MoveStrategy moveStrategy) {
        if (moveStrategy == null) {
            throw new IllegalArgumentException("Move strategy cannot be null.");
        }

        moveStrategies.add(moveStrategy);
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

    /**
     * Validates the piece owner
     *
     * @param owner the piece owner
     */
    private void validateOwner(final Player owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Piece owner cannot be null.");
        }
    }

    /**
     * Validates the piece location
     *
     * @param location the piece location
     */
    private void validateLocation(final Square location) {
        if (location == null) {
            throw new IllegalArgumentException("Piece location cannot be null.");
        }
    }
}



