package Worlds;

import Map.Map;

import java.util.ArrayList;

public abstract class World {
    protected final String name = "World";
    // map
    protected Map world;
    // battles
    protected ArrayList<Battle> battles = new ArrayList<>();

    public World() {
    }
}
