package Game;

import Characters.Character;
import Characters.CharacterManager;
import Enemies.Enemy;
import Enemies.EnemyManager;
import Utils.Timer;
import enums.ViewEnums;

import java.util.ArrayList;

public class BattleManager {
    private final CharacterManager characterManager;
    private EnemyManager enemyManager;

    private boolean isBattleActive = true;
    private TurnState turnState = TurnState.PLAYER;

    private Handler handler;

    private int seconds = 0;
    private Timer timer = new Timer();

    public BattleManager(Handler handler) {
        this.handler = handler;
        characterManager = handler.getGameState().getCharacterManger();

        timer = new Timer();


    }

    public void newBattle(EnemyManager enemyManager) {
        this.enemyManager = enemyManager;
        handler.getViewManager().setView(ViewEnums.BATTLE);
        isBattleActive = true;
    }

    public void updateTurnState() {
        if (turnState == TurnState.PLAYER) {
            turnState = TurnState.ENEMY;
        } else {
            turnState = TurnState.PLAYER;
        }
        System.out.println("Turn State: " + turnState);
        enemyManager.setAutoSelectEnemy(true);

        if(turnState == TurnState.ENEMY) {
            timer.start().setDelay(1).setAction(() -> {
                enemyManager.getCurrentEnemy().attack(characterManager.getPlayer());
                System.out.println("Timer 1 Action");
                turnState = TurnState.PLAYER;
                timer.reset();
            });
        }
        characterManager.updateTurns();
    }
    public TurnState getTurnState() {
        return turnState;
    }

    public void tick() {

        timer.update();
//        if (turnState == TurnState.PLAYER) {
//
//        } else {
//            timer.update();
//        }
    }

    public Character getPlayer() {
        return characterManager.getPlayer();
    }

    public ArrayList<Character> getCharacters() {
        return characterManager.getCharacters();
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

    public boolean isPlayersTurn() {
        return turnState == TurnState.PLAYER;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public Enemy getCurrentEnemy() {
        return enemyManager.getCurrentEnemy();
    }
}

