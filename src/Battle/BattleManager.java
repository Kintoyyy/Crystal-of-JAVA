package Battle;

import Entities.Characters.CharacterManager;
import Entities.Enemies.Enemy;
import Game.Handler;
import Utils.Timer;
import Views.enums.Views;
import Views.ViewManager;
import Worlds.Battle;
import Worlds.Enums.Turn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BattleManager {
    private final Handler handler;
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ViewManager viewManager;
    private int currentEnemyIndex = 0;
    private final Queue<Turn> turnqueue = new LinkedList<>();

    private final Timer timer = new Timer();

    private boolean isDataLoaded = false;

    private Battle battle;

    public BattleManager(Handler handler) {
        handler.setBattleManager(this);
        this.viewManager = handler.getViewManager();

        this.handler = handler;

        turnqueue.add(Turn.PLAYER);
        turnqueue.add(Turn.ENEMY);
    }

    public void startBattle(Battle battle) {
        this.battle = battle;

        if (!battle.getPreBattleDialogs().isEmpty()) {
            System.out.println("Loading pre battle Dialogs");
        }

        if (battle.isComplete()) {
            System.out.println("Battle already completed: " + battle.getKey());
            abortBattle();
            return;
        }

        loadEnemies(battle.getEnemies());

        this.isDataLoaded = false;
        currentEnemyIndex = 0;

        viewManager.setView(Views.BATTLE);
    }

    public void endBattle() {
        if (!battle.getPreBattleDialogs().isEmpty()) {
            System.out.println("Loading post battle Dialogs");
        }

    }

    public void updateTurnState() {
        Turn turn = turnqueue.poll();
        System.out.println("Turn: " + turn);
        if (turn == Turn.PLAYER) {
            updateTurnState();
            turnqueue.add(Turn.ENEMY);
        } else {
            turnqueue.add(Turn.PLAYER);
            timer.reset();
            timer.start().setDelay(2).setAction(() -> {
                getCurrentEnemy().attack(getCharacterManager().getPlayer());
                updateTurnState();
            });
        }
    }

    public Turn getCurrentTurn() {
        return turnqueue.peek();
    }

    public void skipTurn() {
        Turn turn = turnqueue.remove();
        turnqueue.add(turn);
        turnqueue.add(turn);
    }

    public void tick() {
        // Check if all enemies are dead
        boolean allEnemiesDead = true;
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                allEnemiesDead = false;
                break; // No need to continue if one enemy is alive
            }
        }

//        System.out.println(timer.getTime());

        if (allEnemiesDead && !timer.isActive()) {
//            System.out.println("All enemies dead");
//            timer.start(); // Start the timer if all enemies are dead
        }

        // If there are no enemies left in the list
        if (enemies.isEmpty()) {
            System.out.println("Battle ended");
            viewManager.setView(Views.GAME);
            return;
        }

        timer.update(); // Update the timer regardless
    }


    // TODO: need to move this
    public Handler getHandler() {
        return handler;
    }

    public CharacterManager getCharacterManager() {
        return handler.getCharacterManager();
    }

    public void abortBattle() {
        enemies.clear();
//        System.out.println("Aborting battle: " + enemies);
        viewManager.setView(Views.GAME);
        this.currentEnemyIndex = 0;
    }

    public boolean isDataLoaded() {
        return isDataLoaded;
    }

    public void setDataLoaded(boolean isDataLoaded) {
        this.isDataLoaded = isDataLoaded;
    }

    public void loadEnemies(ArrayList<Enemy> enemies) {
//        System.out.println("Loading enemies: " + enemies);
        this.currentEnemyIndex = 0;
        this.enemies.clear();
        this.enemies.addAll(enemies);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setCurrentEnemy(int index) {
//        System.out.println("Setting current enemy: " + index);
        this.currentEnemyIndex = index;
    }

    public Enemy getCurrentEnemy() {
        if (enemies.isEmpty()) {
            return null;
        }
        return enemies.get(currentEnemyIndex);
    }

    public void killAllEnemies() {
        for (Enemy enemy : enemies) {
            enemy.getHealth().setHealth(0);
        }
    }
}
