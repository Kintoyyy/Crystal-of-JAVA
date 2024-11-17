package Entities.Common;

/**
 * Represents the energy system for characters or entities in the game.
 * <p>
 * The class manages energy points, including regenerating energy over time,
 * using energy, adding energy, and checking if the entity has enough energy
 * for specific actions.
 * </p>
 */
public class Energy {
    private double energy;
    private double baseEnergy;
    private double energyRegenRate;

    /**
     * Constructs an Energy object with an initial energy and base energy value.
     *
     * @param energy The initial energy value for the character or entity.
     * @param baseEnergy The base energy for the character or entity.
     */
    public Energy(int energy, int baseEnergy) {
        this.energy = energy;
        this.baseEnergy = baseEnergy;
        this.energyRegenRate = 0.01; // Default energy regeneration rate
    }

    /**
     * Gets the current energy value.
     *
     * @return The current energy.
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * Sets the current energy value.
     *
     * @param energy The new energy value.
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Gets the base energy value.
     *
     * @return The base energy.
     */
    public double getBaseEnergy() {
        return baseEnergy;
    }

    /**
     * Sets the base energy value.
     *
     * @param baseEnergy The new base energy value.
     */
    public void setBaseEnergy(int baseEnergy) {
        this.baseEnergy = baseEnergy;
    }

    /**
     * Gets the rate at which energy regenerates over time.
     *
     * @return The energy regeneration rate.
     */
    public double getEnergyRegenRate() {
        return energyRegenRate;
    }

    /**
     * Sets the rate at which energy regenerates over time.
     *
     * @param energyRegenRate The new energy regeneration rate.
     */
    public void setEnergyRegenRate(double energyRegenRate) {
        this.energyRegenRate = energyRegenRate;
    }

    /**
     * Regenerates energy based on the energy regeneration rate.
     * Ensures that energy does not exceed the base energy value.
     */
    public void regenEnergy() {
        energy += energyRegenRate;
        if (energy > baseEnergy) {
            energy = baseEnergy;
        }
    }

    /**
     * Uses a specified amount of energy, reducing the current energy.
     * If the energy goes below zero, it is set to zero.
     *
     * @param energy The amount of energy to be used.
     */
    public void useEnergy(double energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    /**
     * Adds a specified amount of energy to the current energy.
     * If the energy exceeds the base energy value, it is capped at the base energy.
     *
     * @param energy The amount of energy to be added.
     */
    public void addEnergy(double energy) {
        this.energy += energy;
        if (this.energy > baseEnergy) {
            this.energy = baseEnergy;
        }
    }

    /**
     * Checks if the character or entity has enough energy for a specific action.
     *
     * @param energy The amount of energy required for the action.
     * @return true if there is enough energy, false otherwise.
     */
    public boolean hasEnoughEnergy(double energy) {
        return this.energy >= energy;
    }

    /**
     * Resets the current energy to zero.
     */
    public void resetEnergy() {
        this.energy = 0;
    }

    /**
     * Resets the current energy to the specified value.
     *
     * @param energy The new energy value.
     */
    public void resetEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Checks if the character or entity is out of energy.
     *
     * @return true if energy is zero, false otherwise.
     */
    public boolean isOutOfEnergy() {
        return this.energy == 0;
    }
}
