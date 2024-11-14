package World;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParseWorld {
    private int width, height;
    private int spawnX, spawnY;
    private int[][][] tilesLayer;
    private ParseTileTypes parseTileTypes;

    /**
     * @param worldPath Path to the .tmx world file
     */
    public ParseWorld(String worldPath) {
        try {
            File inputFile = new File(worldPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            // Extract the map element
            NodeList map = doc.getElementsByTagName("map");
            if (map.getLength() > 0) {
                Element mapElement = (Element) map.item(0);
                // Initialize ParseTileTypes
                NodeList tilesets = mapElement.getElementsByTagName("tileset");
                parseTileTypes = new ParseTileTypes(tilesets);

                // Retrieve map dimensions
                this.width = Integer.parseInt(mapElement.getAttribute("width"));
                this.height = Integer.parseInt(mapElement.getAttribute("height"));

                // Initialize ParseWorldLayers with extracted dimensions
                NodeList layers = mapElement.getElementsByTagName("layer");

                tilesLayer = new ParseWorldLayers(layers, width, height).getTiles();

                spawnX = mapElement.hasAttribute("spawnX") ?
                        Integer.parseInt(mapElement.getAttribute("spawnX")) : 10;
                spawnY = mapElement.hasAttribute("spawnY") ?
                        Integer.parseInt(mapElement.getAttribute("spawnY")) : 10;

//                entityManager.getPlayer().setX(spawnX * Tile.TILEWIDTH);
//                entityManager.getPlayer().setY(spawnY * Tile.TILEWIDTH);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int[][][] getLayers() {
        return tilesLayer;
    }
    public ParseTileTypes getTileTypes() {
        return parseTileTypes;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getSpawnX() {
        return spawnX;
    }
    public int getSpawnY() {
        return spawnY;
    }
}

