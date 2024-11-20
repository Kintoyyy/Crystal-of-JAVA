
package Map.Tile;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The TileLayers class is responsible for parsing and storing multiple tile layers from an XML map file.
 */
public class TileLayers {
    private int[][][] tiles; // A 3D array to hold tile IDs for multiple layers.

    /**
     * Constructs the TileLayers object by parsing layers from the given NodeList.
     *
     * @param layers A NodeList containing XML elements for the layers.
     * @param width  The width of the map in tiles.
     * @param height The height of the map in tiles.
     */
    public TileLayers(NodeList layers, int width, int height) {
        if (layers != null) {
            tiles = new int[layers.getLength()][height][width];

            for (int l = 0; l < layers.getLength(); l++) {
                Element layer = (Element) layers.item(l);

                int rows = Integer.parseInt(layer.getAttribute("height"));
                int columns = Integer.parseInt(layer.getAttribute("width"));
                boolean visible = !layer.hasAttribute("visible");

                if (!visible) {
                    continue;
                }

                String tintcolor = layer.getAttribute("tintcolor");

                NodeList properties = layer.getElementsByTagName("properties");

                for (int i = 0; i < properties.getLength(); i++) {
                    Element property = (Element) properties.item(i);

                    String name = property.getAttribute("name");
                    String value = property.getAttribute("value");
                    String type = property.getAttribute("type");
                }

                NodeList dataNodes = layer.getElementsByTagName("data");
                if (dataNodes.getLength() > 0) {
                    String[] values = dataNodes.item(0).getTextContent().trim().split(",");
                    for (int y = 0; y < rows; y++) {
                        for (int x = 0; x < columns; x++) {
                            try {
                                tiles[l][y][x] = Integer.parseInt(values[x + y * columns].trim());
                            } catch (NumberFormatException e) {
                                System.err.println("Tile will not be loaded at layer " + l + ", row " + y + ", column " + x + " id: " + values[x + y * columns]);
                                tiles[l][y][x] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Retrieves the parsed 3D array of tile IDs for all layers.
     *
     * @return A 3D array containing tile IDs, where the dimensions are [layer][y][x].
     */
    public int[][][] getTiles() {
        return tiles;
    }
}
