package Entities.Common;

/**
 * Represents the defense system for characters or entities in the game.
 * <p>
 * This class handles the defense points, including adding or removing defense,
 * checking if the entity has enough defense, and resetting defense values.
 * It is used to mitigate incoming damage based on defense points.
 * </p>
 */
public class Defense {
    private double defense;
    private double baseDefense;

    /**
     * Constructs a Defense object with an initial defense value.
     *
     * @param defense The initial defense value for the character or entity.
     */
    public Defense(int defense) {
        this.defense = defense;
        this.baseDefense = defense;
    }

    /**
     * Gets the current defense value.
     *
     * @return The current defense.
     */
    public double getDefense() {
        return defense;
    }

    /**
     * Sets the current defense value.
     *
     * @param defense The new defense value.
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Gets the base defense value.
     *
     * @return The base defense.
     */
    public double getBaseDefense() {
        return baseDefense;
    }

    /**
     * Sets the base defense value.
     *
     * @param baseDefense The new base defense value.
     */
    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    /**
     * Adds defense to the current defense value.
     *
     * @param defense The amount of defense to be added.
     */
    public void addDefense(double defense) {
        this.defense += defense;
    }

    /**
     * Removes defense from the current defense value.
     *
     * @param defense The amount of defense to be removed.
     */
    public void removeDefense(double defense) {
        this.defense -= defense;
    }

    /**
     * Checks if the character or entity has enough defense to withstand a specific amount of damage.
     *
     * @param defense The amount of defense required.
     * @return true if there is enough defense, false otherwise.
     */
    public boolean hasEnoughDefense(double defense) {
        return this.defense >= defense;
    }

    /**
     * Resets the current defense value to the base defense value.
     */
    public void resetDefense() {
        this.defense = baseDefense;
    }

    /**
     * Resets the base defense value to zero.
     */
    public void resetBaseDefense() {
        this.baseDefense = 0;
    }

    /**
     * Resets both the current defense and base defense values to their default state.
     */
    public void resetAll() {
        resetDefense();
        resetBaseDefense();
    }
}
