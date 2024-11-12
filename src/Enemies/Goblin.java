package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;

public class Goblin extends Enemy {
    // Low health, high dodge rate, may steal mana.
    public Goblin() {
        super(new Health(60), new AttackPower(10), new Defense(5));
    }

    @Override
    void attack(Character character) {

    }

    @Override
    void useSpecialSkill(Character character) {

    }
}
