package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;

public class SkeletonWarrior extends Enemy {
    // Moderate health, high defense, low attack, immune to poison.
    public SkeletonWarrior() {
        super(new Health(85), new AttackPower(7), new Defense(10));
    }
}
