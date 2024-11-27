package Entities.Enemies;

import Animations.Entities.EntityAnimation;
import Entities.Characters.Character;
import Entities.Common.*;
import Battle.Effects.Effect;
import Entities.Enemies.SpecialSkill.SpecialSkill;
import Entities.Entity;
import Utils.ImageUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing an selectedEnemy entity in the game.
 * Enemies have health, attack power, defense, and a special skill that can be used under certain conditions.
 * The class provides methods for performing regular and special attacks.
 */
public abstract class Enemy extends Entity {

    protected SpecialSkill specialSkill; // The special skill that the selectedEnemy can use

    // Base chance and parameters for damage calculation
    protected double specialSkillChance = 0.05; // 5% base chance for special attack
    protected double minDamage = 0.5; // Minimum damage multiplier
    protected double maxDamage = 1.5; // Maximum damage multiplier
    protected double lowHealthThreshold = 0.3; // Threshold to consider health as "low" (30%)
    protected double dodge = 0.05; // Chance for the selectedEnemy to dodge attacks
    protected String key;

    /**
     * Constructs an selectedEnemy with health, attack power, defense, and a special skill.
     *
     * @param health       The health of the selectedEnemy.
     * @param attackPower  The attack power of the selectedEnemy.
     * @param defense      The defense of the selectedEnemy.
     * @param specialSkill The special skill of the selectedEnemy.
     */
    Enemy(Health health, AttackPower attackPower, Defense defense, SpecialSkill specialSkill) {
        this(health, attackPower, defense);
        this.name = "Enemy"; // Default name
        this.key = this.name.toUpperCase().replace(" ", "_");
        this.description = "A generic selectedEnemy"; // Default description
        this.specialSkill = specialSkill; // Set the special skill
    }

    /**
     * Constructs an selectedEnemy with health, attack power, and defense, but without a special skill.
     *
     * @param health      The health of the selectedEnemy.
     * @param attackPower The attack power of the selectedEnemy.
     * @param defense     The defense of the selectedEnemy.
     */
    protected Enemy(Health health, AttackPower attackPower, Defense defense) {
        super(0, 0, 32, 32); // Initialize with default position and size
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defense;

        animation = new EntityAnimation(ImageUtils.loadImage("/Player/Kent/Character.png"));
    }

    /**
     * Calculates the damage dealt by the selectedEnemy based on its attack power.
     * The damage is randomized within the minimum and maximum damage range.
     *
     * @return The damage value dealt by the selectedEnemy.
     */
    protected double calculateDamage() {
        return attackPower.getDamage() * (Math.random() * (maxDamage - minDamage) + minDamage);
    }

    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        // Render the selectedEnemy on the screen (e.g., draw the selectedEnemy's image or animation)
//         animation.render(); // Uncomment if animation is used
    }

    /**
     * Returns whether the selectedEnemy is alive or dead based on its health.
     *
     * @return true if the selectedEnemy is alive, false if dead.
     */
    public boolean isAlive() {
        return !health.isDead();
    }

    /**
     * The selectedEnemy attacks the selectedPlayer character.
     * The attack can be either a regular attack or a special attack based on conditions.
     *
     * @param player The selectedPlayer character to be attacked.
     */
    public double attack(Character player) {
        // Check if the special attack should be used
        if (shouldUseSpecialAttack()) {
            useSpecialSkill(player); // Use the special skill
        } else {
            // Regular attack process
            if (calculateHit(this.getAccuracy(), player.getDodge())) {
                double damage = calculateDamage() - player.getDefense().getDefense();
                if (damage < 1) damage = 1; // Ensure minimum damage of 1
                player.takeDamage(damage);
                player.getHealth().takeDamage(damage);
                return damage;
            } else {

            }
        }
        return -1;
    }

    /**
     * Determines whether the selectedEnemy should use a special attack.
     * The chance of using the special attack increases if the selectedEnemy's health is low.
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
     * Returns the accuracy of the selectedEnemy's attack.
     *
     * @return The accuracy value for the attack.
     */
    public double getAccuracy() {
        return 0.75; // Default accuracy value
    }

    /**
     * Determines whether the attack hits the target based on the selectedEnemy's accuracy and the target's dodge chance.
     *
     * @param accuracy The accuracy of the selectedEnemy's attack.
     * @param dodge    The dodge chance of the target.
     * @return true if the attack hits, false otherwise.
     */
    private boolean calculateHit(double accuracy, double dodge) {
        return Math.random() < accuracy * (1 - dodge); // Attack hit calculation
    }

    /**
     * Executes the special skill of the selectedEnemy if one is available.
     *
     * @param player The selectedPlayer character to apply the special skill to.
     */
    protected void useSpecialSkill(Character player) {
        if (specialSkill != null) {
            specialSkill.apply(player); // Apply the special skill to the selectedPlayer
            System.out.println(this.name + " used " + specialSkill.getName() + "!");
        }
    }

    /**
     * The selectedEnemy takes damage from an attack.
     * The dodge chance is checked before applying the damage.
     *
     * @param damage The amount of damage to be dealt to the selectedEnemy.
     */
    public void takeDamage(double damage) {
        // Check if the selectedEnemy dodges the attack
        if (Math.random() < dodge) {
            System.out.println(this.name + " dodged the attack!");
            return;
        }

        // Apply damage to the selectedEnemy's health
        System.out.println(this.name + " takes " + damage + " damage!");
        health.takeDamage(damage);
    }

    public String getKey() {
        return key;
    }


    private final List<Effect> effects = new ArrayList<>();

    public void addEffect(Effect effect) {
        effects.add(effect);
        effect.applyToEnemy(this);
    }

    public void processEffects() {
        effects.removeIf(Effect::isExpired);
    }
}
