package World;

import ImageStuff.ImageLoader;
import ImageStuff.SpriteSheet;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class TileSet {
    private HashMap<Integer, Tile> tiles;

    public TileSet(NodeList tilesets) {
        tiles = new HashMap<>();
        for (int i = 0; i < tilesets.getLength(); i++) {
            Element tileset = (Element) tilesets.item(i);

            int tileWidth = Integer.parseInt(tileset.getAttribute("tilewidth"));
            int tileHeight = Integer.parseInt(tileset.getAttribute("tileheight"));
            int firstId = Integer.parseInt(tileset.getAttribute("firstgid"));

            Element imageElement = (Element) tileset.getElementsByTagName("image").item(0);
            String path = imageElement.getAttribute("source");
            String name = imageElement.getAttribute("name");

            int imageWidth = Integer.parseInt(imageElement.getAttribute("width"));
            int imageHeight = Integer.parseInt(imageElement.getAttribute("height"));
            int columns = imageWidth / tileWidth;
            int rows = imageHeight / tileHeight;

            System.out.println("loading image: " + path);
            SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(path));

            int tileId = firstId;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    BufferedImage img = sheet.crop(col * tileWidth, row * tileHeight, tileWidth, tileHeight);
                    tiles.put(tileId++, new Tile(img, name, path));
                }
            }
        }
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }
}
