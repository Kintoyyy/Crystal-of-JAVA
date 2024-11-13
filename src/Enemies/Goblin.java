package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;

public class Goblin extends Enemy {
    // Low health, high dodge rate, may steal mana.
    public Goblin() {
        super(new Health(500), new AttackPower(50), new Defense(0));
        name = "Goblin";
        description = "A generic enemy";
    }
}
