package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;

public class Golem extends Enemy {
    // Very high health, low speed, high defense, vulnerable to magic.
    public Golem() {
        super(new Health(180), new AttackPower(8), new Defense(15));
    }

    @Override
    void attack(Character character) {

    }

    @Override
    void useSpecialSkill(Character character) {

    }
}
