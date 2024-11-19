package Worlds;

import Entities.Enemies.Goblin;
import Entities.Enemies.Kai;
import Entities.Enemies.Orc;

import java.util.HashMap;

public class Worlds {
    private final HashMap<String, World> worlds = new HashMap<>();

    public Worlds() {
        this.setWorlds(
                new World("FOREST", "res/Maps/Forest.tmx").setBattles(
                        new Battle("BATTLE1").enemies(
                                new Goblin()
                        ),
                        new Battle("BATTLE2").enemies(
                                new Orc(),
                                new Goblin()
                        ),
                        new Battle("BATTLE3").enemies(
                                new Kai(),
                                new Orc(),
                                new Goblin()
                        )
                ),
                new World("ICE", "res/Maps/Ice.tmx").setBattles(
                        new Battle("BATTLE1").enemies(
                                new Kai(),
                                new Orc(),
                                new Goblin()
                        ),
                        new Battle("BATTLE2").enemies(
                                new Kai(),
                                new Orc(),
                                new Goblin()
                        )
                )
        );
    }

    private void setWorlds(World... worldsObjects) {
        for (World world : worldsObjects) {
            worlds.put(world.getName(), world);
        }
    }

    public World getWorld(String worldName) {
        return worlds.get(worldName);
    }

    public Battle getBattle(String worldName, String battleName) {
        return worlds.get(worldName).getBattle(battleName);
    }
}
