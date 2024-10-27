package Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

import Game.Game;
import Game.Handler;
import Assets.Assets;

public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new Tile(Assets.grass, 0, false);
    public static Tile rockTile = new Tile(Assets.rock, 1, false);

    public static Tile grass_0 = new Tile(Assets.grass_tiles[1][0], 23, true);
    public static Tile grass_1 = new Tile(Assets.grass_tiles[1][1], 24, true);
    public static Tile grass_2 = new Tile(Assets.grass_tiles[0][2], 25, true);
    public static Tile grass_3 = new Tile(Assets.grass_tiles[1][3], 31, true);
    public static Tile grass_4 = new Tile(Assets.grass_tiles[1][4], 2, true);
    public static Tile grass_5 = new Tile(Assets.grass_tiles[1][5], 3, true);
    public static Tile grass_6 = new Tile(Assets.grass_tiles[1][6], 4, true);
    public static Tile grass_7 = new Tile(Assets.grass_tiles[1][7], 5, true);
    public static Tile grass_8 = new Tile(Assets.grass_tiles[1][8], 6, true);
    public static Tile grass_9 = new Tile(Assets.grass_tiles[1][9], 45, true);
    public static Tile grass_10 = new Tile(Assets.grass_tiles[1][10], 42, true);
    public static Tile grass_11 = new Tile(Assets.grass_tiles[1][11], 49, true);
    public static Tile grass_12 = new Tile(Assets.grass_tiles[1][12], 50, true);


    public static Tile bush = new Tile(Assets.bush, 2, false);

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;
    protected boolean isSolid;

    public Tile(BufferedImage texture, int id, boolean isSolid) {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
        this.isSolid = isSolid;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);

        // TODO: MAKE THIS INTO A TOGGLE
        if (isSolid) {
            g.setColor(Color.RED);
            g.drawRect(x, y, TILEWIDTH, TILEHEIGHT);
        }
    }

    public boolean isSolid() {
        return isSolid;
    }


    public int getId() {
        return id;
    }


}
