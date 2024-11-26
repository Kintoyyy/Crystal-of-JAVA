package Entities;

import Animations.Animation;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Battle.Effects.Effect;
import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Map.Object.Object;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Abstract base class representing an entity in the game.
 * Entities can have health, attack power, defense, and a set of ActiveEffects.
 * Provides methods for updating and rendering the entity on the screen.
 */
public abstract class Entity {
    protected String name; // Name of the entity
    protected String description = ""; // Description of the entity
    protected Health health; // Health of the entity
    protected AttackPower attackPower; // Attack power of the entity
    protected Defense defense; // Defense of the entity
    protected ArrayList<Effect> ActiveEffects = new ArrayList<>(); // Effects applied to the entity

    protected BufferedImage profileImage; // Profile image for the entity
    protected Animation animation; // Animation for the entity
    protected Rectangle bounds; // Bounding box for collision detection

    protected float x, y; // Position of the entity
    protected int width, height; // Dimensions of the entity
    protected Point location = new Point(); // Location of the entity
    protected Object object;
    protected Ellipse2D.Float detectionCircle; // Detection circle


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

        float radius = Math.max(width, height) * 1.5f; // Example: 1.5 times the larger dimension
        this.detectionCircle = new Ellipse2D.Float(x - radius / 2, y - radius / 2, radius, radius);
    }

    public void updateDetectionCircle() {
        float radius = (float) detectionCircle.getWidth();
        detectionCircle.setFrame(x - radius / 2, y - radius / 2, radius, radius);
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
     * @param g       The graphics context used for rendering.
     * @param xOffset
     * @param yOffset
     */
    public abstract void render(Graphics g, int xOffset, int yOffset);


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

    public AttackPower getAttackPower() {
        return attackPower;
    }

    public BufferedImage getProfile() {
        return profileImage;
    }

    public ArrayList<Effect> getActiveEffects() {
        return ActiveEffects;
    }

    public Point getLocation() {
        return location;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void addEffect(Effect effect) {
        ActiveEffects.add(effect);
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }


    public boolean isPointInDetectionCircle(float px, float py) {
        return detectionCircle.contains(px, py);
    }

    /**
     * Checks if another entity is within the detection circle.
     *
     * @param other The other entity.
     * @return True if the other entity is within the detection circle, false otherwise.
     */
    public boolean isEntityInDetectionCircle(Entity other) {
        float otherX = other.x + other.width / 2f;
        float otherY = other.y + other.height / 2f;
        return detectionCircle.contains(otherX, otherY);
    }

    public boolean isPointInDetectionCircle(Point playerScreenLocation) {
        return detectionCircle.contains(playerScreenLocation);
    }
}

