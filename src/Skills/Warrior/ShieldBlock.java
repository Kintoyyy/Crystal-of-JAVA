package Skills.Warrior;

import Skills.SkillType;
import Skills.Skill;

public class ShieldBlock extends Skill {

    private static final String name = "Shield Block";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Blocks 50% of incoming damage for 1 turn";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public ShieldBlock() {
        super(name, description, cost, damage, SKILL_TYPE);
    }

    @Override
    public void useSkill() {

    }
}
