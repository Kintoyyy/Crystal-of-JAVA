package Worlds;

import Entities.Entity;
import Map.Map;
import Map.Object.Object;
import Map.Object.ObjectGroup;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class World {
    private final Map world;
    private final String worldKey;
    private final HashMap<String, Battle> battles = new HashMap<>();
    private Point playerLastPosition;
    private final HashMap<String, Entity> npc = new HashMap<>();

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

    public ObjectGroup getObjects() {
        return world.getObjectGroup();
    }

    public String getName() {
        return worldKey;
    }

    public Battle getBattle(String battleName) {
        return battles.get(battleName);
    }

    public World setNpc(Entity... npcObjects) {
        for (Entity entity : npcObjects) {
            npc.put(entity.getName(), entity);
        }
        return this;
    }

    public Entity getNpc(String npcName) {
        return npc.get(npcName);
    }
}
