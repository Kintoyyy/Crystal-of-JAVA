package Utils;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageUtils {

    private static final String FALLBACK_IMAGE_PATH = "/fallback.png";
    private static boolean isFallbackImage = false;

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
                System.exit(1);
                return null;
            }
        }
        return image;
    }

    public static boolean isFallback() {
        return isFallbackImage;
    }

    public BufferedImage flipX(BufferedImage image) {
        AffineTransform transform = new AffineTransform();
        transform.scale(-1, 1);  // Flip horizontally
        transform.translate(-image.getWidth(), 0);  // Reposition the image

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }

    public BufferedImage flipY(BufferedImage image) {
        AffineTransform transform = new AffineTransform();
        transform.scale(1, -1);  // Flip vertically
        transform.translate(0, -image.getHeight());  // Reposition the image

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }

    public BufferedImage crop(BufferedImage image, int col, int row, int width, int height) {
        // Check if the image is a fallback image
        if (isFallback()) {
            System.err.println("Cropping not allowed on fallback image.");
            return image;
        }

        // Ensure crop dimensions are within the image bounds
        int cropX = col * width;
        int cropY = row * height;
        int cropWidth = Math.min(width, image.getWidth() - cropX);
        int cropHeight = Math.min(height, image.getHeight() - cropY);

        // Check if the crop region is valid
        if (cropX >= image.getWidth() || cropY >= image.getHeight() || cropWidth <= 0 || cropHeight <= 0) {
            System.err.println("Invalid crop region. Returning original image.");
            return image;
        }

        return image.getSubimage(cropX, cropY, cropWidth, cropHeight);
    }

}
