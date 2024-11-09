package Game;

import Characters.*;
import Characters.Character;
import World.World;
import Item.Item;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Character> characters = new ArrayList<>(4);
    private int currentCharacterIndex = 0;
    private World world;
    private ArrayList<Item> items = new ArrayList<>(10);
    private Handler handler;

    public GameState(Handler handler) {
        this.handler = handler;
        //initialize available characters
        this.characters.add(new Kent());
        this.characters.add(new Cedi());
        this.characters.add(new Nathan());
//        this.characters.add(new Ambot());
    }

    public void tick() {
        if(handler.getViewManager().isInGame()) {
            for (Character character : characters) {
                character.regenHealth();
                character.regenMana();
            }
        }
    }

    public Character getPlayer() {
        return this.characters.get(currentCharacterIndex);
    }

    public void setPlayerByIndex(int index) {
        this.currentCharacterIndex = index;
    }

    public int getCurrentCharacterIndex() {
        return this.currentCharacterIndex;
    }

    public ArrayList<Character> getCharacters() {
        return this.characters;
    }
}
