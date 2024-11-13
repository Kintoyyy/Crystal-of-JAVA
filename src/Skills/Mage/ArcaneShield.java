package Skills.Mage;

import Skills.SkillType;
import Skills.Skill;

public class ArcaneShield extends Skill {

    private static final String name = "Arcane Shield";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Reduces all incoming magic damage for a few turns";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public ArcaneShield() {
        super(name, description, cost, damage, SKILL_TYPE);
    }

    @Override
    public void useSkill() {

    }
}
