package Map.Tile;

import Utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Tile class represents a single tile in the game world, including its appearance,
 * dimensions, and identifying attributes such as name and ID.
 */
public class Tile {

    /** The image used to represent the tile. */
    protected BufferedImage image;

    /** The file path of the tile image. */
    protected String path;

    /** The name of the tile. */
    protected String name;

    /** The unique identifier of the tile. */
    protected int id;

    /** Standard width of a tile in pixels. */
    public static final int width = 64;

    /** Standard height of a tile in pixels. */
    public static final int height = 64;

    /** Default tile used when no valid tile is found. */
    public static final Tile defaultTile = new Tile(
            ImageUtils.loadImage("/Tiles/Grass/Grass_1_Middle.png"),
            "default",
            0
    );

    /** Tile representing a transparent or empty area (null by design). */
    public static final Tile transparentTile = null;

    /**
     * Constructs a Tile with an image, name, and unique ID.
     *
     * @param image The BufferedImage representing the tile's appearance.
     * @param name The name of the tile.
     * @param id The unique identifier for the tile.
     */
    public Tile(BufferedImage image, String name, int id) {
        this.image = image;
        this.name = name;
        this.id = id;
    }

    /**
     * Constructs a Tile with an image, name, path, and unique ID.
     *
     * @param image The BufferedImage representing the tile's appearance.
     * @param name The name of the tile.
     * @param path The file path of the tile image.
     * @param id The unique identifier for the tile.
     */
    public Tile(BufferedImage image, String name, String path, int id) {
        this.image = image;
        this.name = name;
        this.path = path;
        this.id = id;
    }

    /**
     * Renders the tile at a specific position on the screen.
     *
     * @param g The Graphics object used for drawing.
     * @param x The x-coordinate of the tile's position.
     * @param y The y-coordinate of the tile's position.
     */
    public void render(Graphics g, int x, int y) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        }
    }

    /**
     * Retrieves the name of the tile.
     *
     * @return The tile's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the file path of the tile's image.
     *
     * @return The tile's image path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Retrieves the unique identifier of the tile.
     *
     * @return The tile's ID.
     */
    public int getId() {
        return id;
    }
}
