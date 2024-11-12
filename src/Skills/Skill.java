package Skills;

import Characters.Character;

public abstract class Skill {
    private final String name;
    private final String type;
    private final int damage;
    private final int cost;
    private Character character;
    private String description;
    protected String imagePath;

    public Skill(String name,String description, int cost, int damage, String type) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public String getType() {
        return type;
    }

    public abstract void useSkill();
}