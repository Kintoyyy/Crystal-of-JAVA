package Worlds;

import Map.Map;
import Map.Object.Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class World {
    private final Map world;
    private final String worldKey;
    private final HashMap<String, Battle> battles = new HashMap<>();
    private Point playerLastPosition;

    public World(String worldKey, String mapPath) {
        this.worldKey = worldKey;
        this.world = new Map(mapPath);
    }

    public World setBattles(Battle... battlesObjects) {
        for (Battle battle : battlesObjects) {
            battles.put(battle.getKey(), battle);
        }
        return this;
    }

    public Map getWorld() {
        return world;
    }

    public ArrayList<Object> getObjects() {
        return world.getObjects();
    }


    public Point getSpawnPoint() {
        return world.getSpawnPoint();
    }

    public String getName() {
        return worldKey;
    }

    public Battle getBattle(String battleName) {
        return battles.get(battleName);
    }

    public Point getPlayerLastPosition() {
        return playerLastPosition;
    }

    public void setPlayerLastPosition(Point playerLastPosition) {
        this.playerLastPosition = playerLastPosition;
    }
}
