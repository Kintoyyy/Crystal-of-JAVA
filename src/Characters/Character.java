package Characters;

import Animations.PlayerAnimation;
import Characters.Stats.Energy;
import Characters.Stats.Health;
import Characters.Stats.Mana;
import Skills.Basic;
import Skills.Healing;
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
    protected ArrayList<Skill> skills = new ArrayList<>(3);
    public SpriteSheet spriteSheet;
    private PlayerAnimation animation;
    private BufferedImage playerProfile;

    public Character(String name, int level, Health health, Mana mana, ArrayList<Skill> skills) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.mana = mana;
        this.energy = new Energy(100, 100);
        this.skills = skills;
        this.spriteSheet = new SpriteSheet(ImageUtils.loadImage("/Player/Player_New/Player_Anim/Player_Idle_Run_Death_Anim.png"));
        this.playerProfile = spriteSheet.crop(0, 0, 32, 32);
        this.animation = new PlayerAnimation(120, this.spriteSheet);
        this.experience = 0;

        this.skills.add(new Basic());
        this.skills.add(new Healing());
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
}


