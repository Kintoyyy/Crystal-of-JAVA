package Game;

import Characters.*;
import Characters.Character;
import Enemies.Enemy;
import Utils.Timer;
import World.World;
import Item.Item;
import enums.ViewEnums;

import java.util.ArrayList;

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
