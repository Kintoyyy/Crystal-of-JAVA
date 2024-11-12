package Skills.Healer;

import Skills.Skill;

public class Blessing extends Skill {
    private static final String name = "Blessing";
    private static final int cost = 10;
    private static final int damage = 0;
    private static final String description = "Boosts team’s health regen for a few turns";
    private static final String type = "Healing";

    public Blessing() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
