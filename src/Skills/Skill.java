package Skills;

import Characters.Character;
import Enemies.Enemy;

public abstract class Skill {
    private final String name;
    private final String type;
    protected final double damage;
    private final int cost;
    protected Character player;
    private String description;
    protected String imagePath;
    protected int cooldownTurns = 0;
    protected int effectDurationTurns = 0;

    protected Enemy enemy;

    public Skill(String name,String description, int cost, double damage, String type) {
        this(name, description, cost, damage, type, 0, 0);
    }

    public Skill(String name,String description, int cost, double damage, String type, int cooldownTurns, int effectDurationTurns) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.type = type;
        this.cooldownTurns = cooldownTurns;
        this.effectDurationTurns = effectDurationTurns;
    }

    public String getName() {
        return name;
    }

    public void setCharacter(Character player) {
        this.player = player;
    }

    public int getCost() {
        return cost;
    }

    public double getDamage() {
        return damage;
    }

    public String getType() {
        return type;
    }


    public abstract void useSkill();

    public void attack(Enemy enemy) {
        this.enemy = enemy;

        if(checkIfSkillIsAvailable()) {
            useSkill();
        }
    }

    private boolean checkIfSkillIsAvailable() {
        if(cooldownTurns > 0) {
            System.out.println("Skill is on cooldown");
            return false;
        }
        if(!player.getMana().hasEnoughMana(cost)) {
            System.out.println("Not enough mana");
            return false;
        }
        player.getMana().useMana(cost);
        return true;
    }

    public void updateTurns() {
        if(cooldownTurns > 0) {
            cooldownTurns--;
        }
        if(effectDurationTurns > 0) {
            effectDurationTurns--;
        }
    }
}