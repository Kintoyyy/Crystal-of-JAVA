package Battle.Effects;

import Entities.Characters.Character;
import Entities.Enemies.Enemy;

import java.util.List;

public abstract class Effect {
    private int remainingTurns;

    public Effect(int durationTurns) {
        this.remainingTurns = durationTurns;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public void reduceTurn() {
        if (remainingTurns > 0) {
            remainingTurns--;
        }
    }

    public boolean isExpired() {
        return remainingTurns <= 0;
    }

    // Apply effect on a single character or selectedEnemy
    public abstract void applyToCharacter(Character character);

    public abstract void applyToEnemy(Enemy enemy);

    // Apply effect on multiple targets
    public void applyToCharacters(List<Character> characters) {
        for (Character character : characters) {
            applyToCharacter(character);
        }
    }

    public void applyToEnemies(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            applyToEnemy(enemy);
        }
    }

    public abstract void apply(); // General effect application (if needed)

    public abstract void remove(); // Cleanup after expiration
}
