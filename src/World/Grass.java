package World;

import Utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Grass extends Tile{

    private int heigth = 16;
    private int width = 16;
    protected ArrayList<BufferedImage> grass;
    protected BufferedImage grassImage;
    private ImageUtils img = new ImageUtils();

    public Grass(BufferedImage texture, int id, boolean isSolid, boolean isFront) {
        super(texture, id, isSolid, isFront);
        grassImage = ImageUtils.loadImage("grass.png");
        initializeAsset();
    }

    private void initializeAsset(){

    }

}
