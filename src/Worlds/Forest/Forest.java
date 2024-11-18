package Worlds.Forest;

import Worlds.Forest.Battles.*;
import Worlds.World;

public class Forest extends World {
    public Forest() {
        super("res/Maps/world_1.tmx");

        // setting the battles
        setBattle(BattleNames.BATTLE_1, new Battle1());
        setBattle(BattleNames.BATTLE_2, new Battle2());
        setBattle(BattleNames.BATTLE_3, new Battle3());
    }
}
