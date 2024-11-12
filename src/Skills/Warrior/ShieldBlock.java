package Skills.Warrior;

import Skills.Skill;

public class ShieldBlock extends Skill {

    private static final String name = "Shield Block";
    private static final int cost = 0;
    private static final int damage = 20;
    private static final String description = "Blocks 50% of incoming damage for 1 turn";
    private static final String type = "warrior";

    public ShieldBlock() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
