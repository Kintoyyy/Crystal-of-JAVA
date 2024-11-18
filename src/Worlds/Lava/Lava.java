package Worlds.Lava;

import Worlds.Lava.Battles.Battle1;
import Worlds.Lava.Battles.Battle2;
import Worlds.Lava.Battles.Battle3;
import Worlds.Lava.Battles.BattleNames;
import Worlds.World;

import java.awt.*;

public class Lava extends World {
    public Lava() {
        super("res/Maps/world_1.tmx");

        // setting the battles
//        setBattle(BattleNames.BATTLE_1, new Battle1());
//        setBattle(BattleNames.BATTLE_2, new Battle2());
//        setBattle(BattleNames.BATTLE_3, new Battle3());
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
