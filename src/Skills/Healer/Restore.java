package Skills.Healer;

import Skills.Skill;
import Skills.SkillType;

public class Restore extends Skill {
    private static final String name = "Restore";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Heals a teammate";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Restore() {
        super(name, description, cost, damage, SKILL_TYPE);
    }

    @Override
    public void useSkill() {

    }
}
