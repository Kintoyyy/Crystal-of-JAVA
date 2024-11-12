package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;

public class Orc extends Enemy {
    // High health, high defense, strong attack.
    public Orc() {
        super(new Health(130), new AttackPower(15), new Defense(8));
    }
}
