package Skills.Healer;

import Skills.Skill;
import Skills.SkillType;

public class Blessing extends Skill {
    private static final String name = "Blessing";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Boosts team’s health regen for a few turns";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Blessing() {
        super(name, description, cost, damage, SKILL_TYPE);
    }

    @Override
    public void useSkill() {

    }
}
