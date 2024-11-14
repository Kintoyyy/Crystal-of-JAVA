package Game;

import Entities.Characters.*;
import World.World;

public class GameState {
    private final CharacterManager characters = new CharacterManager();
    private World world;

    private Handler handler;


    public GameState(Handler handler) {
        this.handler = handler;
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
