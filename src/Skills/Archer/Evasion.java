package Skills.Archer;

import Enemies.Enemy;
import Skills.Skill;

public class Evasion extends Skill {
    private static final String name = "Evasion";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Increases dodge chance for a few turns";
    private static final String type = "Healing";

    public Evasion() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {
    }
}
