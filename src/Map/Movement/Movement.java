package Map.Movement;

import Animations.Animation;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Entities.Characters.CharacterManager;
import Game.Handler;
import Inputs.InputKeyboardListener;
import Map.Map;
import Map.Tile.Tile;
import Worlds.WorldManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * The Movement class handles the movement mechanics of a character in the game.
 * It includes logic for handling input, managing animations, checking for collisions, and moving the character
 * based on user input or predefined conditions.
 * <p>
 * It supports both single-direction and diagonal movement, adjusting the character's speed accordingly. The class
 * also includes methods for rendering the character, updating the character's position, and checking for collisions
 * with entities and tiles in the game world.
 * </p>
 * <p>
 * The movement system is tied to the game handler, camera, world, and character manager for seamless integration
 * with other components like the camera for smooth scrolling and the character animations for movement visuals.
 * </p>
 *
 * <p>
 * This class supports the following movement directions:
 * - UP
 * - DOWN
 * - LEFT
 * - RIGHT
 * - Diagonal movement (combination of the directions above)
 * </p>
 *
 * @see Handler
 * @see Map
 * @see CharacterManager
 * @see Camera
 * @see Tile
 * @see Animation
 */
public class Movement {
    private final Handler handler; // The game handler for managing the game state
    private final int width, height; // Dimensions of the character
    private float x, y; // The current position of the character on the screen

    public static final float DEFAULT_SPEED = 4.0f; // Default movement speed

    protected float speed; // Speed at which the character moves
    private static float xMove, yMove; // Movement offsets for the character

    private Animation animation; // Animation object to handle character's movements

    private DIRECTION direction = DIRECTION.DOWN; // Current movement direction of the character

    private Rectangle bounds; // The bounding rectangle of the character used for collision detection

    private final Camera camera; // The camera for controlling the viewport
    private final CharacterManager characterManager; // Manages the character-related logic
    private final InputKeyboardListener keyboard; // Listens for keyboard input
    private final Collision collision;
    private final WorldManager worldManager;

    /**
     * Constructor for initializing the Movement class.
     *
     * @param handler          The game handler.
     * @param worldManager     The manager handling the game world.
     * @param characterManager The manager handling the character's logic.
     */
    public Movement(Handler handler, WorldManager worldManager, CharacterManager characterManager) {
        this.worldManager = worldManager;
        this.handler = handler;
        this.keyboard = handler.getKeyManager();
        this.collision = new Collision(handler, this);

        camera = new Camera(this, 0, 0);

        this.characterManager = characterManager;

        this.x = 0;
        this.y = 0;
        this.width = 128;
        this.height = 128;

        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;

        setLocation(worldManager.getCurrentWorld().getSpawnPoint());
    }

    /**
     * Updates the character's state, processing input and animations.
     */
    public void tick() {
        animation = characterManager.getPlayer().getAnimation();
        bounds = characterManager.getPlayer().getBounds();

        animation.tick();
        getInput();
        camera.centerOnEntity(this);
    }

    /**
     * Handles the player's input to determine movement direction and speed.
     * Adjusts for diagonal movement and sets the appropriate speed and direction.
     */
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

    /**
     * Renders the character on the screen, adjusting for camera position.
     *
     * @param g The Graphics object used for rendering.
     */
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - camera.getXOffset()),
                (int) (y - camera.getYOffset()), width, height, null);

        g.setColor(Color.red);
        g.drawRect((int) (x + bounds.x - camera.getXOffset()),
                (int) (y + bounds.y - camera.getYOffset()), bounds.width, bounds.height);
    }

    /**
     * Gets the current frame for the character's animation based on movement direction.
     *
     * @return The current animation frame.
     */
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

    /**
     * Moves the character based on input and handles collisions.
     */
    public void move() {
        if (xMove != 0 && collision.objectCollisions(xMove, 0f)) {
            moveX();
        }
        if (yMove != 0 && collision.objectCollisions(0f, yMove)) {
            moveY();
        }
    }

    private void moveX() {
        int tx = (int) (x + xMove + bounds.x + (xMove > 0 ? bounds.width : 0)) / Tile.width;

        if (canMoveX(tx)) {
            x += xMove;
        } else {
            x = xMove > 0
                    ? tx * Tile.width + bounds.x - bounds.width - 1
                    : tx * Tile.width + Tile.width - bounds.x;
        }
    }

    private void moveY() {
        int ty = (int) (y + yMove + bounds.y + (yMove > 0 ? bounds.height : 0)) / Tile.height;

        if (canMoveY(ty)) {
            y += yMove;
        } else {
            y = yMove > 0
                    ? ty * Tile.height - bounds.y - bounds.height - 1
                    : ty * Tile.height + Tile.height - bounds.y;
        }
    }

    private boolean canMoveX(int tx) {
        return collision.tileCollisions(tx, (int) (y + bounds.y) / Tile.height) &&
                collision.tileCollisions(tx, (int) (y + bounds.y + bounds.height) / Tile.height);
    }


    private boolean canMoveY(int ty) {
        return collision.tileCollisions((int) (x + bounds.x) / Tile.width, ty) &&
                collision.tileCollisions((int) (x + bounds.x + bounds.width) / Tile.width, ty);
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public Rectangle getBounds() {
        return bounds;
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

    public WorldManager getWorld() {
        return worldManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setLocation(Point spawnPoint) {
        x = spawnPoint.x;
        y = spawnPoint.y;
    }

    public Point getLocation() {
        return new Point((int) x, (int) y);
    }
}
