package Characters;

import Utils.SpriteSheet;
import Skills.Skill;
import Utils.ImageUtils;

import java.util.ArrayList;

public abstract class Character {

    private String name;
    private String description = "";
    private int level;
    private int health;
    private int mana;
    private int baseHealth;
    public ArrayList<Skill> skills;
    public SpriteSheet spriteSheet;

    public Character(String name, int level, int health, int mana, int baseHealth, ArrayList<Skill> skills) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.mana = mana;
        this.baseHealth = baseHealth;
        this.skills = skills;
        this.spriteSheet = new SpriteSheet(ImageUtils.loadImage("/textures/Default.png"));
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
}


