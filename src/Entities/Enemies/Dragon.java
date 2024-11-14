package Entities.Enemies;

import Entities.Characters.Stats.AttackPower;
import Entities.Characters.Stats.Defense;
import Entities.Characters.Stats.Health;
import Entities.Enemies.SpecialSkill.FireBreath;

public class Dragon extends Enemy {
    // High health, high attack, has Fire Breath skill.
    public Dragon() {
        super(new Health(2500), new AttackPower(150, 200), new Defense(20), new FireBreath());

        name = "Dragon";
        description = "A generic enemy";
    }
}
