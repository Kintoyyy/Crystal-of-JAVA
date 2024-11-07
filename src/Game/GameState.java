package Game;

import Characters.Cedi;
import Characters.Kent;
import Characters.Character; // Assuming Character is a superclass of Kent
import java.util.ArrayList;

public class GameState {
    private Character player;
    private ArrayList<Character> characters = new ArrayList<>(4);

    public GameState() {
        //initialize character
        this.player = new Kent();

        //initialize available characters
        this.characters.add(player);
        this.characters.add(new Cedi());

    }

    public Character getPlayer() {
        return this.player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public ArrayList<Character> getCharacters() {
        return this.characters;
    }
}
