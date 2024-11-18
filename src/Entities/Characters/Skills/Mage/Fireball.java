package Entities.Characters.Skills.Mage;

import Entities.Characters.Skills.Skill;
import Entities.Characters.Skills.SkillType;

public class Fireball extends Skill {

    private static final String name = "Fireball";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Moderate AoE damage";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Fireball() {
        super(name, description, cost, damage, SKILL_TYPE);
        skillImage = sheet.crop(18,18, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
