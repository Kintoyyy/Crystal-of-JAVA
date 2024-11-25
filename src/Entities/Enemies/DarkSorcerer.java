package Entities.Enemies;

import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Entities.Enemies.SpecialSkill.ManaDrain;

public class DarkSorcerer extends Enemy {
    // Low health, high attack, can cast curses that reduce selectedPlayer’s mana.
    public DarkSorcerer() {
        super(new Health(400), new AttackPower(150), new Defense(10),  new ManaDrain());
        name = "Dark Sorcerer";
        description = "A generic selectedEnemy";
    }
}
