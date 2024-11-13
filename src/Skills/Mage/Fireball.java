package Skills.Mage;

import Skills.Skill;

public class Fireball extends Skill {

    private static final String name = "Fireball";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Moderate AoE damage";
    private static final String type = "Basic";

    public Fireball() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
