package Worlds;

import Entities.Enemies.Forest.SkeletonBoss;
import Entities.Enemies.Forest.SkeletonBowman;
import Entities.Enemies.Ice.Goblin;
import Entities.Enemies.Dungeon.Kai;
import Entities.Enemies.Ice.IceGoblinArcher;
import Entities.Enemies.Ice.IceGoblinMaceman;
import Entities.Enemies.Ice.IceOrcChief;
import Entities.Enemies.Lava.Orc;
import Entities.Enemies.Forest.SkeletonSwordman;
import Entities.Enemies.Lava.RedOrcArcher;
import Entities.Enemies.Lava.RedOrcGrunt;
import Entities.Enemies.Lava.ZarokAxilleBoss;

import java.util.HashMap;

public class Worlds {
    private final HashMap<String, World> worlds = new HashMap<>();

    public Worlds() {
        this.setWorlds(
                new World("FOREST", "res/Maps/Forest.tmx").addBattles(
                        new Battle("BATTLE1").enemies(
                                new SkeletonSwordman("SKELETON_SWORDMAN_1")
                        ).dialogBefore(
                                "You have encountered a Skeleton Swordman!",
                                "Prepare for battle!",
                                "Defeat the Goblin to continue!",
                                "Good luck!"
                        ).dialogAfter(
                                "You have defeated the Skeleton Swordman!",
                                "You have unlocked the next area!"
                        ).rewards(
                                new Reward("Coin", 10, 10)
                        ),
                        new Battle("BATTLE2").enemies(
                                new SkeletonSwordman("SKELETON_SWORDMAN_2"),
                                new SkeletonBowman("SKELETON_BOWMAN_1")
                        ).dialogBefore(
                                "You have encountered a Skeleton Swordman and Skeleton BowMan!",
                                "Prepare for battle!",
                                "Defeat the Goblin to continue!",
                                "Good luck!"
                        ).dialogAfter(
                                "You have defeated the Goblin!",
                                "You have unlocked the next area!"
                        ),
                        new Battle("BATTLE3").enemies(
                                new SkeletonSwordman("SKELETON_SWORDMAN_3"),
                                new SkeletonSwordman("SKELETON_SWORDMAN_4"),
                                new SkeletonBowman("SKELETON_BOWMAN_2")
                        ).dialogBefore(
                                "You have encountered a Skeleton Swordman and Skeleton BowMan!",
                                "Prepare for battle!",
                                "Good luck!"
                        ).dialogAfter(
                                "You have defeated the Enemies!",
                                "You have unlocked the next area!"
                        ),
                        new Battle("BATTLE4").enemies(
                                new SkeletonBoss("SKELETON_BOSS")
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
                        )
                ).setBattleBackground("/Backgrounds/Forest.png"),
                new World("ICE", "res/Maps/Snow.tmx").addBattles(
                        new Battle("BATTLE1").enemies(
                                new Goblin("GOBLIN_1")
                        ).dialogBefore(
                                "You have encountered a Goblin!",
                                "Prepare for battle!",
                                "Defeat the Goblin to continue!",
                                "Good luck!"
                        ).dialogAfter(
                                "You have defeated the Goblin!",
                                "You have unlocked the next area!"
                        ),
                        new Battle("BATTLE2").enemies(
                                new IceGoblinArcher("ICE_GOBLIN_ARCHER"),
                                new Goblin("GOBLIN_2")
                        ).
                        dialogBefore(
                                "You have encountered a Goblin and Ice Goblin Archer!",
                                "Prepare for battle!",
                                "Defeat the Goblin to continue!",
                                "Good luck!"
                        ).dialogAfter(
                                "You have defeated the Goblin!",
                                "You have unlocked the next area!"
                        ),
                        new Battle("BATTLE3").enemies(
                                new IceGoblinMaceman("ICE_GOBLIN_MACEMAN_3"),
                                new IceGoblinArcher("ICE_GOBLIN_ARCHER_3"),
                                new IceOrcChief("ICE_ORC_CHIEF_3")
                        ).dialogBefore(
                                "You have encountered a Goblin, Ice Goblin Archer and Ice Goblin Maceman!",
                                "Prepare for battle!",
                                "Defeat the Goblin to continue!",
                                "Good luck!"
                        ).dialogAfter(
                                "You have defeated the Goblin!",
                                "You have unlocked the next area!"
                        )
                ).setBattleBackground("/Backgrounds/Ice.png"),
                new World("LAVA", "res/Maps/Lava.tmx").addBattles(
                        new Battle("BATTLE1").enemies(
                                new RedOrcArcher("RED_ORC_ARCHER_1")
                        ),
                        new Battle("BATTLE2").enemies(
                                new RedOrcArcher("RED_ORC_ARCHER_2"),
                                new RedOrcGrunt("RED_ORC_GRUNT_2")
                        ),
                        new Battle("BATTLE3").enemies(
                                new RedOrcGrunt("RED_ORC_GRUNT_3"),
                                new RedOrcArcher("RED_ORC_ARCHER_3"),
                                new ZarokAxilleBoss("ZAROK_AXILLE_BOSS")
                        )
                ).setBattleBackground("/Backgrounds/Lava.png"),
                new World("DUNGEON", "res/Maps/Dungeon.tmx").addBattles(
                        new Battle("BATTLE1").enemies(
                                new Kai()
                        ).dialogBefore(
                                "You have encountered a Kai!",
                                "Prepare for battle!",
                                "Defeat the Kai to continue!",
                                "Good luck!"
                        ).dialogAfter(
                                "You have defeated the Kai!",
                                "You have finished the game!"
                        )
                ).setBattleBackground("/Backgrounds/Forest.png")
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
