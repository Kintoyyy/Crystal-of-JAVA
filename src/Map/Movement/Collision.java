package Map.Movement;

import Game.Handler;
import Map.Object.Object;
import Map.Object.ClassType;

public class Collision {
    private final Movement movement;
    private final Handler handler;

    public Collision(Handler handler, Movement movement) {
        this.movement = movement;
        this.handler = handler;
    }

    /**
     * Checks for collisions with solid tiles at the specified position.
     *
     * @param x Tile x-coordinate
     * @param y Tile y-coordinate
     * @return True if no collision, false if collision detected
     */
    public boolean tileCollisions(int x, int y) {
        // Tile collision logic placeholder. Defaulting to true for now.
        return true;
    }

    /**
     * Checks for collisions with objects based on the movement offsets.
     *
     * @param xOffset Horizontal offset for the collision check
     * @param yOffset Vertical offset for the collision check
     * @return False if a collision with a ClassType. COLLISION object is detected, true otherwise
     */
    public boolean objectCollisions(float xOffset, float yOffset) {
        // Precompute the collision bounds for the moving entity
        var collisionBounds = movement.getCollisionBounds(xOffset, yOffset);
        // Loop through objects and check for collisions
        for (Object object : movement.getWorld().getCurrentWorld().getObjects()) {

            // Skip if the object doesn't intersect with the collision bounds
            if (!object.getBounds().intersects(collisionBounds)) {
                continue;
            }

            // Handle specific collision types
            if (object.getTriggerType() == ClassType.COLLISION) {
                return false; // Collision detected
            }

            if (object.getTriggerType() == ClassType.TELEPORT) {
                handler.getWorldManager().changeWorld(object.getName());
            }

            if (object.getTriggerType() == ClassType.BATTLE) {
                handler.getBattleManager().startBattle(object.getName());
            }

            System.out.println("Collision with object: " + object.getName() + " triggerType: " + object.getTriggerType());
        }
        return true; // No collision detected
    }
}
