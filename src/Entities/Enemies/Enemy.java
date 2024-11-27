package Entities.Enemies;

import Animations.Entities.EntityAnimation;
import Entities.Characters.Character;
import Entities.Common.*;
import Battle.Effects.Effect;
import Battle.Effects.DamageIndicatorManager;
import Entities.Enemies.SpecialSkill.SpecialSkill;
import Entities.Entity;
import Game.Handler;
import Utils.ImageUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing an enemy entity in the game.
 * Enemies have health, attack power, defense, and a special skill that can be used under certain conditions.
 * The class provides methods for performing regular and special attacks.
 */
public abstract class Enemy extends Entity {
    private Handler handler = Handler.getInstance();
    protected SpecialSkill specialSkill; // The special skill that the enemy can use
    protected double specialSkillChance = 0.05; // 5% base chance for special attack
    protected double minDamage = 0.5; // Minimum damage multiplier
    protected double maxDamage = 1.5; // Maximum damage multiplier
    protected double lowHealthThreshold = 0.3; // Threshold to consider health as "low" (30%)
    protected double dodge = 0.05; // Chance for the enemy to dodge attacks
    protected String key;

    /**
     * Constructs an enemy with health, attack power, defense, and a special skill.
     *
     * @param health       The health of the enemy.
     * @param attackPower  The attack power of the enemy.
     * @param defense      The defense of the enemy.
     * @param specialSkill The special skill of the enemy.
     */
    Enemy(Health health, AttackPower attackPower, Defense defense, SpecialSkill specialSkill) {
        this(health, attackPower, defense);
        this.name = "Enemy"; // Default name
        this.key = this.name.toUpperCase().replace(" ", "_");
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
    protected Enemy(Health health, AttackPower attackPower, Defense defense) {
        super(0, 0, 32, 32); // Initialize with default position and size
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defense;

        animation = new EntityAnimation(ImageUtils.loadImage("/Player/Kent/Character.png"));
    }

    /**
     * Calculates the damage dealt by the enemy based on its attack power.
     * The damage is randomized within the minimum and maximum damage range.
     *
     * @return The damage value dealt by the enemy.
     */
    protected double calculateDamage() {
        return attackPower.getDamage() * (Math.random() * (maxDamage - minDamage) + minDamage);
    }

    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        // Render the enemy on the screen (e.g., draw the enemy's image or animation)
        // animation.render(); // Uncomment if animation is used
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
                Handler.getInstance().getBattleManager().getDamageIndicatorManager()
                    .addDamageIndicator(damage, (float) player.getDisplayX(), (float) (player.getDisplayY() - 20));
                return damage;
            } else {

            }
        }
        return -1;
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
        if(getHealth().isDead()){
            return;
        }
        // Check if the enemy dodges the attack
        if (Math.random() < dodge) {
            Handler.getInstance().getBattleManager().getDamageIndicatorManager()
                .addDodgeIndicator((float) getDisplayX(), (float) (getDisplayY() - 20));
            System.out.println(this.name + " dodged the attack!");
            return;
        }

        // Apply damage to the enemy's health
        System.out.println(this.name + " takes " + damage + " damage!");
        Handler.getInstance().getBattleManager().getDamageIndicatorManager()
            .addDamageIndicator(damage, (float) getDisplayX(), (float) (getDisplayY() - 20));
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
