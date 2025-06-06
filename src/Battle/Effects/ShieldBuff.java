package Battle.Effects;

import Entities.Characters.Character;
import Entities.Enemies.Enemy;

public class ShieldBuff extends Effect {
    /**
     * Constructs an Effect instance.
     *
     * @param turnsDuration The number of turns the effect lasts.
     * @param effectValue   The magnitude or value of the effect.
     */
    public ShieldBuff(int turnsDuration, double effectValue) {
        super(3);
    }


    @Override
    public void applyToCharacter(Character character) {

    }

    @Override
    public void applyToEnemy(Enemy enemy) {

    }

    @Override
    public void apply() {

    }

    @Override
    public void remove() {

    }
}