package Map;

import Map.Tile.TileLayers;
import Map.Tile.TileTypes;
import Map.Object.ObjectGroup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * The Map class is responsible for parsing a .tmx map file to extract world data such as dimensions,
 * spawn positions, tile layers, and tile types.
 */
public class Map {

    /**
     * Width of the world in tiles.
     */
    private int worldWidth;

    /**
     * Height of the world in tiles.
     */
    private int worldHeight;

    /**
     * X-coordinate of the spawn point.
     */
    private int spawnX;

    /**
     * Y-coordinate of the spawn point.
     */
    private int spawnY;

    /**
     * 3D array representing the tile layers in the world.
     */
    private int[][][] tilesLayer;

    /**
     * TileTypes object for managing the tileset data.
     */
    private TileTypes tileTypes;

    /**
     * ObjectGroup object for managing trigger data.
     */
    private ObjectGroup objectGroup;

    /**
     * Constructs a Map object by parsing a .tmx map file.
     *
     * @param worldPath Path to the .tmx map file to parse.
     */
    public Map(String worldPath) {
        try {
            // Load the .tmx map file
            File inputFile = new File(worldPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            // Extract the root "map" element
            NodeList map = doc.getElementsByTagName("map");
            if (map.getLength() > 0) {
                Element mapElement = (Element) map.item(0);

                // Initialize tile types using the tilesets
                NodeList tilesets = mapElement.getElementsByTagName("tileset");
                tileTypes = new TileTypes(tilesets);

                // Retrieve map dimensions
                this.worldWidth = Integer.parseInt(mapElement.getAttribute("width"));
                this.worldHeight = Integer.parseInt(mapElement.getAttribute("height"));

                // Map tile layers
                NodeList layers = mapElement.getElementsByTagName("layer");
                tilesLayer = new TileLayers(layers, worldWidth, worldHeight).getTiles();

                // Map triggers
                NodeList objectGroups = mapElement.getElementsByTagName("objectgroup");
                objectGroup = new ObjectGroup(objectGroups);

                // Retrieve spawn coordinates, with default values if not specified
                spawnX = mapElement.hasAttribute("spawnX") ?
                        Integer.parseInt(mapElement.getAttribute("spawnX")) : 200;
                spawnY = mapElement.hasAttribute("spawnY") ?
                        Integer.parseInt(mapElement.getAttribute("spawnY")) : 200;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the parsed tile layers.
     *
     * @return A 3D array representing the tile layers.
     */
    public int[][][] getLayers() {
        return tilesLayer;
    }

    /**
     * Retrieves the TileTypes object containing tileset data.
     *
     * @return The TileTypes object.
     */
    public TileTypes getTileTypes() {
        return tileTypes;
    }

    /**
     * Retrieves the ObjectGroup object containing trigger data.
     *
     * @return The ObjectGroup object.
     */
    public ObjectGroup getObjectGroup() {
        return objectGroup;
    }

    /**
     * Retrieves the width of the world in tiles.
     *
     * @return The world width.
     */
    public int getWorldWidth() {
        return worldWidth;
    }

    /**
     * Retrieves the height of the world in tiles.
     *
     * @return The world height.
     */
    public int getWorldHeight() {
        return worldHeight;
    }

    /**
     * Retrieves the X-coordinate of the spawn point.
     *
     * @return The spawn X-coordinate.
     */
    public float getSpawnX() {
        return spawnX;
    }

    /**
     * Retrieves the Y-coordinate of the spawn point.
     *
     * @return The spawn Y-coordinate.
     */
    public float getSpawnY() {
        return spawnY;
    }
}
