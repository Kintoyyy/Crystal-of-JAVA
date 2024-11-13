package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;
import Enemies.SpecialSkill.ManaDrain;

public class DarkSorcerer extends Enemy {
    // Low health, high attack, can cast curses that reduce player’s mana.
    public DarkSorcerer() {
        super(new Health(400), new AttackPower(150), new Defense(10),  new ManaDrain());
        name = "Dark Sorcerer";
        description = "A generic enemy";
    }
}
