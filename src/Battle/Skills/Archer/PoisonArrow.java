package Battle.Skills.Archer;

import Battle.Skills.Skill;

public class PoisonArrow extends Skill {
    private static final String name = "Poison Arrow";
    private static final int cost = 15;
    private static final String description = "Deals damage over time to an selectedEnemy";

    public PoisonArrow() {
        super(name, description, cost, 3, 3);
        skillImage = sheet.crop(54, 36, 18, 18);
    }

    @Override
    public void useSkill() {
        selectedEnemy.takeDamage(randomizeDamage(300, 500));
    }
}
