package Utils;

import ImageStuff.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageUtils {

    public static BufferedImage loadImage(String path) {
        try {
            //load in image
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    /**
     * Flip a given image horizontally (X-axis).
     * @param image The BufferedImage to flip.
     * @return The flipped BufferedImage.
     */
    public BufferedImage flipX(BufferedImage image) {
        AffineTransform transform = new AffineTransform();
        transform.scale(-1, 1);  // Flip horizontally
        transform.translate(-image.getWidth(), 0);  // Reposition the image

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }

    /**
     * Flip a given image vertically (Y-axis).
     * @param image The BufferedImage to flip.
     * @return The flipped BufferedImage.
     */
    public BufferedImage flipY(BufferedImage image) {
        AffineTransform transform = new AffineTransform();
        transform.scale(1, -1);  // Flip vertically
        transform.translate(0, -image.getHeight());  // Reposition the image

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }
    /**
     * Crop a specific image from the sprite sheet.
     * @param col The column index of the sprite.
     * @param row The row index of the sprite.
     * @param width The width of the sprite.
     * @param height The height of the sprite.
     * @return The cropped BufferedImage.
     */
    public BufferedImage crop(BufferedImage image, int col, int row, int width, int height) {
        return image.getSubimage(col * width, row * height, width, height);
    }
}
