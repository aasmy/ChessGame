package components;

/**
 * Represents the color of a chess player or chess piece.
 */
public enum Color
{
    WHITE,
    BLACK;

    /**
     * Returns the opposite chess color.
     *
     * @return the opposite color
     */
    public Color getOppositeColor()
    {
        if (this == WHITE) {
            return BLACK;
        }

        return WHITE;
    }
}