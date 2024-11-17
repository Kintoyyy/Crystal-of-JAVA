package Entities.Enemies;

import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Entities.Enemies.SpecialSkill.FireBreath;

public class Dragon extends Enemy {
    // High health, high attack, has Fire Breath skill.
    public Dragon() {
        super(new Health(2500), new AttackPower(150, 200), new Defense(20), new FireBreath());

        name = "Dragon";
        description = "A generic enemy";
    }
}
