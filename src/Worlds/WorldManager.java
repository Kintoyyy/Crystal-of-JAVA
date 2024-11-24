package Worlds;

import Animations.AnimationManager;
import Game.GameState;
import Game.Handler;
import Map.Map;
import Map.Movement.Camera;
import Map.Movement.Collision;
import Map.Movement.Movement;
import Map.Render;

import java.awt.*;

public class WorldManager {
    private final Worlds worlds = new Worlds();
    private final Render render;
    private String currentWorld = "FOREST";
    private final AnimationManager animationManager;

    public WorldManager(GameState gameState, Handler handler) {
        gameState.getHandler().setWorldManager(this);

        Camera camera = new Camera(handler, this, 0, 0);

        Collision collision = new Collision(handler);

        Movement movement = new Movement(handler, gameState.getCharacterManger(), collision, camera);

        this.animationManager = new AnimationManager(worlds);

        this.render = new Render(handler, movement, this);
    }

    public Map getMap() {
        return worlds.getWorld(currentWorld).getWorld();
    }

    public World getWorld() {
        return worlds.getWorld(currentWorld);
    }

    public Battle getBattle(String battleName) {

        return worlds.getBattle(currentWorld, battleName);
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    public void changeWorld(String world) {
        this.currentWorld = world;
        render.loadWorld(worlds.getWorld(currentWorld).getWorld());
    }

    public void tick() {
        render.tick();
    }

    public void render(Graphics g) {
        render.render(g);
    }
}
