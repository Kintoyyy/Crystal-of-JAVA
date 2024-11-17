package Entities.Characters.Skills.Archer;

import Entities.Characters.Skills.Skill;
import Entities.Characters.Skills.SkillType;

public class PrecisionShot extends Skill {
    private static final String name = "Precision Shot";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Ignores enemy defense";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public PrecisionShot() {
        super(name, description, cost, damage, SKILL_TYPE);
        skillImage = sheet.crop(18, 36, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
