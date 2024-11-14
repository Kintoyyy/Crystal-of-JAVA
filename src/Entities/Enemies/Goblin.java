package Entities.Enemies;

import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;

public class Goblin extends Enemy {
    // Low health, high dodge rate, may steal mana.
    public Goblin() {
        super(new Health(500), new AttackPower(50), new Defense(0));
        name = "Goblin";
        description = "A generic enemy";
        dodge = 0.2; // 20% chance to dodge attacks
    }
}
