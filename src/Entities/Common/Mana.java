package Entities.Common;

/**
 * Represents a mana system for characters or entities in the game.
 * <p>
 * The class manages mana points, including regenerating mana over time,
 * using mana for abilities, and checking if there is enough mana for an action.
 * </p>
 */
public class Mana {
    private double mana;
    private double baseMana;
    private double manaRegenRate;

    /**
     * Constructs a Mana object with an initial base amount of mana.
     *
     * @param baseMana The base mana for the character or entity.
     */
    public Mana(int baseMana) {
        this.mana = baseMana;
        this.baseMana = baseMana;
        this.manaRegenRate = 0.01; // Default mana regeneration rate
    }

    /**
     * Gets the current mana value.
     *
     * @return The current mana.
     */
    public double getMana() {
        return mana;
    }

    /**
     * Sets the current mana value.
     *
     * @param mana The new mana value.
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

    /**
     * Gets the base mana value.
     *
     * @return The base mana.
     */
    public double getBaseMana() {
        return baseMana;
    }

    /**
     * Sets the base mana value.
     *
     * @param baseMana The new base mana value.
     */
    public void setBaseMana(int baseMana) {
        this.baseMana = baseMana;
    }

    /**
     * Gets the rate at which mana regenerates over time.
     *
     * @return The mana regeneration rate.
     */
    public double getManaRegenRate() {
        return manaRegenRate;
    }

    /**
     * Sets the rate at which mana regenerates over time.
     *
     * @param manaRegenRate The new mana regeneration rate.
     */
    public void setManaRegenRate(double manaRegenRate) {
        this.manaRegenRate = manaRegenRate;
    }

    /**
     * Regenerates mana based on the mana regeneration rate.
     * Ensures that mana does not exceed the base mana value.
     */
    public void regenMana() {
        mana += manaRegenRate;
        if (mana > baseMana) {
            mana = baseMana;
        }
    }

    /**
     * Uses a specified amount of mana, reducing the current mana.
     * If the mana goes below zero, it is set to zero.
     *
     * @param mana The amount of mana to be used.
     */
    public void useMana(double mana) {
        this.mana -= mana;
        if (this.mana < 0) {
            this.mana = 0;
        }
    }

    /**
     * Adds a specified amount of mana, increasing the current mana.
     * If the mana exceeds the base mana value, it is capped at the base mana.
     *
     * @param mana The amount of mana to be added.
     */
    public void addMana(double mana) {
        this.mana += mana;
        if (this.mana > baseMana) {
            this.mana = baseMana;
        }
    }

    /**
     * Checks if the current mana is enough to perform an action requiring a certain amount of mana.
     *
     * @param mana The amount of mana required.
     * @return true if there is enough mana, false otherwise.
     */
    public boolean hasEnoughMana(double mana) {
        return this.mana >= mana;
    }

    /**
     * Checks if the character or entity is out of mana.
     *
     * @return true if mana is zero, false otherwise.
     */
    public boolean isOutOfMana() {
        return this.mana == 0;
    }

    /**
     * Checks if the character or entity has full mana.
     *
     * @return true if mana is equal to the base mana, false otherwise.
     */
    public boolean isFullMana() {
        return this.mana == this.baseMana;
    }

    /**
     * Resets the current mana to zero.
     */
    public void resetMana() {
        this.mana = 0;
    }

    /**
     * Resets the current mana to a specified value.
     *
     * @param mana The new value for the current mana.
     */
    public void resetMana(int mana) {
        this.mana = mana;
    }
}
