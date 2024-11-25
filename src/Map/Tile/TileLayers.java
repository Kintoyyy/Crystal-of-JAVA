package Map.Tile;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The TileLayers class parses and stores multiple tile layers from an XML map file.
 */
public class TileLayers {
    private final int[][][] tiles; // A 3D array to store tile IDs for multiple layers.
    private int playerLayer;

    /**
     * Constructs the TileLayers object by parsing layers from the given NodeList.
     *
     * @param layers A NodeList containing XML elements for the layers.
     * @param width  The width of the map in tiles.
     * @param height The height of the map in tiles.
     */
    public TileLayers(NodeList layers, int width, int height) {
        if (layers == null) {
            throw new IllegalArgumentException("Layers NodeList cannot be null.");
        }

        int totalLayers = layers.getLength();
        tiles = new int[totalLayers][height][width];

        for (int layerIndex = 0; layerIndex < totalLayers; layerIndex++) {
            Element layer = (Element) layers.item(layerIndex);
            parseLayer(layer, layerIndex, width, height);
        }

        // Set the selectedPlayer layer to the middle if not explicitly defined
        if (this.playerLayer == 0) {
            this.playerLayer = totalLayers / 2;
        }
    }

    /**
     * Parses a single layer and populates the tile data.
     *
     * @param layer      The XML Element representing the layer.
     * @param layerIndex The index of the current layer.
     * @param mapWidth   The width of the map in tiles.
     * @param mapHeight  The height of the map in tiles.
     */
    private void parseLayer(Element layer, int layerIndex, int mapWidth, int mapHeight) {
        String layerName = layer.getAttribute("name");
        System.out.println("Parsing layer: " + layerName);

        int rows = getIntAttribute(layer, "height", mapHeight);
        int columns = getIntAttribute(layer, "width", mapWidth);
        boolean isVisible = !layer.hasAttribute("visible") || Boolean.parseBoolean(layer.getAttribute("visible"));

        if (isPlayerLayer(layer)) {
            this.playerLayer = layerIndex;
        }

        if (!isVisible || isPlayerLayer(layer)) {
            return; // Skip invisible layers or selectedPlayer-specific layers
        }

        NodeList dataNodes = layer.getElementsByTagName("data");
        if (dataNodes.getLength() > 0) {
            populateTileData(layerIndex, rows, columns, dataNodes.item(0).getTextContent().trim());
        }
    }

    /**
     * Populates the tile data for a specific layer.
     *
     * @param layerIndex The index of the current layer.
     * @param rows       The number of rows in the layer.
     * @param columns    The number of columns in the layer.
     * @param data       The comma-separated tile data as a string.
     */
    private void populateTileData(int layerIndex, int rows, int columns, String data) {
        String[] values = data.split(",");
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                try {
                    int tileIndex = x + y * columns;
                    tiles[layerIndex][y][x] = Integer.parseInt(values[tileIndex].trim());
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error parsing tile at layer " + layerIndex + ", row " + y + ", column " + x);
                    tiles[layerIndex][y][x] = 0; // Default to 0 if parsing fails
                }
            }
        }
    }

    /**
     * Determines if the given layer is designated as the selectedPlayer layer.
     *
     * @param layer The XML Element representing the layer.
     * @return True if the layer is the selectedPlayer layer, false otherwise.
     */
    private boolean isPlayerLayer(Element layer) {
        return "PLAYER".equals(layer.getAttribute("class"));
    }

    /**
     * Safely retrieves an integer attribute from the XML element, with a fallback value.
     *
     * @param element      The XML element.
     * @param attribute    The attribute name.
     * @param defaultValue The default value if the attribute is not found or invalid.
     * @return The integer value of the attribute or the default value.
     */
    private int getIntAttribute(Element element, String attribute, int defaultValue) {
        try {
            return Integer.parseInt(element.getAttribute(attribute));
        } catch (NumberFormatException e) {
            return defaultValue;
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

    /**
     * Retrieves the index of the selectedPlayer layer.
     *
     * @return The index of the selectedPlayer layer.
     */
    public int getPlayerLayer() {
        return playerLayer;
    }
}
