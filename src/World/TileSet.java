package World;

import Utils.SpriteSheet;
import Utils.ImageUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class TileSet {
    private final HashMap<Integer, Tile> tiles;

    public TileSet(NodeList filesets) {
        tiles = new HashMap<>();
        for (int i = 0; i < filesets.getLength(); i++) {
            Element tileSet = (Element) filesets.item(i);

            int tileWidth = Integer.parseInt(tileSet.getAttribute("tilewidth"));
            int tileHeight = Integer.parseInt(tileSet.getAttribute("tileheight"));
            int firstId = Integer.parseInt(tileSet.getAttribute("firstgid"));

            Element imageElement = (Element) tileSet.getElementsByTagName("image").item(0);
            String path = imageElement.getAttribute("source");
            String name = imageElement.getAttribute("name");

            int imageWidth = Integer.parseInt(imageElement.getAttribute("width"));
            int imageHeight = Integer.parseInt(imageElement.getAttribute("height"));
            int columns = imageWidth / tileWidth;
            int rows = imageHeight / tileHeight;

            SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage(path));

            int tileId = firstId;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    BufferedImage img = sheet.crop(col * tileWidth, row * tileHeight, tileWidth, tileHeight);
                    int id = tileId++;
                    tiles.put(id, new Tile(img, name, path, id));
                }
            }
        }
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }
}
