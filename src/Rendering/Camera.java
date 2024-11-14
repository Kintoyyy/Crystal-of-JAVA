package Rendering;

import Entities.Characters.Movement.Movement;
import Game.Handler;
import World.ParseWorld;
import World.Tile;

public class Camera {

    private final Handler handler;
    private float xOffset, yOffset;
    private int width, height;

    public Camera(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void centerOnEntity(Movement e) {
        xOffset = e.getX() - (float) handler.getWidth() / 2 + (float) e.getWidth() / 2;
        yOffset = e.getY() - (float) handler.getHeight() / 2 + (float) e.getHeight() / 2;
        checkBlankSpace();
    }

    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
            xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        }

        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
            yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        }
    }

    public float getXOffset() {
        return xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }
}
