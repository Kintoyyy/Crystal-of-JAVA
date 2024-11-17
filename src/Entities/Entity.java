package Entities;

import Animations.Animation;
import Entities.Common.Effects.Effect;
import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Abstract base class representing an entity in the game.
 * Entities can have health, attack power, defense, and a set of effects.
 * Provides methods for updating and rendering the entity on the screen.
 */
public abstract class Entity {
    protected String name; // Name of the entity
    protected String description = ""; // Description of the entity
    protected Health health; // Health of the entity
    protected AttackPower attackPower; // Attack power of the entity
    protected Defense defense; // Defense of the entity
    protected ArrayList<Effect> effects = new ArrayList<>(); // Effects applied to the entity

    protected BufferedImage profileImage; // Profile image for the entity
    protected Animation animation; // Animation for the entity

    protected float x, y; // Position of the entity
    protected int width, height; // Dimensions of the entity
    protected Rectangle bounds; // Bounding box for collision detection

    /**
     * Constructs an entity at the specified position and size.
     *
     * @param x      X-coordinate of the entity.
     * @param y      Y-coordinate of the entity.
     * @param width  Width of the entity.
     * @param height Height of the entity.
     */
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(0, 0, width, height);
    }

    /**
     * Updates the state of the entity.
     * To be implemented by subclasses to define entity behavior each tick.
     */
    public abstract void tick();

    /**
     * Renders the entity on the screen.
     * To be implemented by subclasses to draw the entity on the graphics context.
     *
     * @param g The graphics context used for rendering.
     */
    public abstract void render(Graphics g);

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

    public Rectangle getBounds() {
        return bounds;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Health getHealth() {
        return health;
    }

    public Defense getDefense() {
        return defense;
    }

    public BufferedImage getProfile() {
        return profileImage;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public Animation getAnimation() {
        return animation;
    }
}
