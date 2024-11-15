package Rendering;

import Entities.Characters.Movement.Movement;
import Game.Handler;
import World.ParseWorld;
import World.Tile;

public class Camera {

    private float xOffset, yOffset;
    private final int screenWidth, screenHeight;
    private final ParseWorld world;

    public Camera(Movement movement, float xOffset, float yOffset) {
        Handler handler = movement.getHandler();
        this.world = movement.getWorld();
        this.screenWidth = handler.getWidth();
        this.screenHeight = handler.getHeight();
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void centerOnEntity(Movement e, ParseWorld world) {
        xOffset = e.getX() - (float) screenWidth / 2 + (float) e.getWidth() / 2;
        yOffset = e.getY() - (float) screenHeight / 2 + (float) e.getHeight() / 2;
        checkBlankSpace();
    }

    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > world.getWorldWidth() * Tile.TILEWIDTH - screenWidth) {
            xOffset = world.getWorldWidth() * Tile.TILEWIDTH - screenWidth;
        }

        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > world.getWorldHeight() * Tile.TILEHEIGHT - screenHeight) {
            yOffset = world.getWorldHeight() * Tile.TILEHEIGHT - screenHeight;
        }
    }

    public float getXOffset() {
        return xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }
}
