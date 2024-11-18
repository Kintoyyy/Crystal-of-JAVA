package Worlds.Dungeon;

import Worlds.World;

import java.awt.*;

public class Dungeon extends World {
    public Dungeon() {
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
