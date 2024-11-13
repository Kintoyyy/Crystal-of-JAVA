package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;
import Enemies.SpecialSkill.FireBreath;

public class Dragon extends Enemy {
    // High health, high attack, has Fire Breath skill.
    public Dragon() {
        super(new Health(200), new AttackPower(25), new Defense(12), new FireBreath());
    }
}
