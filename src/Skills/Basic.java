package Skills;

import Characters.Stats.AttackPower;

public class Basic extends Skill {

    private static final String name = "Warrior";
    private static final int cost = 1;
    private static final String description = "Warrior attack";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Basic(AttackPower attackPower) {
        super(name, description, cost, attackPower.getAttackPower(), SKILL_TYPE);
    }

    @Override
    public void useSkill() {
        enemy.takeDamage(damage);
    }
}