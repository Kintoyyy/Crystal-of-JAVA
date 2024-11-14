package Skills.Healer;

import Entities.Characters.Zeith;
import Skills.Skill;
import Skills.SkillType;

public class Healer extends Skill {

    private static final String name = "Basic Attack";
    private static final int cost = 1;
    private static final String description = "Basic attack";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Healer(Zeith attackPower) {
        super(name, description, cost, attackPower.getAttackPower(), SKILL_TYPE);
        skillImage = sheet.crop(0, 54, 18, 18);
    }

    @Override
    public void useSkill() {
        enemy.takeDamage(damage);
    }
}