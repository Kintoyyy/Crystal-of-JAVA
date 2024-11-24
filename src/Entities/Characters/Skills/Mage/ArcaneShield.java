package Entities.Characters.Skills.Mage;

import Entities.Characters.Skills.SkillType;
import Entities.Characters.Skills.Skill;

public class ArcaneShield extends Skill {

    private static final String name = "Arcane Shield";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Reduces all incoming magic damage for a few turns";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public ArcaneShield() {
        super(name, description, cost, damage, SKILL_TYPE);
        skillImage = sheet.crop(72, 18, 18, 18);
    }

    @Override
    public void useSkill() {
        // Logic for using the skill can go here

    }
}
