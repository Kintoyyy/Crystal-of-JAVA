package Entities;

import Animations.Animation;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Battle.Effects.Effect;
import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Game.Handler;
import Map.Object.Object;
import Map.Movement.DetectionZone;
import Components.Dialog.EntityDialog;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class representing an entity in the game.
 * Entities can have health, attack power, defense, and a set of ActiveEffects.
 * Provides methods for updating and rendering the entity on the screen.
 */
public abstract class Entity {
    protected String name; // Name of the entity
    protected String description = ""; // Description of the entity
    protected int renderOrder = 0; // Base render order for layering
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
    protected DetectionZone detectionZone;
    protected List<String> dialogLines = new ArrayList<>();
    public EntityDialog dialog;

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
        this.detectionZone = new DetectionZone(x, y, DetectionZone.DEFAULT_RADIUS);
        this.dialog = (EntityDialog) new EntityDialog(this, dialogLines).setLocation(400,400);
    }

    public void updateDetectionCircle() {
        detectionZone.update(x, y);
    }

    /**
     * Updates the state of the entity.
     * To be implemented by subclasses to define entity behavior each tick.
     */
//    public abstract void tick();

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
    
    // Get the Y position for render sorting
    public float getRenderY() {
        if (object != null) {
            return object.getPosition().y + height;
        }
        return y + height;
    }

    public void setDialogLines(String... lines) {
        this.dialogLines = List.of(lines);
        if (dialog != null) {
            dialog = (EntityDialog) new EntityDialog(this, List.of(lines)).setLocation(400,400);
        }
    }

    public void showDialog() {
        if (dialog != null && !dialogLines.isEmpty()) {
            dialog.show();
        }
    }

    public void nextDialog() {
        if (dialog != null && dialog.isActive()) {
            dialog.nextLine();
        }
    }

    public void tick() {
        if (dialog != null) {
            dialog.tick();
           
           // Handle dialog advancement with E key
           if (Handler.getInstance().getKeyManager().isKeyPressed('E').exactMatch()) {
               nextDialog();
           }
        }
    }

    public DetectionZone getDetectionZone() {
        return detectionZone;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
