package Skills;

import Characters.Character;
import Enemies.Enemy;
import Game.BattleManager;

public abstract class Skill {
    private final String name;
    private final SkillType skillType;
    protected final double damage;
    private final int cost;
    protected Character player;
    private String description;
    protected String imagePath;
    private int cooldownTurns = 0;
    private int effectDurationTurns = 0;
    protected BattleManager battleManager;

    protected Enemy enemy;

    public Skill(String name,String description, int cost, double damage, SkillType skillType) {
        this(name, description, cost, damage, skillType, 0, 0);
    }

    public Skill(String name, String description, int cost, double damage, SkillType skillType, int cooldownTurns, int effectDurationTurns) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.skillType = skillType;
        this.cooldownTurns = cooldownTurns;
        this.effectDurationTurns = effectDurationTurns;
    }

    public String getName() {
        return name;
    }

    public int getCooldownTurns() {
        return cooldownTurns;
    }

    public int getEffectDurationTurns() {
        return effectDurationTurns;
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

    public SkillType getType() {
        return skillType;
    }

    // battleManager.getLastEnemy()
    // battleManager.attackLowest()
    // battleManager.attackAll()
    // etc
    public abstract void useSkill();

    public void attack(BattleManager battleManager) {
        this.enemy = battleManager.getCurrentEnemy();

        if(checkIfSkillIsAvailable()) {
            useSkill();
            battleManager.updateTurnState();
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