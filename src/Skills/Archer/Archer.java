package Skills.Archer;

import Characters.Nathan;
import Skills.Skill;
import Skills.SkillType;

public class Archer extends Skill {

    private static final String name = "Basic Arrow";
    private static final int cost = 1;
    private static final String description = "Basic Arrow";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Archer(Nathan attackPower) {
        super(name, description, cost, attackPower.getAttackPower(), SKILL_TYPE);
        skillImage = sheet.crop(0, 36, 18, 18);
    }

    @Override
    public void useSkill() {
        enemy.takeDamage(damage);
    }
}