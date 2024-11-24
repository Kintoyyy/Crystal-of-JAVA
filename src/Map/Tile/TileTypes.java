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
    private final HashMap<String, WangTile> wangTile;

    /**
     * Constructs a TileTypes object by parsing tileset information from the provided NodeList.
     *
     * @param filesets A NodeList containing XML elements describing tilesets.
     */
    public TileTypes(NodeList filesets) {
        tiles = new HashMap<>();
        wangTile = new HashMap<>();

        for (int i = 0; i < filesets.getLength(); i++) {
            Element tileSet = (Element) filesets.item(i);

            // Parse attributes with validation
            int tileWidth = parseIntOrDefault(tileSet.getAttribute("tilewidth"));
            int tileHeight = parseIntOrDefault(tileSet.getAttribute("tileheight"));
            int firstId = parseIntOrDefault(tileSet.getAttribute("firstgid"));

            if (tileWidth == 0 || tileHeight == 0 || firstId == 0) {
                throw new IllegalArgumentException("Invalid tileset attributes: tileWidth, tileHeight, or firstGid is zero.");
            }

            // Retrieve and validate the image element
            Element imageElement = (Element) tileSet.getElementsByTagName("image").item(0);
            if (imageElement == null) {
                throw new IllegalArgumentException("Tileset image element is missing.");
            }

            String path = imageElement.getAttribute("source");
            if (path.isEmpty()) {
                throw new IllegalArgumentException("Tileset image path is missing.");
            }

            String name = imageElement.getAttribute("name");
            int imageWidth = parseIntOrDefault(imageElement.getAttribute("width"));
            int imageHeight = parseIntOrDefault(imageElement.getAttribute("height"));

            if (imageWidth == 0 || imageHeight == 0) {
                throw new IllegalArgumentException("Invalid image dimensions: width or height is zero.");
            }

            NodeList wangsets = tileSet.getElementsByTagName("wangsets");
            for (int j = 0; j < wangsets.getLength(); j++) {
                Element wangtile = (Element) wangsets.item(j);

                String tileid = wangtile.getAttribute("tileid");
                String wangid = wangtile.getAttribute("wangid");
                
                wangTile.put(tileid, new WangTile(wangid));
            }

            // Load the tileset image and process tiles
            SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage(path));
            int columns = imageWidth / tileWidth;
            int rows = imageHeight / tileHeight;

            int tileId = firstId;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    BufferedImage img = sheet.crop(col * tileWidth, row * tileHeight, tileWidth, tileHeight);
                    tiles.put(tileId, new Tile(img, name, path, tileId, wangTile.get(tileId)));
                    tileId++;
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

    /**
     * Safely parses an integer value from a string, returning a default value if parsing fails.
     *
     * @param value The string to parse.
     * @return The parsed integer or the default value if parsing fails.
     */
    private int parseIntOrDefault(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
