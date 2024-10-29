package Assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Game;
import Game.Handler;
import Utils.DebugMode;
import World.World;
import ImageStuff.PlayerAnimation;
import States.BattleState;
import World.Tile;

import static Constants.PlayerAnimation.*;

public class Player extends Creature {

    private PlayerAnimation animation;
    public static String dir = "down";
    public static float xPosition;
    public static float yPosition;
    public static int health;
    public static int baseHealth;
    public static int level;
    public static String name;

    private static int SCALE = 2;

    private boolean isSpeedBoost;

    private boolean flag;
    private boolean flag2;
    private boolean flag3;
    private int a;
    private int b;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.PLAYER_WIDTH, Creature.PLAYER_HEIGHT);

        bounds.x = 40;
        bounds.height = 24;
        bounds.y = 64;
        bounds.width = 40;

        health = 100;
        baseHealth = 100;
        level = 1;
        name = "Hero";

        animation = new PlayerAnimation(120, Assets.player_animation);
    }

    @Override
    public void tick() {
        animation.tick();
        getInput();
        move();
        checkEncounter();

        handler.getGameCamera().centerOnEntity(this);
    }

    // TODO: MOVE TO A SEPARATE CLASS
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

        if (handler.getMouseManager().isRightPressed() && !isSpeedBoost) {
            isSpeedBoost = true;
            this.speed = this.speed == 16.0f ? 4.0f : this.speed;
        } else if (!handler.getMouseManager().isRightPressed() && isSpeedBoost) {
            isSpeedBoost = false;
        }

        if (debounceKeyPress(handler.getKeymanager().f3)) {
            DebugMode.SetDebugMode(!DebugMode.debugMode());
        }

        if (debounceKeyPress(handler.getKeymanager().f12)) {
            DebugMode.setRenderedLayerIndex(DebugMode.getRenderedLayerIndex() + 1);
        }

        if (debounceKeyPress(handler.getKeymanager().f11)) {
            DebugMode.setRenderedLayerIndex(DebugMode.getRenderedLayerIndex() - 1);
        }

        boolean movingUp = handler.getKeymanager().up || handler.getKeymanager().Up;
        boolean movingDown = handler.getKeymanager().down || handler.getKeymanager().Down;
        boolean movingLeft = handler.getKeymanager().left || handler.getKeymanager().Left;
        boolean movingRight = handler.getKeymanager().right || handler.getKeymanager().Right;

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


    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width * SCALE, height * SCALE, null);

        if (DebugMode.debugMode()) {
            g.setColor(Color.red);
            g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                    (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
        }

    }


    private void checkEncounter() {
        World w = handler.getWorld();
//        System.out.println((w.getTile(w.getSpawnX() + ((int) Creature.xPosition) / 64,
//                w.getSpawnY() + ((int) Creature.yPosition) / 64).getId() == 10));
        Tile tile = w.getTile(w.getSpawnX() + ((int) Creature.xPosition) / 64, w.getSpawnY() + ((int) Creature.yPosition) / 64);
        if(tile != null){
            if ((tile.getId() == 10) && !BattleState.encounterFlag
                    && Math.random() >= 0.99) {
                a = w.getSpawnX() + ((int) Creature.xPosition) / 64;
                b = w.getSpawnY() + ((int) Creature.yPosition) / 64;
                System.out.println("a:" + a + "b:" + b);
                if (!flag3) {
                    flag3 = true;
                    Game.flag = true;
                }

            } else if (BattleState.encounterFlag) {
                if (a != w.getSpawnX() + ((int) Creature.xPosition) / 64
                        || b != w.getSpawnY() + ((int) Creature.yPosition) / 64) {
                    BattleState.encounterFlag = false;
                    flag3 = false;
                }
            }
        }

    }
}
