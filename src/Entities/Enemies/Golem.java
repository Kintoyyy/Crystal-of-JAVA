package Entities.Enemies;

import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;

public class Golem extends Enemy {
    // Very high health, low speed, high defense, vulnerable to magic.
    public Golem() {
        super(new Health(2000), new AttackPower(60, 80), new Defense(30));

        name = "Golem";
        description = "A generic selectedEnemy";
    }
}
