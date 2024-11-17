package Entities.Characters.Skills.Archer;

import Entities.Characters.Skills.Skill;
import Entities.Characters.Skills.SkillType;

public class Evasion extends Skill {
    private static final String name = "Evasion";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Increases dodge chance for a few turns";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Evasion() {
        super(name, description, cost, damage, SKILL_TYPE);
        skillImage = sheet.crop(72, 36, 18, 18);
    }

    @Override
    public void useSkill() {
    }
}
