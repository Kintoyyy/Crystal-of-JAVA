package Worlds;

import Animations.Animation;
import Entities.Entity;
import Map.Map;
import Map.Object.CLASS;
import Map.Object.Object;
import Map.Object.Properties;

import java.util.HashMap;
import java.util.List;

public class World {
    private final Map world;
    private final String worldKey;
    private final HashMap<String, Battle> battles = new HashMap<>();
    private final HashMap<String, Entity> npcs = new HashMap<>();
    private final List<Object> objects;

    public World(String worldKey, String mapPath) {
        this.worldKey = worldKey;
        this.world = new Map(mapPath);

        this.objects = world.getObjectGroup().getObjects();
    }

    public World addBattles(Battle... battleObjects) {
        for (Battle battle : battleObjects) {
            battles.put(battle.getKey(), battle);

            // Populate NPC map
            for (Entity entity : battle.getEnemies()) {
                npcs.put(worldKey + "_" + entity.getName(), entity);
            }
        }
        return this;
    }

    public Map getWorld() {
        return world;
    }

//    public Objects getObjects() {
//        return world.getObjectGroup();
//    }

    public List<Object> getObjects() {
        return objects;
    }

    public String getName() {
        return worldKey;
    }

    public Battle getBattle(String battleKey) {
        return battles.get(battleKey);
    }

    public HashMap<String, Battle> getBattles() {
        return battles;
    }

    public Entity getNpc(String npcKey) {
        return npcs.get(npcKey);
    }

    public Animation getNpcAnimation(String npcKey) {
        Entity npc = getNpc(npcKey);
        return npc != null ? npc.getAnimation() : null;
    }


}
