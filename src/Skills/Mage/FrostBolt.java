package Skills.Mage;

import Skills.Skill;

public class FrostBolt extends Skill {

    private static final String name = "Frost Bolt";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Slows enemy and deals damage";
    private static final String type = "Basic";

    public FrostBolt() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
