package Skills.Archer;

import Skills.Skill;
import Skills.SkillType;

public class MultiShot extends Skill {
    private static final String name = "Multi-Shot";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Hits multiple enemies for reduced damage";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public MultiShot() {
        super(name, description, cost, damage, SKILL_TYPE);
    }

    @Override
    public void useSkill() {

    }
}
