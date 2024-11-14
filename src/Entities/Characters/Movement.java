package Entities.Characters;

import Animations.PlayerAnimation;
import Game.GameCamera;
import Inputs.InputKeyboardManager;
import Utils.DebugMode;
import World.Tile;


import java.awt.*;
import java.awt.image.BufferedImage;

import static Constants.PlayerAnimation.*;

public class Movement {

    public static final float DEFAULT_SPEED = 4.0f;
    private static int SCALE = 2;

    protected float speed;
    public static float xMove, yMove;
    public static float xPosition, yPosition;
    public static boolean collided = false;

    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected PlayerAnimation animation;

    private static String dir = "down";

    private GameCamera camera;
    private InputKeyboardManager keyManager;

    private Character character;

    public Movement(Character character) {
        this.character = character;
        this.x = character.getX();
        this.y = character.getY();
        this.width = character.getWidth();
        this.height = character.getHeight();
        this.bounds = character.getBounds();
        this.animation = character.getAnimation();

        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    private long lastKeyPress = 0;
    private static final long DEBOUNCE_TIME = 300; // milliseconds

    private boolean debounceKeyPress(boolean isKeyPressed) {
        long currentTime = System.currentTimeMillis();
        if (isKeyPressed) {
            if (currentTime - lastKeyPress > DEBOUNCE_TIME) {
                lastKeyPress = currentTime; // Update last press time
                return true; // Key press is valid and should trigger action
            }
        }
        return false; // Key press is ignored due to debounce
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (debounceKeyPress(keyManager.f3)) {
            DebugMode.SetDebugMode(!DebugMode.debugMode());
        }

        if (debounceKeyPress(keyManager.f9)) {
            System.out.println("GAME VIEW");
//            handler.getViewManager().setView(ViewEnums.GAME);
        }

        if (debounceKeyPress(keyManager.f10)) {
            System.out.println("MENU VIEW");
//            handler.getViewManager().setView(ViewEnums.MAIN_MENU);
        }

        if (debounceKeyPress(keyManager.f12)) {
            DebugMode.setRenderedLayerIndex(DebugMode.getRenderedLayerIndex() + 1);
        }

        if (debounceKeyPress(keyManager.f11)) {
            DebugMode.setRenderedLayerIndex(DebugMode.getRenderedLayerIndex() - 1);
        }

        boolean movingUp = keyManager.up || keyManager.Up;
        boolean movingDown = keyManager.down || keyManager.Down;
        boolean movingLeft = keyManager.left || keyManager.Left;
        boolean movingRight = keyManager.right || keyManager.Right;

        if ((movingUp || movingDown) && (movingLeft || movingRight)) {
            float diagonalSpeed = speed * 0.7071f;
            if (movingUp) yMove = -diagonalSpeed;
            if (movingDown) yMove = diagonalSpeed;
            if (movingLeft) xMove = -diagonalSpeed;
            if (movingRight) xMove = diagonalSpeed;
        } else {
            if (movingUp) yMove = -speed;
            if (movingDown) yMove = speed;
            if (movingLeft) xMove = -speed;
            if (movingRight) xMove = speed;
        }

        if (xMove > 0) {
            dir = RIGHT;
        } else if (xMove < 0) {
            dir = LEFT;
        } else if (yMove < 0) {
            dir = UP;
        } else if (yMove > 0) {
            dir = DOWN;
        }
    }


    private BufferedImage getCurrentAnimationFrame() {
        // Diagonal movement frames are handled first
        if (xMove < 0 && yMove < 0) {
            return animation.getCurrentFrame(LEFT, RUNNING);
        } else if (xMove > 0 && yMove < 0) {
            return animation.getCurrentFrame(RIGHT, RUNNING);
        } else if (xMove < 0 && yMove > 0) {
            return animation.getCurrentFrame(LEFT, RUNNING);
        } else if (xMove > 0 && yMove > 0) {
            return animation.getCurrentFrame(RIGHT, RUNNING);
        }

        // Single direction movement frames
        if (xMove < 0) {
            return animation.getCurrentFrame(LEFT, RUNNING);
        } else if (xMove > 0) {
            return animation.getCurrentFrame(RIGHT, RUNNING);
        } else if (yMove < 0) {
            return animation.getCurrentFrame(UP, RUNNING);
        } else if (yMove > 0) {
            return animation.getCurrentFrame(DOWN, RUNNING);
        } else {
            return animation.getCurrentFrame(dir, IDLE);
        }
    }


    public boolean checkEntityCollisions(float xOffset, float yOffset) {
//        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
//            if(e.equals(this)) {
//                continue;
//            }
//            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
//                Creature.collided = true;
//                return true;
//            }
//        }
        return false;
    }

    protected boolean collisionWithTile(int x, int y) {
//		if(handler.getWorld().getTile(x, y).isSolid()) {
//			collided = true;
//		}
//		return handler.getWorld().getTile(x, y).isSolid();
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public void tick() {

        getInput();
        animation.tick();
        camera.centerOn(this);

        if (xMove != 0 && !checkEntityCollisions(xMove, 0f)) {
            moveX();
        }
        if (yMove != 0 && !checkEntityCollisions(0f, yMove)) {
            moveY();
        }
    }

    public void moveX() {
        if (xMove > 0) {
            collided = false;
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
                xPosition += xMove;
                //System.out.println("x added to xMove:" + x + " xPosition=" + xPosition);
            } else {
                x = tx * Tile.TILEWIDTH + bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) {
            collided = false;
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
                xPosition += xMove;
                //System.out.println("x added to xMove:" + x + " xPosition=" + xPosition);
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if (yMove < 0) {
            collided = false;
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
                yPosition += yMove;
                //System.out.println("y added to yMove:" + y + " yPosition=" + yPosition);
            } else {
                collided = true;
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        } else if (yMove > 0) {
            collided = false;
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
                yPosition += yMove;
                //System.out.println("y added to yMove:" + y + " yPosition=" + yPosition);
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                collided = true;
            }

        }
    }

    public void render(Graphics g) {
//        System.out.println("Rendering : " + character.getName());
//        System.out.println("Camera: " + camera + " KeyManager: " + keyManager);

//        System.out.println("X: " + (x - camera.getxOffset()) + " Y: " + (y - camera.getyOffset()) + " Width: " + width + " Height: " + height);

        g.setColor(Color.red);
        g.drawRect((int) (x - camera.getxOffset()), (int) (y - camera.getyOffset()), width * SCALE, height * SCALE);

//        g.drawImage(getCurrentAnimationFrame(), (int) (x - camera.getxOffset()),
//                (int) (y - camera.getyOffset()), width * SCALE, height * SCALE, null);
    }

    public void setControllers(GameCamera camera, InputKeyboardManager keyManager) {
        this.camera = camera;
        this.keyManager = keyManager;
//        System.out.println("Camera: " + camera + " KeyManager: " + keyManager);
    }

    public GameCamera getCamera() {
        return camera;
    }

    public InputKeyboardManager getKeyManager() {
        return keyManager;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public Object getHeight() {
        return height;
    }
}
