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
        validateName(name);
        validateColor(color);

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
     * Validates the player name.
     *
     * @param name the player name
     */
    private void validateName(final String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty.");
        }
    }

    /**
     * Validates the player color.
     *
     * @param color the player color
     */
    private void validateColor(final Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Player color cannot be null.");
        }
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