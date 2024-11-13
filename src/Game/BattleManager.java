package Game;

import Characters.Character;
import Enemies.Enemy;
import enums.ViewEnums;

import java.awt.*;
import java.util.ArrayList;

public class BattleManager {
    private boolean isBattleActive = false;
    private TurnState turnState = TurnState.PLAYER;
    private ArrayList<Character> characters;
    private int currentCharacterIndex = 0;
    private ArrayList<Enemy> enemies;
    private int currentEnemyIndex = 0;
    private Handler handler;

    public BattleManager(Handler handler, ArrayList<Character> characters) {
        this.handler = handler;
        this.characters = characters;
        enemies = null;
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

    public void tick() {
        if (turnState == TurnState.PLAYER) {
            for (Character character : characters) {

            }
        } else {

        }
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void attackCurrentEnemy() {
        // Get the current player
    }

    public void attackLowestEnemy() {
        // Get the current player
    }

    public void attackAllEnemies() {
        // Get the current player
    }


    public boolean isBattleActive() {
        return isBattleActive;
    }

    public int getCurrentCharacterIndex() {
        return currentCharacterIndex;
    }

    public void setCurrentCharacterIndex(int currentCharacterIndex) {
        this.currentCharacterIndex = currentCharacterIndex;
    }

    public int getCurrentEnemyIndex() {
        return currentEnemyIndex;
    }

    public void setCurrentEnemyIndex(int currentEnemyIndex) {
        this.currentEnemyIndex = currentEnemyIndex;
    }
}

