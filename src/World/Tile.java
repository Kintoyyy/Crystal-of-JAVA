package World;

import ImageStuff.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    protected BufferedImage image;
    protected String path;
    protected String name;
    protected int id;

    public static final int TILEWIDTH = 64;
    public static final int TILEHEIGHT = 64;
    public static final Tile defaultTile = new Tile(ImageLoader.loadImage("/Tiles/Grass/Grass_1_Middle.png"), "default", 0);
    public static final Tile transparentTile = null; // Set to null for transparency

    public Tile(BufferedImage image, String name, int id) {
        this.image = image;
        this.name = name;
        this.id = id;
    }

    public Tile(BufferedImage image, String name, String path, int id) {
        this.image = image;
        this.name = name;
        this.path = path;
        this.id = id;
    }

    public void render(Graphics g, int x, int y) {
        if (image != null) {
            g.drawImage(image, x, y, TILEWIDTH, TILEHEIGHT, null);
        }
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getId() {
        return id;
    }
}
