package CharacterMovement;

import Game.Handler;

import World.Tile;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 4.0f;
    public static final int PLAYER_WIDTH = 128;
    public static final int PLAYER_HEIGHT = 128;

    protected float speed;
    public static float xMove, yMove;
    public static float xPosition, yPosition;
    public static boolean collided = false;

    public Creature(Handler handler,int width, int height) {
        super(handler, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
//		System.out.println("xPosition: " + xPosition + " yPosition: " + yPosition);
//		System.out.println("xMove: " + xMove + " yMove: " + yMove);

        if (xMove != 0 && !checkEntityCollisions(xMove, 0f)) {
            moveX();
        }
        if (yMove != 0 && !checkEntityCollisions(0f, yMove)) {
            moveY();
        }

        System.out.println("x: " + x + " y: " + y);
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
}
