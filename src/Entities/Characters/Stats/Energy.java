package Entities.Characters.Stats;

public class Energy {
    private double energy;
    private double baseEnergy;
    private double energyRegenRate;

    public Energy(int energy, int baseEnergy) {
        this.energy = energy;
        this.baseEnergy = baseEnergy;
        this.energyRegenRate = 0.01;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public double getBaseEnergy() {
        return baseEnergy;
    }

    public void setBaseEnergy(int baseEnergy) {
        this.baseEnergy = baseEnergy;
    }

    public double getEnergyRegenRate() {
        return energyRegenRate;
    }

    public void setEnergyRegenRate(double energyRegenRate) {
        this.energyRegenRate = energyRegenRate;
    }

    public void regenEnergy() {
        energy += energyRegenRate;
        if (energy > baseEnergy) {
            energy = baseEnergy;
        }
    }

    public void useEnergy(double energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public void addEnergy(double energy) {
        this.energy += energy;
        if (this.energy > baseEnergy) {
            this.energy = baseEnergy;
        }
    }

    public boolean hasEnoughEnergy(double energy) {
        return this.energy >= energy;
    }

    public void setEnergyRegenRate(int energyRegenRate) {
        this.energyRegenRate = energyRegenRate;
    }

    public void setBaseEnergy(double baseEnergy) {
        this.baseEnergy = baseEnergy;
    }

    public void resetEnergy() {
        this.energy = 0;
    }

    public void resetEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isOutOfEnergy() {
        return this.energy == 0;
    }
}
