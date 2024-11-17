package Entities.Characters.Skills.Warrior;

import Entities.Characters.Skills.SkillType;
import Entities.Characters.Skills.Skill;

public class WarCry extends Skill {

    private static final String name = "War Cry";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Increases team’s attack power temporarily";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public WarCry() {
        super(name, description, cost, damage, SKILL_TYPE,3,1);
        skillImage = sheet.crop(54, 0, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
