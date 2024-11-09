package Characters;

import Animations.PlayerAnimation;
import Utils.SpriteSheet;
import Skills.Skill;
import Utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Character {

    private String name;
    private String description = "";
    private int level;
    private int health;
    private int mana;
    private int baseHealth;
    private int experience;
    public ArrayList<Skill> skills = new ArrayList<>(3);
    public SpriteSheet spriteSheet;
    private PlayerAnimation animation;
    private BufferedImage playerProfile;

    public Character(String name, int level, int health, int mana, int baseHealth, ArrayList<Skill> skills) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.mana = mana;
        this.baseHealth = baseHealth;
        this.skills = skills;
        this.spriteSheet = new SpriteSheet(ImageUtils.loadImage("/Player/Player_New/Player_Anim/Player_Idle_Run_Death_Anim.png"));
        this.playerProfile = spriteSheet.crop(0, 0, 32, 32);
        this.animation = new PlayerAnimation(120, this.spriteSheet);
        this.experience = 0;
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

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getBaseHealth() {
        return baseHealth;
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
}


