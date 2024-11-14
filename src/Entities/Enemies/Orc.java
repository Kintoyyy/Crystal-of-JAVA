package Entities.Enemies;

import Entities.Characters.Stats.AttackPower;
import Entities.Characters.Stats.Defense;
import Entities.Characters.Stats.Health;

public class Orc extends Enemy {
    // High health, high defense, strong attack.
    public Orc() {
        super(new Health(1500), new AttackPower(120, 150), new Defense(30));

        name = "Orc";
        description = "A generic enemy";
    }
}
