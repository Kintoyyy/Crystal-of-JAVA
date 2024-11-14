package Entities;

import Animations.PlayerAnimation;
import Entities.Characters.Effects.Effect;
import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Entity {

    protected String name;
    protected String description = "";
    protected Health health;
    protected AttackPower attackPower;
    protected Defense defense;
    protected ArrayList<Effect> effects = new ArrayList<>();

    protected SpriteSheet spriteSheet;
    protected BufferedImage profileImage;
    protected PlayerAnimation animation;

    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

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

    public PlayerAnimation getAnimation() {
        return animation;
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
}
