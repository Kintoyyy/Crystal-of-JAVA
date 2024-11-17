package Entities.Characters.Movement;

import Animations.Animation;
import Animations.Entities.CharacterAnimation;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Entities.Characters.CharacterManager;
import Game.Handler;
import Inputs.InputKeyboardListener;
import World.Camera;
import World.ParseWorld;
import World.Tile.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Movement {
    protected Handler handler;
    protected float x, y;
    protected int width, height;

    public static final float DEFAULT_SPEED = 4.0f;

    protected float speed;
    public static float xMove, yMove;
    public static float xPosition, yPosition;
    public static boolean collided = false;

    private Animation animation;

    private DIRECTION direction = DIRECTION.DOWN;

    protected Rectangle bounds; // Character bounds

    private ParseWorld world;
    private final Camera camera;
    private final CharacterManager characterManager;
    private final InputKeyboardListener keyboard;

    public Movement(Handler handler, ParseWorld world, CharacterManager characterManager) {
        this.world = world;
        this.handler = handler;
        this.keyboard = handler.getKeyManager();

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

        animation = characterManager.getPlayer().getAnimation();
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
        animation = characterManager.getPlayer().getAnimation();
        animation.tick();
        getInput();
        camera.centerOnEntity(this, world);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        boolean movingUp = keyboard.isKeyPressed("w").ignoreCaps() || keyboard.isKeyPressed(KeyEvent.VK_UP).exactMatch();
        boolean movingDown = keyboard.isKeyPressed("s").ignoreCaps() || keyboard.isKeyPressed(KeyEvent.VK_DOWN).exactMatch();
        boolean movingLeft = keyboard.isKeyPressed("a").ignoreCaps() || keyboard.isKeyPressed(KeyEvent.VK_LEFT).exactMatch();
        boolean movingRight = keyboard.isKeyPressed("d").ignoreCaps() || keyboard.isKeyPressed(KeyEvent.VK_RIGHT).exactMatch();

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
            direction = DIRECTION.RIGHT;
        } else if (xMove < 0) {
            direction = DIRECTION.LEFT;
        } else if (yMove < 0) {
            direction = DIRECTION.UP;
        } else if (yMove > 0) {
            direction = DIRECTION.DOWN;
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
            return animation.getFrame(TYPE.WALK, DIRECTION.LEFT);
        } else if (xMove > 0 && yMove < 0) {
            return animation.getFrame(TYPE.WALK, DIRECTION.RIGHT);
        } else if (xMove < 0 && yMove > 0) {
            return animation.getFrame(TYPE.WALK, DIRECTION.LEFT);
        } else if (xMove > 0 && yMove > 0) {
            return animation.getFrame(TYPE.WALK, DIRECTION.RIGHT);
        }

        // Single direction movement frames
        if (xMove < 0) {
            return animation.getFrame(TYPE.WALK, DIRECTION.LEFT);
        } else if (xMove > 0) {
            return animation.getFrame(TYPE.WALK, DIRECTION.RIGHT);
        } else if (yMove < 0) {
            return animation.getFrame(TYPE.WALK, DIRECTION.UP);
        } else if (yMove > 0) {
            return animation.getFrame(TYPE.WALK, DIRECTION.DOWN);
        } else {
            return animation.getFrame(TYPE.IDLE, direction);
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
        return collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT);
    }


    private boolean canMoveY(int ty) {
        return collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty);
    }

    protected boolean collisionWithTile(int x, int y) {
//		if(handler.getWorld().getTile(x, y).isSolid()) {
//			collided = true;
//		}
//		return handler.getWorld().getTile(x, y).isSolid();
        return true;
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
