package Map.Tile;

import Utils.SpriteSheet;
import Utils.ImageUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * The TileTypes class is responsible for parsing and managing tile types from a tileset.
 */
public class TileTypes {
    private final HashMap<Integer, Tile> tiles; // Stores tiles mapped by their unique IDs.

    /**
     * Constructs a TileTypes object by parsing tileset information from the provided NodeList.
     *
     * @param filesets A NodeList containing XML elements describing tilesets.
     */
    public TileTypes(NodeList filesets) {
        tiles = new HashMap<>();
        for (int i = 0; i < filesets.getLength(); i++) {
            Element tileSet = (Element) filesets.item(i);

            // Extract tileset attributes
            int tileWidth = Integer.parseInt(tileSet.getAttribute("tilewidth"));
            int tileHeight = Integer.parseInt(tileSet.getAttribute("tileheight"));
            int firstId = Integer.parseInt(tileSet.getAttribute("firstgid"));

            // Retrieve the image element from the tileset
            Element imageElement = (Element) tileSet.getElementsByTagName("image").item(0);
            String path = imageElement.getAttribute("source");
            String name = imageElement.getAttribute("name");

            int imageWidth = Integer.parseInt(imageElement.getAttribute("width"));
            int imageHeight = Integer.parseInt(imageElement.getAttribute("height"));

            // Calculate rows and columns of tiles based on image dimensions
            int columns = imageWidth / tileWidth;
            int rows = imageHeight / tileHeight;

            // Load the tileset image into a SpriteSheet
            SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage(path));

            // Iterate through tiles and add them to the tiles HashMap
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

    /**
     * Retrieves a Tile object by its unique ID.
     *
     * @param id The unique ID of the tile.
     * @return The corresponding Tile object, or null if the ID does not exist.
     */
    public Tile getTile(int id) {
        return tiles.get(id);
    }
}
