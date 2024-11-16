package Entities.Enemies;

import Entities.Characters.Character;
import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Entities.Enemies.SpecialSkill.SpecialSkill;
import Entities.Entity;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.*;

public abstract class Enemy extends Entity {

    protected SpecialSkill specialSkill;

    protected double specialSkillChance = 0.05; // Base chance of 5% for special attack
    protected double minDamage = 0.5;
    protected double maxDamage = 1.5;
    protected double lowHealthThreshold = 0.3; // Threshold to consider health as "low" (30%)
    protected double dodge = 0.05;

    Enemy(Health health, AttackPower attackPower, Defense defence, SpecialSkill specialSkill) {
        this(health, attackPower, defence);
        this.name = "Enemy";
        this.description = "A generic enemy";
        this.specialSkill = specialSkill;
    }

    Enemy(Health health, AttackPower attackPower, Defense defence) {
        super(0, 0, 32, 32);
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defence;
    }

    protected double calculateDamage() {
        return attackPower.getAttackPower() * (Math.random() * (maxDamage - minDamage) + minDamage);
    }

    @Override
    public void tick() {
//        animation.tick();
    }

    @Override
    public void render(Graphics g) {
//        animation.render();
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return !health.isDead();
    }

    public void attack(Character player) {
        // Check if the special attack should be used
        if (shouldUseSpecialAttack()) {
            useSpecialSkill(player);
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

    // Helper method to check if the special attack should be used
    private boolean shouldUseSpecialAttack() {
        double currentHealthPercentage = health.getHealth() / health.getBaseHealth();

        // Increase special attack chance if health is low
        double adjustedSpecialSkillChance = specialSkillChance;
        if (currentHealthPercentage < lowHealthThreshold) {
            adjustedSpecialSkillChance += 0.2; // Increase chance by 20% when health is low
        }

        return Math.random() < adjustedSpecialSkillChance;
    }

    public double getAccuracy() {
        return 0.75;
    }

    private boolean calculateHit(double accuracy, double dodge) {
        return Math.random() < accuracy * (1 - dodge); // Warrior hit calculation
    }

    // Use special skill if the enemy has one
    protected void useSpecialSkill(Character player) {
        if (specialSkill != null) {
            specialSkill.apply(player);
            System.out.println(this.name + " used " + specialSkill.getName() + "!");
        }
    }

    public Defense getDefense() {
        return defense;
    }

    public Health getHealth() {
        return health;
    }


    public void takeDamage(double damage) {
        if (Math.random() < dodge) {
            System.out.println(this.name + " dodged the attack!");
            return;
        }

        System.out.println(this.name + " takes " + damage + " damage!");
        health.takeDamage(damage);
    }

}
