package Entities.Enemies;

import Entities.Characters.Stats.AttackPower;
import Entities.Characters.Stats.Defense;
import Entities.Characters.Stats.Health;
import Entities.Enemies.SpecialSkill.LifeSteal;

public class Vampire extends Enemy {
    // Moderate health, moderate attack, regenerates health when it attacks.
    public Vampire() {
        super(new Health(1000), new AttackPower(100), new Defense(10), new LifeSteal());

        name = "Vampire";
        description = "A generic enemy";
    }
}
