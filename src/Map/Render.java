package Map;

import Map.Movement.Movement;
import Game.Handler;
import Map.Object.ClassType;
import Map.Object.Object;
import Map.Object.ObjectGroup;
import Utils.DebugMode;
import Map.Tile.Tile;
import Map.Tile.TileTypes;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Render class is responsible for rendering the game world and handling
 * the graphical representation of tiles and player movement on the map.
 */
public class Render {

    /**
     * Width of the world in tiles.
     */
    private int width;

    /**
     * Height of the world in tiles.
     */
    private int height;

    /**
     * 3D array representing the tile layers in the world.
     */
    private int[][][] TileLayers;

    /**
     * TileTypes instance for retrieving tile information.
     */
    private TileTypes tileTypes;

    /**
     * Movement object to handle character movement and camera.
     */
    private final Movement movement;

    private ObjectGroup objectGroup;

    private Map map;

    /**
     * Constructs a Render object using parsed world data and a movement handler.
     *
     * @param movement The Movement instance for managing character movement.
     */
    public Render(Movement movement) {
        this.movement = movement;
        map = movement.getWorld().getCurrentWorld().getWorld();

        // Set the initial location of the character to the spawn point of the map
//        movement.setLocation(map.getSpawnPoint());

        loadWorld();
    }

    public void loadWorld() {
        map = movement.getWorld().getCurrentWorld().getWorld();

        System.out.println("Loading world...");
        System.out.println("World width: " + map.getWorldWidth());
        System.out.println("World height: " + map.getWorldHeight());
        System.out.println("Tile layers: " + map.getLayers().length);

        this.width = map.getWorldWidth();
        this.height = map.getWorldHeight();
        this.TileLayers = map.getLayers();
        this.tileTypes = map.getTileTypes();
        this.objectGroup = map.getObjects();

//        movement.setLocation(map.getSpawnPoint());
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
        Handler handler = movement.getHandler();

        int xStart = (int) Math.max(0, movement.getCamera().getXOffset() / Tile.width);
        int xEnd = (int) Math.min(width, (movement.getCamera().getXOffset() + handler.getWidth()) / Tile.width + 1);
        int yStart = (int) Math.max(0, movement.getCamera().getYOffset() / Tile.height);
        int yEnd = (int) Math.min(height, (movement.getCamera().getYOffset() + handler.getHeight()) / Tile.height + 1);

        boolean playerRendered = false;
        int playerRenderY = (int) (movement.getY() - movement.getCamera().getYOffset() + 70); // Calculate player Y offset

        for (int layer = 0; layer < TileLayers.length; layer++) {
            for (int i = yStart; i < yEnd; i++) {
                for (int j = xStart; j < xEnd; j++) {
                    Tile tile = getTile(i, j, layer);

                    if (tile != null) {
                        int tilePosX = (int) (j * Tile.width - movement.getCamera().getXOffset());
                        int tilePosY = (int) (i * Tile.height - movement.getCamera().getYOffset());

//                        // Render the player before tiles below its Y position
//                        if (!playerRendered && tilePosY >= playerRenderY) {
//                            movement.render(g); // Render player
//                            playerRendered = true;
//
//                            // Debugging: Mark where the player is rendered
//                            g.setColor(Color.RED);
//                            g.drawString("F", tilePosX, tilePosY);
//                        }

                        // Render the tile
                        tile.render(g, tilePosX, tilePosY);

                        // Debugging visualization (optional)
                        if (DebugMode.debugMode() && layer == DebugMode.getRenderedLayerIndex()) {
                            g.setColor(Color.RED);
                            g.drawRect(tilePosX, tilePosY, Tile.width, Tile.height);
                        }
                    }
                }
            }

            // Render player if not yet rendered and this is the last layer
//            if (!playerRendered && layer == TileLayers.length - 1) {
//                movement.render(g);
//                playerRendered = true;
//            }
        }


        movement.render(g);

//        // Debugging line (optional, for visualization)
//        g.setColor(Color.GREEN);
//        g.drawLine(400, playerRenderY, 600, playerRenderY);

        // Render object groups
        objectGroup.render(g, (int) movement.getCamera().getXOffset(), (int) movement.getCamera().getYOffset());
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
//        if (x < 0 || y < 0 || x >= map.getWorldWidth() || y >= map.getWorldHeight() || layer < 0 || layer >= TileLayers.length) {
//            return Tile.defaultTile;
//        }

        Tile t = tileTypes.getTile(TileLayers[layer][x][y]);
        if (t == null) {
            return Tile.transparentTile;
        }
        return t;
    }
}
