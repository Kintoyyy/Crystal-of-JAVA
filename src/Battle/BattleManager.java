package Battle;

import Entities.Characters.Character;
import Entities.Characters.CharacterManager;
import Battle.Effects.EffectsManager;
import Entities.Enemies.Enemy;
import Entities.Enemies.EnemyManager;
import Game.Handler;
import Utils.Timer;
import Views.Game.DialogScene;
import Views.enums.Views;
import Views.ViewManager;
import Worlds.Battle;

import java.util.ArrayList;

public class BattleManager {
    private final Handler handler;
    private final ViewManager viewManager;

    private final CharacterManager characterManager;
    private final EnemyManager enemyManager;

    private final EffectsManager effectsManager;

    private final Timer timer = new Timer();
    private boolean isDataLoaded = false;
    private boolean isPlayersTurn = true;

    public BattleManager(Handler handler) {
        handler.setBattleManager(this);
        this.handler = handler;

        this.effectsManager = new EffectsManager();
        this.enemyManager = new EnemyManager(handler);
        this.characterManager = handler.getCharacterManager();

        this.viewManager = handler.getViewManager();
    }

    public void startBattle(Battle battle) {
        //set battle
        if (!battle.getPreBattleDialogs().isEmpty()) {
            System.out.println("Loading pre battle Dialogs");
        }
        if (battle.isComplete()) {
            System.out.println("Battle already completed: " + battle.getKey());
            abortBattle();
            return;
        }
        enemyManager.loadEnemies(battle.getEnemies()); // load enemies to enemies array
        this.isDataLoaded = false;// flag for ui components tobe removed

        viewManager.customView(new DialogScene(battle.getPreBattleDialogs()));

        viewManager.setView(Views.BATTLE); // set view to battle
    }

    public void enemiesTurn() {
        isPlayersTurn = false;
        System.out.println("Enemies turn");
        timer.reset();
        timer.start().setDelay(1).setAction(() -> {
            System.out.println("Enemy Attacked!");
            // decide to randomly select a character or attack the lowest
            getCurrentEnemy().attack(getCharacterManager().getPlayer());
            isPlayersTurn = true;
            characterManager.updateTurns();
        });
    }

    public void tick() {
        timer.update(); // Update the timer regardless
        // Check if all enemies are dead
        if (enemyManager.isEmpty()) {
            System.out.println("Battle ended");
            viewManager.setView(Views.GAME);
            return;
        }
    }

    // TODO: need to move this
    public Handler getHandler() {
        return handler;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemyManager.getEnemies();
    }

    // for ui
    public boolean isDataLoaded() {
        return isDataLoaded;
    }

    public void setDataLoaded(boolean isDataLoaded) {
        this.isDataLoaded = isDataLoaded;
    }


    public void abortBattle() {
        viewManager.setView(Views.GAME);
        enemyManager.clearEnemies();
    }

    public void setCurrentEnemy(int index) {
        System.out.println("Enemy Selected: " + index);
        enemyManager.setCurrentEnemy(index);
    }

    // get current selectedEnemy
    public Enemy getCurrentEnemy() {
        return enemyManager.getCurrentEnemy();
    }

    //for debugging only
    public void killAllEnemies() {
        enemyManager.killAllEnemies();
    }

    public boolean isPlayersTurn() {
        return isPlayersTurn;
    }

    public Double getTimer() {
        return timer.getTime();
    }
}
