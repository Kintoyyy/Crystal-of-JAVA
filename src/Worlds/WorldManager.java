package Worlds;

import Entities.Characters.CharacterManager;
import Game.Handler;
import Map.Movement.Movement;
import Map.Render;

import java.awt.*;

public class WorldManager {
    private final Worlds worlds;
    private final Render render;
    private final Movement movement;
    private String currentWorld = "FOREST";

    public WorldManager(Handler handler) {
        handler.setWorldManager(this);
        this.worlds = new Worlds();

        CharacterManager characterManager = handler.getGameState().getCharacterManger();

        movement = new Movement(handler, this, characterManager);

        this.render = new Render(movement);
    }

    public World getCurrentWorld() {
        return worlds.getWorld(currentWorld);
    }

    public Battle getBattle(String battleName) {
        return worlds.getBattle(currentWorld, battleName);
    }

    public void setCurrentWorld(String world) {
        this.currentWorld = world;
        movement.setLocation(getCurrentWorld().getSpawnPoint());
        render.loadWorld();
    }

    public void tick() {
        render.tick();
    }

    public void render(Graphics g) {
        render.render(g);
    }
}
