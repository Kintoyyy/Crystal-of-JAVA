package Skills.Warrior;

import Skills.SkillType;
import Skills.Skill;

public class Taunt extends Skill {

    private static final String name = "Taunt";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Forces enemies to target Warrior for 1 turn";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Taunt() {
        super(name, description, cost, damage, SKILL_TYPE);
    }

    @Override
    public void useSkill() {

    }
}
