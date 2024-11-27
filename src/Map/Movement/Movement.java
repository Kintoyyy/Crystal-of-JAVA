package Map.Movement;

import Animations.Animation;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Entities.Characters.CharacterManager;
import Game.Handler;
import Inputs.InputKeyboardListener;
import Map.Tile.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Movement {
    private final int width, height; // Dimensions of the character
    private float x, y; // The current position of the character on the screen
    protected float speed; // Speed at which the character moves
    private static float xMove, yMove; // Movement offsets for the character
    private Animation animation; // Animation object to handle character's movements
    private DIRECTION direction = DIRECTION.DOWN; // Current movement direction of the character
    private Rectangle bounds; // The bounding rectangle of the character used for collision detection
    private final Camera camera; // The camera for controlling the viewport
    private final CharacterManager characterManager; // Manages the character-related logic
    private final InputKeyboardListener keyboard; // Listens for keyboard input
    private final Collision collision;
    private float renderY; // For entity layering

    public Movement(Handler handler, Collision collision, Camera camera) {
        handler.setMovement(this);
        this.keyboard = handler.getKeyManager();
        this.characterManager = handler.getCharacterManager();
        this.collision = collision;
        this.camera = camera;
        this.x = 0;
        this.y = 0;
        this.width = 128;
        this.height = 128;
        xMove = 0;
        yMove = 0;
    }

    public void tick() {
        animation = characterManager.getPlayer().getAnimation();
        bounds = characterManager.getPlayer().getBounds();
        speed = characterManager.getPlayer().getSpeed();
        camera.centerOnEntity(this);
        animation.tick();
        getInput();
        updateRenderY();
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

        move();
    }

    private void updateRenderY() {
        renderY = y + bounds.y + bounds.height;
    }

    public Point getPlayerScreenLocation() {
        return new Point((int) (x - camera.getXOffset()), (int) (y - camera.getYOffset()));
    }

    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - camera.getXOffset()), (int) (y - camera.getYOffset()), width, height, null);
        g.setColor(Color.red);
        g.drawRect((int) (x + bounds.x - camera.getXOffset()), (int) (y + bounds.y - camera.getYOffset()), bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0 && yMove < 0) return animation.getFrame(TYPE.WALK, DIRECTION.LEFT);
        else if (xMove > 0 && yMove < 0) return animation.getFrame(TYPE.WALK, DIRECTION.RIGHT);
        else if (xMove < 0 && yMove > 0) return animation.getFrame(TYPE.WALK, DIRECTION.LEFT);
        else if (xMove > 0 && yMove > 0) return animation.getFrame(TYPE.WALK, DIRECTION.RIGHT);

        if (xMove < 0) return animation.getFrame(TYPE.WALK, DIRECTION.LEFT);
        else if (xMove > 0) return animation.getFrame(TYPE.WALK, DIRECTION.RIGHT);
        else if (yMove < 0) return animation.getFrame(TYPE.WALK, DIRECTION.UP);
        else if (yMove > 0) return animation.getFrame(TYPE.WALK, DIRECTION.DOWN);
        else return animation.getFrame(TYPE.IDLE, direction);
    }

    public void move() {
        if (xMove != 0 && collision.objectCollisions(xMove, 0f, this)) moveX();
        if (yMove != 0 && collision.objectCollisions(0f, yMove, this)) moveY();
    }

    private void moveX() {
        int tx = (int) (x + xMove + bounds.x + (xMove > 0 ? bounds.width : 0)) / Tile.WIDTH;
        if (collision.canMoveX(tx, (int) (y + bounds.y) / Tile.HEIGHT, (int) (y + bounds.y + bounds.height) / Tile.HEIGHT)) {
            x += xMove;
        }
    }

    private void moveY() {
        int ty = (int) (y + yMove + bounds.y + (yMove > 0 ? bounds.height : 0)) / Tile.HEIGHT;
        if (collision.canMoveY(ty, (int) (x + bounds.x) / Tile.WIDTH, (int) (x + bounds.x + bounds.width) / Tile.WIDTH)) {
            y += yMove;
        }
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

    public void setLocation(Point spawnPoint) {
        x = spawnPoint.x;
        y = spawnPoint.y;
    }

    public Camera getCamera(){
        return camera;
    }
    
    // Get render Y position for entity layering
    public float getRenderY() {
        return renderY;
    }
}
