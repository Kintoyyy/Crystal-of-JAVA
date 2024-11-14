package Rendering;

import Entities.Characters.CharacterManager;
import Entities.Characters.Movement.Movement;
import Game.Handler;
import World.ParseWorld;

import java.awt.*;

public class GameRenderer {
    private final RenderWorld world;
    private final CharacterManager characterManager;

    public GameRenderer(Handler handler) {



        this.characterManager = handler.getGameState().getCharacterManger();

        this.world = new RenderWorld(handler, new ParseWorld("res/worlds/world_1.tmx"), characterManager);
    }

    public void render(Graphics g) {
        world.render(g);
    }

    public void tick() {
        world.tick();
    }
}
