package Worlds.Forest.Battles;

import Entities.Enemies.Goblin;
import Entities.Enemies.Kai;
import Entities.Enemies.Orc;
import Worlds.Battle;

public class Battle2 extends Battle {
    public Battle2() {

        name = "Battle 2";

        enemies.add(new Orc());
        enemies.add(new Goblin());
    }
}
