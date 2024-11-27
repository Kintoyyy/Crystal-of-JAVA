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
                                "A shadow emerges from the trees...",
                                "It's a Skeleton Swordman!",
                                "Stand firm and prepare for battle!",
                                "Victory here will be your first step forward!"
                        ).dialogAfter(
                                "The Skeleton Swordman crumbles into dust!",
                                "You've proven your worth and can move ahead!"
                        ).rewards(
                                new Reward("Coin", 10, 10)
                        ),
                        new Battle("BATTLE2").enemies(
                                new SkeletonSwordman("SKELETON_SWORDMAN_2"),
                                new SkeletonBowman("SKELETON_BOWMAN_1")
                        ).dialogBefore(
                                "You hear the rustling of leaves...",
                                "A Skeleton Swordman and a Skeleton BowMan ambush you!",
                                "Keep your guard up and fight wisely!",
                                "The journey grows harder with each step."
                        ).dialogAfter(
                                "The foes have been vanquished!",
                                "The next challenge awaits on the horizon."
                        ),
                        new Battle("BATTLE3").enemies(
                                new SkeletonSwordman("SKELETON_SWORDMAN_3"),
                                new SkeletonSwordman("SKELETON_SWORDMAN_4"),
                                new SkeletonBowman("SKELETON_BOWMAN_2")
                        ).dialogBefore(
                                "Multiple shadows block your path...",
                                "Skeleton Swordmen and a BowMan unite against you!",
                                "Focus your attacks and claim victory!",
                                "You’ve come too far to fall now!"
                        ).dialogAfter(
                                "The skeletons shatter under your might!",
                                "A new path forward reveals itself."
                        ),
                        new Battle("BATTLE4").enemies(
                                new SkeletonBoss("SKELETON_BOSS")
                        ).dialogBefore(
                                "An ominous presence looms ahead...",
                                "The Skeleton Boss steps forward!",
                                "Prepare yourself for the ultimate trial!",
                                "You’ve faced lesser foes, now prove your strength!"
                        ).dialogAfter(
                                "The Skeleton Boss collapses with a deafening roar!",
                                "Glory is yours, along with riches and experience!"
                        )
                ).setBattleBackground("/Backgrounds/Forest.png"),
                new World("ICE", "res/Maps/Snow.tmx").addBattles(
                        new Battle("BATTLE1").enemies(
                                new Goblin("GOBLIN_1")
                        ).dialogBefore(
                                "The cold air bites at your skin...",
                                "A lone Goblin appears amidst the snow!",
                                "Fight valiantly to keep warm and alive!",
                                "Let the frost not consume your spirit!"
                        ).dialogAfter(
                                "The Goblin falls, its green skin blending with the white snow.",
                                "The frozen path ahead is now yours."
                        ),
                        new Battle("BATTLE2").enemies(
                                new IceGoblinArcher("ICE_GOBLIN_ARCHER"),
                                new Goblin("GOBLIN_2")
                        ).dialogBefore(
                                "From the icy cliffs, arrows rain down!",
                                "An Ice Goblin Archer and Goblin emerge!",
                                "Strike quickly, and silence the menace!",
                                "The chill of their weapons won’t hold you back!"
                        ).dialogAfter(
                                "Their frozen forms shatter into the snow!",
                                "Your will burns bright, guiding you forward."
                        ),
                        new Battle("BATTLE3").enemies(
                                new IceGoblinMaceman("ICE_GOBLIN_MACEMAN_3"),
                                new IceGoblinArcher("ICE_GOBLIN_ARCHER_3"),
                                new IceOrcChief("ICE_ORC_CHIEF_3")
                        ).dialogBefore(
                                "The frozen tundra erupts with fury!",
                                "An Ice Goblin Maceman, Archer, and Orc Chief challenge you!",
                                "Brace yourself for a deadly blizzard of attacks!",
                                "The fight will test the limits of your endurance!"
                        ).dialogAfter(
                                "Their icy might is no match for your fiery determination!",
                                "The path to victory glows faintly ahead."
                        )
                ).setBattleBackground("/Backgrounds/Ice.png"),
                new World("LAVA", "res/Maps/Lava.tmx").addBattles(
                        new Battle("BATTLE1").enemies(
                                new RedOrcArcher("RED_ORC_ARCHER_1")
                        ).dialogBefore(
                                "The heat is suffocating...",
                                "A Red Orc Archer eyes you from across the molten ground!",
                                "Only one will leave this fiery pit alive!",
                                "Aim true and endure the flames!"
                        ).dialogAfter(
                                "The Red Orc Archer’s fiery reign is extinguished!",
                                "You walk unscathed, the lava bubbling beneath you."
                        ),
                        new Battle("BATTLE2").enemies(
                                new RedOrcArcher("RED_ORC_ARCHER_2"),
                                new RedOrcGrunt("RED_ORC_GRUNT_2")
                        ).dialogBefore(
                                "The ground shakes beneath heavy footsteps...",
                                "A Red Orc Archer and Grunt block your way!",
                                "Their fiery rage seeks to consume you!",
                                "Face them with courage and strength!"
                        ).dialogAfter(
                                "The molten earth reclaims the defeated foes.",
                                "The volcano’s path opens up, beckoning you forward."
                        ),
                        new Battle("BATTLE3").enemies(
                                new RedOrcGrunt("RED_ORC_GRUNT_3"),
                                new RedOrcArcher("RED_ORC_ARCHER_3"),
                                new ZarokAxilleBoss("ZAROK_AXILLE_BOSS")
                        ).dialogBefore(
                                "The volcano roars in fury...",
                                "Zarok Axille, a fearsome boss, stands ready with his minions!",
                                "This will be the fight of your life!",
                                "Strike true and survive the inferno!"
                        ).dialogAfter(
                                "Zarok Axille falls, his fiery aura fading away!",
                                "The volcano’s heat cools as the path ahead opens."
                        )
                ).setBattleBackground("/Backgrounds/Lava.png"),
                new World("DUNGEON", "res/Maps/Dungeon.tmx").addBattles(
                        new Battle("BATTLE1").enemies(
                                new Kai()
                        ).dialogBefore(
                                "The dungeon walls echo with an eerie silence...",
                                "Kai, a shadowy figure, steps into the light!",
                                "This is your final challenge. Prepare yourself!",
                                "Victory here will mark the end of your journey!"
                        ).dialogAfter(
                                "Kai falls, his shadow dissipating into nothingness.",
                                "You have triumphed, and the game is complete!"
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
