package Map.Tile;

import Utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents a single tile in the game world, including its appearance,
 * dimensions, and identifying attributes such as name, path, and ID.
 */
public class Tile {

    /**
     * The image used to visually represent the tile.
     */
    protected BufferedImage image;

    /**
     * The file path of the tile image.
     */
    protected String path;

    /**
     * The name of the tile.
     */
    protected String name;

    /**
     * The unique identifier of the tile.
     */
    protected int id;

    /**
     * Wang tile details associated with the tile.
     */
    protected WangTile wangTile;

    /**
     * Standard width of a tile in pixels.
     */
    public static final int WIDTH = 64;

    /**
     * Standard height of a tile in pixels.
     */
    public static final int HEIGHT = 64;

    /**
     * Default tile used when no valid tile is specified.
     */
    public static final Tile DEFAULT_TILE = new Tile(
            ImageUtils.loadImage("/Tiles/Grass/Grass_1_Middle.png"),
            "default",
            "/Tiles/Grass/Grass_1_Middle.png",
            0,
            new WangTile("0,0,0,0,0,0,0,0")
    );

    /**
     * Tile representing a transparent or empty area (explicitly null by design).
     */
    public static final Tile TRANSPARENT_TILE = null;

    /**
     * Constructs a Tile with an image, name, path, ID, and Wang tile attributes.
     *
     * @param image    The {@link BufferedImage} representing the tile's appearance.
     * @param name     The name of the tile.
     * @param path     The file path of the tile image.
     * @param id       The unique identifier for the tile.
     * @param wangTile The Wang tile attributes for the tile.
     */
    public Tile(BufferedImage image, String name, String path, int id, WangTile wangTile) {
        this.image = image;
        this.name = name;
        this.path = path;
        this.id = id;
        this.wangTile = wangTile;
    }

    /**
     * Constructs a Tile with an image, name, path, and unique ID.
     *
     * @param image The {@link BufferedImage} representing the tile's appearance.
     * @param name  The name of the tile.
     * @param path  The file path of the tile image.
     * @param id    The unique identifier for the tile.
     */
    public Tile(BufferedImage image, String name, String path, int id) {
        this(image, name, path, id, null);
    }

    /**
     * Renders the tile at a specific position on the screen.
     *
     * @param g The {@link Graphics} object used for rendering.
     * @param x The x-coordinate of the tile's position.
     * @param y The y-coordinate of the tile's position.
     */
    public void render(Graphics g, int x, int y) {
        if (image != null) {
            g.drawImage(image, x, y, WIDTH, HEIGHT, null);
        }
    }

    /**
     * Retrieves the name of the tile.
     *
     * @return The name of the tile.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the file path of the tile's image.
     *
     * @return The file path of the tile image.
     */
    public String getPath() {
        return path;
    }

    /**
     * Retrieves the unique identifier of the tile.
     *
     * @return The unique ID of the tile.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the Wang tile attributes of the tile.
     *
     * @return The {@link WangTile} attributes or {@code null} if none exist.
     */
    public WangTile getWangTile() {
        return wangTile;
    }
}
