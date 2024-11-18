package Map.Movement;

import Game.Handler;
import Map.Object.Object;
import Map.Object.Type;
import Worlds.Enums.WorldNames;
import Views.enums.Views;

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

            if (object.getType() == Type.TELEPORT) {
                handler.getWorldManager().setCurrentWorld(WorldNames.valueOf(object.getName()));
            }

//            if (object.getType() == Type.BATTLE) {
//                handler.getViewManager().setView(Views.PAUSE);
////                handler.getWorldManager().getCurrentWorld().);
////                handler.getWorldManager().setCurrentWorld(WorldNames.valueOf(object.getName()));
//            }

            System.out.println("Collision with object: " + object.getName() + " type: " + object.getType());
        }
        return true; // No collision detected
    }
}
