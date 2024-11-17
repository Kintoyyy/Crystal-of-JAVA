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

public class Parse {
    private int worldWidth, worldHeight;
    private int spawnX, spawnY;
    private int[][][] tilesLayer;
    private TileTypes tileTypes;

    private ArrayList<TriggerArea> triggerAreas = new ArrayList<>(); // move to a separate class

    /**
     * @param worldPath Path to the .tmx map file
     */
    public Parse(String worldPath) {
        try {
            File inputFile = new File(worldPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            // Extract the map element
            NodeList map = doc.getElementsByTagName("map");
            if (map.getLength() > 0) {
                Element mapElement = (Element) map.item(0);
                // Initialize TileTypes
                NodeList tilesets = mapElement.getElementsByTagName("tileset");
                tileTypes = new TileTypes(tilesets);

                // Retrieve map dimensions
                this.worldWidth = Integer.parseInt(mapElement.getAttribute("width"));
                this.worldHeight = Integer.parseInt(mapElement.getAttribute("height"));

                // Initialize TileLayers with extracted dimensions
                NodeList layers = mapElement.getElementsByTagName("layer");

                tilesLayer = new TileLayers(layers, worldWidth, worldHeight).getTiles();

                spawnX = mapElement.hasAttribute("spawnX") ?
                        Integer.parseInt(mapElement.getAttribute("spawnX")) : 200;
                spawnY = mapElement.hasAttribute("spawnY") ?
                        Integer.parseInt(mapElement.getAttribute("spawnY")) : 200;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[][][] getLayers() {
        return tilesLayer;
    }

    public TileTypes getTileTypes() {
        return tileTypes;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public float getSpawnX() {
        return spawnX;
    }

    public float getSpawnY() {
        return spawnY;
    }
}


