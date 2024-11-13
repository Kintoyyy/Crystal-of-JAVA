package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;

public class Kai extends Enemy {
    // Ultra-high health, ultra-high attack, has a special skill that can instantly kill the player.
    public Kai() {
        super(new Health(2500), new AttackPower(200), new Defense(20));
        this.name = "Kai";
    }
}
