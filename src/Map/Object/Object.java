package Map.Object;

import java.awt.*;

/**
 * Represents a generic object within the game world.
 *
 * <p>This class defines the core attributes and behaviors of objects in the game,
 * such as their position, size, name, type, and collision bounds. It is used
 * to manage and interact with objects placed on the game map.</p>
 *
 * <p>The type of the object is determined by an enumerated {@link Type}, which
 * defaults to {@code NONE} if the provided type is invalid or null.</p>
 */
public class Object {

    /**
     * The name of the object.
     */
    protected String name;

    /**
     * The type of the object, represented by the {@link Type} enumeration.
     */
    protected Type type;

    /**
     * The rectangular bounds of the object used for collision detection.
     */
    private final Rectangle bounds;

    /**
     * The x-coordinate of the object's position on the map.
     */
    protected int x;

    /**
     * The y-coordinate of the object's position on the map.
     */
    protected int y;

    /**
     * The width of the object.
     */
    protected int width;

    /**
     * The height of the object.
     */
    protected int height;

    /**
     * Constructs a new {@code Object} with the specified attributes.
     *
     * @param name   the name of the object
     * @param type   the type of the object as a string (case-insensitive)
     * @param x      the x-coordinate of the object
     * @param y      the y-coordinate of the object
     * @param width  the width of the object
     * @param height the height of the object
     *
     * @throws IllegalArgumentException if the provided {@code type} does not match any {@link Type} value
     * @throws NullPointerException     if {@code type} is null
     */
    public Object(String name, String type, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;

        try {
            this.type = Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            this.type = Type.NONE;
        }

        this.bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Gets the y-coordinate of the object's position.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the x-coordinate of the object's position.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the width of the object.
     *
     * @return the object's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the object.
     *
     * @return the object's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the collision bounds of the object as a {@link Rectangle}.
     *
     * @return the rectangular bounds of the object
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Gets the name of the object.
     *
     * @return the object's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the object.
     *
     * @return the {@link Type} of the object
     */
    public Type getType() {
        return type;
    }
}
