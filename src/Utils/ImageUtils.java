package Utils;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
/**
 * The ImageUtils class provides utility methods for handling images such as loading, flipping, and cropping.
 * It also handles a fallback mechanism when images are not found, providing a default fallback image.
 *
 * <p>Features:</p>
 * <ul>
 *   <li>Load images from a specified path with a fallback option.</li>
 *   <li>Flip images horizontally or vertically.</li>
 *   <li>Crop images into smaller regions based on the specified coordinates and dimensions.</li>
 *   <li>Prevent cropping on fallback images to ensure consistency.</li>
 * </ul>
 *
 * <p>Usage Example:</p>
 * <pre>
 * BufferedImage image = ImageUtils.loadImage("/path/to/image.png");
 * BufferedImage flippedImage = ImageUtils.flipX(image);
 * BufferedImage croppedImage = ImageUtils.crop(image, 0, 0, 32, 32);
 * </pre>
 */
public class ImageUtils {

    private static final String FALLBACK_IMAGE_PATH = "/fallback.png"; // Path for the fallback image
    private static boolean isFallbackImage = false; // Flag indicating if the fallback image is being used

    /**
     * Loads an image from the specified path. If the image is not found, a fallback image is loaded.
     *
     * @param path The path to the image file.
     * @return The loaded BufferedImage, or the fallback image if the main image is not found.
     */
    public static BufferedImage loadImage(String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(Objects.requireNonNull(ImageUtils.class.getResource(path)));
            isFallbackImage = false;  // Set to false if the main image loads successfully
        } catch (IOException | NullPointerException e) {
            System.err.println("Image not found at path: " + path + ". Loading fallback image.");
            try {
                image = ImageIO.read(Objects.requireNonNull(ImageUtils.class.getResource(FALLBACK_IMAGE_PATH)));
                isFallbackImage = true;  // Set to true if loading fallback image
            } catch (IOException | NullPointerException fallbackException) {
                System.err.println("Fallback image also not found. Exiting.");
                fallbackException.printStackTrace();
                System.exit(1);  // Exit the program if the fallback image is not found
                return null;
            }
        }
        return image;
    }

    /**
     * Checks if the currently loaded image is the fallback image.
     *
     * @return True if the fallback image is being used, otherwise false.
     */
    public static boolean isFallback() {
        return isFallbackImage;
    }

    /**
     * Flips the given image horizontally (along the X-axis).
     *
     * @param image The BufferedImage to flip.
     * @return A new BufferedImage that is the flipped version of the input image.
     */
    public BufferedImage flipX(BufferedImage image) {
        AffineTransform transform = new AffineTransform();
        transform.scale(-1, 1);  // Flip horizontally
        transform.translate(-image.getWidth(), 0);  // Reposition the image

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }

    /**
     * Flips the given image vertically (along the Y-axis).
     *
     * @param image The BufferedImage to flip.
     * @return A new BufferedImage that is the flipped version of the input image.
     */
    public BufferedImage flipY(BufferedImage image) {
        AffineTransform transform = new AffineTransform();
        transform.scale(1, -1);  // Flip vertically
        transform.translate(0, -image.getHeight());  // Reposition the image

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }

    /**
     * Crops a region from the given image based on the specified column, row, width, and height.
     * If the image is the fallback image, cropping is not allowed, and the original image is returned.
     *
     * @param image The BufferedImage to crop.
     * @param col The column of the sprite (starting from 0).
     * @param row The row of the sprite (starting from 0).
     * @param width The width of the cropped region.
     * @param height The height of the cropped region.
     * @return A BufferedImage representing the cropped region, or the original image if the crop is invalid or cropping on a fallback image.
     */
    public BufferedImage crop(BufferedImage image, int col, int row, int width, int height) {
        // Check if the image is a fallback image
        if (isFallback()) {
            System.err.println("Cropping not allowed on fallback image.");
            return image;  // Return the original image if it's the fallback image
        }

        // Ensure crop dimensions are within the image bounds
        int cropX = col * width;
        int cropY = row * height;
        int cropWidth = Math.min(width, image.getWidth() - cropX);
        int cropHeight = Math.min(height, image.getHeight() - cropY);

        // Check if the crop region is valid
        if (cropX >= image.getWidth() || cropY >= image.getHeight() || cropWidth <= 0 || cropHeight <= 0) {
            System.err.println("Invalid crop region. Returning original image.");
            return image;  // Return the original image if the crop is invalid
        }

        // Return the cropped subimage
        return image.getSubimage(cropX, cropY, cropWidth, cropHeight);
    }
}
