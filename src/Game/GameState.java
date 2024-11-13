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
    private ArrayList<Character> characters = new ArrayList<>(4);
    private int currentCharacterIndex = 0;

    private ArrayList<Enemy> enemies = new ArrayList<>();

    private World world;
    private ArrayList<Item> items = new ArrayList<>(10);

    private Handler handler;
    private int seconds = 0;
    private Timer timer = new Timer();

    private boolean isBattleActive = false;
    private TurnState turnState = TurnState.PLAYER;

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

        // Perform actions that should happen every tick
        if (handler.getViewManager().isInGame()) {
            for (Character character : characters) {
                character.regenHealth();
                character.regenMana();
            }
        }
    }

    public void newBattle( ArrayList<Enemy> enemies) {
        handler.getViewManager().setView(ViewEnums.BATTLE);
        this.enemies = enemies;
        isBattleActive = true;
    }

    public void updateTurnState() {
        if (turnState == TurnState.PLAYER) {
            turnState = TurnState.ENEMY;
        } else {
            turnState = TurnState.PLAYER;
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
