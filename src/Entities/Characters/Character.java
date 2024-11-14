package Entities.Characters;

import Animations.PlayerAnimation;
import Entities.Characters.Stats.*;
import Entities.Entity;
import Utils.SpriteSheet;
import Skills.Skill;
import Utils.ImageUtils;

import java.awt.*;
import java.util.ArrayList;

public abstract class Character extends Entity {
    // Character only attributes
    protected Mana mana;
    protected Energy energy;
    protected ArrayList<Skill> skills;
    protected int level;
    protected int experience;
    protected double dodgeRate = 0.0;
    protected double dodgeChance = 0.0;

    public Character(String name, int level, Health health, Mana mana, AttackPower attackPower, Defense defense, ArrayList<Skill> skills) {
        super(0, 0, 32, 32);

        this.spriteSheet = new SpriteSheet(ImageUtils.loadImage("/Player/Player_New/Player_Anim/Player_Idle_Run_Death_Anim.png"));
        this.animation = new PlayerAnimation(120, this.spriteSheet);
        this.description = "A generic enemy";

        this.name = name;
        this.level = level;
        this.health = health;
        this.mana = mana;
        this.attackPower = attackPower;
        this.defense = defense;
        this.energy = new Energy(100, 100);
        this.skills = skills;

        this.profileImage = spriteSheet.crop(0, 0, 32, 32);

        this.experience = 0;
    }

    public void addSkill(Skill skill) {
        // TODO: need to check if skill is compatible with the character
        if (skill == null || skills.contains(skill)) return;
        skill.setCharacter(this);
        skills.add(skill);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    public double getDodgeRate() {
        return dodgeRate;
    }

    public void setDodgeRate(double dodgeRate) {
        this.dodgeRate = dodgeRate;
    }

    public double getDodge() {
        return dodgeChance;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public abstract void useSkill(int index);

    public abstract void useSkill(Skill skill);

    public Mana getMana() {
        return mana;
    }

    public void setMana(Mana mana) {
        this.mana = mana;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public void regenHealth() {
        health.regenHealth();
    }

    public void regenMana() {
        mana.regenMana();
    }

    public double getAttackPower() {
        return attackPower.getAttackPower();
    }
}


