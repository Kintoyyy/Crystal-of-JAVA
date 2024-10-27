package World;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileSet {
    public static ArrayList<Tile> tiles;

    public TileSet(){
        tiles = new ArrayList<>();
//        tiles.add()
    }


    public BufferedImage cropImage(BufferedImage image, int col, int row, int width, int height) {
        return image.getSubimage(col * width, row * height, width, height);
    }

}
