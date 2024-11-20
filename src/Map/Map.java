package Map;

import Map.Object.Object;
import Map.Tile.TileLayers;
import Map.Tile.TileTypes;
import Map.Object.ObjectGroup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

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


    private Point spawnPoint = new Point(0, 0);

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

                // Retrieve map dimensions
                this.worldWidth = Integer.parseInt(mapElement.getAttribute("width"));
                this.worldHeight = Integer.parseInt(mapElement.getAttribute("height"));

                System.out.println("Creating world: "  + worldPath + " with world dimensions of " + worldWidth + " by " + worldHeight + " tiles");

                // Initialize tile types using the tilesets
                NodeList tilesets = mapElement.getElementsByTagName("tileset");
                this.tileTypes = new TileTypes(tilesets);

                // Map tile layers
                NodeList layers = mapElement.getElementsByTagName("layer");
                this.tilesLayer = new TileLayers(layers, this.worldWidth, this.worldHeight).getTiles();

                // Map triggers
                NodeList objectGroups = mapElement.getElementsByTagName("objectgroup");
                this.objectGroup = new ObjectGroup(objectGroups);

                this.spawnPoint = objectGroup.getPawnPoint();
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
    public ArrayList<Object> getObjects() {
        return objectGroup.getObjects();
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
     * Retrieves the x-coordinate of the spawn point.
     *
     * @return The x-coordinate of the spawn point.
     */
    public Point getSpawnPoint() {
        return spawnPoint;
    }
}
