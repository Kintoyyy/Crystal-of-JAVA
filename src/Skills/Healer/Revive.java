package Skills.Healer;

import Skills.Skill;

public class Revive extends Skill {
    private static final String name = "Revive";
    private static final int cost = 10;
    private static final int damage = 0;
    private static final String description = "Brings a fallen teammate back to life with low health";
    private static final String type = "Healing";

    public Revive() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
