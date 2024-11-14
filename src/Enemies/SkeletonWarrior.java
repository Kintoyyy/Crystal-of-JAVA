package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;

public class SkeletonWarrior extends Enemy {
    // Moderate health, high defense, low attack, immune to poison.
    public SkeletonWarrior() {
        super(new Health(800), new AttackPower(50, 70), new Defense(40));

        name = "Skeleton Warrior";
        description = "A generic enemy";
    }
}
