package components;

/**
 * Represents a chess player.
 */
public class Player {
    private final String name;
    private final Color color;

    /**
     * Creates a player with a name and a chess color.
     *
     * @param name the player name
     * @param color the player color
     */
    public Player(final String name, final Color color) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty.");
        }

        if (color == null) {
            throw new IllegalArgumentException("Player color cannot be null.");
        }

        this.name = name;
        this.color = color;
    }

    /**
     * Returns the player name.
     *
     * @return the player name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player color.
     *
     * @return the player color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns a readable text representation of the player.
     *
     * @return the player as text
     */
    @Override
    public String toString() {
        return "Player{"
                + "name='" + name + '\''
                + ", color=" + color
                + '}';
    }
}