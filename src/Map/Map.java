package Map;

import Map.Object.Objects;
import Map.Tile.TileLayers;
import Map.Tile.TileTypes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;

/**
 * The Map class is responsible for parsing a .tmx map file and extracting information such as dimensions,
 * spawn positions, tile layers, tile types, and object data.
 */
public class Map {

    private int worldWidth;  // Width of the world in tiles.
    private int worldHeight; // Height of the world in tiles.
    private Point spawnPoint = new Point(0, 0); // Default spawn point (0, 0).

    private int[][][] tileLayers;  // 3D array representing all tile layers.
    private TileTypes tileTypes;  // Object for managing tileset data.
    private Objects objects;  // Object for managing object and trigger data.

    private int playerLayer; // The layer where the player resides.

    /**
     * Constructs a Map object by parsing a .tmx map file.
     *
     * @param mapPath Path to the .tmx map file.
     */
    public Map(String mapPath) {
        try {
            File mapFile = new File(mapPath);

            // Parse the XML document from the map file.
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(mapFile);

            // Extract the root <map> element.
            NodeList mapNodes = document.getElementsByTagName("map");
            if (mapNodes.getLength() > 0) {
                Element mapElement = (Element) mapNodes.item(0);

                // Parse map dimensions.
                this.worldWidth = Integer.parseInt(mapElement.getAttribute("width"));
                this.worldHeight = Integer.parseInt(mapElement.getAttribute("height"));

                System.out.printf("Loaded map: %s (%dx%d tiles)%n", mapPath, worldWidth, worldHeight);

                // Parse tilesets.
                NodeList tilesetNodes = mapElement.getElementsByTagName("tileset");
                this.tileTypes = new TileTypes(tilesetNodes);

                // Parse tile layers.
                NodeList layerNodes = mapElement.getElementsByTagName("layer");
                TileLayers tileLayerParser = new TileLayers(layerNodes, worldWidth, worldHeight);
                this.tileLayers = tileLayerParser.getTiles();
                this.playerLayer = tileLayerParser.getPlayerLayer();

                // Parse object groups (e.g., triggers, spawn points).
                NodeList objectGroupNodes = mapElement.getElementsByTagName("objectgroup");
                this.objects = new Objects(objectGroupNodes);

                // Retrieve the spawn point from the object group.
                this.spawnPoint = objects.getSpawnPoint();
            } else {
                System.err.println("Error: Map file does not contain a valid <map> element.");
            }
        } catch (Exception e) {
            System.err.printf("Failed to parse map file '%s': %s%n", mapPath, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Returns the tile layers as a 3D array.
     *
     * @return A 3D array representing the tile layers ([layer][y][x]).
     */
    public int[][][] getTileLayers() {
        return tileLayers;
    }

    /**
     * Returns the TileTypes object containing tileset data.
     *
     * @return TileTypes object.
     */
    public TileTypes getTileTypes() {
        return tileTypes;
    }

    /**
     * Returns the Objects object containing triggers and object data.
     *
     * @return Objects object.
     */
    public Objects getObjectGroup() {
        return objects;
    }

    /**
     * Returns the width of the world in tiles.
     *
     * @return World width.
     */
    public int getWorldWidth() {
        return worldWidth;
    }

    /**
     * Returns the height of the world in tiles.
     *
     * @return World height.
     */
    public int getWorldHeight() {
        return worldHeight;
    }

    /**
     * Returns the spawn point for the player as a Point object.
     *
     * @return Spawn point (x, y).
     */
    public Point getSpawnPoint() {
        return spawnPoint;
    }

    /**
     * Returns the index of the player's layer.
     *
     * @return Player layer index.
     */
    public int getPlayerLayer() {
        return playerLayer;
    }
}
