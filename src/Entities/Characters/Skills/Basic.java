package Entities.Characters.Skills;

import Entities.Common.AttackPower;

public class Basic extends Skill {

    private static final String name = "Basic Attack";
    private static final int cost = 1;
    private static final String description = "basic attack";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Basic(AttackPower attackPower) {
        super(name, description, cost, attackPower.getAttackPower(), SKILL_TYPE);
    }

    @Override
    public void useSkill() {
        enemy.takeDamage(damage);
    }
}