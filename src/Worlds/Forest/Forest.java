package Worlds.Forest;

import Worlds.Forest.Battles.*;
import Worlds.World;

import java.awt.*;

public class Forest extends World {
    public Forest() {
        super("res/Maps/world_1.tmx");

        // setting the battles
        setBattle("BATTLE1", new Battle1());
        setBattle("BATTLE2", new Battle2());
//        setBattle(BattleNames.BATTLE_3, new Battle3());
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
