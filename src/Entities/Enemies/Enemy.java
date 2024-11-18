package Entities.Enemies;

import Entities.Characters.Character;
import Entities.Common.*;
import Entities.Enemies.SpecialSkill.SpecialSkill;
import Entities.Entity;

import java.awt.*;

/**
 * Abstract class representing an enemy entity in the game.
 * Enemies have health, attack power, defense, and a special skill that can be used under certain conditions.
 * The class provides methods for performing regular and special attacks.
 */
public abstract class Enemy extends Entity {

    protected SpecialSkill specialSkill; // The special skill that the enemy can use

    // Base chance and parameters for damage calculation
    protected double specialSkillChance = 0.05; // 5% base chance for special attack
    protected double minDamage = 0.5; // Minimum damage multiplier
    protected double maxDamage = 1.5; // Maximum damage multiplier
    protected double lowHealthThreshold = 0.3; // Threshold to consider health as "low" (30%)
    protected double dodge = 0.05; // Chance for the enemy to dodge attacks

    /**
     * Constructs an enemy with health, attack power, defense, and a special skill.
     *
     * @param health      The health of the enemy.
     * @param attackPower The attack power of the enemy.
     * @param defense     The defense of the enemy.
     * @param specialSkill The special skill of the enemy.
     */
    Enemy(Health health, AttackPower attackPower, Defense defense, SpecialSkill specialSkill) {
        this(health, attackPower, defense);
        this.name = "Enemy"; // Default name
        this.description = "A generic enemy"; // Default description
        this.specialSkill = specialSkill; // Set the special skill
    }

    /**
     * Constructs an enemy with health, attack power, and defense, but without a special skill.
     *
     * @param health      The health of the enemy.
     * @param attackPower The attack power of the enemy.
     * @param defense     The defense of the enemy.
     */
    Enemy(Health health, AttackPower attackPower, Defense defense) {
        super(0, 0, 32, 32); // Initialize with default position and size
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    /**
     * Calculates the damage dealt by the enemy based on its attack power.
     * The damage is randomized within the minimum and maximum damage range.
     *
     * @return The damage value dealt by the enemy.
     */
    protected double calculateDamage() {
        return attackPower.getAttackPower() * (Math.random() * (maxDamage - minDamage) + minDamage);
    }

    @Override
    public void tick() {
        // Update the state of the enemy (e.g., animation updates, AI behavior)
//         animation.tick(); // Uncomment if animation is used
    }

    @Override
    public void render(Graphics g) {
        // Render the enemy on the screen (e.g., draw the enemy's image or animation)
//         animation.render(); // Uncomment if animation is used
    }

    /**
     * Returns whether the enemy is alive or dead based on its health.
     *
     * @return true if the enemy is alive, false if dead.
     */
    public boolean isAlive() {
        return !health.isDead();
    }

    /**
     * The enemy attacks the player character.
     * The attack can be either a regular attack or a special attack based on conditions.
     *
     * @param player The player character to be attacked.
     */
    public void attack(Character player) {
        // Check if the special attack should be used
        if (shouldUseSpecialAttack()) {
            useSpecialSkill(player); // Use the special skill
        } else {
            // Regular attack process
            if (calculateHit(this.getAccuracy(), player.getDodge())) {
                double damage = calculateDamage() - player.getDefense().getDefense();
                if (damage < 1) damage = 1; // Ensure minimum damage of 1
                player.getHealth().takeDamage(damage);
                System.out.println(this.name + " hits for " + damage + " damage!");
            } else {
                System.out.println(this.name + " missed the attack!");
            }
        }
    }

    /**
     * Determines whether the enemy should use a special attack.
     * The chance of using the special attack increases if the enemy's health is low.
     *
     * @return true if the special attack should be used, false otherwise.
     */
    private boolean shouldUseSpecialAttack() {
        double currentHealthPercentage = health.getHealth() / health.getBaseHealth();

        // Increase special attack chance if health is low
        double adjustedSpecialSkillChance = specialSkillChance;
        if (currentHealthPercentage < lowHealthThreshold) {
            adjustedSpecialSkillChance += 0.2; // Increase chance by 20% when health is low
        }

        return Math.random() < adjustedSpecialSkillChance;
    }

    /**
     * Returns the accuracy of the enemy's attack.
     *
     * @return The accuracy value for the attack.
     */
    public double getAccuracy() {
        return 0.75; // Default accuracy value
    }

    /**
     * Determines whether the attack hits the target based on the enemy's accuracy and the target's dodge chance.
     *
     * @param accuracy The accuracy of the enemy's attack.
     * @param dodge    The dodge chance of the target.
     * @return true if the attack hits, false otherwise.
     */
    private boolean calculateHit(double accuracy, double dodge) {
        return Math.random() < accuracy * (1 - dodge); // Attack hit calculation
    }

    /**
     * Executes the special skill of the enemy if one is available.
     *
     * @param player The player character to apply the special skill to.
     */
    protected void useSpecialSkill(Character player) {
        if (specialSkill != null) {
            specialSkill.apply(player); // Apply the special skill to the player
            System.out.println(this.name + " used " + specialSkill.getName() + "!");
        }
    }

    /**
     * The enemy takes damage from an attack.
     * The dodge chance is checked before applying the damage.
     *
     * @param damage The amount of damage to be dealt to the enemy.
     */
    public void takeDamage(double damage) {
        // Check if the enemy dodges the attack
        if (Math.random() < dodge) {
            System.out.println(this.name + " dodged the attack!");
            return;
        }

        // Apply damage to the enemy's health
        System.out.println(this.name + " takes " + damage + " damage!");
        health.takeDamage(damage);
    }
}
