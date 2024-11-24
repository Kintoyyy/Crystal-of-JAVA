package Animations;

import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Entities.Enemies.Enemy;
import Map.Movement.Camera;
import Map.Object.Object;
import Worlds.Worlds;
import Worlds.World;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationManager {
    private final HashMap<String, Enemy> npcAnimations = new HashMap<>();

    public AnimationManager(Worlds worlds) {
        // loop through all worlds, battles, and enemies to populate npcAnimations
        worlds.getWorlds().values().forEach(world ->
                world.getBattles().values().forEach(battle ->
                        battle.getEnemies().forEach(entity ->
                                npcAnimations.put(world.getName() + "_" + entity.getName().toUpperCase(), entity)
                        )
                )
        );
    }

    public void tick() {
        for(Enemy animation : npcAnimations.values()) {
            animation.getAnimation().tick();
        }
    }

    public void render(Graphics g, int x, int y, String key) {
        Animation animation = npcAnimations.get(key).getAnimation();
        if (animation != null) {
            g.drawImage(animation.getFrame(TYPE.IDLE), x, y, null);
        }
    }

    public void render(Graphics g, List<Object> npcObjects, World world, Camera camera) {
        for (Object object : npcObjects) {
            Point pos = object.getPosition();
            Enemy animation = npcAnimations.get(world.getName() + "_" + object.getKey());
            System.out.println(object.getProperties().getFirst().value());
            if (animation != null) {

                g.drawImage(animation.getAnimation().getFrame(TYPE.IDLE, DIRECTION.valueOf(object.getProperties().getFirst().value())), (int) (pos.x - camera.getXOffset()), (int) (pos.y - camera.getYOffset()), 32 * 4, 32 * 4, null);
//                animation.render2(g, (int) (pos.x - camera.getXOffset()), (int) (pos.y - camera.getYOffset()));
            }
        }
    }
}
