package Worlds;

import Animations.Animation;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Entities.Enemies.Enemy;
import Entities.Entity;
import Game.Handler;
import Map.Movement.Movement;
import Map.Parser;
import Map.Object.Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;

public class World {
    private Handler handler = Handler.getInstance();
    private String battleBackground;
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

    public List<Entity> getNpcs() {
        return npcs;
    }

    public void renderEntities(Graphics g, float xOffset, float yOffset, Movement playerMovement) {
        // Create a temporary class to hold renderable entities
        class RenderableEntity {
            Entity entity;
            float renderY;
            Point renderPosition;

            RenderableEntity(Entity entity, float renderY, Point renderPosition) {
                this.entity = entity;
                this.renderY = renderY;
                this.renderPosition = renderPosition;
            }
        }

        // Create a list of all renderable entities
        List<RenderableEntity> renderableEntities = new ArrayList<>();

        // Add NPCs to the list
        for (Entity entity : npcs) {
            Point pos = new Point(
                (int)(entity.getObject().getPosition().x - xOffset),
                (int)(entity.getObject().getPosition().y - yOffset)
            );
            entity.setLocation(pos);
            renderableEntities.add(new RenderableEntity(entity, entity.getRenderY(), pos));
        }

        // Add player to the list if it exists
        if (playerMovement != null) {
            renderableEntities.add(new RenderableEntity(null, playerMovement.getRenderY(), 
                playerMovement.getPlayerScreenLocation()));
        }

        // Sort entities by Y position
        renderableEntities.sort(Comparator.comparingDouble(e -> e.renderY));

        // Render entities in sorted order
        for (RenderableEntity renderableEntity : renderableEntities) {
            if (renderableEntity.entity == null) {
                playerMovement.render(g);
            } else {
                renderEntity(g, renderableEntity.entity, renderableEntity.renderPosition);
                if (renderableEntity.entity.dialog != null) {
                    renderableEntity.entity.dialog.render(g);
                }
            }
        }
    }

    private void renderEntity(Graphics g, Entity entity, Point renderPos) {
        // Update render method to use the passed position
        int x = renderPos.x;
        int y = renderPos.y;

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
            g.drawImage(animation.getFrame(TYPE.DEAD), renderX, renderY, frameWidth, frameHeight, null);
        } else {
            g.drawImage(animation.getFrame(TYPE.IDLE, direction), renderX, renderY, frameWidth, frameHeight, null);
        }

        // Render the entity with the appropriate animation frame

    }

    public World setBattleBackground(String s) {
        this.battleBackground = s;
        return this;
    }

    public String getBattleBackground() {
        if(battleBackground == null) {
            battleBackground = "/Backgrounds/Forest.png";
        }
        return battleBackground;
    }
}
