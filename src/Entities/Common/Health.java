package Entities.Common;

/**
 * Represents the health system for characters or entities in the game.
 * <p>
 * The class manages health points, including regenerating health over time,
 * taking damage, healing, and checking if the character is dead or at full health.
 * </p>
 */
public class Health {
    private double health;
    private double baseHealth;
    private double healthRegenRate;

    /**
     * Constructs a Health object with an initial base amount of health.
     *
     * @param baseHealth The base health for the character or entity.
     */
    public Health(double baseHealth) {
        this.health = baseHealth;
        this.baseHealth = baseHealth;
        this.healthRegenRate = 0.04; // Default health regeneration rate
    }

    /**
     * Gets the current health value.
     *
     * @return The current health.
     */
    public double getHealth() {
        return health;
    }

    /**
     * Sets the current health value.
     *
     * @param health The new health value.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gets the base health value.
     *
     * @return The base health.
     */
    public double getBaseHealth() {
        return baseHealth;
    }

    /**
     * Sets the base health value.
     *
     * @param baseHealth The new base health value.
     */
    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    /**
     * Gets the rate at which health regenerates over time.
     *
     * @return The health regeneration rate.
     */
    public double getHealthRegenRate() {
        return healthRegenRate;
    }

    /**
     * Sets the rate at which health regenerates over time.
     *
     * @param healthRegenRate The new health regeneration rate.
     */
    public void setHealthRegenRate(double healthRegenRate) {
        this.healthRegenRate = healthRegenRate;
    }

    /**
     * Regenerates health based on the health regeneration rate.
     * Ensures that health does not exceed the base health value.
     */
    public void regenHealth() {
        health += healthRegenRate;
        if (health > baseHealth) {
            health = baseHealth;
        }
    }

    /**
     * Applies damage to the character or entity, reducing the current health.
     * If the health goes below zero, it is set to zero.
     *
     * @param damage The amount of damage to be taken.
     */
    public void takeDamage(double damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    /**
     * Heals the character or entity by adding health.
     * If the health exceeds the base health value, it is capped at the base health.
     *
     * @param heal The amount of health to be restored.
     */
    public void heal(double heal) {
        health += heal;
        if (health > baseHealth) {
            health = baseHealth;
        }
    }

    /**
     * Checks if the character or entity is dead.
     *
     * @return true if health is zero, false otherwise.
     */
    public boolean isDead() {
        return health == 0;
    }

    /**
     * Resets the current health to the base health value.
     */
    public void resetHealth() {
        health = baseHealth;
    }

    /**
     * Checks if the character or entity has full health.
     *
     * @return true if health is equal to the base health, false otherwise.
     */
    public boolean isFullHealth() {
        return this.health == this.baseHealth;
    }
}
