package Worlds;

import Animations.Animation;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Entities.Enemies.Enemy;
import Entities.Entity;
import Game.Handler;
import Map.Parser;
import Map.Object.Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {
    private Handler handler = Handler.getInstance();
    private final Parser world;
    private final String worldKey;
    private final HashMap<String, Battle> battles = new HashMap<>();
    private final List<Entity> npcs = new ArrayList<>();
    private final List<Object> objects;

    public World(String worldKey, String mapPath) {
        this.worldKey = worldKey;
        this.world = new Parser(mapPath);

        this.objects = world.getObjectGroup().getObjects();
    }

    public World addBattles(Battle... battleObjects) {
        for (Battle battle : battleObjects) {
            battles.put(battle.getKey(), battle);
            // Populate NPC map
            for (Enemy entity : battle.getEnemies()) {
                for (Object object : world.getObjectGroup().getNpcObjects()) {
                    if (object.getName().equals(entity.getKey())) {
//                        entity.getLocation().setLocation(object.getPosition());
                        entity.setObject(object);
                        npcs.add(entity);
                    }
                }
            }
        }
        return this;
    }

    public Parser getWorld() {
        return world;
    }

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


    public void renderEntities(Graphics g, float xOffset, float yOffset) {
        for (Entity entity : npcs) {
            int x = (int) (entity.getObject().getPosition().x - xOffset);
            int y = (int) (entity.getObject().getPosition().y - yOffset);

            Animation animation = entity.getAnimation();
            int frameWidth = animation.getWidth() * 4;
            int frameHeight = animation.getHeight() * 4;

            // Adjust coordinates to center the image
            int renderX = x - frameWidth / 2;
            int renderY = y - frameHeight / 2;

            int playerX = (int) handler.getMovement().getPlayerScreenLocation().x;
            int playerY = (int) handler.getMovement().getPlayerScreenLocation().y;

            // Draw a line to visualize the connection between player and enemy
            g.drawLine(playerX, playerY, x, y);

            // Determine direction to face
            int dx = playerX - x;
            int dy = playerY - y;

            DIRECTION direction;

            if (Math.abs(dx) > Math.abs(dy)) {
                // Horizontal movement is dominant
                if (dx > 0) {
                    direction = DIRECTION.RIGHT;
                } else {
                    direction = DIRECTION.LEFT;
                }
            } else {
                // Vertical movement is dominant
                if (dy > 0) {
                    direction = DIRECTION.DOWN;
                } else {
                    direction = DIRECTION.UP;
                }
            }


            if (entity.getHealth().isDead()) {
                // Render the entity with the appropriate animation frame
//                g.drawImage(animation.getFrame(TYPE.DEAD), renderX, renderY, frameWidth, frameHeight, null);
            } else {
                g.drawImage(animation.getFrame(TYPE.IDLE, direction), renderX, renderY, frameWidth, frameHeight, null);
            }

            // Render the entity with the appropriate animation frame

        }
    }
}
