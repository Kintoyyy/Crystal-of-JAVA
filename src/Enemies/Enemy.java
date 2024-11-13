package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;
import Enemies.SpecialSkill.SpecialSkill;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.image.BufferedImage;

public abstract class Enemy {

    protected String name = "Enemy";
    protected String description = "A generic enemy";

    protected SpriteSheet spriteSheet;
    private BufferedImage playerProfile;

    protected Health health;
    protected AttackPower attackPower;
    protected Defense defence;
    protected SpecialSkill specialSkill;
    protected double specialSkillChance = 0.05; // Base chance of 5% for special attack
    protected double minDamage = 0.5;
    protected double maxDamage = 1.5;
    protected double lowHealthThreshold = 0.3; // Threshold to consider health as "low" (30%)


    Enemy(Health health, AttackPower attackPower, Defense defence, SpecialSkill specialSkill) {
        this(health, attackPower, defence);
        this.specialSkill = specialSkill;
    }

    Enemy(Health health, AttackPower attackPower, Defense defence) {

        this.spriteSheet = new SpriteSheet(ImageUtils.loadImage("/Player/Player_New/Player_Anim/Player_Idle_Run_Death_Anim.png"));
        this.playerProfile = spriteSheet.crop(0, 0, 32, 32);

        this.health = health;
        this.attackPower = attackPower;
        this.defence = defence;
    }

    protected double calculateDamage() {
        return attackPower.getAttackPower() * (Math.random() * (maxDamage - minDamage) + minDamage);
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
        return defence;
    }

    public Health getHealth() {
        return health;
    }


    public void takeDamage(double damage) {
        System.out.println(this.name + " takes " + damage + " damage!");
        health.takeDamage(damage);
    }
}
