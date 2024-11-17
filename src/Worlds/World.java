package Worlds;

import Map.Map;
import Worlds.Forest.Battles.*;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class World {
    protected final String name = "World";
    // map
    protected Map world;
    // battles
    protected ArrayList<Battle> battles = new ArrayList<>();

    // The key is the objectId
    protected HashMap<String, Battle> battleMap = new HashMap<>();

    public World() {

        battles.add(new Battle1());
        battles.add(new Battle2());
        battles.add(new Battle3());


    }
}
