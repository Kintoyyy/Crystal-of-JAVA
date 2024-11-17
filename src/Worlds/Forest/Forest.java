package Worlds.Forest;

import Map.Map;
import Worlds.World;

public class Forest extends World {
    public Forest() {
        world = new Map("res/Maps/world_1.tmx");
        loadbattle(world);
    }


    private void loadbattle(Map world){

    }
}
