package Animations;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

/**
 * The {@code Frames} class manages a sprite sheet and provides functionality
 * for extracting frames of animation. It divides a sprite sheet into smaller
 * frames and allows for retrieving specific frames or ranges of frames.
 * <p>
 * The class supports extracting frames with an option to flip them horizontally.
 * </p>
 */
public class Frames {
    private final BufferedImage sheet;
    private final int frameWidth;
    private final int frameHeight;

    private final int columns;
    private final int rows;

    private final BufferedImage[][] frames;

    /**
     * Constructs a {@code Frames} object with the provided sprite sheet and frame dimensions.
     *
     * @param sheet       The sprite sheet image containing the frames.
     * @param frameWidth  The width of each individual frame.
     * @param frameHeight The height of each individual frame.
     * @throws IllegalArgumentException if any of the following conditions are met:
     *                                   - The sheet is null.
     *                                   - Frame width or height is non-positive.
     *                                   - The sheet's dimensions are not divisible by the frame's dimensions.
     */
    public Frames(BufferedImage sheet, int frameWidth, int frameHeight) {
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet image cannot be null.");
        }
        if (frameWidth <= 0 || frameHeight <= 0) {
            throw new IllegalArgumentException("Frame width and height must be positive integers.");
        }

        this.sheet = sheet;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        int sheetWidth = sheet.getWidth();
        int sheetHeight = sheet.getHeight();

        if (sheetWidth % frameWidth != 0 || sheetHeight % frameHeight != 0) {
            throw new IllegalArgumentException("Sheet dimensions must be divisible by frame dimensions.");
        }

        this.columns = sheetWidth / frameWidth;
        this.rows = sheetHeight / frameHeight;

        this.frames = new BufferedImage[rows][columns];
        extractFrames();
    }

    /**
     * Extracts all frames from the sprite sheet and stores them in a 2D array.
     * This method is called during object initialization.
     */
    private void extractFrames() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                frames[row][col] = sheet.getSubimage(
                        col * frameWidth, row * frameHeight, frameWidth, frameHeight
                );
            }
        }
    }

    /**
     * Retrieves a specific frame from the sprite sheet.
     *
     * @param row The row index of the frame.
     * @param col The column index of the frame.
     * @return The {@code BufferedImage} corresponding to the frame at the specified position.
     * @throws IndexOutOfBoundsException if the row or column index is out of bounds.
     */
    public BufferedImage getFrame(int row, int col) {
        validateFramePosition(row, col);
        return frames[row][col];
    }

    /**
     * Extracts a range of frames from the sprite sheet, from the specified starting
     * column and row to the ending column and row.
     *
     * @param colStart The starting column index (inclusive).
     * @param rowStart The starting row index (inclusive).
     * @param colEnd   The ending column index (exclusive).
     * @param rowEnd   The ending row index (exclusive).
     * @return An array of {@code BufferedImage} frames in the specified range.
     * @throws IllegalArgumentException if the range is invalid.
     */
    public BufferedImage[] extractFrames(int colStart, int rowStart, int colEnd, int rowEnd) {
        validateFrameRange(colStart, rowStart, colEnd, rowEnd);

        int totalFrames = (rowEnd - rowStart) * (colEnd - colStart);
        BufferedImage[] animationFrames = new BufferedImage[totalFrames];
        int index = 0;
        for (int row = rowStart; row < rowEnd; row++) {
            for (int col = colStart; col < colEnd; col++) {
                animationFrames[index] = frames[row][col];
                index++;
            }
        }

        return animationFrames;
    }

    /**
     * Extracts a range of frames from the sprite sheet, with an option to flip
     * them horizontally.
     *
     * @param colStart The starting column index (inclusive).
     * @param rowStart The starting row index (inclusive).
     * @param colEnd   The ending column index (exclusive).
     * @param rowEnd   The ending row index (exclusive).
     * @param flipX    Whether to flip the frames horizontally.
     * @return An array of {@code BufferedImage} frames in the specified range, possibly flipped.
     * @throws IllegalArgumentException if the range is invalid.
     */
    public BufferedImage[] extractFrames(int colStart, int rowStart, int colEnd, int rowEnd, boolean flipX) {
        validateFrameRange(colStart, rowStart, colEnd, rowEnd);

        int totalFrames = (rowEnd - rowStart) * (colEnd - colStart);
        BufferedImage[] animationFrames = new BufferedImage[totalFrames];
        int index = 0;

        for (int row = rowStart; row < rowEnd; row++) {
            for (int col = colStart; col < colEnd; col++) {
                BufferedImage frame = frames[row][col];
                if (flipX) {
                    frame = flipImageHorizontally(frame);
                }
                animationFrames[index++] = frame;
            }
        }

        return animationFrames;
    }

    /**
     * Flips a given image horizontally.
     *
     * @param image The {@code BufferedImage} to flip.
     * @return A new {@code BufferedImage} that is horizontally flipped.
     */
    private BufferedImage flipImageHorizontally(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage flipped = new BufferedImage(width, height, image.getType());
        Graphics2D g2d = flipped.createGraphics();

        // Flip the image horizontally
        g2d.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
        g2d.dispose();

        return flipped;
    }

    /**
     * Validates that the specified frame position is within bounds.
     *
     * @param row The row index of the frame.
     * @param col The column index of the frame.
     * @throws IndexOutOfBoundsException if the frame position is out of bounds.
     */
    private void validateFramePosition(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= columns) {
            throw new IndexOutOfBoundsException(
                    "Frame position out of bounds. Row: " + row + ", Column: " + col
            );
        }
    }

    /**
     * Validates that the specified frame range is valid and within bounds.
     *
     * @param colStart The starting column index (inclusive).
     * @param rowStart The starting row index (inclusive).
     * @param colEnd   The ending column index (exclusive).
     * @param rowEnd   The ending row index (exclusive).
     * @throws IllegalArgumentException if the range is invalid.
     */
    private void validateFrameRange(int colStart, int rowStart, int colEnd, int rowEnd) {
        if (colStart < 0 || colEnd > columns || rowStart < 0 || rowEnd > rows || colStart >= colEnd || rowStart >= rowEnd) {
            throw new IllegalArgumentException(
                    "Invalid frame range. Ensure colStart < colEnd and rowStart < rowEnd, and range is within bounds."
            );
        }
    }
}
