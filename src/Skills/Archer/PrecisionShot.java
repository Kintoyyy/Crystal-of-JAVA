package Skills.Archer;

import Skills.Skill;
import Skills.SkillType;

public class PrecisionShot extends Skill {
    private static final String name = "Precision Shot";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Ignores enemy defense";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public PrecisionShot() {
        super(name, description, cost, damage, SKILL_TYPE);
    }

    @Override
    public void useSkill() {

    }
}
