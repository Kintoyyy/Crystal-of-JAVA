package Skills.Archer;

import Skills.Skill;

public class PoisonArrow extends Skill {
    private static final String name = "Poison Arrow";
    private static final int cost = 10;
    private static final int damage = 0;
    private static final String description = "Deals damage over time to an enemy";
    private static final String type = "Healing";

    public PoisonArrow() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
