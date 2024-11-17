package Entities.Characters.Skills.Mage;

import Entities.Characters.Skills.Skill;
import Entities.Characters.Skills.SkillType;

public class ManaSurge extends Skill {

    private static final String name = "Mana Surge";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Restores some mana";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public ManaSurge() {
        super(name, description, cost, damage, SKILL_TYPE);
        skillImage = sheet.crop(36, 18, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
