package Characters.Stats;

public class Health {
    private double health;
    private double baseHealth;
    private double healthRegenRate;

    public Health(int health, int baseHealth) {
        this.health = health;
        this.baseHealth = baseHealth;
        this.healthRegenRate = 0.04;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public double getHealthRegenRate() {
        return healthRegenRate;
    }

    public void setHealthRegenRate(double healthRegenRate) {
        this.healthRegenRate = healthRegenRate;
    }

    public void regenHealth() {
        health += healthRegenRate;
        if (health > baseHealth) {
            health = baseHealth;
        }
    }
}
