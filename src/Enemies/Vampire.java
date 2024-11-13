package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;
import Enemies.SpecialSkill.LifeSteal;

public class Vampire extends Enemy {
    // Moderate health, moderate attack, regenerates health when it attacks.
    public Vampire() {
        super(new Health(1000), new AttackPower(100), new Defense(10), new LifeSteal());

        name = "Vampire";
        description = "A generic enemy";
    }
}
