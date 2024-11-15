package Rendering;

import Entities.Characters.CharacterManager;
import Entities.Characters.Movement.Movement;
import Game.Handler;
import World.ParseWorld;

import java.awt.*;

public class GameRenderer {
    private final RenderWorld world;
    private final CharacterManager characterManager;
    private Camera camera;

    public GameRenderer(Handler handler) {

        ParseWorld world = new ParseWorld("res/worlds/world_1.tmx");

        camera = new Camera(handler, 0, 0);

        this.characterManager = handler.getGameState().getCharacterManger();

        this.world = new RenderWorld(handler, world, characterManager);
    }

    public Camera getCamera() {
        return camera;
    }

    public void render(Graphics g) {
        world.render(g);
    }

    public void tick() {
        world.tick();
    }
}
