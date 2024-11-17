package Skills.Mage;

import Entities.Characters.Cedi;
import Skills.Skill;
import Skills.SkillType;

public class Mage extends Skill {

    private static final String name = "Basic Magic";
    private static final int cost = 1;
    private static final String description = "Basic Magic";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Mage(Cedi attackPower) {
        super(name, description, cost, attackPower.getAttackPower(), SKILL_TYPE);
        skillImage = sheet.crop(0, 18, 18, 18);
    }

    @Override
    public void useSkill() {
        enemy.takeDamage(damage);
    }
}