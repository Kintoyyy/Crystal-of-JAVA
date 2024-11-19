package Worlds;

import Entities.Characters.CharacterManager;
import Game.Handler;
import Map.Movement.Movement;
import Map.Render;
import Worlds.Dungeon.Dungeon;
import Worlds.Enums.WorldNames;
import Worlds.Forest.Forest;
import Worlds.Ice.Battles.BattleNames;
import Worlds.Ice.Ice;
import Worlds.Lava.Lava;

import java.awt.*;
import java.util.HashMap;

public class WorldManager {
    private final Render render;
    private final HashMap<WorldNames, World> worlds = new HashMap<>();
    private WorldNames currentWorld = WorldNames.FOREST;
    private final Movement movement;

    public WorldManager(Handler handler) {
        handler.setWorldManager(this);

        // Add the world to the game
        addWorld(WorldNames.FOREST, new Forest());
        addWorld(WorldNames.ICE, new Ice());
        addWorld(WorldNames.LAVA, new Lava());
        addWorld(WorldNames.DUNGEON, new Dungeon());

        // Ensure the current world exists
        if (getCurrentWorld() == null) {
            throw new IllegalStateException("Current world is null! Ensure worlds are added correctly.");
        }

        CharacterManager characterManager = handler.getGameState().getCharacterManger();

        movement = new Movement(handler, this, characterManager);

        this.render = new Render(movement);
    }


    public World getCurrentWorld() {
        return worlds.get(currentWorld);
    }


    public Battle getBattle(String battleName) {
        return worlds.get(currentWorld).getBattles().get(battleName);
    }


    public void setCurrentWorld(WorldNames world) {
        System.out.println("Setting current world to " + world);
//        if (!worlds.containsKey(world)) {
//            throw new IllegalArgumentException("World " + world + " does not exist!");
//        }
        currentWorld = world;
        movement.setLocation(getCurrentWorld().getSpawnPoint());
        render.loadWorld();
    }

    public void tick() {
        getCurrentWorld().tick();
        render.tick();
    }

    public void render(Graphics g) {
        render.render(g);
    }

    public void setWorld(WorldNames world) {
        if (world == null) {
            throw new IllegalArgumentException("World cannot be null.");
        }
        if (worlds.get(world) == null) {
            throw new IllegalArgumentException("World " + world + " does not exist.");
        }
        System.out.println("Setting world to " + world);
        setCurrentWorld(world);
    }

    public World getWorld(WorldNames world) {
        return worlds.get(world);
    }

    public void setWorld(WorldNames world, World w) {
        worlds.put(world, w);
    }

    public void removeWorld(WorldNames world) {
        worlds.remove(world);
    }

    public void clearWorlds() {
        worlds.clear();
    }

    public void clearCurrentWorld() {
        worlds.remove(currentWorld);
    }

    public void clear() {
        clearWorlds();
        clearCurrentWorld();
    }

    // Correctly implement this method to populate the worlds map
    public void addWorld(WorldNames worldNames, World world) {
        if (worldNames == null || world == null) {
            throw new IllegalArgumentException("World name and world cannot be null.");
        }
        worlds.put(worldNames, world);
    }
}
