package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;

public class Orc extends Enemy {
    // High health, high defense, strong attack.
    public Orc() {
        super(new Health(1500), new AttackPower(120, 150), new Defense(30));

        name = "Orc";
        description = "A generic enemy";
    }
}
