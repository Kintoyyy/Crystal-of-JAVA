package Worlds.Forest.Battles;

import Entities.Enemies.Goblin;
import Worlds.Battle;

public class Battle1 extends Battle {
    public Battle1() {
        super();
        
        name = "Battle 1";

        enemies.add(new Goblin());
    }
}
