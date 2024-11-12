package Skills.Warrior;

import Skills.Skill;

public class PowerStrike extends Skill {

    private static final String name = "Power Strike";
    private static final String description = "High single-target damage";
    private static final int cost = 0;
    private static final int damage = 20;
    private static final String type = "warrior";

    public PowerStrike() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
