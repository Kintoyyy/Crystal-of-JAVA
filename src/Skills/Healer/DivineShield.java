package Skills.Healer;

import Skills.Skill;

public class DivineShield extends Skill {
    private static final String name = "Divine Shield";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Blocks all damage to a teammate for 1 turn";
    private static final String type = "Healing";

    public DivineShield() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
