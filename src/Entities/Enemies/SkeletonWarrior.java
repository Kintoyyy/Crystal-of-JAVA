package Entities.Enemies;

import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;

public class SkeletonWarrior extends Enemy {
    // Moderate health, high defense, low attack, immune to poison.
    public SkeletonWarrior() {
        super(new Health(800), new AttackPower(50, 70), new Defense(40));

        name = "Skeleton Warrior";
        key = "SKELETON_WARRIOR";
        description = "A generic selectedEnemy";
    }
}
