package Map;

import Map.Tile.TileLayers;
import Map.Tile.TileTypes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * The Parse class is responsible for parsing a .tmx map file to extract world data such as dimensions,
 * spawn positions, tile layers, and tile types.
 */
public class Parse {

    /** Width of the world in tiles. */
    private int worldWidth;

    /** Height of the world in tiles. */
    private int worldHeight;

    /** X-coordinate of the spawn point. */
    private int spawnX;

    /** Y-coordinate of the spawn point. */
    private int spawnY;

    /** 3D array representing the tile layers in the world. */
    private int[][][] tilesLayer;

    /** TileTypes object for managing the tileset data. */
    private TileTypes tileTypes;

    /** List of trigger areas in the map. Should be moved to a separate class for better design. */
    private ArrayList<TriggerArea> triggerAreas = new ArrayList<>();

    /**
     * Constructs a Parse object by parsing a .tmx map file.
     *
     * @param worldPath Path to the .tmx map file to parse.
     */
    public Parse(String worldPath) {
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

                // Parse tile layers
                NodeList layers = mapElement.getElementsByTagName("layer");
                tilesLayer = new TileLayers(layers, worldWidth, worldHeight).getTiles();

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
