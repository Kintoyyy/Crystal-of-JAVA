package Rendering;

import Entities.Characters.CharacterManager;
import Entities.Characters.Movement;
import Game.Handler;
import World.ParseWorld;

import java.awt.*;

public class Renderer {
    private RenderWorld world;
    private Handler handler;
    private CharacterManager characterManager;
    public final Movement movement;

    public Renderer(Handler handler) {
        this.handler = handler;

        this.movement = new Movement(handler);
        this.movement.setSpawn(300,300);

        this.characterManager = handler.getGameState().getCharacterManger();

        this.world = new RenderWorld(handler, new ParseWorld("res/worlds/world_1.tmx"), characterManager, movement);
    }

    public void render(Graphics g) {
//        movement.render(g);
        world.render(g);
    }

    public void tick() {
        movement.tick();
    }
}
