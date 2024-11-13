package Characters;

import Animations.PlayerAnimation;
import Characters.Stats.*;
import Skills.Basic;
import Utils.SpriteSheet;
import Skills.Skill;
import Utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Character {

    protected String name;
    protected String description = "";
    protected int level;
    protected Health health;
    protected Mana mana;
    protected Energy energy;
    protected int experience;
    protected double dodgeRate = 0.0;

    protected Defense defense;
    protected AttackPower attackPower;
    protected double dodgeChance = 0.0;

    protected ArrayList<Skill> skills;
    protected SpriteSheet spriteSheet;
    private PlayerAnimation animation;
    private BufferedImage playerProfile;

    public Character(String name, int level, Health health, Mana mana, AttackPower attackPower, Defense defense, ArrayList<Skill> skills) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.mana = mana;
        this.attackPower = attackPower;
        this.defense = defense;
        this.energy = new Energy(100, 100);
        this.skills = skills;
        this.spriteSheet = new SpriteSheet(ImageUtils.loadImage("/Player/Player_New/Player_Anim/Player_Idle_Run_Death_Anim.png"));
        this.playerProfile = spriteSheet.crop(0, 0, 32, 32);
        this.animation = new PlayerAnimation(120, this.spriteSheet);
        this.experience = 0;
    }

    public void addSkill(Skill skill) {
        // TODO: need to check if skill is compatible with the character
        if( skill == null || skills.contains(skill)) return;
        skill.setCharacter(this);
        skills.add(skill);
    }

    public double getDodgeRate() {
        return dodgeRate;
    }

    public void setDodgeRate(double dodgeRate) {
        this.dodgeRate = dodgeRate;
    }

    public Defense getDefense() {
        return defense;
    }

    public double getDodge() {
        return dodgeChance;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public PlayerAnimation getAnimation() {
        return animation;
    }

    public BufferedImage getProfile() {
        return playerProfile;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Mana getMana() {
        return mana;
    }

    public void setMana(Mana mana) {
        this.mana = mana;
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


