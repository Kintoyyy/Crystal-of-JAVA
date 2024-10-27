package World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    protected BufferedImage image;
    protected final int id;
    protected boolean isSolid;
    protected boolean isFront;

    public static final int TILE_SIZE = 16;

    public Tile(BufferedImage image, int id, boolean isSolid, boolean isFront) {
        this.image = image;
        this.id = id;
        this.isSolid = isSolid;
        this.isFront = isFront;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(image, x, y, TILE_SIZE, TILE_SIZE, null);
    }

    public boolean isSolid() {
        return isSolid;
    }

    public boolean front() {
        return isFront;
    }

    public int getId() {
        return id;
    }
}
