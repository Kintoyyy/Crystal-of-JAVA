package Skills.Healer;

import Skills.Skill;
import Skills.SkillType;

public class DivineShield extends Skill {
    private static final String name = "Divine Shield";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Blocks all damage to a teammate for 1 turn";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public DivineShield() {
        super(name, description, cost, damage, SKILL_TYPE);
        skillImage = sheet.crop(36, 54, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
