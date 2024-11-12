package Skills.Archer;

import Skills.Skill;

public class PrecisionShot extends Skill {
    private static final String name = "Precision Shot";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Ignores enemy defense";
    private static final String type = "Healing";

    public PrecisionShot() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
