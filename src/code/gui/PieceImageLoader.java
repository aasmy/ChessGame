package gui;

import components.Color;
import pieces.Piece;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

/**
 * Loads piece images for the GUI board
 */
public class PieceImageLoader {
    private static final String PIECES_PATH = "/pieces/";
    private static final String FILE_EXTENSION = ".png";
    private static final int PIECE_IMAGE_SIZE = 95;

    /**
     * Gets the icon for a chess piece
     *
     * @param piece the chess piece
     * @return the piece icon
     */
    public ImageIcon getPieceIcon(final Piece piece) {
        validatePiece(piece);

        final String fileName = buildFileName(piece);
        final URL imageUrl = getClass().getResource(PIECES_PATH + fileName);

        if (imageUrl == null) {
            throw new IllegalStateException("Piece image was not found: " + fileName);
        }

        final ImageIcon originalIcon = new ImageIcon(imageUrl);
        final Image scaledImage = originalIcon.getImage().getScaledInstance(
                PIECE_IMAGE_SIZE,
                PIECE_IMAGE_SIZE,
                Image.SCALE_SMOOTH
        );

        return new ImageIcon(scaledImage);
    }

    /**
     * Builds the image file name for a chess piece
     *
     * @param piece the chess piece
     * @return the image file name
     */
    private String buildFileName(final Piece piece) {
        final String colorPrefix = getColorPrefix(piece);
        final String pieceName = piece.getName().toLowerCase();

        return colorPrefix + pieceName + FILE_EXTENSION;
    }

    /**
     * Gets the image color prefix for a chess piece
     *
     * @param piece the chess piece
     * @return the image color prefix
     */
    private String getColorPrefix(final Piece piece) {
        if (piece.getOwner().getColor() == Color.WHITE) {
            return "w";
        }

        return "b";
    }

    /**
     * Validates the piece before loading its image
     *
     * @param piece the chess piece
     */
    private void validatePiece(final Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("Piece cannot be null");
        }
    }
}