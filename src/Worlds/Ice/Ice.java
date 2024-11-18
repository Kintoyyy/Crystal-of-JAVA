package Worlds.Ice;

import Worlds.Ice.Battles.Battle1;
import Worlds.Ice.Battles.Battle2;
import Worlds.Ice.Battles.Battle3;
import Worlds.Ice.Battles.BattleNames;
import Worlds.World;

import java.awt.*;

public class Ice extends World {
    public Ice() {
        super("res/Maps/world_2.tmx");

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
