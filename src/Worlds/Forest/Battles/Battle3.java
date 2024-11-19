package Worlds.Forest.Battles;

import Entities.Enemies.Goblin;
import Entities.Enemies.Kai;
import Entities.Enemies.Orc;
import Worlds.Battle;

public class Battle3 extends Battle {
    public Battle3() {

        name = "Battle 3";

        enemies.add(new Kai());
        enemies.add(new Orc());
        enemies.add(new Goblin());
    }
}
