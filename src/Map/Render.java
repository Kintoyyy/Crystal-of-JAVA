package Map;

import Animations.AnimationManager;
import Map.Movement.Camera;
import Map.Movement.Movement;
import Game.Handler;
import Map.Object.Objects;
import Utils.DebugMode;
import Map.Tile.Tile;
import Map.Tile.TileTypes;

import java.awt.*;

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

    private final Camera camera;

    private Objects objects;

    private final int gameWidth;

    private final int gameHeight;

    private int playerLayer;

    private final Map map;

    private final AnimationManager animationManager;

//    Entity entity = new Orc();

    /**
     * Constructs a Render object using parsed world data and a movement handler.
     * <p>
     * //     * @param movement The Movement instance for managing character movement.
     */
    public Render(Handler handler, Movement movement, Map map) {
        this.movement = movement;
        this.camera = movement.getCamera();
        this.gameHeight = handler.getHeight();
        this.gameWidth = handler.getWidth();
        this.map = map;
        this.animationManager = map.getAnimationManager();
        loadWorld(map.getMap());
    }

    public void loadWorld(Parser parser) {

        if (DebugMode.isShowObjects()) {
            System.out.println("Loading world..." + parser);
            System.out.println("World width: " + parser.getWorldWidth());
            System.out.println("World height: " + parser.getWorldHeight());
            System.out.println("Tile layers: " + parser.getTileLayers().length);
        }

        this.width = parser.getWorldWidth();
        this.height = parser.getWorldHeight();
        this.TileLayers = parser.getTileLayers();
        this.tileTypes = parser.getTileTypes();
        this.objects = parser.getObjectGroup();

        this.playerLayer = parser.getPlayerLayer();

        movement.setLocation(parser.getSpawnPoint());
    }

    /**
     * Updates the state of the movement object.
     * Called on each game tick to update character and camera positions.
     */
    public void tick() {
        movement.tick();
        animationManager.tick();
    }

    /**
     * Renders the visible portion of the game world based on camera position.
     * Draws tiles and debug information if debug mode is enabled.
     *
     * @param g The Graphics object used for rendering.
     */
    public void render(Graphics g) {
        int xStart = (int) Math.max(0, camera.getXOffset() / Tile.WIDTH);
        int xEnd = (int) Math.min(width, (camera.getXOffset() + this.gameWidth) / Tile.WIDTH + 1);
        int yStart = (int) Math.max(0, camera.getYOffset() / Tile.HEIGHT);
        int yEnd = (int) Math.min(height, (camera.getYOffset() + this.gameHeight) / Tile.HEIGHT + 1);

        for (int layer = 0; layer < TileLayers.length; layer++) {
            for (int i = yStart; i < yEnd; i++) {
                for (int j = xStart; j < xEnd; j++) {
                    Tile tile = getTile(i, j, layer);

                    if (tile != null) {
                        int tilePosX = (int) (j * Tile.WIDTH - camera.getXOffset());
                        int tilePosY = (int) (i * Tile.HEIGHT - camera.getYOffset());

                        // Render the tile
                        tile.render(g, tilePosX, tilePosY);

                        // Debugging visualization (optional)
                        if (DebugMode.debugMode() && layer == DebugMode.getRenderedLayerIndex()) {
                            g.setColor(Color.RED);
                            g.drawRect(tilePosX, tilePosY, Tile.WIDTH, Tile.HEIGHT);
                        }
                    }
                }
            }
            // render player
            if (layer == this.playerLayer) {
                map.getCurrentWorld().renderEntities(g, camera.getXOffset(), camera.getYOffset(), movement);
            }
        }


        // Render object groups
        if (DebugMode.isShowObjects()) {
            objects.render(g, (int) camera.getXOffset(), (int) camera.getYOffset());
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
        Tile t = tileTypes.getTile(TileLayers[layer][x][y]);
        if (t == null) {
            return Tile.TRANSPARENT_TILE;
        }
        return t;
    }
}
