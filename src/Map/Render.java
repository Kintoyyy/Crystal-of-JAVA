package Map;

import Entities.Characters.Movement.Movement;
import Game.Handler;
import Utils.DebugMode;
import Map.Tile.Tile;
import Map.Tile.TileTypes;

import java.awt.*;

/**
 * The Render class is responsible for rendering the game world and handling
 * the graphical representation of tiles and player movement on the map.
 */
public class Render {

    /** Width of the world in tiles. */
    private final int width;

    /** Height of the world in tiles. */
    private final int height;

    /** 3D array representing the tile layers in the world. */
    private final int[][][] TileLayers;

    /** TileTypes instance for retrieving tile information. */
    private final TileTypes tileTypes;

    /** Movement object to handle character movement and camera. */
    private final Movement movement;

    /**
     * Constructs a Render object using parsed world data and a movement handler.
     *
     * @param parse    The Parse object containing world data such as layers and dimensions.
     * @param movement The Movement instance for managing character movement.
     */
    public Render(Parse parse, Movement movement) {
        this.movement = movement;
        movement.setSpawn(parse.getSpawnX(), parse.getSpawnY());
        this.width = parse.getWorldWidth();
        this.height = parse.getWorldHeight();
        this.TileLayers = parse.getLayers();
        this.tileTypes = parse.getTileTypes();
    }

    /**
     * Updates the state of the movement object.
     * Called on each game tick to update character and camera positions.
     */
    public void tick() {
        movement.tick();
    }

    /**
     * Renders the visible portion of the game world based on camera position.
     * Draws tiles and debug information if debug mode is enabled.
     *
     * @param g The Graphics object used for rendering.
     */
    public void render(Graphics g) {
        for (int layer = 0; layer < TileLayers.length; layer++) {
            Handler handler = movement.getHandler();

            int xStart = (int) Math.max(0, movement.getCamera().getXOffset() / Tile.width);
            int xEnd = (int) Math.min(width, (movement.getCamera().getXOffset() + handler.getWidth()) / Tile.width + 1);
            int yStart = (int) Math.max(0, movement.getCamera().getYOffset() / Tile.height);
            int yEnd = (int) Math.min(height, (movement.getCamera().getYOffset() + handler.getHeight()) / Tile.height + 1);

            for (int i = yStart; i < yEnd; i++) {
                for (int j = xStart; j < xEnd; j++) {
                    Tile tile = getTile(i, j, layer);
                    if (tile != null) {
                        int tilePosX = (int) (j * Tile.width - movement.getCamera().getXOffset());
                        int tilePosY = (int) (i * Tile.height - movement.getCamera().getYOffset());

                        tile.render(g, tilePosX, tilePosY);

                        if (DebugMode.debugMode()) {
                            if (layer == DebugMode.getRenderedLayerIndex()) {
                                g.drawRect(tilePosX, tilePosY, Tile.width, Tile.height);
                            }
                        }
                    }
                }
            }

            if (layer == TileLayers.length - 1) {
                movement.render(g);
            }
        }
    }

    /**
     * Retrieves the tile at the specified coordinates and layer.
     * Returns a default tile if the coordinates are out of bounds or the tile is null.
     *
     * @param x     The x-coordinate in tile space.
     * @param y     The y-coordinate in tile space.
     * @param layer The layer index to retrieve the tile from.
     * @return The Tile object at the specified coordinates and layer.
     */
    private Tile getTile(int x, int y, int layer) {
        if (x < 0 || y < 0 || x >= width || y >= height || layer < 0 || layer >= TileLayers.length) {
            return Tile.defaultTile;
        }
        Tile t = tileTypes.getTile(TileLayers[layer][x][y]);
        if (t == null) {
            return Tile.transparentTile;
        }
        return t;
    }
}
