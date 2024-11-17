package Utils;

import java.awt.image.BufferedImage;
/**
 * The SpriteSheet class represents a sprite sheet and provides functionality to crop individual sprites from it.
 * A sprite sheet is a single image that contains multiple smaller images (sprites), and this class allows for extracting
 * specific regions of the sprite sheet as individual images.
 *
 * <p>Features:</p>
 * <ul>
 *   <li>Load a sprite sheet (BufferedImage).</li>
 *   <li>Crop individual sprites from the sprite sheet by specifying a region (x, y, width, height).</li>
 *   <li>Handles edge cases such as invalid crop boundaries and ensures the crop is within the bounds of the sprite sheet.</li>
 * </ul>
 *
 * <p>Usage Example:</p>
 * <pre>
 * BufferedImage sheet = ImageIO.read(new File("spritesheet.png"));
 * SpriteSheet spriteSheet = new SpriteSheet(sheet);
 * BufferedImage sprite = spriteSheet.crop(0, 0, 32, 32); // Crop a 32x32 sprite from the top-left corner.
 * </pre>
 */
public class SpriteSheet {
	/**
	 * The sprite sheet image containing multiple sprites.
	 */
	private final BufferedImage sheet;

	/**
	 * Constructs a new SpriteSheet using the provided BufferedImage.
	 *
	 * @param sheet The BufferedImage representing the sprite sheet.
	 */
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	/**
	 * Crops a subimage (sprite) from the sprite sheet based on the provided coordinates and dimensions.
	 *
	 * @param x The x-coordinate of the top-left corner of the sprite to crop.
	 * @param y The y-coordinate of the top-left corner of the sprite to crop.
	 * @param width The width of the sprite to crop.
	 * @param height The height of the sprite to crop.
	 * @return A BufferedImage representing the cropped sprite, or the original sprite sheet if the crop is out of bounds.
	 */
	public BufferedImage crop(int x, int y, int width, int height) {
		// Ensure the cropped width and height are within the bounds of the sprite sheet.
		int cropWidth = Math.min(width, sheet.getWidth() - x);
		int cropHeight = Math.min(height, sheet.getHeight() - y);

		// Check if the crop coordinates and size are valid.
		if (x < 0 || y < 0 || cropWidth <= 0 || cropHeight <= 0 || x + cropWidth > sheet.getWidth() || y + cropHeight > sheet.getHeight()) {
			return sheet; // If invalid, return the original sheet as a fallback.
		}

		// Return the cropped subimage.
		return sheet.getSubimage(x, y, cropWidth, cropHeight);
	}
}
