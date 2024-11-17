package Map.Movement;

import Game.Handler;
import Map.Object.Object;
import Map.Object.Type;

public class Collision {
    private final Handler handler;
    private final Movement movement;

    public Collision(Handler handler, Movement movement) {
        this.handler = handler;
        this.movement = movement;
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
     * @return False if a collision with a Type. COLLISION object is detected, true otherwise
     */
    public boolean objectCollisions(float xOffset, float yOffset) {
        // Precompute the collision bounds for the moving entity
        var collisionBounds = movement.getCollisionBounds(xOffset, yOffset);

        // Loop through objects and check for collisions
        for (Object object : movement.getWorld().getObjectGroup().getObjects()) {
            // Skip if the object doesn't intersect with the collision bounds
            if (!object.getBounds().intersects(collisionBounds)) {
                continue;
            }

            // Handle specific collision types
            if (object.getType() == Type.COLLISION) {
                return false; // Collision detected
            }

            System.out.println("Collision with object: " + object.getName());
        }
        return true; // No collision detected
    }
}
