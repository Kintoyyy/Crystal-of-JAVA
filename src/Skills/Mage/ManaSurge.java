package Skills.Mage;

import Skills.Skill;

public class ManaSurge extends Skill {

    private static final String name = "Mana Surge";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Restores some mana";
    private static final String type = "Basic";

    public ManaSurge() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
