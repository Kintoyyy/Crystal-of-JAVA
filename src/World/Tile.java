package World;

import ImageStuff.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Tile {

    protected BufferedImage image;
    protected String path;
    protected String name;

    public static final int TILEWIDTH = 64;
    public static final int TILEHEIGHT = 64;
    public static final Tile defaultTile  = new Tile(ImageLoader.loadImage("/Tiles/Grass/Grass_1_Middle.png"), "grass");

    public Tile(BufferedImage image, String name) {
        this.image = image;
        this.name = name;
    }

    public Tile(BufferedImage image, String name, String path) {
        this.image = image;
        this.name = name;
        this.path = path;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(image, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
