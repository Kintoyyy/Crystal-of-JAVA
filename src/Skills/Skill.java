package Skills;

public abstract class Skill {
    public String name;
    public String description;
    public String type;
    public int damage;
    public int cost;

    public Skill(String name,String description, int cost, int damage, String type) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
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

    // Method to use the skill
    public abstract void useSkill();
}