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
                        ).dialogBefore(
                                "You have encountered a Goblin!",
                                "Prepare for battle!",
                                "Defeat the Goblin to continue!",
                                "Good luck!",
                                "Goblin: I will defeat you!",
                                "Goblin: You will never defeat me!"
                        ).dialogAfter(
                                "You have defeated the Goblin!",
                                "You have earned 10 gold and 10 exp!",
                                "You have unlocked the next area!"
                        ).rewards(
                                new Reward("Goblin", 10, 10)
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
                ).setNpc(
                        new Kai()
                ),
                new World("ICE", "res/Maps/Snow.tmx").setBattles(
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
                ),
                new World("LAVA", "res/Maps/Lava.tmx").setBattles(
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
                ),
                new World("DUNGEON", "res/Maps/Dungeon.tmx").setBattles(
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
