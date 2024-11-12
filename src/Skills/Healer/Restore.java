package Skills.Healer;

import Skills.Skill;

public class Restore extends Skill {
    private static final String name = "Restore";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Heals a teammate";
    private static final String type = "Healing";

    public Restore() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
