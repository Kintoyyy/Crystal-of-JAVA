package Skills.Archer;

import Skills.Skill;
import Skills.SkillType;

public class PoisonArrow extends Skill {
    private static final String name = "Poison Arrow";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Deals damage over time to an enemy";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public PoisonArrow() {
        super(name, description, cost, damage, SKILL_TYPE);
    }

    @Override
    public void useSkill() {

    }
}
