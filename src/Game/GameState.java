package Game;

import Characters.*;
import Characters.Character;
import Enemies.Enemy;
import Utils.Timer;
import World.World;
import Item.Item;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Character> characters = new ArrayList<>(4);
    private int currentCharacterIndex = 0;
    private World world;
    private ArrayList<Item> items = new ArrayList<>(10);
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Handler handler;
    private int seconds = 0;
    private Timer timer = new Timer();

    public GameState(Handler handler) {
        this.handler = handler;


        //initialize available characters
        this.characters.add(new Kent());
        this.characters.add(new Cedi());
        this.characters.add(new Nathan());
        this.characters.add(new Zeith());

        timer = new Timer().setDelay(60).setAction(() -> System.out.println("Timer 1 Action"));

        timer.start();
    }

    public void tick() {
        // Call the timer update method to handle the delay

        timer.update();
        System.out.println("Timer: " + timer.getElapsedTime());

        // Perform actions that should happen every tick
        if (handler.getViewManager().isInGame()) {
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

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
}
