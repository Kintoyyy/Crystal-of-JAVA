package Entities.Characters.Movement;

import Animations.PlayerAnimation;
import Entities.Characters.CharacterManager;
import Entities.Characters.Kent;
import Game.Handler;
import World.Camera;
import World.ParseWorld;
import World.Tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Constants.PlayerAnimation.*;

public class Movement {
    protected Handler handler;
    protected float x, y;
    protected int width, height;

    public static final float DEFAULT_SPEED = 4.0f;

    protected float speed;
    public static float xMove, yMove;
    public static float xPosition, yPosition;
    public static boolean collided = false;

    private PlayerAnimation animation;
    public static String dir = "down";

    protected Rectangle bounds; // Character bounds

    private ParseWorld world;
    private final Camera camera;
    private final CharacterManager characterManager;

    public Movement(Handler handler, ParseWorld world, CharacterManager characterManager) {
        this.world = world;
        this.handler = handler;

        camera = new Camera(this, 0, 0);

        this.characterManager = characterManager;

        this.x = 0;
        this.y = 0;
        this.width = 128;
        this.height = 128;

        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;

        bounds = new Rectangle(0, 0, width, height);

        animation = new Kent().getAnimation();
    }

    public Handler getHandler() {
        return handler;
    }

    public ParseWorld getWorld() {
        return world;
    }

    public void setSpawn(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick() {

        animation.tick();
        getInput();
        camera.centerOnEntity(this, world);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        boolean movingUp = handler.getKeyManager().up || handler.getKeyManager().Up;
        boolean movingDown = handler.getKeyManager().down || handler.getKeyManager().Down;
        boolean movingLeft = handler.getKeyManager().left || handler.getKeyManager().Left;
        boolean movingRight = handler.getKeyManager().right || handler.getKeyManager().Right;

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
        // Move the player based on the input
        move();
    }

    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - camera.getXOffset()),
                (int) (y - camera.getYOffset()), width, height, null);

        g.setColor(Color.red);
        g.drawRect((int) (x + bounds.x - camera.getXOffset()),
                (int) (y + bounds.y - camera.getYOffset()), bounds.width, bounds.height);
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

    public void move() {
        if (xMove != 0 && !checkEntityCollisions(xMove, 0f)) {
            moveX();
        }
        if (yMove != 0 && !checkEntityCollisions(0f, yMove)) {
            moveY();
        }
    }

    private void moveX() {
        collided = false;
        int tx = (int) (x + xMove + bounds.x + (xMove > 0 ? bounds.width : 0)) / Tile.TILEWIDTH;

        if (canMoveX(tx)) {
            x += xMove;
            xPosition += xMove;
        } else {
            x = xMove > 0
                    ? tx * Tile.TILEWIDTH + bounds.x - bounds.width - 1
                    : tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
        }
    }

    private void moveY() {
        collided = false;
        int ty = (int) (y + yMove + bounds.y + (yMove > 0 ? bounds.height : 0)) / Tile.TILEHEIGHT;

        if (canMoveY(ty)) {
            y += yMove;
            yPosition += yMove;
        } else {
            y = yMove > 0
                    ? ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1
                    : ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            collided = true;
        }
    }


    private boolean canMoveX(int tx) {
        return !collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT);
    }


    private boolean canMoveY(int ty) {
        return !collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty);
    }

    protected boolean collisionWithTile(int x, int y) {
//		if(handler.getWorld().getTile(x, y).isSolid()) {
//			collided = true;
//		}
//		return handler.getWorld().getTile(x, y).isSolid();
        return false;
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
//        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
//            if (e.equals(this)) {
//                continue;
//            }
//            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
////				Creature.collided = true;
//                return true;
//            }
//        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
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

    public int getHeight() {
        return height;
    }

    public Camera getCamera() {
        return camera;
    }
}
