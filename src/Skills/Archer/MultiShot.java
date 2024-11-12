package Skills.Archer;

import Skills.Skill;

public class MultiShot extends Skill {
    private static final String name = "Multi-Shot";
    private static final int cost = 10;
    private static final int damage = 0;
    private static final String description = "Hits multiple enemies for reduced damage";
    private static final String type = "Healing";

    public MultiShot() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
