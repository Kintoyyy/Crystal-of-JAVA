package Entities.Characters.Skills.Warrior;

import Entities.Characters.Skills.SkillType;
import Entities.Characters.Skills.Skill;

public class ShieldBlock extends Skill {

    private static final String name = "Shield Block";
    private static final int cost = 10;
    private static final int damage = 0;
    private static final String description = "Blocks 50% of incoming damage for 1 turn";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public ShieldBlock() {
        super(name, description, cost, damage, SKILL_TYPE,3,1);
        skillImage = sheet.crop(36, 0, 18, 18);
    }

    @Override
    public void useSkill() {
//        System.out.println(battleManagerOld);
    }
}
