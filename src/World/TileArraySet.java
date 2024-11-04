package World;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TileArraySet {
    private int[][][] tiles;

    public TileArraySet(NodeList layers, int width, int height) {
        if (layers != null) {
            tiles = new int[layers.getLength()][height][width]; // Initialize 3D array
            for (int l = 0; l < layers.getLength(); l++) {
                Element layer = (Element) layers.item(l);
                int rows = Integer.parseInt(layer.getAttribute("height"));
                int columns = Integer.parseInt(layer.getAttribute("width"));

                NodeList dataNodes = layer.getElementsByTagName("data");
                if (dataNodes.getLength() > 0) {
                    String[] values = dataNodes.item(0).getTextContent().trim().split(",");
                    for (int y = 0; y < rows; y++) {
                        for (int x = 0; x < columns; x++) {
                            tiles[l][y][x] = Integer.parseInt(values[x + y * columns].trim());
                        }
                    }
                }
            }
        }
    }

    public int[][][] getTiles() {
        return tiles;
    }
}
