package Map.Movement;

import Map.Map;
import Game.Handler;
import Map.Tile.Tile;
import Worlds.WorldManager;

/**
 * The Camera class is responsible for controlling the view of the game world by
 * adjusting the camera's position based on the player's movement or specific events.
 */
public class Camera {

    /**
     * Current horizontal offset of the camera from the origin.
     */
    private float xOffset;

    /**
     * Current vertical offset of the camera from the origin.
     */
    private float yOffset;

    /**
     * Width of the screen in pixels.
     */
    private final int screenWidth;

    /**
     * Height of the screen in pixels.
     */
    private final int screenHeight;

    /**
     * Reference to the parsed world, used for world dimensions.
     */
    private final WorldManager worldManager;

    /**
     * Constructs a Camera object.
     *
     * @param movement The Movement object controlling the player's movement.
     * @param xOffset  Initial horizontal offset.
     * @param yOffset  Initial vertical offset.
     */
    public Camera(Movement movement, float xOffset, float yOffset) {
        Handler handler = movement.getHandler();
        this.worldManager = movement.getWorld();
        this.screenWidth = handler.getWidth();
        this.screenHeight = handler.getHeight();
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * Centers the camera on a specific entity, ensuring the entity is positioned
     * in the center of the screen if possible.
     *
     * @param e The Movement object representing the entity to center on.
     */
    public void centerOnEntity(Movement e) {
        xOffset = e.getX() - (float) screenWidth / 2 + (float) e.getWidth() / 2;
        yOffset = e.getY() - (float) screenHeight / 2 + (float) e.getHeight() / 2;
        checkBlankSpace();
    }

    /**
     * Ensures the camera does not display blank spaces outside the bounds of the world.
     * Adjusts offsets to keep the view within the game world's dimensions.
     */
    public void checkBlankSpace() {
        // Restrict horizontal offset
//        System.out.println(world.getWorldWidth() + " " + world.getWorldHeight());

        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > worldManager.getCurrentWorld().getWorld().getWorldWidth() * Tile.width - screenWidth) {
            xOffset = worldManager.getCurrentWorld().getWorld().getWorldWidth() * Tile.width - screenWidth;
        }

        // Restrict vertical offset
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > worldManager.getCurrentWorld().getWorld().getWorldHeight() * Tile.height - screenHeight) {
            yOffset = worldManager.getCurrentWorld().getWorld().getWorldHeight() * Tile.height - screenHeight;
        }
    }

    /**
     * Retrieves the current horizontal offset of the camera.
     *
     * @return The x-offset.
     */
    public float getXOffset() {
        return xOffset;
    }

    /**
     * Retrieves the current vertical offset of the camera.
     *
     * @return The y-offset.
     */
    public float getYOffset() {
        return yOffset;
    }
}
