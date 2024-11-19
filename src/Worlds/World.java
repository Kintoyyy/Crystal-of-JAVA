package Worlds;

import Map.Map;
import Map.Object.Object;
import Worlds.Forest.Battles.BattleNames;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class World {
    protected final String name = "World";
    // map
    protected Map world;
    // battle
    protected HashMap<String, Battle> battles = new HashMap<>();
    protected ArrayList<Object> objects;
    protected Point spawnPoint;

    // The key is the objectId
    public World(String mapPath) {
        world = new Map(mapPath);
        spawnPoint = world.getSpawnPoint();
        objects = world.getObjectGroup().getObjects();
    }

    protected void setBattle(String objectName, Battle battle) {
        battles.put(objectName, battle);
    }

    public Map getWorld() {
        return world;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public HashMap<String, Battle> getBattles() {
        return battles;
    }

    public Point getSpawnPoint() {
        return spawnPoint;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void load() {
    }
}
