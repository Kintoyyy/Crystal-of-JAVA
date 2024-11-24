package Worlds;

import Entities.Enemies.Goblin;
import Entities.Enemies.Kai;
import Entities.Enemies.Orc;
import Entities.Enemies.SkeletonWarrior;

import java.util.HashMap;

public class Worlds {
    private final HashMap<String, World> worlds = new HashMap<>();

    public Worlds() {
        this.setWorlds(
                new World("FOREST", "res/Maps/Forest.tmx").addBattles(
                        new Battle("BATTLE1").enemies(
                                new SkeletonWarrior(),
                                new Kai()
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
                                new Reward("Coin", 10, 10)
                        ),
                        new Battle("BATTLE2").enemies(
                                new SkeletonWarrior(),
                                new SkeletonWarrior()
                        ),
                        new Battle("BATTLE3").enemies(
                                new SkeletonWarrior(),
                                new SkeletonWarrior(),
                                new SkeletonWarrior()
                        )
                ),
                new World("ICE", "res/Maps/Snow.tmx").addBattles(
                        new Battle("BATTLE1").enemies(
                                new Goblin()
                        ),
                        new Battle("BATTLE2").enemies(
                                new Orc(),
                                new Goblin()
                        ),
                        new Battle("BATTLE3").enemies(
                                new Orc(),
                                new Goblin(),
                                new Orc()
                        )
                ),
                new World("LAVA", "res/Maps/Lava.tmx").addBattles(
                        new Battle("BATTLE1").enemies(
                                new Orc()
                        ),
                        new Battle("BATTLE2").enemies(
                                new Orc(),
                                new Orc()
                        ),
                        new Battle("BATTLE3").enemies(
                                new Orc(),
                                new Orc(),
                                new Orc()
                        )
                ),
                new World("DUNGEON", "res/Maps/Dungeon.tmx").addBattles(
                        new Battle("BATTLE1").enemies(
                                new Kai()
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
        if (!worlds.containsKey(worldName)) {
            throw new IllegalArgumentException("World " + worldName + " does not exist.");
        }
        return worlds.get(worldName);
    }

    public Battle getBattle(String worldName, String battleName) {
        World world = getWorld(worldName);
        Battle battle = world.getBattle(battleName);
        if (battle == null) {
            throw new IllegalArgumentException("Battle " + battleName + " does not exist in " + worldName);
        }
        return battle;
    }

    public HashMap<String, World> getWorlds() {
        return worlds;
    }
}
