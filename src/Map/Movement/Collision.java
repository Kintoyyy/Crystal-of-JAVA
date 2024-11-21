package Map.Movement;

import Battle.BattleManager;
import Game.Handler;
import Map.Object.Object;
import Map.Object.CLASS;
import Worlds.Battle;
import Worlds.WorldManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Collision {
    private final WorldManager worldManager;
    private final BattleManager battleManager;

    public Collision(Handler handler) {
        this.worldManager = handler.getWorldManager();
        this.battleManager = handler.getBattleManager();
    }

    /**
     * Checks for collisions with objects based on the movement offsets.
     *
     * @param xOffset Horizontal offset for the collision check
     * @param yOffset Vertical offset for the collision check
     * @return False if a collision with a CLASS. COLLISION object is detected, true otherwise
     */
    public boolean objectCollisions(float xOffset, float yOffset, Movement movement) {
        // Precompute the collision bounds for the moving entity
        var collisionBounds = movement.getCollisionBounds(xOffset, yOffset);

        // Loop through objects and check for collisions
        for (Object object : worldManager.getCurrentWorld().getObjectGroup().getObjects()) {

            // Check polygon-rectangle collision using Separating Axis Theorem (SAT)
            if (!object.getBounds().intersects(collisionBounds)) continue;

            if (object.getClassType() == CLASS.COLLISION) return false;

            switch (object.getClassType()) {
                case TELEPORT -> {
                    System.out.println("Changing world to " + object.getKey());
                    worldManager.changeWorld(object.getKey());
                }
                case INTERACT -> {
//sTODO: Should have an interact ui Manager, only temporary ui elements
//                gameState.getViewManager().getCurrentView();
                    System.out.println("Interaction ");
                }
                case BATTLE -> {
                    Battle battle = worldManager.getBattle(object.getKey());
                    battleManager.startBattle(battle);
                }
                default -> {
                    System.out.println("Collision with object: " + object.getKey() + " Type: " + object.getClassType());
                }
            }
        }
        return true; // No collision detected
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

    public boolean canMoveX(int tx, int ty1, int ty2) {
        return tileCollisions(tx, ty1) && tileCollisions(tx, ty2);
    }

    public boolean canMoveY(int ty, int tx1, int tx2) {
        return tileCollisions(tx1, ty) && tileCollisions(tx2, ty);
    }

    /**
     * Checks if a polygon collides with a rectangle using the Separating Axis Theorem (SAT).
     *
     * @param polygon    The polygon object
     * @param rectBounds The bounding box of the moving object (player)
     * @return True if no collision, false if collision detected
     */
    private boolean polygonCollidesWithRect(Polygon polygon, Rectangle rectBounds) {
        // Create a list of axes to test (edges of the rectangle and polygon)
        List<Vector2D> axes = new ArrayList<>();

        // Add the rectangle's edges as axes (normals)
        axes.add(new Vector2D(1, 0));  // Right direction (horizontal edge)
        axes.add(new Vector2D(0, 1));  // Down direction (vertical edge)

        // Add the polygon's edges as axes (normals)
        for (int i = 0; i < polygon.npoints; i++) {
            int j = (i + 1) % polygon.npoints;  // Wrap around to create edges
            Vector2D edge = new Vector2D(polygon.xpoints[j] - polygon.xpoints[i], polygon.ypoints[j] - polygon.ypoints[i]);
            axes.add(edge.perpendicular()); // Perpendicular vector (axis) of the edge
        }

        // Test for overlaps along each axis
        for (Vector2D axis : axes) {
            Projection rectProjection = projectRectangle(rectBounds, axis);
            Projection polyProjection = projectPolygon(polygon, axis);

            if (!rectProjection.overlaps(polyProjection)) {
                return false; // No collision if projections do not overlap
            }
        }

        return true; // Collision detected if projections overlap on all axes
    }

    // Helper class to represent a 2D vector
    public static class Vector2D {
        public float x, y;

        public Vector2D(float x, float y) {
            this.x = x;
            this.y = y;
        }

        // Perpendicular vector (normal)
        public Vector2D perpendicular() {
            return new Vector2D(-y, x); // 90 degree rotation
        }

        // Dot product
        public float dot(Vector2D other) {
            return x * other.x + y * other.y;
        }
    }

    // Helper class for projection
    public static class Projection {
        public float min, max;

        // Constructor to calculate min and max projections
        public Projection(float min, float max) {
            this.min = min;
            this.max = max;
        }

        // Check if two projections overlap
        public boolean overlaps(Projection other) {
            return max > other.min && min < other.max;
        }
    }

    // Project the rectangle onto the given axis
    private Projection projectRectangle(Rectangle rect, Vector2D axis) {
        float[] vertices = {
                rect.x, rect.y,  // top-left corner
                rect.x + rect.width, rect.y,  // top-right corner
                rect.x + rect.width, rect.y + rect.height,  // bottom-right corner
                rect.x, rect.y + rect.height  // bottom-left corner
        };

        float min = axis.dot(new Vector2D(vertices[0], vertices[1]));
        float max = min;

        for (int i = 2; i < vertices.length; i += 2) {
            float projection = axis.dot(new Vector2D(vertices[i], vertices[i + 1]));
            min = Math.min(min, projection);
            max = Math.max(max, projection);
        }

        return new Projection(min, max);
    }

    // Project the polygon onto the given axis
    private Projection projectPolygon(Polygon polygon, Vector2D axis) {
        float min = axis.dot(new Vector2D(polygon.xpoints[0], polygon.ypoints[0]));
        float max = min;

        for (int i = 1; i < polygon.npoints; i++) {
            float projection = axis.dot(new Vector2D(polygon.xpoints[i], polygon.ypoints[i]));
            min = Math.min(min, projection);
            max = Math.max(max, projection);
        }

        return new Projection(min, max);
    }
}
