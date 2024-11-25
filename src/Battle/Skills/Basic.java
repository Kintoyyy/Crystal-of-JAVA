package Battle.Skills;

import Entities.Common.AttackPower;

public class Basic extends Skill {

    private static final String name = "Basic Attack";
    private static final int cost = 1;
    private static final String description = "basic attack";

    public Basic(AttackPower attackPower) {
        super(name, description, cost, 0, 0);
    }

    @Override
    public void useSkill() {
//        selectedEnemy.takeDamage(damage);
    }
}