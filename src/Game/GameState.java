package Game;

import Entities.Characters.*;
import World.World;

public class GameState {
    private final CharacterManager characters;
    private World world;

    private Handler handler;


    public GameState(Handler handler) {
        this.handler = handler;
        this.characters = new CharacterManager();
    }

    public void tick() {
        if (handler.getViewManager().isInGame()) {
            characters.tick();
        }
    }

    public CharacterManager getCharacterManger() {
        return this.characters;
    }
}
