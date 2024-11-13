package Skills;

import Characters.Stats.AttackPower;

public class Basic extends Skill {

    private static final String name = "Basic";
    private static final int cost = 0;
    private static final String description = "Basic attack";
    private static final String type = "all";

    public Basic(AttackPower attackPower) {
        super(name, description, cost, attackPower.getAttackPower(), type);
    }

    @Override
    public void useSkill() {
        enemy.takeDamage(damage);
    }
}