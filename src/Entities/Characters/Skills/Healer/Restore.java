package Entities.Characters.Skills.Healer;

import Entities.Characters.Skills.Skill;
import Entities.Characters.Skills.SkillType;

public class Restore extends Skill {
    private static final String name = "Restore";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Heals a teammate";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Restore() {
        super(name, description, cost, damage, SKILL_TYPE);
        skillImage = sheet.crop(18, 54, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
