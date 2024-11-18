package Worlds;

import Map.Map;
import Map.Object.Object;
import Worlds.Forest.Battles.BattleNames;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class World {
    protected final String name = "World";
    // map
    protected Map world;
    // battle
    protected HashMap<BattleNames, Battle> battles = new HashMap<>();
    protected ArrayList<Object> objects;

    // The key is the objectId
    public World(String mapPath) {
        world = new Map(mapPath);
        objects = world.getObjectGroup().getObjects();
    }

    protected void setBattle(BattleNames objectName, Battle battle) {
        battles.put(objectName, battle);
    }

    public Map getWorld() {
        return world;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public HashMap<BattleNames, Battle> getBattles() {
        return battles;
    }
}
