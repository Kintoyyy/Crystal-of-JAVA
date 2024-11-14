package Entities.Enemies;

import Entities.Characters.Stats.AttackPower;
import Entities.Characters.Stats.Defense;
import Entities.Characters.Stats.Health;

public class SkeletonWarrior extends Enemy {
    // Moderate health, high defense, low attack, immune to poison.
    public SkeletonWarrior() {
        super(new Health(800), new AttackPower(50, 70), new Defense(40));

        name = "Skeleton Warrior";
        description = "A generic enemy";
    }
}
