package Rendering;

import Entities.Characters.CharacterManager;
import Game.Handler;
import World.ParseWorld;

import java.awt.*;

public class Renderer {
    private RenderWorld world;
    private ParseWorld worldParser;
    private Handler handler;
    private CharacterManager characterManager;

    public Renderer(Handler handler) {
        this.handler = handler;

        this.characterManager = handler.getGameState().getCharacterManger();

        this.worldParser = new ParseWorld("res/worlds/world_1.tmx");

        this.world = new RenderWorld(handler, worldParser, characterManager);
    }

    public void render(Graphics g) {
        world.render(g);
    }

    public void tick() {

    }
}
