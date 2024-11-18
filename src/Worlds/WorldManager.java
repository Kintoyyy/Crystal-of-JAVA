package Worlds;

import Worlds.Forest.Forest;

import java.util.ArrayList;

public class WorldManager {
    private final ArrayList<World> worlds = new ArrayList<>();
    private int currentWorld = 0;

    public WorldManager() {
        // set the worlds
        worlds.add(new Forest());
    }

    public World getCurrentWorld() {
        return worlds.get(currentWorld);
    }

    public void setCurrentWorld(int world) {
        currentWorld = world;
    }

    public void setCurrentWorld(World world) {
        currentWorld = worlds.indexOf(world);
    }

    public void nextWorld() {
        currentWorld++;
    }

    public void previousWorld() {
        currentWorld--;
    }

    public int getCurrentWorldIndex() {
        return currentWorld;
    }

    public int getWorldsSize() {
        return worlds.size();
    }

    public World getWorld(int index) {
        return worlds.get(index);
    }
}
