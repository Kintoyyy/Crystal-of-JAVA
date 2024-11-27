package Entities.Characters;

import Animations.Entities.EntityAnimation;
import Entities.Common.*;
import Battle.Effects.Effect;
import Entities.Entity;
import Battle.Skills.Skill;
import Game.Handler;
import Utils.ImageUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Character class represents a generic character in the game with attributes like health, mana, energy,
 * skills, level, experience, and dodge rate. It provides basic functionality for managing character attributes
 * and interacting with skills. This class serves as a base class for specific character types.
 *
 * <p>Characters can have various stats such as health, mana, attack power, and defense. They can also hold
 * multiple skills, which can be used during gameplay. The dodge mechanics, along with the ability to regenerate
 * health and mana, are also managed here. Characters' levels and experience points are tracked, allowing
 * for progression over time.</p>
 *
 * <p>Subclasses of Character will need to implement specific skill usage logic through the {@link #useSkill(int)}
 * and {@link #useSkill(Skill)} methods to handle different types of skills in combat.</p>
 *
 * @see Entity
 * @see Skill
 * @see Mana
 * @see Health
 * @see Energy
 */
public abstract class Character extends Entity {
    private Handler handler = Handler.getInstance();
    // Character attributes
    protected Mana mana;               // Character's mana
    protected Energy energy;           // Character's energy
    protected ArrayList<Skill> skills; // List of character's skills
    protected int level;               // Character's current level
    protected int experience;          // Character's experience points
    protected double dodgeRate = 0.0;  // Character's dodge rate
    protected double dodgeChance = 0.0; // Character's chance to dodge attacks
    protected float speed = 4.0F;  // Character's movement speed

    /**
     * Constructs a new Character with the specified attributes.
     *
     * @param name        The name of the character.
     * @param level       The initial level of the character.
     * @param health      The health stats for the character.
     * @param mana        The mana stats for the character.
     * @param attackPower The attack power of the character.
     * @param defense     The defense stats of the character.
     * @param skills      A list of skills available to the character.
     */
    public Character(String name, int level, Health health, Mana mana, AttackPower attackPower, Defense defense, ArrayList<Skill> skills) {
        super(0, 0, 32, 32);

        // Initialize default animation for the character
        animation = new EntityAnimation(ImageUtils.loadImage("/Player/Player_New/Player_Anim/Player_Idle_Run_Death_Anim.png"));
        this.description = "A generic selectedEnemy";

        this.name = name;
        this.level = level;
        this.health = health;
        this.mana = mana;
        this.attackPower = attackPower;
        this.defense = defense;
        this.energy = new Energy(100, 100);
        this.skills = skills;
        this.experience = 0;
        // Set the default bounds for the character
        this.bounds = new Rectangle(46, 64, 34, 26);
    }

    /**
     * Adds a new skill to the character's skill set.
     *
     * <p>If the skill is already added or is null, no action will be performed.</p>
     *
     * @param skill The skill to be added to the character.
     */
    public void addSkill(Skill skill) {
        if (skill == null || skills.contains(skill)) return;
        skill.setCharacter(this);
        skills.add(skill);
    }

    @Override
    public void render(Graphics g, int xOffset, int yOffset) {

        // Override this method to render the character on the screen
    }

    /**
     * Gets the current dodge rate of the character.
     *
     * @return The character's dodge rate.
     */
    public double getDodgeRate() {
        return dodgeRate;
    }

    /**
     * Sets the dodge rate of the character.
     *
     * @param dodgeRate The dodge rate to set for the character.
     */
    public void setDodgeRate(double dodgeRate) {
        this.dodgeRate = dodgeRate;
    }

    /**
     * Gets the current dodge chance of the character.
     *
     * @return The dodge chance of the character.
     */
    public double getDodge() {
        return dodgeChance;
    }

    /**
     * Gets the current level of the character.
     *
     * @return The character's level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the character.
     *
     * @param level The level to set for the character.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets the list of skills available to the character.
     *
     * @return The list of skills the character has.
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Abstract method to be implemented by subclasses for using a skill based on its index.
     *
     * @param index The index of the skill to use.
     */
    public abstract void useSkill(int index);

    /**
     * Abstract method to be implemented by subclasses for using a specific skill.
     *
     * @param skill The skill to use.
     */
    public abstract void useSkill(Skill skill);

    /**
     * Gets the character's mana.
     *
     * @return The mana of the character.
     */
    public Mana getMana() {
        return mana;
    }

    /**
     * Sets the character's mana.
     *
     * @param mana The mana to set for the character.
     */
    public void setMana(Mana mana) {
        this.mana = mana;
    }

    /**
     * Sets the character's health.
     *
     * @param health The health to set for the character.
     */
    public void setHealth(Health health) {
        this.health = health;
    }

    /**
     * Regenerates the character's health over time.
     */
    public void regenHealth() {
        health.regenHealth();
    }

    /**
     * Regenerates the character's mana over time.
     */
    public void regenMana() {
        mana.regenMana();
    }


    public void takeDamage(double damage) {
        if(health.isDead()) return;
        if (Math.random() < getDodge()) {
            handler.getBattleManager().getDamageIndicatorManager()
                .addDodgeIndicator((float) getDisplayX(), (float) (getDisplayY() - 20));
            return;
        }
        
        handler.getBattleManager().getDamageIndicatorManager()
            .addDamageIndicator(damage, (float) getDisplayX(), (float) (getDisplayY() - 20));
        health.takeDamage(damage);
    }

    public float getSpeed() {
        return speed;
    }

    private final List<Effect> effects = new ArrayList<Effect>();

    public void addEffect(Effect effect) {
        effects.add(effect);
        effect.applyToCharacter(this);
    }

    public void processEffects() {
        effects.removeIf(Effect::isExpired);
    }
}
