package Entities.Characters.Skills.Warrior;

import Entities.Characters.Kent;
import Entities.Characters.Skills.Skill;
import Entities.Characters.Skills.SkillType;

public class Warrior extends Skill {

    private static final String name = "Basic Strike";
    private static final int cost = 1;
    private static final String description = "Basic Strike";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Warrior(Kent attackPower) {
        super(name, description, cost, attackPower.getAttackPower(), SKILL_TYPE);
        skillImage = sheet.crop(0, 0, 18, 18);
    }

    @Override
    public void useSkill() {
        enemy.takeDamage(damage);
    }
}