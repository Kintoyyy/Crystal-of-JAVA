package Rendering;

import Entities.Characters.CharacterManager;
import Entities.Characters.Movement.Movement;
import Game.Handler;
import World.ParseWorld;

import java.awt.*;

public class GameRenderer {
    private final RenderWorld renderWorld;
    private final CharacterManager characterManager;

    private Movement movement;
    protected Handler handler;

    public GameRenderer(Handler handler) {
        this.handler = handler;
        ParseWorld world = new ParseWorld("res/worlds/world_1.tmx");

        this.characterManager = handler.getGameState().getCharacterManger();
        System.out.println("World " + world);
        movement = new Movement(handler, world, characterManager);

        this.renderWorld = new RenderWorld(world, movement);
    }

    public void render(Graphics g) {
        renderWorld.render(g);
    }

    public void tick() {
        renderWorld.tick();
    }
}
