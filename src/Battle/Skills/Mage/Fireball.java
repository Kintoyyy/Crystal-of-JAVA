package Battle.Skills.Mage;

import Battle.Skills.Skill;
import Entities.Enemies.Enemy;

public class Fireball extends Skill {

    private static final String name = "Fireball";
    private static final int cost = 25;
    private static final String description = "Moderate AoE damage";

    public Fireball() {
        super(name, description, cost, 2, 0);
        skillImage = sheet.crop(18, 18, 18, 18);
    }

    @Override
    public void useSkill() {
        for (Enemy enemy : enemies) {
            // Deal damage to all enemies
            enemy.takeDamage(randomizeDamage(150, 250));
        }
    }
}
