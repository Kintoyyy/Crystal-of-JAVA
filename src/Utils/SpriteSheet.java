package Utils;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private final BufferedImage sheet;

	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	public BufferedImage crop(int x, int y, int width, int height) {
		int cropWidth = Math.min(width, sheet.getWidth() - x);
		int cropHeight = Math.min(height, sheet.getHeight() - y);

		if (x < 0 || y < 0 || cropWidth <= 0 || cropHeight <= 0 || x + cropWidth > sheet.getWidth() || y + cropHeight > sheet.getHeight()) {
			return sheet;
		}

		return sheet.getSubimage(x, y, cropWidth, cropHeight);
	}
}
