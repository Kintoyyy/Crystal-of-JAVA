package Animations;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Frames {
    private final BufferedImage sheet;
    private final int frameWidth;
    private final int frameHeight;

    private final int columns;
    private final int rows;

    private final BufferedImage[][] frames;

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

    private void extractFrames() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                frames[row][col] = sheet.getSubimage(
                        col * frameWidth, row * frameHeight, frameWidth, frameHeight
                );
            }
        }
    }

    public BufferedImage getFrame(int row, int col) {
        validateFramePosition(row, col);
        return frames[row][col];
    }

    public BufferedImage[] extractFrames(int colStart, int rowStart, int colEnd, int rowEnd) {
        validateFrameRange(colStart, rowStart, colEnd, rowEnd); // Uncomment for error validation

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

    public BufferedImage[] extractFrames(int colStart, int rowStart, int colEnd, int rowEnd, boolean flipX) {
        validateFrameRange(colStart, rowStart, colEnd, rowEnd); // Uncomment for error validation

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


    private void validateFramePosition(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= columns) {
            throw new IndexOutOfBoundsException(
                    "Frame position out of bounds. Row: " + row + ", Column: " + col
            );
        }
    }

    private void validateFrameRange(int colStart, int rowStart, int colEnd, int rowEnd) {
        if (colStart < 0 || colEnd > columns || rowStart < 0 || rowEnd > rows || colStart >= colEnd || rowStart >= rowEnd) {
            throw new IllegalArgumentException(
                    "Invalid frame range. Ensure colStart < colEnd and rowStart < rowEnd, and range is within bounds."
            );
        }
    }
}
