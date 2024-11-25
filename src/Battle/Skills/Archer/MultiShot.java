package Battle.Skills.Archer;

import Battle.Skills.Skill;
import Entities.Enemies.Enemy;

public class MultiShot extends Skill {
    private static final String name = "Multi-Shot";
    private static final int cost = 20;
    private static final String description = "Hits multiple enemies for reduced damage";

    public MultiShot() {
        super(name, description, cost, 3, 0);
        skillImage = sheet.crop(36, 36, 18, 18);
    }

    @Override
    public void useSkill() {
        for (Enemy enemy : enemies) {
            enemy.takeDamage(randomizeDamage(200, 300));
        }
    }
}
