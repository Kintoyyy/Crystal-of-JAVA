package Entities.Common;

public class Health {
    private double health;
    private double baseHealth;
    private double healthRegenRate;

    public Health(double baseHealth) {
        this.health = baseHealth;
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

    public void takeDamage(double damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public void heal(double heal) {
        health += heal;
        if (health > baseHealth) {
            health = baseHealth;
        }
    }

    public boolean isDead() {
        return health == 0;
    }

    public void resetHealth() {
        health = baseHealth;
    }

    public boolean isFullHealth() {
        return this.health == this.baseHealth;
    }
}
